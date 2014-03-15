package uk.co.benjiweber.underscore;

import java.util.Arrays;
import java.util.List;
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
}
