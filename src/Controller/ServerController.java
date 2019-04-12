package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

import Controller.NetworkForfeitObserver.NetworkForfeitSubject;
import Controller.NetworkInputObserver.NetworkInputSubject;
import Controller.NetworkTurnObserver.NetworkTurnSubject;
import com.google.common.base.*;


public class ServerController implements Runnable{
    private final String host = NetworkConfigurator.getProperty("SERVER_IP");
    private final int portNumber = Integer.parseInt(NetworkConfigurator.getProperty("SERVER_PORT"));
    private PrintWriter out;
    private BufferedReader br;
    private Socket socket;
    private boolean running = true;
    private String[] splittedMessage;
    Map<String, String> splitMap;
    private ClientCommands clientcom = new ClientCommands();
    private String playerToMove;
    private String GameType;

    private String subGameType;

    private static ServerController persistentServerController;
    private TournamentChecker checktour = new TournamentChecker();

    public ServerController(String subType) {
        if (subType != null) {
            this.subGameType = subType;
        }
    }

//Open a socket connection to the server if possible
    private void connectToServer(){
        System.out.println("ServerController constructor called");
       try{
           running = true;
           socket = new Socket(host, portNumber);
           br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           out = new PrintWriter(socket.getOutputStream(), true);

           System.out.println("server says:" + br.readLine());

       } catch (IOException e){
            e.printStackTrace();
       }
    }

//        TODO: Make the client do login and sub instead of hardcode, hardcode only for testing
    public void run()  {
        connectToServer();
        clientcom.loginToServer(NetworkConfigurator.getProperty("PLAYER_NAME"), out);
        if (this.subGameType != null) {
            clientcom.subTogame(this.subGameType, out);
        }
        while(running){
            handleMessage();
        }
    }

//    server message handling
    private void handleMessage(){
        try {
            String servmessage = br.readLine();

            System.out.println(servmessage);

            if (servmessage.contains("{")) {
                genMap(servmessage);
            }

            splittedMessage = servmessage.split("\\s+");

            switch (splittedMessage[0]){
                case "OK":
                    System.out.println("server said OK");
                    break;
                case "ERR":
                    System.out.println("The server gave an error");
                    break;
                case "SVR":
                    System.out.println("Server message");
                    handleSrv(splittedMessage[1]);
                    break;
            }
        }
        catch (SocketException se) {
            System.err.println("connections was closed");
        }
        catch (IOException IE){
            IE.printStackTrace();
        }
    }

//    Generate the maps given in the servermessage
//    Uses the guava library to split the map into keys and values
    private void genMap(String serverMessage){

        String tempString = serverMessage.substring(serverMessage.indexOf("{"));
        tempString = tempString.trim();
//        Trims the following: { } "
        tempString = tempString.replaceAll("[{}\"]", "");

        tempString = tempString.replace(", ", ",");
        tempString = tempString.replace(": ", ":");

        splitMap = Splitter.on(",").withKeyValueSeparator(":").split(tempString);
    }


//    Handles all server(SVR) related messages
    private void handleSrv(String typeOfMessage){
            switch (typeOfMessage){
                case "GAME":
                    gamehandler(splittedMessage[2]);
                    break;
                case "GAMELIST":
                    break;
                case "PLAYERLIST":
                    break;
            }
    }
// handles all messages containing the word GAME
    private void gamehandler(String gameOption){
        switch(gameOption){
            case "MATCH":
                this.playerToMove = splitMap.get("PLAYERTOMOVE");
                this.GameType = splitMap.get("GAMETYPE");

                checktour.CheckTournament(GameType);
                break;
            case "YOURTURN":
                NetworkTurnSubject.giveTurn();
                break;
            case "WIN":
                System.out.println("You've won!");
                resetGameData();
                NetworkForfeitSubject.forfeit();
                break;
            case "LOSS":
                System.out.println("You've lost :(");
                resetGameData();
                NetworkForfeitSubject.forfeit();
                break;
            case "DRAW":
                System.out.println("It's a draw!");
                resetGameData();
                NetworkForfeitSubject.forfeit();
                break;
            case "MOVE":
                try {
                    NetworkInputSubject.notify(Byte.parseByte(splitMap.get("MOVE")));
                } catch (NumberFormatException nfe) {
                    System.err.println("NumberFormatException");
                }
                break;
        }
    }


    public String getGame(){
       return this.GameType;
    }


    public void sendMove(byte[] move, byte boardSize) {
        clientcom.move(Integer.toString((move[0] * boardSize) + move[1]), out);
    }


    public String getPlayerToMove() {
        return this.playerToMove;
    }

    public void resetGameData() {
        playerToMove = null;
    }


    public static void createPersistentServerController() {
        persistentServerController = new ServerController(null);
        Thread t = new Thread(persistentServerController);
        t.start();
    }


    public static ServerController getPersistentServerController() {
        if (persistentServerController == null) {
            createPersistentServerController();
        }
        return persistentServerController;
    }


    public void disconnect(){
        try{
            socket.close();
            clientcom.logout(out);
            running = false;
        }
        catch (IOException ioe){
            System.out.println("There is no server to disconnect from");
        }
    }
}
