package no.miles.streams;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderExample {
    public static void main(String[] args) {
        String csvFilePath = "records.csv"; // Replace with the actual path to your CSV file

        List<CsvRecord> records = readCSVFile(csvFilePath);

        // Print the records
        for (CsvRecord record : records) {
            System.out.println(record);
        }
    }

    public static List<CsvRecord> readCSVFile(String filePath) {
        List<CsvRecord> records = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] nextRecord;
            // Skip the header row
            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                CsvRecord record = new CsvRecord();
                record.setId(Integer.parseInt(nextRecord[0].trim()));
                record.setMaxValue(Integer.parseInt(nextRecord[1].trim()));
                record.setMinValue(Integer.parseInt(nextRecord[2].trim()));
                record.setAverage(Integer.parseInt(nextRecord[3].trim()));
                record.setSum(Integer.parseInt(nextRecord[4].trim()));

                records.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}
