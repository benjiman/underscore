package uk.co.benjiweber.underscore;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.setAll;
import static java.util.stream.Collectors.toList;

public class Underscore<T> implements Iterable<T> {
    private final List<T> wrapped;

    public Underscore(List<T> wrapped) {
        this.wrapped = wrapped;
    }

    public static <T> Underscore<T> _(T... in) {
        return new Underscore<T>(asList(in));
    }

    public static <T> Underscore<T> _(List<T> in) {
        return new Underscore<T>(in);
    }

    public static <T> Underscore<T> _(Stream<T> in) {
        return new Underscore<T>(in.collect(Collectors.<T>toList()));
    }

    @Override
    public Iterator<T> iterator() {
        return wrapped.iterator();
    }

    public List<T> toList() {
        return wrapped;
    }

    public Underscore<T> each(Consumer<T> onEach) {
        wrapped.forEach(onEach);
        return this;
    }

    public <R> Underscore<R> map(Function<T,R> mapper) {
        return new Underscore<R>(wrapped.stream().map(mapper).collect(Collectors.<R>toList()));
    }

    public Optional<T> reduce(BinaryOperator<T> reducer) {
        return wrapped.stream().reduce(reducer);
    }

    public Optional<T> find(Predicate<T> predicate) {
        return wrapped.stream().filter(predicate).findFirst();
    }

    public Underscore<T> filter(Predicate<T> predicate) {
        return new Underscore<T>(wrapped.stream().filter(predicate).collect(Collectors.<T>toList()));
    }

    public Underscore<T> where(PropertyValue<T,?>... values) {
        return filter(Predicates.all(values));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Underscore) {
            Underscore<T> _other = (Underscore<T>)other;
            return toList().equals(_other.toList());
        }
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    public Stream<T> stream() {
        return wrapped.stream();
    }
}
