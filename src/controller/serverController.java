package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

// TODO: 03/04/2019 clean this mess up and make handlers for the switch cases

public class serverController implements Runnable{
    private final String host= "localhost";
    private final int portNumber = 7789;
    private PrintWriter out;
    private BufferedReader br;
    private Socket socket;
    private boolean running = true;
    private String[] splitedMessage;

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
          //  serverMessages();
            handleMessage();
        }
    }


// TODO: 02/04/2019 fix all methods so they work with the events happening in the game
// TODO: 03/04/2019 make this cleaner
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
            splitedMessage = servmessage.split("\\s+");
                System.out.println(Arrays.toString(splitedMessage));
            switch (splitedMessage[0]){
                case "OK":
                    System.out.println("OK was called");
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

    private void gamehandler(String gameOption){
        switch(gameOption){
            case "MATCH":
                System.out.println("match was called");
                break;
            case "YOURTURN":
                System.out.println("yourturn was called");
                break;
            case "WIN":
                System.out.println("win was called");
                break;
            case "LOSS":
                System.out.println("loss was called");
                break;
            case "DRAW":
                System.out.println("draw was called");
                break;
        }
    }

}
