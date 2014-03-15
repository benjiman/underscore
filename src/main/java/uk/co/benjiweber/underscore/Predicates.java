package uk.co.benjiweber.underscore;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Predicates {
    public static <T> Predicate<T> all(Predicate<T>... predicates) {
        return all(Arrays.asList(predicates));
    }

    private static <T> Predicate<T> all(List<Predicate<T>> predicates) {
        if (predicates.size() == 0) return t -> true;
        if (predicates.size() == 1) return t -> predicates.get(0).test(t);
        return t -> predicates.get(0).test(t) && all(predicates.subList(1,predicates.size())).test(t);
    }

    public static <T> Predicate<T> falsey() {
        return t ->
               Objects.equals(t, false)
            || Objects.equals(t, null)
            || Objects.equals(t, 0)
            || Objects.equals(t, Float.NaN)
            || Objects.equals(t, Double.NaN)
            || Objects.equals(t, "");
    }

    public static <T> Predicate<T> contains(Collection<T> collection) {
        return t -> collection.contains(t);
    }

    public static <T> Predicate<T> truthy() {
        return Predicates.<T>falsey().negate();
    }

    public static Predicate<Integer> isEven() {
        return i -> i % 2 == 0;
    }

    public static Predicate<Integer> isOdd() {
        return isEven().negate();
    }
}
