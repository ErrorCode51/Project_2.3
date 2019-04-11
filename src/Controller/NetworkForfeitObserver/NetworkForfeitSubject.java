package Controller.NetworkForfeitObserver;

import Controller.NetworkTurnObserver.NetworkTurnObserver;

import java.util.ArrayList;

public class NetworkForfeitSubject {
    private static ArrayList<NetworkForfeitObserver> observers = new ArrayList<>();

    public static synchronized void subscribe(NetworkForfeitObserver observer) {
        observers.add(observer);
    }

    public static synchronized void unsubscribe(NetworkForfeitObserver observer) {
        observers.remove(observer);
    }

    public static void forfeit() {
        for (NetworkForfeitObserver observer : observers) {
            observer.informAboutEnemyForfeit();
        }
    }
}
