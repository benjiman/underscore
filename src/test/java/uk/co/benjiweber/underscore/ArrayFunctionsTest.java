package uk.co.benjiweber.underscore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.co.benjiweber.underscore.MapMaker.$;
import static uk.co.benjiweber.underscore.MapMaker.$$;
import static uk.co.benjiweber.underscore.Underscore._;

public class ArrayFunctionsTest {

    @Test
    public void first() {
        Integer five = Integer.valueOf(5);
        assertEquals(five, _(5, 4, 3, 2, 1).first().get());
    }

    @Test
    public void head() {
        Integer four = Integer.valueOf(4);
        assertEquals(four, _(4, 3, 2, 1).head().get());
    }

    @Test
    public void take() {
        Integer three = Integer.valueOf(3);
        assertEquals(three, _(3, 2, 1).take().get());
    }

    @Test
    public void initial() {
        assertEquals(_(5, 4, 3, 2), _(5, 4, 3, 2, 1).initial());
    }

    @Test
    public void initial_n() {
        assertEquals(_(5, 4, 3), _(5, 4, 3, 2, 1).initial(2));
    }

    @Test
    public void last() {
        Integer one = Integer.valueOf(1);
        assertEquals(one, _(5, 4, 3, 2, 1).last().get());
    }

    @Test
    public void last_n() {
        assertEquals(_(2, 1), _(5, 4, 3, 2, 1).last(2));
    }

    @Test
    public void rest() {
        assertEquals(_(4, 3, 2, 1), _(5, 4, 3, 2, 1).rest());
    }

    @Test
    public void rest_n() {
        assertEquals(_(3, 2, 1), _(5, 4, 3, 2, 1).rest(2));
    }

    @Test
    public void tail() {
        assertEquals(_(4, 3, 2, 1), _(5, 4, 3, 2, 1).tail());
    }

    @Test
    public void tail_n() {
        assertEquals(_(3, 2, 1), _(5, 4, 3, 2, 1).tail(2));
    }

    @Test
    public void drop() {
        assertEquals(_(4, 3, 2, 1), _(5, 4, 3, 2, 1).drop());
    }

    @Test
    public void drop_n() {
        assertEquals(_(3, 2, 1), _(5, 4, 3, 2, 1).drop(2));
    }

    @Test
    public void compact() {
        assertEquals(
            _(1, 2, 3),
            _(0, 1, false, 2, "", 3).compact()
        );
    }

    @Test
    public void flatten() {
        assertEquals(
            _(2, 3, 4),
            _.flatten(_(_(2), _(3, 4)))
        );
    }

    @Test
    public void without() {
        assertEquals(
            _(2, 3, 4),
            _(1, 2, 1, 0, 3, 1, 4).without(0, 1)
        );
    }

    @Test
    public void partition() {
        assertEquals(
            _(_(1, 3, 5), _(0, 2, 4)),
            _(0, 1, 2, 3, 4, 5).partition(Predicates.isOdd())
        );
    }

    @Test
    public void partitionTyped() {
        assertEquals(
            _(1, 3, 5),
            _(0, 1, 2, 3, 4, 5).partitionTyped(Predicates.isOdd()).matching()
        );
    }

    @Test
    public void union() {
        assertEquals(
            _(1, 2, 3, 101, 10),
            _(1, 2, 3).union(_(101, 10))
        );
    }

    @Test
    public void union_more() {
        assertEquals(
                _(1, 2, 3, 101, 10),
                _(1, 2, 3).union(_(101), _(10))
        );
    }

    @Test
    public void intersection() {
        assertEquals(
            _(1, 2),
            _(101, 2, 1, 10).intersection(_(2, 1), _(1, 2, 3), _(5, 4, 3, 2, 1))
        );
    }

    @Test
    public void difference() {
        assertEquals(
                _(1, 3, 4),
                _(1, 2, 3, 4, 5).difference(_(5, 2, 10))
        );
    }

    @Test
    public void difference_more() {
        assertEquals(
                _(1, 4),
                _(1, 2, 3, 4, 5).difference(_(5, 2, 10), _(3))
        );
    }

    @Test
    public void uniq() {
        assertEquals(
            _(1, 2, 3, 4),
            _(1, 2, 1, 3, 1, 4).uniq()
        );
    }

    @Test
    public void zip() {
        assertEquals(
            _(_("moe", 30, true), _("larry", 40, false), _("curly", 50, false)),
            _("moe", "larry", "curly").zip(_(30, 40, 50), _(true, false, false))
        );
    }

    @Test
    public void object() {
        assertEquals(
            $$($("moe", 30), $("larry", 40), $("curly", 50)),
            _("moe", "larry", "curly").object(_(30, 40, 50))
        );
    }

    @Test
    public void indexOf() {
        assertEquals(1, _(1, 2, 3).indexOf(2));
    }

    @Test
    public void lastIndexOf() {
        assertEquals(4, _(1, 2, 3, 1, 2, 3).lastIndexOf(2));
    }

    @Test
    public void sortedIndex() {
        assertEquals(3, _(10, 20, 30, 40, 50).sortedIndex(35));
    }

    @Test
    public void range() {
        assertEquals(
            _(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
            _.range(10)
        );
    }

    @Test
    public void range_from_to() {
        assertEquals(
            _(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            _.range(1, 11)
        );
    }

    @Test
    public void range_from_to_step() {
        assertEquals(
                _(0, 5, 10, 15, 20, 25),
                _.range(0, 30, 5)
        );
    }

    @Test
    public void range_from_to_step_negative() {
        assertEquals(
            _(0, -1, -2, -3, -4, -5, -6, -7, -8, -9),
            _.range(0, -10, -1)
        );
    }

    @Test
    public void range_empty() {
        assertEquals(
            _(),
            _.range(0)
        );
    }


}
