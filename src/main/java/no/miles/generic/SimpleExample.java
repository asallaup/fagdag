package no.miles.generic;

import java.util.ArrayList;
import java.util.List;


public class SimpleExample {

    // Java 1.4
    public void oldFashionWithClassCast() {
        System.out.println("oldFashionWithClassCast");
        List list = new ArrayList();
        list.add("String 1");
        list.add("String 2");
        list.add(45);
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            System.out.println(s);
        }
    }

    // Java 1.5
    public void withGeneric() {
        System.out.println("withGeneric");

        List<String> list = new ArrayList<String>();
        list.add("String 1");
        list.add("String 2");
        //list.add(45);

        for (String s : list) {
            System.out.println(s);
        }
    }

    // Java 8
    public void withLambda() {
        System.out.println("withLambda");

        List<String> list = new ArrayList<>();

        list.add("String 1");
        list.add("String 2");
        list.forEach(System.out::println);
    }

    // Java 10
    public void withVar() {
        System.out.println("withVar");
        var list = new ArrayList<String>();
        list.add("String 1");
        list.add("String 2");
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        SimpleExample se = new SimpleExample();
        se.oldFashionWithClassCast();
//        se.withGeneric();
//        se.withLambda();

    }
}
