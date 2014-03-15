package uk.co.benjiweber.underscore;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Curry {

    public static <T,U,R> Function<T,Function<U,R>> curry(BiFunction<T,U,R> f) {
        return t -> u -> f.apply(t, u);
    }

    public static <T,U,R,V> Function<T,Function<U,Function<V,R>>> curry(TriFunction<T,U,V,R> f) {
        return t -> u -> v -> f.apply(t, u, v);
    }

}
