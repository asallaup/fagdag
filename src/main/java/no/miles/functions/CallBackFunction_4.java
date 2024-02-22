package no.miles.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CallBackFunction_4 {

    private static class Adapter<T> {
        List<Consumer<T>> cbs = new ArrayList<>();

        void subscribe(Consumer<T> consumer) {
            cbs.add(consumer);
        }

        void messageReceived(T message) {
            cbs.forEach(consumer -> consumer.accept(message));
        }
    }

    public void callbackExample() {

        Adapter<String> stringAdapter = new Adapter<>();

        // Example 1
        stringAdapter.subscribe(s -> {
            handleMessage(s);
        });

        // Example 2
        stringAdapter.subscribe(this::handleMessage);

        // Example
        Adapter<Integer> numberAdapter = new Adapter<>();
        numberAdapter.subscribe(this::handleMessage);

    }

    private void handleMessage(String s) {
        System.out.println(s);
    }

    private void handleMessage(Number i) {
        System.out.println(i);
    }

}
