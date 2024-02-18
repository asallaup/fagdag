package no.miles.bike.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import no.miles.bike.model.BikeRideRecord;

import java.io.FileReader;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BikeRecordCSVReader {
    public static void main(String[] args) {
        String csvFilePath = "bike.csv"; // Replace with the actual path to your CSV file
        List<BikeRideRecord> parsedRecord = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            csvReader.skip(1);
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                BikeRideRecord bikeRecord = new BikeRideRecord(
                        OffsetDateTime.parse(record[0].replace(" ", "T")),
                        OffsetDateTime.parse(record[1].replace(" ", "T")),
                        Integer.parseInt(record[2]),
                        Integer.parseInt(record[3]),
                        record[4],
                        record[5],
                        Double.parseDouble(record[6]),
                        Double.parseDouble(record[7]),
                        Integer.parseInt(record[8]),
                        record[9],
                        record[10],
                        Double.parseDouble(record[11]),
                        Double.parseDouble(record[12])
                );

                // Now you can use the bikeRecord as needed
                //     System.out.println(bikeRecord);
                parsedRecord.add(bikeRecord);

            }
            var groupedBystartStationNane = parsedRecord.stream()
                    .collect(Collectors.groupingBy(BikeRideRecord::startStationName));

            Map<String, Integer> result = groupedBystartStationNane.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,          // Key mapper
                            e -> e.getValue().size(),
                            (size1, size2) -> size1, // Merge function (if key collision occurs, keep the first size)
                            LinkedHashMap::new // Use a LinkedHashMap to maintain insertion order
                    ))
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (size1, size2) -> size1,
                            LinkedHashMap::new
                    ));
            result.forEach((key, size) -> System.out.println("Key: " + key + ", Size: " + size));


        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
