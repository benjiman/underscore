package uk.co.benjiweber.underscore;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static uk.co.benjiweber.underscore.MapMaker.$;
import static uk.co.benjiweber.underscore.MapMaker.$$;
import static uk.co.benjiweber.underscore.PropertyValue.prop;
import static uk.co.benjiweber.underscore.Underscore._;

public class CollectionFunctionsTest {

    @Test
    public void each() {
         List<Integer> eached = _();
        _(1,2,3).each(eached::add);
        assertEquals(_(1,2,3), eached);
    }

    @Test
    public void  map_list() {
        assertEquals(_(3, 6, 9), _(1, 2, 3).map(num -> num * 3));

    }

    @Test
    public void map_map() {
        assertEquals(_(3, 6, 9), _.map($$($("one", 1), $("two", 2), $("three", 3)), (num, key) -> num * 3));
    }

    @Test
    public void reduce() {
        Integer six = Integer.valueOf(6);
        assertEquals(six, _(1, 2, 3).reduce((memo, num) -> memo + num, 0));
    }

    @Test
    public void find() {
        Integer two = Integer.valueOf(2);
        assertEquals(two, _(1, 2, 3, 4, 5, 6).find(num -> num % 2 == 0).get());
    }

    @Test
    public void filter() {
        assertEquals(_(2, 4, 6), _(1, 2, 3, 4, 5, 6).filter(num -> num % 2 == 0));
    }


    @Test
    public void where() {
        Play cymbeline = new Play("Shakespeare", "Cymbeline",1611);
        Play theTempest = new Play("Shakespeare","The Tempest",1611);
        Play henryV = new Play("Shakespeare","History of Henry V", 1598);
        Play foo = new Play("foo", "foo", 1234);
        Play bar = new Play("bar","bar", 1234);

        assertEquals(
                _(cymbeline, theTempest),
                _(cymbeline, theTempest, henryV, foo, bar)
                    .where(prop(Play::author, "Shakespeare"), prop(Play::year, 1611))
        );

        assertEquals(
                _(foo, bar),
                _(cymbeline, theTempest, henryV, foo, bar)
                    .where(prop(Play::year, 1234))
        );
    }

    @Test
    public void findWhere() {
        Play cymbeline = new Play("Shakespeare", "Cymbeline",1611);
        Play theTempest = new Play("Shakespeare","The Tempest",1611);
        Play henryV = new Play("Shakespeare","History of Henry V", 1598);
        Play foo = new Play("foo", "foo", 1234);
        Play bar = new Play("bar","bar", 1234);

        assertEquals(
                cymbeline,
                _(cymbeline, theTempest, henryV, foo, bar)
                        .findWhere(prop(Play::author, "Shakespeare"), prop(Play::year, 1611))
                        .get()
        );
    }

    @Test
    public void reject() {
        assertEquals(_(1, 3, 5), _(1, 2, 3, 4, 5, 6).reject(num -> num % 2 == 0));
    }

    @Test
    public void every() {
        assertFalse(_(true, 1, null, "yes").every(Predicates.truthy()));
        assertTrue(_(true, 1, "yes").every(item -> item != null));
    }

    @Test
    public void some() {
        assertTrue(_(null, 0, "yes", false).some());
        assertTrue(_(null, 0, "yes", false).some(item -> "yes".equals(item)));
        assertFalse(_(null, 0, "yes", false).some(item -> "no".equals(item)));
    }

    @Test
    public void contains() {
        assertTrue(_(1, 2, 3).contains(3));
        assertFalse(_(1, 2, 3).contains(4));
    }

    @Test
    public void invoke() {
        assertFalse(_(new Pokeable(), new Pokeable()).all(Pokeable::wasPoked));
        assertTrue(_(new Pokeable(), new Pokeable()).invoke(Pokeable::poke).all(Pokeable::wasPoked));
    }

    @Test
    public void pluck() {
        Stooge moe = new Stooge("moe", 40);
        Stooge larry = new Stooge("larry", 50);
        Stooge curly = new Stooge("curly", 60);
        assertEquals(_("moe", "larry", "curly"), _(moe, larry, curly).pluck(Stooge::name));
    }

    @Test
    public void pluck_map() {
        Map<String, Object> moe = new HashMap<String, Object>(){{ put("name", "moe"); put("age", 40); }};
        Map<String, Object> larry= new HashMap<String, Object>(){{ put("name", "larry"); put("age", 50); }};
        Map<String, Object> curly = new HashMap<String, Object>(){{ put("name", "curly"); put("age", 60); }};

        assertEquals(_("moe", "larry", "curly"), _.pluck(_(moe, larry, curly),"name"));
    }

