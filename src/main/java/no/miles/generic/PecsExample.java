package no.miles.generic;

import java.util.Collection;

// PECS Producer Extend Consumer Super
public class PecsExample<T> {
    public  Collection<? super T> mapAndTransfer(Collection<? extends T> producer, Collection<? super T> consumer){ // in out
        for(T item : producer){
            T mapped = map(item);
            consumer.add(mapped);
        }
        return consumer;
    }

    private T map(T item) {
        return item ;
    }
}
