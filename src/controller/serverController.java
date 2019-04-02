package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class serverController implements Runnable{
    private final String host= "localhost";
    private final int portNumber = 7789;

    private void connectToServer(){
       try{
           Socket socket = new Socket(host, portNumber);
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

           System.out.println("server says:" + br.readLine());

       } catch (IOException e){
            e.printStackTrace();
       }
    }

    public void run()  {
        System.out.println("Creating socket to '" + host + "' on port " + portNumber);
        connectToServer();

    }

    public void loginToServer(){

    }


}
