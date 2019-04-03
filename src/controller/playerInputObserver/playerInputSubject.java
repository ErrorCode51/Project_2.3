package controller.playerInputObserver;

import java.util.ArrayList;

public class playerInputSubject {
    static ArrayList<playerInputObserver> observers;

    public static synchronized void subscribe(playerInputObserver o) {
        observers.add(o);
    }


    public static synchronized void unsubscribe(playerInputObserver o) {
        observers.remove(o);
    }


    public static void notify(byte x, byte y) {
        for (playerInputObserver observer : observers) {
            observer.notify(x, y);
        }
    }

}
