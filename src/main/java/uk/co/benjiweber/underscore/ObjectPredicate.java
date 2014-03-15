package uk.co.benjiweber.underscore;

import java.util.function.Predicate;

public interface ObjectPredicate {
    public boolean matches(Object other);

    default ObjectPredicate negate() {
        return item -> !this.matches(item);
    }

    default <T> Predicate<T> toPredicate() {
        return item -> this.matches(item);
    }
}
