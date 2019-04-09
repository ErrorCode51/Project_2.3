package Controller;

import java.io.PrintWriter;

// TODO: 09/04/2019 MAKE ALL METHODS IN HERE AVAILABLE 
// Clientcommands holds all the client options for the server
public class ClientCommands {

    public void loginToServer(String name, PrintWriter out){
        out.println("login "+name);
    }

    public void logout(PrintWriter out){
        out.println("bye");
    }

    // parameter can be gamelist or playerlist
    public void getlist(String listtype,PrintWriter out){
        out.println("get " + listtype);
    }

    public void subTogame(String game,PrintWriter out){
        out.println("subscribe " + game);
    }

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