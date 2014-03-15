package uk.co.benjiweber.underscore;

public interface Partitioned<T> {
    Underscore<T> matching();
    Underscore<T> notMatching();
}
