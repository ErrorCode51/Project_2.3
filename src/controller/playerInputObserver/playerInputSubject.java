package controller.playerInputObserver;

import java.util.ArrayList;

public class playerInputSubject {
    static ArrayList<playerInputObserver> observers = new ArrayList<playerInputObserver>();

    public static synchronized void subscribe(playerInputObserver o) {
        System.out.println("a player has subscribed to the screen input");
        observers.add(o);
    }


    public static synchronized void unsubscribe(playerInputObserver o) {
        observers.remove(o);
    }


    public static void notify(byte x, byte y) {
        System.out.println("all players have been notified");
        for (playerInputObserver observer : observers) {
            observer.notify(x, y);
        }
    }

}
