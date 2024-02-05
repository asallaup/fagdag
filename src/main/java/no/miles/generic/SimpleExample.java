package no.miles.generic;

import java.util.ArrayList;
import java.util.List;


public class SimpleExample {

    // Java 1.4
    public void oldFashionWithClassCast() {
        List list = new ArrayList();
        list.add("String 1");
        list.add("String 2");
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(0);
            System.out.println(s);
        }
    }

    // Java 1.5
    public void withGeneric() {
        List<String> list = new ArrayList<String>();
        list.add("String 1");
        list.add("String 2");
        for (String s : list) {
            System.out.println(s);
        }
    }

    // Java 8
    public void withLambda() {
        List<String> list = new ArrayList<>();
        list.add("String 1");
        list.add("String 2");
        list.forEach(System.out::println);
    }

    // Java 10
    public void withVar() {
        var list = new ArrayList<String>();
        list.add("String 1");
        list.add("String 2");
        list.forEach(System.out::println);
    }
}
