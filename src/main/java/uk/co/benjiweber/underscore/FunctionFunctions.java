package uk.co.benjiweber.underscore;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface FunctionFunctions {

    default <T,R> NoArg<R> partial(Function<T, R> f, T t) {
        return () -> f.apply(t);
    }

    default <T,U,R> NoArg<R> partial(BiFunction<T,U,R> f, T t, U u) {
        return () -> f.apply(t, u);
    }

    default <T,U,R> Function<U,R> partial(BiFunction<T,U,R> f, T t) {
        return u -> f.apply(t,u);
    }

    default <T,U,V,R> NoArg<R> partial(TriFunction<T,U,V,R> f, T t, U u, V v) {
        return () -> f.apply(t,u,v);
    }

    default <T,U,V,R> Function<T,R> partial(TriFunction<T,U,V,R> f, StaticUnderscore _, U u, V v) {
        return t -> f.apply(t, u, v);
    }

    default <T,U,V,R> Function<U,R> partial(TriFunction<T,U,V,R> f, T t, StaticUnderscore _, V v) {
        return u -> f.apply(t, u, v);
    }

    default <T,U,V,R> Function<V,R> partial(TriFunction<T,U,V,R> f, T t, U u) {
        return v -> f.apply(t,u,v);
    }

    default <T,U,V,R> Function<V,R> partial(TriFunction<T,U,V,R> f, T t, U u, StaticUnderscore _) {
        return v -> f.apply(t,u,v);
    }

    default <T,U,V,R> BiFunction<T,U,R> partial(TriFunction<T,U,V,R> f, StaticUnderscore _1, StaticUnderscore _2, V v) {
        return (t,u) -> f.apply(t, u, v);
    }

    default <T,U,V,R> BiFunction<U,V,R> partial(TriFunction<T,U,V,R> f, T t) {
        return (u,v) -> f.apply(t,u,v);
    }

    default <T,U,V,R> BiFunction<U,V,R> partial(TriFunction<T,U,V,R> f, T t, StaticUnderscore _1, StaticUnderscore _2) {
        return (u,v) -> f.apply(t,u,v);
    }

    default <T,U,V,R> BiFunction<T,V,R> partial(TriFunction<T,U,V,R> f, StaticUnderscore _1, U u, StaticUnderscore _2) {
        return (t,v) -> f.apply(t,u,v);
    }

}
