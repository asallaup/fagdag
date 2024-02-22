package no.miles.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ProducerConsumer {



    public void consumerExample() {

        Supplier<SupplierData> supplier = () -> produceData();
        Consumer<ConsumerData> consumer = c -> sendToCanada(c);
        Consumer<ConsumerData> consumer2 = c -> sendToEuropa(c);
        Consumer<ConsumerData> combinedConsumer = consumer.andThen(consumer2);

        transfer(supplier, combinedConsumer);


    }

    private void sendToEuropa(ConsumerData data) {
        System.out.println("Send to Europa: " + data.data);
    }

    private void transfer(Supplier<SupplierData> supplier, Consumer<ConsumerData> consumer) {
        SupplierData supplierData = null;
     //   consumer.accept();
        while ((supplierData = supplier.get()) != null) {
            ConsumerData consumerData = new ConsumerData(supplierData.data.length());
            consumer.accept(consumerData);
        }
    }

    private SupplierData produceData() {
        return new SupplierData("kjsadfhksdfjh");
    }

    private void sendToCanada(ConsumerData data) {
        System.out.println("Send to canada: " + data.data);
    }


    record SupplierData(String data) {
    }

    record ConsumerData(Integer data) {
    }

    public static void main(String[] args) {
        // Creating a list of integers
        List<Integer> numbers = new ArrayList<>();

        // Creating two consumers for adding and printing elements
        Consumer<Integer> addConsumer = numbers::add;
        Consumer<Integer> printConsumer = System.out::println;

        // Combining consumers using andThen
        Consumer<Integer> addAndPrintConsumer = addConsumer.andThen(printConsumer);

        // Using the combined consumer to add and print elements
        addAndPrintConsumer.accept(1); // Adds and prints 1
        addAndPrintConsumer.accept(2); // Adds and prints 2
        addAndPrintConsumer.accept(3); // Adds and prints 3

        // Displaying the final list
        System.out.println("Final List: " + numbers);




    }


}
