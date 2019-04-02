package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class serverController implements Runnable{
    private final String host= "localhost";
    private final int portNumber = 7789;
    private PrintWriter out;
    private BufferedReader br;
    private Socket socket;
    private boolean running = true;

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
            serverMessages();
        }
    }


//Gets the messages that are being send by the server
    private void serverMessages(){
        try{
            System.out.println(br.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

// TODO: 02/04/2019 fix all methods so they work with the events happening in the game
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

}
