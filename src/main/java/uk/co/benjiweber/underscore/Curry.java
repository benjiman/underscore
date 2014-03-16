package uk.co.benjiweber.underscore;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Curry {

    default <T,U,R> Function<T,Function<U,R>> curry(BiFunction<T,U,R> f) {
        return t -> u -> f.apply(t, u);
    }

    default <T,U,V,R> Function<T,Function<U,Function<V,R>>> curry(TriFunction<T,U,V,R> f) {
        return t -> u -> v -> f.apply(t, u, v);
    }

    default <T,U,V,W,R> Function<T,Function<U,Function<V,Function<W,R>>>> curry(QuadFunction<T,U,V,W,R> f) {
        return t -> u -> v -> w -> f.apply(t, u, v, w);
    }

}
