package no.miles.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PecsUser {
    void testPecsExample(){
        PecsExample<Number> pecsExample = new PecsExample<>();
        List<Number>  numberList = new ArrayList<>();
        List<Double>  doubleList = new ArrayList<>();
        List<Integer>  integerList = new ArrayList<>();

        numberList.add(34);
        numberList.add(3);
        Collection<?> res = pecsExample.mapAndTransfer(doubleList, numberList);
        for (Object item : res){
            System.out.println(item);
        }
        for (Number item : numberList){
            System.out.println(item);
        }
      //  pecsExample.mapAndTransfer(numberList, doubleList);
      //  pecsExample.mapAndTransfer(doubleList, integerList);
    }
}
