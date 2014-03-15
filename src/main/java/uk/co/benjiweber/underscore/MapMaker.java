package uk.co.benjiweber.underscore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapMaker {

    public static <T,U> Map<T,U> $$(Pair<T, U>... pairs) {
        Map<T,U> made = new HashMap<>();
        Arrays.asList(pairs).forEach(pair -> {
            made.put(pair.key, pair.value);
        });
        return made;
    }

    public static <T,U> Pair<T,U> $(T t, U u) {
        return new Pair<T,U>(t,u);
    }


    public static class Pair<T,U> {
        public final T key;
        public final U value;

        public Pair(T key, U value) {
            this.key = key;
            this.value = value;
        }

        public T key() {
            return key;
        }

        public U value() {
            return value;
        }
    }
}
