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
        while(running){
            System.out.println("Creating socket to '" + host + "' on port " + portNumber);
            connectToServer();
            loginToServer();
        }



    }

    public void loginToServer(){
        out.println("login kevin");
        System.out.println("it's printed");
    }


}
