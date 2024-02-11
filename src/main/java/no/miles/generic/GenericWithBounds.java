package no.miles.generic;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Function;


// PECS Producer Extend Consumer Super
public class GenericWithBounds {
    public void testUpperBond() {
        //  public TreeSet(Comparator<? super E> comparator)
        Collection<Number> numberList = new TreeSet<>(new NumberComparator());

        numberList.add(3);
        numberList.add(1L);
        numberList.forEach(System.out::println);

        var doubleList = new TreeSet<Double>(new NumberComparator());
        // public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        var max = Collections.max(doubleList);

//        var numberList2 = new TreeSet<Number>(new DoubleComparator());
//        var max2 = Collections.max(numberList2);
    }

    private static class DoubleComparator implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            return o1.intValue() - o2.intValue();
        }
    }

    private static class NumberComparator implements Comparator<Number> {
        @Override
        public int compare(Number o1, Number o2) {
            return o1.intValue() - o2.intValue();
        }
    }
}
