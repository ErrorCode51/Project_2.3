package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import com.google.common.base.*;

//todo: remove once random string generator is remove from yourturn case
import java.util.Random;

// TODO: 03/04/2019 clean this mess up and make handlers for the switch cases

public class ServerController implements Runnable{
    private final String host= "localhost";
    private final int portNumber = 7789;
    private PrintWriter out;
    private BufferedReader br;
    private Socket socket;
    private boolean running = true;
    private String[] splittedMessage;
    private ClientCommands clientcom = new ClientCommands();

//Open a socket connection to the server
    private void connectToServer(){
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


    public void run()  {
        System.out.println("Creating socket to '" + host + "' on port " + portNumber);
        connectToServer();
        clientcom.loginToServer("kevin",out);
        clientcom.subTogame("Tic-tac-toe",out);
        while(running){
            handleMessage();
        }
    }

//    server message handling
    // TODO: 03/04/2019 Make this its own class
    // TODO: 03/04/2019 handle events with AI
    private void handleMessage(){
        try {
            String servmessage = br.readLine();

            if (servmessage.contains("{")) {
                genMap(servmessage);
            }

            splittedMessage = servmessage.split("\\s+");
            System.out.println(Arrays.toString(splittedMessage));

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
//    Uses the guava Splitter library to split the map into keys and values
    private void genMap(String serverMessage){

        String tempString = serverMessage.substring(serverMessage.indexOf("{"));

        tempString = tempString.replaceAll("[{}\"]", "");

        Map<String, String> splitMap = Splitter.on(",").withKeyValueSeparator(":").split(tempString);

//            TEST TODO: REMOVE WHEN DONE
        for ( String key : splitMap.keySet()) {
            System.out.println("The value that belongs to " + key + " is " + splitMap.get(key));
        }
//            TEST END
    }


//    Handles all server(SVR) related messages
    private void handleSrv(String typeOfMessage){
            switch (typeOfMessage){
                case "HELP":
                    System.out.println("Help was called");
                    break;
                case "GAME":
                    System.out.println("game was called");
                    gamehandler(splittedMessage[2]);
                    break;
                case "GAMELIST":
                    System.out.println("gamelist was called");
                    break;
                case "PLAYERLIST":
                    System.out.println("playerlist was called");
                    break;
            }
    }
// handles all messages containing the word GAME
    private void gamehandler(String gameOption){
        switch(gameOption){
            case "MATCH":
                System.out.println("match was called");
                break;
            case "YOURTURN":
                System.out.println("yourturn was called");
                // TODO: 03/04/2019  Add code for doing the move ( and remove the random one, pls)
                Random rand = new Random();
                int n = rand.nextInt(8);
                String str = Integer.toString(n);
                clientcom.move(str,out);
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
        }
    }

}
