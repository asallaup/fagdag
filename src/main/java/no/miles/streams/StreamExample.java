package no.miles.streams;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExample {

    public CsvRecord createStatisticUsingStream(Collection<CsvRecord> input) {
        var res = input.stream()

//                <R> R collect(Supplier<R> supplier,
//                BiConsumer<R, ? super T> accumulator,
//                BiConsumer<R, R> combiner);
                .collect(CsvRecordStatistic::new,
                        (statistic, record) -> akkumulere(statistic, record),
                        (s1, s2) -> s1.kombinere(s2));
        return res.getAccumulated();
    }

    private CsvRecordStatistic akkumulere(CsvRecordStatistic statistic, CsvRecord record) {
        statistic.akkumulere(record);
        return statistic;
    }


    public CsvRecord createStatisticUsingStream2(Collection<CsvRecord> input) {
        var res = input.stream()

                .collect(CsvRecordStatistic::new,
                        CsvRecordStatistic::akkumulere,
                        CsvRecordStatistic::kombinere);
        return res.getAccumulated();
    }

    public CsvRecord createStatisticUsingParallelStream(Collection<CsvRecord> input) {
        var res = input.parallelStream()
                .collect(CsvRecordStatistic::new,
                        (statistic, record) -> statistic.akkumulere(record),
                        (s1, s2) -> s1.kombinere(s2));
        return res.getAccumulated();
    }

    public CsvRecord createStatisticUsingToInt(Collection<CsvRecord> input) {
        var sum = input.stream()
                .mapToInt(r -> r.getSum())
                .sum();

        var avg = input.stream()
                .mapToInt(r -> r.getAverage())
                .average();

        return null;

    }


    public List<CsvRecord> createStatisticWithGroup(Collection<CsvRecord> input) {
        return input.stream()
                .collect(Collectors.groupingBy(CsvRecord::getId))
                .entrySet().stream()  // Is there a more elegant solution ?
                .map(entry -> createStatisticUsingStream(entry.getValue()))
                .toList();
    }


    static class CsvRecordStatistic {
        private CsvRecord accumulated = new CsvRecord();
        int count = 0;

        public CsvRecord getAccumulated() {
            if (count > 0) {
                accumulated.setAverage(accumulated.getAverage() / count);
                accumulated.setCount(count);
            }
            return accumulated;
        }

        public void akkumulere(CsvRecord record) {
            accumulated.setId(record.getId());
            accumulated.setMaxValue(Math.max(accumulated.getMaxValue(), record.getMaxValue()));
            accumulated.setSum(accumulated.getSum() + record.getSum());
            accumulated.setAverage(accumulated.getAverage() + record.getAverage());
            accumulated.setMinValue(Math.min(accumulated.getMinValue(), record.getMinValue()));
            count++;
        }

        public void kombinere(CsvRecordStatistic other) {
            CsvRecord record = other.accumulated;
//            accumulated.setMaxValue(Math.max(accumulated.getMaxValue(), record.getMaxValue()));
//            accumulated.setSum(accumulated.getSum() + record.getSum());
//            accumulated.setAverage(accumulated.getAverage() + record.getAverage());
//            accumulated.setMinValue(Math.min(accumulated.getMinValue(), record.getMinValue()));
//            count += record.getCount();
            throw new NotImplementedException("kombinere not implemented");
        }
    }


    public static void main(String[] args) {
        StreamExample streamExample = new StreamExample();
        String csvFilePath = "records.csv";

        List<CsvRecord> records = CSVReaderExample.readCSVFile(csvFilePath);

        CsvRecord accumulated = streamExample.createStatisticUsingStream(records);
        System.out.println(accumulated);
        System.out.println();

        List<CsvRecord> grouped = streamExample.createStatisticWithGroup(records);
        grouped.forEach(System.out::println);


//        streamExample.createStatisticUsingToInt(records);
    }

}
