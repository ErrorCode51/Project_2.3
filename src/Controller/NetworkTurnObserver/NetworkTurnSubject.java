package Controller.NetworkTurnObserver;

import Controller.NetworkInputObserver.NetworkInputObserver;

import java.util.ArrayList;

public class NetworkTurnSubject {

    private static ArrayList<NetworkTurnObserver> observers = new ArrayList<>();

    public static synchronized void subscribe(NetworkTurnObserver observer) {
        observers.add(observer);
    }

    public static synchronized void unsubscribe(NetworkTurnObserver observer) {
        observers.remove(observer);
    }

    public static void giveTurn() {
        while (observers.isEmpty()) {
            Thread.yield();
        }
        for (NetworkTurnObserver observer : observers) {
            observer.giveTurn();
        }
    }

}
