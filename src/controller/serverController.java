package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;

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
    private String[] splitedMessage;
    private Map splitOnMap;

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
        loginToServer("kevin");
        subTogame("Tic-tac-toe");
        while(running){
            handleMessage();
        }
    }


// TODO: 02/04/2019 fix all methods so they work with the events happening in the game
// TODO: 03/04/2019 make this cleaner, maybe put it in another class if possible
    private void loginToServer(String name){
        out.println("login "+name);
    }

    private void logout(){
        out.println("bye");
    }

// parameter can be gamelist or playerlist
    private void getlist(String listtype){
        out.println("get " + listtype);
    }

    private void subTogame(String game){
        out.println("subscribe " + game);
    }

    private void move(String coordinates){
        out.println("move " + coordinates);
    }

    private void challengeAccept(){
        out.println("challenge accept");
    }

    private void forfeit(){
        out.println("forfeit");
    }

    private void help(){
        out.println("help");
    }

//    server message handling
    // TODO: 03/04/2019 Make this its own class
    // TODO: 03/04/2019 handle events with AI
    private void handleMessage(){
        try {
            String servmessage = br.readLine();

            genMap(servmessage);

            splitedMessage = servmessage.split("\\s+");
            System.out.println(Arrays.toString(splitedMessage));

            switch (splitedMessage[0]){
                case "OK":
                    System.out.println("server said OK");
                    break;
                case "ERR":
                    System.out.println("The server gave an error");
                    break;
                case "SVR":
                    System.out.println("Server message");
                    handleSrv(splitedMessage[1]);
                    break;
            }
        }catch (IOException IE){
            IE.printStackTrace();
        }
    }
    private void genMap(String serverMessage){
        if (serverMessage.contains("{")) {
            String tempString = serverMessage.substring(serverMessage.indexOf("{"));
            System.out.println("TEST ->" + tempString);
        }
        // TODO: 03/04/2019 Create map with the data above.
    }

    
//    Handles all server(SVR) related messages
    private void handleSrv(String typeOfMessage){
            switch (typeOfMessage){
                case "HELP":
                    System.out.println("Help was called");
                    break;
                case "GAME":
                    System.out.println("game was called");
                    gamehandler(splitedMessage[2]);
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
                move(str);
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
