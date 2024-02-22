package no.miles.functions;

import java.util.ArrayList;
import java.util.List;

public class CallBackAnonymousClass_1 {
    private static class Adapter {
        List<Callback> cbs = new ArrayList<>();

        void subscribe(Callback cb) {
            cbs.add(cb);
        }

        void messageReceived(String message){
            for (int i = 0; i < cbs.size() ; i++){
                cbs.get(i).onMessage(message);
            }
        }
    }

    private interface Callback {
        void onMessage(String s);
    }
    public void callbackExampleGoodOldDays() {
        Adapter adapter = new Adapter();
        // Good old days
        adapter.subscribe(new Callback() {
            @Override
            public void onMessage(String s) {
                handleMessage(s);
            }
        });
    }
    private void handleMessage(String s) {
        System.out.println(s);
    }

}
