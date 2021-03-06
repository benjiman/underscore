underscore - Underscore.js in Java 8
==========

Mostly as an exercise to learn more about Java 8 features.

Mapping the underscore.js API from http://underscorejs.org/ 

e.g.

# Partial application

```java
    @Test
    public void partial_placeholder() {
        TriFunction<String, String, String, String> concat = (a, b, c) -> a + b + c;
        BiFunction<String, String, String> plusSymbol = _.partial(concat, _, " + ", _);

        assertEquals("5 + 10", plusSymbol.apply("5", "10"));
    }
```
    
# Curry

```java
    @Test
    public void curry() {
        Integer fifteen = Integer.valueOf(15);

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        Function<Integer, Integer> add5 = _.curry(add).apply(5);

        assertEquals(fifteen, add5.apply(10));
    }
```

# Items where a property matches

```java
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
```

# Pluck 

```java
    @Test
    public void pluck() {
        Stooge moe = new Stooge("moe", 40);
        Stooge larry = new Stooge("larry", 50);
        Stooge curly = new Stooge("curly", 60);
        assertEquals(_("moe", "larry", "curly"), _(moe, larry, curly).pluck(Stooge::name));
    }
```

# Zip

```java
    @Test
    public void zip() {
        assertEquals(
            _(_("moe", 30, true), _("larry", 40, false), _("curly", 50, false)),
            _("moe", "larry", "curly").zip(_(30, 40, 50), _(true, false, false))
        );
    }
```
Other examples in the test cases

https://github.com/benjiman/underscore/blob/master/src/test/java/uk/co/benjiweber/underscore/ArrayFunctionsTest.java
https://github.com/benjiman/underscore/blob/master/src/test/java/uk/co/benjiweber/underscore/CollectionFunctionsTest.java
https://github.com/benjiman/underscore/blob/master/src/test/java/uk/co/benjiweber/underscore/FunctionFunctionsTest.java
