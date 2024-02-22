package no.miles.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CallBackLambda_2 {
    private interface Callback {
        void onMessage(String s);
    }

    private static class Adapter {
        List<Callback> cbs = new ArrayList<>();

        void subscribe(Callback cb) {
            cbs.add(cb);
        }

        void subscribe(CallbackWithTwoMethods cb) {
            //  cbs.add(cb);
        }

        void messageReceived(String message){
            cbs.forEach(s -> s.onMessage(message));
        }
    }

    public void callbackExampleLambda() {

        Adapter adapter = new Adapter();

        // Example 1
        adapter.subscribe(s -> {
            handleMessage(s);
        });

        // Example 2
        Callback f = (s) -> handleMessage(s);
        adapter.subscribe(f);

        Function<String, Integer> f2 = (s)  -> {
            return s.length();
        };

        BiFunction<String, Integer, Integer> f3 = (s,  i) -> {
            return s.length() + i;
        };

        f3.apply("de", 4);
        // Example 3

        adapter.subscribe( new CallbackWithTwoMethods () {

            @Override
            public void onMessage(String s) {
                handleMessage(s);
            }

            @Override
            public void onMessage(Integer i) {
                handleMessage( i.toString());
            }
        });
    }

    private void handleMessage(String s) {
        System.out.println(s);
    }

    private interface CallbackWithTwoMethods {
        void onMessage(String s);
        void onMessage(Integer i);
    }

}
