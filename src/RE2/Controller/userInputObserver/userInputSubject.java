package RE2.Controller.userInputObserver;

import java.util.ArrayList;

public class userInputSubject {

    static ArrayList<userInputObserver> observers = new ArrayList<>();

    public static synchronized void subscribe(userInputObserver observer) {
        observers.add(observer);
    }

    public static synchronized void unsubscribe(userInputObserver observer) {
        observers.remove(observer);
    }

    public static void notify(byte row, byte column) {
        for (userInputObserver observer : observers) {
            observer.notify(row, column);
        }
    }

}
