package no.miles.streams;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CsvRecordGenerator {
    private static final int RECORD_COUNT = 100;

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("records.csv")) {
            // Write CSV header
            writer.write("Id,MaxValue,MinValue,Average,Sum\n");

            // Generate and write random records
            for (int i = 1; i <= RECORD_COUNT; i++) {
                CsvRecord record = generateRandomRecord();
                writer.write(recordToCsvString(record) + "\n");
            }

            System.out.println("Generated and wrote " + RECORD_COUNT + " random records to records.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CsvRecord generateRandomRecord() {
        Random random = new Random();
        int id = random.nextInt(20);
        int maxValue = random.nextInt(100);
        int minValue = random.nextInt(100);
        int average = random.nextInt(100);
        int sum = random.nextInt(100);

        return new CsvRecord(id, maxValue, minValue, average, sum, 0);
    }

    private static String recordToCsvString(CsvRecord record) {
        return String.format("%d,%d,%d,%d,%d",
                record.getId(), record.getMaxValue(), record.getMinValue(),
                record.getAverage(), record.getSum());
    }
}
