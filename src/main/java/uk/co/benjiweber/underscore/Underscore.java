package uk.co.benjiweber.underscore;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.setAll;
import static java.util.stream.Collectors.toList;

public class Underscore<T> implements Iterable<T>, ListFunctions<T>, ArrayFunctions<T>, DelegatingList<T> {
    private final List<T> wrapped;

    public Underscore(List<T> wrapped) {
        this.wrapped = wrapped;
    }

    public static StaticUnderscore _ = new StaticUnderscore();

    public static <T> Underscore<T> _(T... in) {
        return new Underscore<T>(new ArrayList<>(asList(in)));
    }

    public static <T> Underscore<T> _(List<T> in) {
        return new Underscore<T>(in);
    }

    public static <T> Underscore<T> _(Set<T> in) {
        return new Underscore<T>(new ArrayList<>(in));
    }

    public static <T> Underscore<T> _(Collection<T> in) {
        return new Underscore<T>(new ArrayList<>(in));
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

    public List<T> unwrap() {
        return wrapped;
    }

    public Underscore<T> self() {
        return this;
    }
}
