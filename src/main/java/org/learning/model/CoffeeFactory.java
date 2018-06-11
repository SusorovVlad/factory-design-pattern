package org.learning.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface CoffeeFactory {

    Coffee prepare(String name);

    static CoffeeFactory factory(Consumer<Builder> consumer) {
        Map<String, Supplier<Coffee>> map = new HashMap<>();
        consumer.accept(map::put);
        return type -> map.getOrDefault(type, () -> {
            throw new IllegalArgumentException("Unknown coffee type.");
        }).get();
    }

    interface Builder {

        void register(String name, Supplier<Coffee> supplier);
    }
}
