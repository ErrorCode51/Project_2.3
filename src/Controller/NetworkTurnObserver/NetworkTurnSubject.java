package Controller.NetworkTurnObserver;

import Controller.NetworkInputObserver.NetworkInputObserver;

import java.util.ArrayList;

public class NetworkTurnSubject {

    static ArrayList<NetworkTurnObserver> observers = new ArrayList<>();

    public static synchronized void subscribe(NetworkTurnObserver observer) {
        observers.add(observer);
        System.err.println("Something subscribed to the NetworkTurnSubject");
    }

    public static synchronized void unsubscribe(NetworkTurnObserver observer) {
        observers.remove(observer);
        System.err.println("Something unsubscribed from the NetworkTurnSubject");
    }

    public static void giveTurn() {
        System.err.println("Its your turn");
        while (observers.isEmpty()) {
        }
        for (NetworkTurnObserver observer : observers) {
            observer.giveTurn();
        }
    }

}
