package uk.co.benjiweber.underscore;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static uk.co.benjiweber.underscore.Underscore._;

public class StaticUnderscore {

    public static <T> List<T> each(List<T> ts, Consumer<T> f) {
        return _(ts).each(f).unwrap();
    }


    public static <K,V,R> Underscore<R> map(Map<K,V> map, BiFunction<V,K,R> f) {
        return _(map.entrySet().stream().map(entry -> f.apply(entry.getValue(), entry.getKey())));
    }

    public static <K,V> Underscore<V> pluck(List<Map<K,V>> maps, K key) {
        return _(maps.stream().map(map -> map.get(key)));
    }

    public static <T> Underscore<T> flatten(Underscore<List<T>> ts, boolean shallow) {
        if (!shallow) throw new UnsupportedOperationException("We can only do shallow flattening");

        return flatten(ts);
    }

    public static <T> Underscore<T> flatten(Underscore<List<T>> ts) {
        return _(ts.stream().flatMap(t -> t.stream()));
    }

    public static Underscore<Integer>  range(int stop) {
        return range(0, stop);
    }
    public static Underscore<Integer>  range(int start, int stop) {
        return range(start, stop, 1);
    }

    public static Underscore<Integer> range(int start, int stop, int step) {
        boolean inverse = stop < 0 && step < 0;
        return _(
                IntStream.range(start, abs(stop))
                        .map(i -> i == start ? start : i * abs(step))
                        .filter(i -> i < abs(stop))
                        .map(i -> inverse ? i * -1 : i)
                        .boxed()
                        .collect(Collectors.<Integer>toList())
        );
    }

}
