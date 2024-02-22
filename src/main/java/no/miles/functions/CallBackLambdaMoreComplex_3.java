package no.miles.functions;

import java.util.ArrayList;
import java.util.List;

public class CallBackLambdaMoreComplex_3 {

    private interface Callback {
        void onMessage(String message, Integer context);
    }

    private static class Adapter {
        List<Callback> cbs = new ArrayList<>();

        void subscribe(Callback cb) {
            cbs.add(cb);
        }
    }

    public void callbackExample() {

        Adapter adapter = new Adapter();

        // Example 1
        adapter.subscribe((s, i) -> {
            handleMessage(s, i);
        });

        // Example 2
        adapter.subscribe(this::handleMessage);

        // Example 3
        adapter.subscribe((s, i) -> {
            handleMessage2(i, s);
        });

        Callback f = (s, i) -> handleMessage2(i, s);

        adapter.subscribe(f);

    }

    private void handleMessage(String s, Integer i) {
        System.out.printf("Received message %s:%d", s, i);
    }

    // Skummelt
    private void handleMessage2(Integer i, String s) {
        System.out.printf("Received message %s:%d", s, i);
    }

}
