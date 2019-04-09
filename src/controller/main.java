package controller;


public class main {
    public static void main(String[] args){
        ServerController server = new ServerController();
        new Thread(server).start();
    }
}