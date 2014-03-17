package uk.co.benjiweber.underscore;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uk.co.benjiweber.underscore.MapMaker.$;
import static uk.co.benjiweber.underscore.Underscore._;

public interface ListFunctions<T> extends Iterable<T> {

    List<T> unwrap();

    default Underscore<T> each(Consumer<T> onEach) {
        forEach(onEach);
        return _(unwrap());
    }

    default<R> Underscore<R> map(Function<T,R> mapper) {
        return _(unwrap().stream().map(mapper).collect(Collectors.<R>toList()));
    }

    default<R> Underscore<R> collect(Function<T,R> mapper) {
        return map(mapper);
    }

    default Optional<T> reduce(BinaryOperator<T> reducer) {
        return unwrap().stream().reduce(reducer);
    }

    default T reduce(BinaryOperator<T> reducer, T identity) {
        return unwrap().stream().reduce(identity, reducer);
    }

    default Optional<T> inject(BinaryOperator<T> reducer) {
        return reduce(reducer);
    }

    default Optional<T> foldl(BinaryOperator<T> reducer) {
        return reduce(reducer);
    }

    default Optional<T> find(Predicate<T> predicate) {
        return unwrap().stream().filter(predicate).findFirst();
    }

    default Optional<T> detect(Predicate<T> predicate) {
        return find(predicate);
    }

    default Underscore<T> filter(Predicate<T> predicate) {
        return _(unwrap().stream().filter(predicate).collect(Collectors.<T>toList()));
    }

    default Underscore<T> select(Predicate<T> predicate) {
        return filter(predicate);
    }

    default Underscore<T> where(PropertyValue<T,?>... values) {
        return filter(Predicates.all(values));
    }

    default Optional<T> findWhere(PropertyValue<T,?>... values) {
        return where(values).stream().findFirst();
    }

    default Underscore<T> reject(Predicate<T> predicate) {
        return filter(predicate.negate());
    }

    default boolean every(Predicate<T> predicate) {
        return unwrap().stream().allMatch(predicate);
    }

    default boolean all(Predicate<T> predicate) {
        return every(predicate);
    }

    default boolean some(Predicate<T> predicate) {
        return unwrap().stream().anyMatch(predicate);
    }

    default boolean some() {
        return some(Predicates.<T>truthy());
    }

    default boolean any(Predicate<T> predicate) {
        return some(predicate);
    }

    default boolean include(T t) {
        return unwrap().contains(t);
    }

    default Underscore<T> invoke(Consumer<T> noArgs) {
        unwrap().forEach(noArgs);
        return self();
    }

    default Underscore<T> self() {
        return _(unwrap());
    }

    default <U> Underscore<T> invoke(BiConsumer<T,U> oneArg, U arg1) {
        unwrap().stream().forEach(value -> oneArg.accept(value, arg1));
        return self();
    }

    default <U,V> Underscore<T> invoke(TriConsumer<T,U,V> twoArg, U arg1, V arg2) {
        unwrap().stream().forEach(value -> twoArg.accept(value, arg1, arg2));
        return self();
    }

    default <U,V,W> Underscore<T> invoke(QuadConsumer<T,U,V,W> threeArg, U arg1, V arg2, W arg3) {
        unwrap().stream().forEach(value -> threeArg.accept(value, arg1, arg2, arg3));
        return self();
    }

    default <R> Underscore<R> pluck(Function<T,R> property) {
        return _(unwrap().stream().map(property).collect(Collectors.<R>toList()));
    }

    default <R extends Comparable<R>> Optional<T> max() {
        return max(v -> (Comparable) v);
    }

    default <R extends Comparable<R>> Optional<T> max(Function<T, R> criterion) {
        return unwrap().stream().max(Comparator.comparing(criterion));
    }

    default Optional<T> min() {
        return min(v -> (Comparable) v);
    }

    default <R extends Comparable<R>> Optional<T> min(Function<T,R> criterion) {
        return unwrap().stream().min(Comparator.comparing(criterion));
    }

    default <R extends Comparable<R>> Underscore<T> sortBy() {
        return sortBy(v -> (Comparable)v);
    }

    default <R extends Comparable<R>> Underscore<T> sort() {
        return sortBy(v -> (Comparable) v);
    }

    default <R extends Comparable<R>> Underscore<T> sortBy(Function<T,R> criterion) {
        return _(unwrap().stream().sorted(Comparator.<T, R>comparing(criterion)));
    }

    default <R> Map<R, List<T>> groupBy(Function<T, R> supplier) {
        return unwrap().stream().collect(Collectors.groupingBy(supplier));
    }

    default <R> Map<R, T> indexBy(Function<T, R> supplier) {
        return unwrap().stream().collect(Collectors.toMap(supplier, Function.<T>identity()));
    }

    default <R> Map<R, Long> countBy(Function<T,R> classifier) {
        return unwrap().stream().collect(Collectors.groupingBy(classifier, Collectors.counting()));
    }

    default Underscore<T> shuffle() {
        ArrayList<T> shuffled = new ArrayList<>(this.unwrap());
        Collections.shuffle(shuffled);
        return _(shuffled);
    }

    default Underscore<T> sample(int num) {
        return _(shuffle().unwrap().subList(0, num));
    }



}
