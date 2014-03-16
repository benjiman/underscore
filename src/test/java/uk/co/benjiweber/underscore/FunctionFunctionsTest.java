package uk.co.benjiweber.underscore;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static uk.co.benjiweber.underscore.Underscore._;

public class FunctionFunctionsTest {
    @Test
    public void partial() {
        Integer fifteen = Integer.valueOf(15);

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        Function<Integer, Integer> add5 = _.partial(add, 5);

        assertEquals(fifteen, add5.apply(10));
    }

    @Test
    public void partial_placeholder() {
        TriFunction<String, String, String, String> concat = (a, b, c) -> a + b + c;
        BiFunction<String, String, String> plusSymbol = _.partial(concat, _, " + ", _);

        assertEquals("5 + 10", plusSymbol.apply("5", "10"));
    }

    @Test
    public void partial_placeholder_append() {
        TriFunction<String, String, String, String> concat = (a, b, c) -> a + b + c;
        BiFunction<String, String, String> appendBaz = _.partial(concat, _, _, "baz");

        assertEquals("foobarbaz", appendBaz.apply("foo", "bar"));
    }
}
