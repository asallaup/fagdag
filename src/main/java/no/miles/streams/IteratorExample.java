package no.miles.streams;

import java.util.Collection;
import java.util.List;

public class IteratorExample {

    public CsvRecord createStatisticUsingIterator(Collection<CsvRecord> input) {
        CsvRecord accumulated = new CsvRecord();
        int count = 0;
        for (CsvRecord record : input) {
            accumulate(accumulated, record);
            count++;
        }
        if (count > 0) {
            accumulated.setAverage(accumulated.getAverage() / count);
        }
        return accumulated;
    }

    public static void accumulate(CsvRecord accumulated, CsvRecord record) {
        accumulated.setSum(accumulated.getSum() + record.getSum());
        accumulated.setMinValue(Math.min(accumulated.getMinValue(), record.getMinValue()));
        accumulated.setMaxValue(Math.max(accumulated.getMaxValue(), record.getMaxValue()));
        accumulated.setAverage(accumulated.getAverage() + record.getAverage());
    }

    public static void main(String[] args) {
        IteratorExample iteratorExample = new IteratorExample();
        String csvFilePath = "records.csv"; // Replace with the actual path to your CSV file
        List<CsvRecord> records = CSVReaderExample.readCSVFile(csvFilePath);
        CsvRecord accumulated = iteratorExample.createStatisticUsingIterator(records);
        System.out.println(accumulated);
    }
}
