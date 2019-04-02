package controller;

import java.io.IOException;

public class main {
    public static void main(String[] args)throws IOException{
    serverController server = new serverController();
    new Thread(server).start();
    }
}