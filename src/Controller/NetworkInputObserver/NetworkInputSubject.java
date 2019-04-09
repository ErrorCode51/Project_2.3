package Controller.NetworkInputObserver;

import java.util.ArrayList;

public class NetworkInputSubject {

    static ArrayList<NetworkInputObserver> observers = new ArrayList<>();

    public static synchronized void subscribe(NetworkInputObserver observer) {
        System.out.println(observer + " has subscribed");
        observers.add(observer);
    }

    public static synchronized void unsubscribe(NetworkInputObserver observer) {
        System.err.println(observer + " has unsubscribed");
        observers.remove(observer);
    }

    public static void notify(byte tile) {
        System.err.println("all NetworkInputObservers have been notified");
        for (NetworkInputObserver observer : observers) {
            observer.notify(tile);
        }
    }

}
