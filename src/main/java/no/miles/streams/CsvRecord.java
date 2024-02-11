package no.miles.streams;

public class CsvRecord {
    private int id;
    private int maxValue;
    private int minValue;
    private int average;
    private int sum;

    private int count;

    public CsvRecord(int id, int maxValue, int minValue, int average, int sum, int count) {
        this.id = id;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.average = average;
        this.sum = sum;
        this.count = count;
    }

    public CsvRecord() {
    }

    // Constructors, getters, and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "CsvRecord{" +
                "id=" + id +
                ", maxValue=" + maxValue +
                ", minValue=" + minValue +
                ", average=" + average +
                ", sum=" + sum +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
