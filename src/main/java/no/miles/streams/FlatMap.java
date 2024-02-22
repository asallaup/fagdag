package no.miles.streams;

import no.miles.bike.model.BikeRideRecord;
import no.miles.bike.service.BikeRecordCSVReader;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {

    public void mapExample() {

        List<String> words = Arrays.asList("hello", "world");
        List<Integer> lengths = words.stream()

                //  <R> Stream<R> map(Function<? super T, ? extends R> mapper);
                .map(s -> mapFunction(s))
                .collect(Collectors.toList());

        // Result: [5, 5]

    }

    private Integer mapFunction(String s) {
        return s.length();
    }

    private Stream<Integer> flatMapFunction(List<String> stringList) {
        return stringList.stream()
                .map(String::length);
    }
    public void flatMapExample() {

        List<List<String>> nestedLists = Arrays.asList(
                Arrays.asList("Lion", "Tiger", "Leopard", "Cheetah"),
                Arrays.asList("Elephant", "Giraffe", "Zebra", "Hippo"),
                Arrays.asList("Monkey", "Kangaroo", "Koala", "Panda"));

        List<Integer> flattenedList = nestedLists.stream()
                //   <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
                .flatMap(this::flatMapFunction)
                .toList();

    }



    public void flatMapExample2() throws Exception {
        var bikeRideRecords = BikeRecordCSVReader.readBikeRecords("bike.csv");
        var gropedByStartStation = bikeRideRecords.stream()
                .collect(Collectors.groupingBy(BikeRideRecord::startStationName));

        // på en gitt stasjon sorter endestasjon etter frekvens
        var groupedByEndeStation = gropedByStartStation.get("Klosteret").stream()
                .collect(Collectors.groupingBy(BikeRideRecord::endStationName));

        var sortedEndeStation = sumAndOrder(groupedByEndeStation);

        sortedEndeStation.forEach((key, size) -> System.out.println("Key: " + key + ", Size: " + size));

    }

    static <T> Map<String, Integer> sumAndOrder(Map<String, List<T>> group){
       return group.entrySet().stream()
           .collect(Collectors.toMap(
                Map.Entry::getKey,          // Key mapper
                e -> e.getValue().size(),   // value mapper
                (size1, size2) -> size1, // Merge function (if key collision occurs, keep the first size)
                LinkedHashMap::new // Use a LinkedHashMap to maintain insertion order
        ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (size1, size2) -> size1,
                        LinkedHashMap::new
                ));

    }

    public static void main(String[] args) {
        FlatMap flatMap = new FlatMap();
        try {
            flatMap.flatMapExample2();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
