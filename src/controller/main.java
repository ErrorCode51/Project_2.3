package controller;


public class main {
    public static void main(String[] args){
        serverController server = new serverController();
        new Thread(server).start();
    }
}