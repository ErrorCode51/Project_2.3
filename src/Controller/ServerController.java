package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;

import Controller.NetworkInputObserver.NetworkInputSubject;
import com.google.common.base.*;

//todo: remove once random string generator is remove from yourturn case
import java.util.Random;

// TODO: 03/04/2019 clean this mess up and make handlers for the switch cases

public class ServerController implements Runnable{
    private final String host = "145.37.149.79";
    private final int portNumber = 7789;
    private PrintWriter out;
    private BufferedReader br;
    private Socket socket;
    private boolean running = true;
    private String[] splittedMessage;
    Map<String, String> splitMap;
    private ClientCommands clientcom = new ClientCommands();
    private String playerToMove;

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
        clientcom.loginToServer("appel",out);
        clientcom.subTogame("Tic-tac-toe",out);
        while(running){
            handleMessage();
        }
    }

//    server message handling
// TODO: 03/04/2019 handle events with AI
    private void handleMessage(){
        try {
            String servmessage = br.readLine();

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
        }catch (IOException IE){
            IE.printStackTrace();
        }
    }

//    Generate the maps given in the servermessage
//    Uses the guava library to split the map into keys and values
    private void genMap(String serverMessage){

        String tempString = serverMessage.substring(serverMessage.indexOf("{"));
        tempString = tempString.trim();
//        Trims the following: { } " and spaces
        tempString = tempString.replaceAll("[{}\" ]", "");

        splitMap = Splitter.on(",").withKeyValueSeparator(":").split(tempString);

    }


//    Handles all server(SVR) related messages
    private void handleSrv(String typeOfMessage){
            switch (typeOfMessage){
                case "HELP":

                    break;
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
                break;
            case "YOURTURN":

                break;
            case "WIN":
                System.out.println("You've won!");
                break;
            case "LOSS":
                System.out.println("You've lost :(");
                break;
            case "DRAW":
                System.out.println("It's a draw!");
                break;
            case "MOVE":
                NetworkInputSubject.notify(Byte.parseByte(splitMap.get("MOVE")));
                break;
        }
    }


    public void sendMove(byte[] move) {
        clientcom.move(Integer.toString((move[0] * 3) + move[1]), out);
    }


    public String getPlayerToMove() {
        return this.playerToMove;
    }
}