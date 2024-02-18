package no.miles.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhyBounds {
    public void testBounds(){
        List<Number> numberList = new ArrayList<>();

        TestExample<Number> numberTestExample = new TestExample<>();

        numberTestExample.doSomehingWithCollection(numberList);
        List<Integer> integerList = new ArrayList<>();


      //  numberTestExample.doSomehingWithCollection(integerList);

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



}
