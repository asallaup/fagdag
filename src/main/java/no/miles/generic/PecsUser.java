package no.miles.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PecsUser {
    void testPecsExample(){
        PecsExample<Number> pecsExample = new PecsExample<>();
        List<Number>  numberList = new ArrayList<>();
        List<Double>  doubleList = new ArrayList<>();

        doubleList.add(3.0);
        doubleList.add(34.0);

        System.out.println("Before");
        for (Number item : numberList){
            System.out.println(item);
        }
        Collection<?> res = pecsExample.mapAndTransfer(doubleList, numberList);
        System.out.println("After");
        for (Object item : res){
            System.out.println(item);
        }

//        pecsExample.mapAndTransfer(numberList, doubleList);

//        List<Integer> integerList = new ArrayList<>();
//        pecsExample.mapAndTransfer(doubleList, integerList);
    }

    public static void main(String[] args) {
        PecsUser pecsUser = new PecsUser();
        pecsUser.testPecsExample();
    }
}
