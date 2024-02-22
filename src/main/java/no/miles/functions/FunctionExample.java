package no.miles.functions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class FunctionExample {

    private static final Map<String, String> cache = new HashMap<>();


    public void simpleFunction() {
        Map<String, Integer> nameMap = new HashMap<>();
        String key = "John";
        Integer value = nameMap.computeIfAbsent(key, k -> k.length());
        nameMap.getOrDefault("ABS", 122344);

        System.out.println(STR."Size of map: \{nameMap.size()}");
        // Skriv innhold

        Optional<String> optional = Optional.empty();
        // Bad use
        optional.ifPresent( o -> {

        });

        var upperCase = optional.stream().map(String::toUpperCase);


    }


    public void simpleFunction2() {
        Map<String, Integer> nameMap = new HashMap<>();
        //  V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)

        String key = "John";

        Integer value = nameMap.computeIfAbsent(key, function);
        System.out.println(STR."Size of map: \{nameMap.size()}");

        // function.apply()
    }


    Function<String, Integer> function = s -> {
        System.out.println(s);
        return s.length();
    };

    Function<? super String, ? extends Integer> mappingFunction2 = (k) -> {
        // Her kan en gjøre mye rart
        System.out.println(STR."Key \{k}");
        return k.length();
    };



    public void simpleFunction3() {
        Map<String, Integer> nameMap = new HashMap<>();
        //  V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)

        String key = "John";
        Integer value = nameMap.computeIfAbsent(key, this::mappingFunction3);
        System.out.println(STR."Size of map: \{nameMap.size()}");
    }

    Integer mappingFunction3(String key){
        // Her kan en gjøre mye rart
        System.out.println(STR."Key \{key}");
        return key.length();
    }


    public static void main(String[] args) {
        var functionExample = new FunctionExample();
        functionExample.simpleFunction();
    }

}
