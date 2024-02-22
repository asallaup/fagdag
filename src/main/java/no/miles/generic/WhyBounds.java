package no.miles.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhyBounds {
    public void testBounds(){
        List<Number> numberList = new ArrayList<>();

        TestExample<Number> numberTestExample = new TestExample<>();
        numberList.add(100);
        numberList.add(200L);
        numberList.add(200.0);

        numberTestExample.doSomehingWithCollection(numberList);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(100.0);
        doubleList.add(200.3);
        doubleList.add(300.5);

        // numberTestExample.doSomehingWithCollection(doubleList);
    }

    static class TestExample<T> {
        void doSomehingWithCollection(Collection<T> items){
            for (T item: items){
                System.out.println(item);
            }
        }
    }

//    static class TestExample<T> {
//        void doSomehingWithCollection(Collection<? extends T> items){
//            for (T item: items){
//                System.out.println(item);
//            }
//        }
//    }

    public static void main(String[] args) {
        WhyBounds whyBounds = new WhyBounds();
        whyBounds.testBounds();
    }

}
