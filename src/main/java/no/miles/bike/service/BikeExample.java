package no.miles.bike.service;

import no.miles.bike.model.BikeRideRecord;
import no.miles.generic.GenericWithBounds;

import java.util.*;
import java.util.stream.Collectors;

public class BikeExample {

    // Hvor mange turer har station  som endestatsjon
    // Hvor mange  turer har station  som startstasjon

    private static class StationFrequenceComparator implements Comparator<StationFrequence> {

        @Override
        public int compare(StationFrequence sf1, StationFrequence sf2) {
            return Long.compare((sf1.start - sf1.end), (sf2.start - sf2.end));
        }
    }

    record StationFrequence(String station, long start, long end) {
    }

    StationFrequence startEndeStasjon(String stasjon, List<BikeRideRecord> records) {
        var endestasjon = records.stream()
                .filter(s -> s.endStationName().equals(stasjon))
                .count();
        var startStasjon = records.stream()
                .filter(s -> s.startStationName().equals(stasjon))
                .count();
        return new StationFrequence(stasjon, startStasjon, endestasjon);

    }

    public void antallReiserFraOgTil() throws Exception {
        var comparator = new StationFrequenceComparator();
        var bikeRideRecords = BikeRecordCSVReader.readBikeRecords("bike.csv");
        var res = bikeRideRecords.stream()
                .map(frequency -> frequency.startStationName())
                .distinct()
                .map(station -> startEndeStasjon(station, bikeRideRecords))
                .sorted(comparator)
                .toList();

        res.forEach(sf -> System.out.println(sf + ":" + (sf.end() - sf.start())));
    }

    private int comp(StationFrequence sf) {
        return 0;
    }


    public void entallEndstajonerFraEnStartStasjon() throws Exception {
        var bikeRideRecords = BikeRecordCSVReader.readBikeRecords("bike.csv");
        var groupedByStation = bikeRideRecords.stream()
                .collect(Collectors.groupingBy(BikeRideRecord::startStationName));

        // på en gitt stasjon sorter endestasjon etter frekvens
        var groupedByEndeStation = groupedByStation.get("Klosteret").stream()
                .collect(Collectors.groupingBy(BikeRideRecord::endStationName));

        var sortedEndeStation = sumAndOrder(groupedByEndeStation);

        sortedEndeStation.forEach((key, size) -> System.out.println("Key: " + key + ", Size: " + size));

    }

    static <T> Map<String, Integer> sumAndOrder(Map<String, List<T>> group) {
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
        BikeExample bikeExample = new BikeExample();
        try {
            // bikeExample.entallEndstajonerFraEnStartStasjon();
            bikeExample.antallReiserFraOgTil();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