    @Test
    public void max() {
        Stooge moe = new Stooge("moe", 40);
        Stooge larry = new Stooge("larry", 50);
        Stooge curly = new Stooge("curly", 60);

        assertEquals(curly, _(moe, larry, curly).max(Stooge::age).get());
    }

    @Test
    public void max_without_criterion() {
        Integer one_thousand = Integer.valueOf(1_000);
        assertEquals(one_thousand, _(10, 5, 100, 2, 1000).max().get());
    }


    @Test
    public void min() {
        Integer two = Integer.valueOf(2);
        assertEquals(two, _(10, 5, 100, 2, 1000).min().get());
    }


    @Test
    public void min_with_criterion() {
        Stooge moe = new Stooge("moe", 40);
        Stooge larry = new Stooge("larry", 50);
        Stooge curly = new Stooge("curly", 60);

        assertEquals(moe, _(moe, larry, curly).min(Stooge::age).get());
    }

    @Test
    public void sortBy() {
        assertEquals(_(5, 4, 6, 3, 1, 2), _(1, 2, 3, 4, 5, 6).sortBy(num -> Math.sin(num)));
    }

    @Test
    public void sortBy_without_criterion() {
        assertEquals(_(1, 2, 3, 4, 5, 6), _(4, 5, 6, 1, 2, 3).sortBy());
    }

    @Test
    public void groupBy() {
        Map<Integer, List<String>> expected = $$($(3, _("one", "two").toList()), $(5, _("three").toList()));
        Map<Integer, List<String>> result = _("one", "two", "three").groupBy(String::length);
        assertEquals(expected, result);


    }

    @Test
    public void groupBy_from_list() {
        Map<Integer, List<Double>> byFloor = _(1.3, 2.1, 2.4).groupBy(num -> (int)Math.floor(num));
        assertEquals(
            $$($(1, _(1.3).toList()), $(2, _(2.1, 2.4).toList())),
            byFloor
        );
    }

    @Test
    public void indexBy() {
        Stooge moe = new Stooge("moe", 40);
        Stooge larry = new Stooge("larry", 50);
        Stooge curly = new Stooge("curly", 60);

        assertEquals(
            $$($(40, moe), $(50, larry), $(60, curly)),
            _(moe, larry, curly).indexBy(Stooge::age)
        );
    }

    @Test
    public void countBy() {
        assertEquals(
            $$($("odd", 3L), $("even", 2L)),
            _(1, 2, 3, 4, 5).countBy( num -> num % 2 == 0 ? "even": "odd")
        );
    }

    @Test
    public void shuffle() throws YouAreUnluckyException {
        List<Boolean> shuffled = IntStream.range(0, 100).boxed()
                .map(i -> Objects.equals(_(1, 2, 3, 4, 5, 6), _(1, 2, 3, 4, 5, 6).shuffle()))
                .collect(Collectors.<Boolean>toList());

        boolean alwaysInOriginalOrder = _(shuffled).all(value -> value == true);

        if (alwaysInOriginalOrder) throw new YouAreUnluckyException();

        assertFalse(alwaysInOriginalOrder);
    }

    @Test
    public void sample() throws YouAreUnluckyException {
        List<Boolean> shuffled = IntStream.range(0, 100).boxed()
                .map(i -> Objects.equals(_(1, 2, 3), _(1, 2, 3, 4, 5, 6).sample(3)))
                .collect(Collectors.<Boolean>toList());

        boolean alwaysFirstThree = _(shuffled).all(value -> value == true);

        if (alwaysFirstThree) throw new YouAreUnluckyException();

        assertFalse(alwaysFirstThree);
        assertEquals(3, _(1, 2, 3, 4, 5, 6).sample(3).size());
    }

    @Test
    public void toArray() {
        assertArrayEquals(Array.array(1, 2, 3), _(1, 2, 3).toArray(new Integer[0]));
    }

    @Test
    public void size() {
        assertEquals(3, _(1, 2, 3).size());
        assertEquals(1, _(1).size());
    }

    static class Pokeable {
        boolean poked = false;
        public void poke() { poked = true; }
        public boolean wasPoked() { return poked; }
    }


    private static class YouAreUnluckyException extends Exception {
    }
}
