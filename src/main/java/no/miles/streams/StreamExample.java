package no.miles.streams;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExample {

    public CsvRecord createStatisticUsingStream(Collection<CsvRecord> input) {
        var res =  input.stream()
                .collect(CsvRecordStatistic::new ,
                        (statistic, record) -> statistic.accept(record),
                        (s1,s2) -> s1.combine(s2));
        return res.getAccumulated();
    }

    public CsvRecord createStatisticUsingStream2(Collection<CsvRecord> input) {
        var res =  input.stream()
//                <R> R collect(Supplier<R> supplier,
//                BiConsumer<R, ? super T> accumulator,
//                BiConsumer<R, R> combiner);

                .collect(CsvRecordStatistic::new,
                        CsvRecordStatistic::accept,
                        CsvRecordStatistic::combine);
        return res.getAccumulated();
    }

    public List<CsvRecord> createStatisticWithGroup(Collection<CsvRecord> input) {
        var res =  input.stream()
                .collect(Collectors.groupingBy(CsvRecord::getId));
        var grouped = res.entrySet().stream()
                .map( entry -> createStatisticUsingStream(entry.getValue()))
                .toList();
        return grouped;
    }


    static class CsvRecordStatistic {
        private CsvRecord accumulated = new CsvRecord();
        int count = 0;

        public CsvRecord getAccumulated(){
            if (count > 0){
                accumulated.setAverage(accumulated.getAverage() / count);
                accumulated.setCount(count);
            }
            return accumulated;
        }

        public void accept(CsvRecord record) {
            accumulated.setId(record.getId());
            accumulated.setMaxValue(Math.max(accumulated.getMaxValue(), record.getMaxValue()));
            accumulated.setSum(accumulated.getSum() + record.getSum());
            accumulated.setAverage(accumulated.getAverage() + record.getAverage());
            accumulated.setMinValue(Math.min(accumulated.getMinValue(), record.getMinValue()));
            count++;
        }

        public void combine(CsvRecordStatistic s) {
            throw new NotImplementedException("combine not implemented");
        }
    }


    public static void main(String[] args) {
        StreamExample streamExample = new StreamExample();
        String csvFilePath = "records.csv"; // Replace with the actual path to your CSV file
        List<CsvRecord> records = CSVReaderExample.readCSVFile(csvFilePath);


        CsvRecord accumulated = streamExample.createStatisticUsingStream(records);
        System.out.println(accumulated);
        System.out.println();



        List<CsvRecord> grouped = streamExample.createStatisticWithGroup(records);
        grouped.forEach(System.out::println);
    }

}
