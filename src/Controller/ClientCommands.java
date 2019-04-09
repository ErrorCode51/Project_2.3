package Controller;

// TODO: 02/04/2019 fix all methods so they work with the events happening in the game

import java.io.PrintWriter;

// Clientcommands holds all the client options for the server
public class ClientCommands {

    // TODO: 05/04/2019 Make the AI choose this setup instead of hardcoding
    public void loginToServer(String name, PrintWriter out){
        out.println("login "+name);
    }

    // TODO: 05/04/2019 AI needs to be able to quit, maybe
    public void logout(PrintWriter out){
        out.println("bye");
    }

    // parameter can be gamelist or playerlist
    public void getlist(String listtype,PrintWriter out){
        out.println("get " + listtype);
    }

    // TODO: 05/04/2019 AI needs to sub to the game by calling this method 
    public void subTogame(String game,PrintWriter out){
        out.println("subscribe " + game);
    }

    // TODO: 05/04/2019 AI needs to be able to call move
    public void move(String coordinates,PrintWriter out){
        out.println("move " + coordinates);
    }

    public void challengeAccept(PrintWriter out){
        out.println("challenge accept");
    }

    public void forfeit(PrintWriter out){
        out.println("forfeit");
    }

    public void help(PrintWriter out){
        out.println("help");
    }

}