package Controller.NetworkInputObserver;

import java.util.ArrayList;

public class NetworkInputSubject {

    static ArrayList<NetworkInputObserver> observers = new ArrayList<>();

    public static synchronized void subscribe(NetworkInputObserver observer) {
        observers.add(observer);
    }

    public static synchronized void unsubscribe(NetworkInputObserver observer) {
        observers.remove(observer);
    }

    public static void notify(byte tile) {
        for (NetworkInputObserver observer : observers) {
            observer.notify(tile);
        }
    }

}
