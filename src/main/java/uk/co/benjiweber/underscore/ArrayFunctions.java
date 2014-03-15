package uk.co.benjiweber.underscore;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static uk.co.benjiweber.underscore.Underscore._;

public interface ArrayFunctions<T> {
    List<T> unwrap();

    default Optional<T> first() {
        return unwrap().size() > 0 ? Optional.of(unwrap().get(0)) : Optional.<T>empty();
    }

    default Optional<T> head() {
        return first();
    }

    default Optional<T> take() {
        return first();
    }

    default Underscore<T> initial() {
        return initial(1);
    }

    public static <T> Underscore<T> empty() {
        return _(Collections.<T>emptyList());
    }

    default Underscore<T> initial(int n) {
        if (unwrap().size() < n) return empty();
        return _(unwrap().subList(0, unwrap().size() - n));
    }

    default Optional<T> last() {
        if (unwrap().size() < 1) return Optional.empty();
        return Optional.of(unwrap().get(unwrap().size() - 1));
    }

    default Underscore<T> last(int n) {
        if (unwrap().size() < n) return empty();
        return _(unwrap().subList(unwrap().size() - n, unwrap().size()));
    }

    default Underscore<T> drop() {
        return rest();
    }

    default Underscore<T> drop(int n) {
        return rest(n);
    }

    default Underscore<T> tail() {
        return rest();
    }

    default Underscore<T> tail(int n) {
        return rest(n);
    }

    default Underscore<T> rest() {
        return rest(1);
    }

    default Underscore<T> rest(int n) {
        if (unwrap().size() < n) return empty();
        return _(unwrap().subList(n, unwrap().size()));
    }

    default Underscore<T> compact() {
        return _(unwrap().stream().filter(Predicates.truthy()));
    }

    public static <T> Underscore<T> flatten(Underscore<List<T>> ts, boolean shallow) {
        if (!shallow) throw new UnsupportedOperationException("We can only do shallow flattening");

        return flatten(ts);
    }

    public static <T> Underscore<T> flatten(Underscore<List<T>> ts) {
        return _(ts.stream().flatMap(t -> t.stream()));
    }

    default Underscore<T> without(T... ts) {
        List<T> without = Arrays.asList(ts);
        return _(unwrap().stream().filter(t -> !without.contains(t)));
    }

    default Partitioned<T> partitionTyped(Predicate<T> predicate) {
        return new Partitioned<T>() {
            public Underscore<T> matching() {
                return _(unwrap().stream().filter(predicate));
            }

            public Underscore<T> notMatching() {
                return _(unwrap().stream().filter(predicate.negate()));
            }
        };
    }

    default Underscore<Underscore<T>> partition(Predicate<T> predicate) {
        Partitioned<T> partitioned = partitionTyped(predicate);
        return _(partitioned.matching(), partitioned.notMatching());
    }

    default Underscore<T> self() {
        return _(unwrap());
    }

    default Underscore<T> push(T t) {
        List<T> newList = new ArrayList<>();
        newList.addAll(unwrap());
        newList.add(t);
        return _(newList);
    }

    default Underscore<T> append(T t) {
        return push(t);
    }

    default Underscore<T> prepend(T t) {
        List<T> newList = new ArrayList<>();
        newList.add(t);
        newList.addAll(unwrap());
        return _(newList);
    }

    default Underscore<T> union(Underscore<T> t, Underscore<T>... ts) {
        return union(self(), _(ts).prepend(t));
    }

    default Underscore<T> union(Underscore<T> head ,Iterable<Underscore<T>> ts) {
        Set<T> unioned = new LinkedHashSet<>();
        unioned.addAll(head.unwrap());
        ts.forEach(t -> {
            unioned.addAll(t.unwrap());
        });
        return _(unioned.stream());
    }

    default Underscore<T> intersection(Underscore<T>... ts) {
        return intersection(self(), Arrays.asList(ts));
    }

    public static <T> Underscore<T> intersection(Underscore<T> head, Iterable<Underscore<T>> ts) {
        List<Underscore<T>> tail = new ArrayList<>();
        ts.forEach(tail::add);
        return intersection(head, tail);
    }

    public static <T> Underscore<T> intersection(Underscore<T> head, List<Underscore<T>> ts) {
        return merge(ArrayFunctions::intersectionTogether, head, ts);
    }

    public static <T> Underscore<T> intersection(Underscore<T> one, Underscore<T> two) {
        return intersectionTogether(one, two);
    }

    public static <T> Underscore<T> intersectionTogether(Underscore<T> one, Underscore<T> two) {
        return _(two.unwrap().stream().filter(one::contains));
    }

    default Underscore<T> difference(Underscore<T>... ts) {
        return difference(self(), Arrays.asList(ts));
    }

    public default Underscore<T> difference(Underscore<T> head, Iterable<Underscore<T>> ts) {
        List<Underscore<T>> tail = new ArrayList<>();
        ts.forEach(tail::add);
        return difference(head, tail);
    }

    default Underscore<T> reverse() {
        ArrayList<T> ts = new ArrayList<>(unwrap());
        Collections.reverse(ts);
        return _(ts);
    }

    default Underscore<T> difference(Underscore<T> head, List<Underscore<T>> ts) {
        if (ts.size() < 1) return head;
        if (ts.size() == 1) return differenceTogether(head, ts.get(0));
        return difference(differenceTogether(head, ts.get(0)),_(ts).tail());
    }

    public static <T> Underscore<T> differenceTogether(Underscore<T> one, Underscore<T> two) {
        return _(one.unwrap().stream().filter(t -> !two.contains(t)));
    }

    default Underscore<T> uniq() {
        return _(unwrap().stream().collect(Collectors.<T>toSet()));
    }

    default Underscore<Underscore<?>> zip(Underscore<?>... ts) {
        return zip(self(), Arrays.asList(ts));
    }

    public static Underscore<Underscore<?>> zip(Underscore<?> head, List<Underscore<?>> tail) {
        tail.forEach(list -> {
            if (list.size() != head.size()) throw new IllegalArgumentException("Lists must be the same size");
        });
        List<List<?>> zipped = new ArrayList<>();
        for (int i = 0; i < head.unwrap().size(); i++) {
            final int pos = i;
            ArrayList row = new ArrayList<>();
            row.add(head.unwrap().get(i));
            tail.forEach(list -> {
                row.add(list.unwrap().get(pos));
            });
            zipped.add(row);
        }
        return _(zipped.stream().map(Underscore::_));
    }

    public static <T> Underscore<T> merge(BiFunction<Underscore<T>, Underscore<T>, Underscore<T>> op, Underscore<T> head, List<Underscore<T>> ts) {
        if (ts.size() < 1) return head;
        if (ts.size() == 1) return op.apply(head, ts.get(0));
        return op.apply(head, op.apply(ts.get(0), ts.get(1)));
    }

    public static <T,R> Map<T,R> object(List<T> keys, List<R> values) {
        return _(keys).object(values);
    }

    default <R> Map<T,R> object(List<R> values) {
        Map<T,R> map = new HashMap<>();
        for (int i = 0; i < unwrap().size() && i < values.size(); i++) {
            map.put(unwrap().get(i), values.get(i));
        }
        return map;
    }

    default int sortedIndex(T value) {
        ArrayList<T> sorted = new ArrayList<>(unwrap());
        sorted.add(value);
        return sorted.stream().sorted().collect(Collectors.toList()).indexOf(value);
    }

}