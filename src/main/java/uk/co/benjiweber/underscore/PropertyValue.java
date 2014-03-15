package uk.co.benjiweber.underscore;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class PropertyValue<T,VALUE> implements Predicate<T> {
    public final Function<T,VALUE> accessor;
    public final VALUE comparisonValue;

    public PropertyValue(Function<T, VALUE> accessor, VALUE comparisonValue) {
        this.accessor = accessor;
        this.comparisonValue = comparisonValue;
    }

    @Override
    public boolean test(T t) {
        return Objects.equals(comparisonValue, accessor.apply(t));
    }

    public static <T,VALUE> PropertyValue<T,VALUE> prop(Function<T, VALUE> accessor, VALUE comparisonValue) {
        return new PropertyValue<T,VALUE>(accessor, comparisonValue);
    }
}
