package no.miles.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class CallBackFunction_5 {

    private static class Adapter<T,V> {
        List<BiConsumer<T, V>> cbs = new ArrayList<>();

        void subscribe(BiConsumer<T, V> consumer) {
            cbs.add(consumer);
        }

        void messageReceived(T message1, V message2) {
            cbs.getFirst();
            cbs.forEach(consumer -> consumer.accept(message1, message2));
        }
    }

    public void callbackExample() {

        Adapter<String,Integer> stringAdapter = new Adapter<>();

        // Example 1
        stringAdapter.subscribe((s, i) -> {
            handleMessage(s, i);
        });

        // Example 2
        stringAdapter.subscribe(this::handleMessage);

        // Example
        Adapter<Number, String> numberAdapter = new Adapter<>();
        numberAdapter.subscribe(this::handleMessage);
        stringAdapter.messageReceived("Message1", 23);


    }

    private void handleMessage(String s, Integer i ) {
        System.out.println(s);
    }

    private void handleMessage(Number i, String s) {
        System.out.println(i);
    }

    public static void main(String[] args) {
        var cbf = new CallBackFunction_5();
        cbf.callbackExample();
    }

}
