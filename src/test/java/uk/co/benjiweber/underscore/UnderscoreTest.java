package uk.co.benjiweber.underscore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.co.benjiweber.underscore.PropertyValue.prop;
import static uk.co.benjiweber.underscore.Underscore._;

public class UnderscoreTest {
    @Test
    public void testCompiles() {

    }

    static class Play {
        public final String author;
        public final String name;
        public final int year;

        Play(String author, String name, int year) {
            this.author = author;
            this.name = name;
            this.year = year;
        }

        public String author() { return author; }
        public int year() { return year; }

        public String toString() {
            return author + " - " + name + " - " + year;
        }
    }

    @Test
    public void where_test() {
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
}
