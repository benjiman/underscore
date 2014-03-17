package uk.co.benjiweber.underscore;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static uk.co.benjiweber.underscore.Underscore._;

public class StaticUnderscore implements FunctionFunctions {

    public <T,K,V,R> Underscore<R> map(Map<K,V> map, BiFunction<V,K,R> f) {
        return _(map.entrySet().stream().map(entry -> f.apply(entry.getValue(), entry.getKey())));
    }

    public <T,K,V> Underscore<V> pluck(List<Map<K,V>> maps, K key) {
        return _(maps.stream().map(map -> map.get(key)));
    }

    public <T> Underscore<T> flatten(Underscore<List<T>> ts, boolean shallow) {
        if (!shallow) throw new UnsupportedOperationException("We can only do shallow flattening");

        return flatten(ts);
    }

    public <T> Underscore<T> flatten(Underscore<List<T>> ts) {
        return _(ts.stream().flatMap(t -> t.stream()));
    }

    public Underscore<Integer>  range(int stop) {
        return range(0, stop);
    }
    public Underscore<Integer>  range(int start, int stop) {
        return range(start, stop, 1);
    }

    public Underscore<Integer> range(int start, int stop, int step) {
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
    
    
    public <T> Underscore<T> each(List<T> ts, Consumer<T> onEach) {
        return _(ts).each(onEach);
    }

    public <T,R> Underscore<R> map(List<T> ts, Function<T,R> mapper) {
        return _(ts).map(mapper);
    }

    public <T,R> Underscore<R> collect(List<T> ts, Function<T,R> mapper) {
        return _(ts).collect(mapper);
    }

    public <T>  Optional<T> reduce(List<T> ts, BinaryOperator<T> reducer) {
        return _(ts).reduce(reducer);
    }

    public <T>  T reduce(List<T> ts, BinaryOperator<T> reducer, T identity) {
        return _(ts).reduce(reducer, identity);
    }

    public <T>  Optional<T> inject(List<T> ts, BinaryOperator<T> reducer) {
        return _(ts).inject(reducer);
    }

    public <T>  Optional<T> foldl(List<T> ts, BinaryOperator<T> reducer) {
        return _(ts).foldl(reducer);
    }

    public <T>  Optional<T> find(List<T> ts, Predicate<T> predicate) {
        return _(ts).find(predicate);
    }

    public <T>  Optional<T> detect(List<T> ts, Predicate<T> predicate) {
        return _(ts).detect(predicate);
    }

    public <T>  Underscore<T> filter(List<T> ts, Predicate<T> predicate) {
        return _(ts).filter(predicate);
    }

    public <T>  Underscore<T> select(List<T> ts, Predicate<T> predicate) {
        return _(ts).select(predicate);
    }

    public <T>  Underscore<T> where(List<T> ts, PropertyValue<T,?>... values) {
        return _(ts).where(values);
    }

    public <T>  Optional<T> findWhere(List<T> ts, PropertyValue<T,?>... values) {
        return _(ts).findWhere(values);
    }

    public <T>  Underscore<T> reject(List<T> ts, Predicate<T> predicate) {
        return _(ts).reject(predicate);
    }

    public <T>  boolean every(List<T> ts, Predicate<T> predicate) {
        return _(ts).every(predicate);
    }

    public <T>  boolean all(List<T> ts, Predicate<T> predicate) {
        return _(ts).all(predicate);
    }

    public <T>  boolean some(List<T> ts, Predicate<T> predicate) {
        return _(ts).some(predicate);
    }

    public <T>  boolean some(List<T> ts) {
        return _(ts).some();
    }

    public <T>  boolean any(List<T> ts, Predicate<T> predicate) {
        return _(ts).any(predicate);
    }

    public <T>  boolean include(List<T> ts, T t) {
        return _(ts).include(t);
    }

    public <T>  Underscore<T> invoke(List<T> ts, Consumer<T> noArgs) {
        return _(ts).invoke(noArgs);
    }

    public <T,U> Underscore<T> invoke(List<T> ts, BiConsumer<T,U> oneArg, U arg1) {
        return _(ts).invoke(oneArg, arg1);
    }

    public <T,U,V> Underscore<T> invoke(List<T> ts, TriConsumer<T,U,V> twoArg, U arg1, V arg2) {
        return _(ts).invoke(twoArg, arg1, arg2);
    }

    public <T,U,V,W> Underscore<T> invoke(List<T> ts, QuadConsumer<T,U,V,W> threeArg, U arg1, V arg2, W arg3) {
        return _(ts).invoke(threeArg, arg1, arg2, arg3);
    }

    public <T,R> Underscore<R> pluck(List<T> ts, Function<T,R> property) {
        return _(ts).pluck(property);
    }

    public <T,R extends Comparable<R>> Optional<T> max(List<T> ts) {
        return _(ts).max();
    }

    public <T,R extends Comparable<R>> Optional<T> max(List<T> ts, Function<T, R> criterion) {
        return _(ts).max(criterion);
    }

    public <T>  Optional<T> min(List<T> ts) {
        return _(ts).min();
    }

    public <T,R extends Comparable<R>> Optional<T> min(List<T> ts, Function<T,R> criterion) {
        return _(ts).min(criterion);
    }

    public <T,R extends Comparable<R>> Underscore<T> sortBy(List<T> ts) {
        return _(ts).sortBy();
    }

    public <T,R extends Comparable<R>> Underscore<T> sort(List<T> ts) {
        return _(ts).sort();
    }

    public <T,R extends Comparable<R>> Underscore<T> sortBy(List<T> ts, Function<T,R> criterion) {
        return _(ts).sortBy(criterion);
    }

    public <T,R> Map<R, List<T>> groupBy(List<T> ts, Function<T, R> supplier) {
        return _(ts).groupBy(supplier);
    }

    public <T,R> Map<R, T> indexBy(List<T> ts, Function<T, R> supplier) {
        return _(ts).indexBy(supplier);
    }

    public <T,R> Map<R, Long> countBy(List<T> ts, Function<T,R> classifier) {
        return _(ts).countBy(classifier);
    }

    public <T>  Underscore<T> shuffle(List<T> ts) {
        return _(ts).shuffle();
    }

    public <T>  Underscore<T> sample(List<T> ts, int num) {
        return _(ts).sample(num);
    }

    public <T> Optional<T> first(List<T> ts) {
        return _(ts).first();
    }

    public <T> Optional<T> head(List<T> ts) {
        return _(ts).head();
    }

    public <T> Optional<T> take(List<T> ts) {
        return _(ts).take();
    }

    public <T> Underscore<T> initial(List<T> ts) {
        return _(ts).initial();
    }

    public <T> Underscore<T> initial(List<T> ts, int n) {
        return _(ts).initial(n);
    }

    public <T> Optional<T> last(List<T> ts) {
        return _(ts).last();
    }

    public <T> Underscore<T> last(List<T> ts, int n) {
        return _(ts).last(n);
    }

    public <T> Underscore<T> drop(List<T> ts) {
        return _(ts).drop();
    }

    public <T> Underscore<T> drop(List<T> ts, int n) {
        return _(ts).drop(n);
    }

    public <T> Underscore<T> tail(List<T> ts) {
        return _(ts).tail();
    }

    public <T> Underscore<T> tail(List<T> ts, int n) {
        return _(ts).tail(n);
    }

    public <T> Underscore<T> rest(List<T> ts) {
        return _(ts).rest();
    }

    public <T> Underscore<T> rest(List<T> ts, int n) {
        return _(ts).rest(n);
    }

    public <T> Underscore<T> compact(List<T> ts) {
        return _(ts).compact();
    }

    public <T> List<T> without(List<T> ts, T... others) {
        return _(ts).without(others);
    }

    public <T> Partitioned<T> partitionTyped(List<T> ts, Predicate<T> predicate) {
        return _(ts).partitionTyped(predicate);
    }

    public <T> Underscore<Underscore<T>> partition(List<T> ts, Predicate<T> predicate) {
        return _(ts).partition(predicate);
    }

    public <T> Underscore<T> push(List<T> ts, T t) {
        return _(ts).push(t);
    }

    public <T> Underscore<T> append(List<T> ts, T t) {
        return _(ts).append(t);
    }

    public <T> Underscore<T> prepend(List<T> ts, T t) {
        return _(ts).prepend(t);
    }

    public <T> Underscore<T> union(List<T> ts, Underscore<T> t, Underscore<T>... others) {
        return _(ts).union(t, others);
    }

    public <T> List<T> union(List<T> ts, Underscore<T> head ,Underscore<Underscore<T>> others) {
        return _(ts).union(head, others);
    }

    public <T> Underscore<T> intersection(List<T> ts, Underscore<T>... others) {
        return _(ts).intersection(others);
    }

    public <T> Underscore<T> difference(List<T> ts, Underscore<T>... others) {
        return _(ts).difference(others);
    }

    public <T> Underscore<T> difference(List<T> ts, Underscore<T> head, List<Underscore<T>> others) {
        return _(ts).difference(head, others);
    }

    public <T> Underscore<T> reverse(List<T> ts) {
        return _(ts).reverse();
    }

    public <T> Underscore<T> uniq(List<T> ts) {
        return _(ts).uniq();
    }

    public <T> Underscore<Underscore<?>> zip(List<T> ts, Underscore<?>... others) {
        return _(ts).zip(others);
    }

    public <T,R> Map<T,R> object(List<T> ts, List<R> values) {
        return _(ts).object(values);
    }

    public <T> int sortedIndex(List<T> ts, T value) {
        return _(ts).sortedIndex(value);
    }
}
