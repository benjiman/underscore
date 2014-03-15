package uk.co.benjiweber.underscore;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface DelegatingList<T> extends List<T> {
    public List<T> unwrap();

    @Override
    default int size() {
        return unwrap().size();
    }

    @Override
    default boolean isEmpty() {
        return unwrap().isEmpty();
    }

    @Override
    default boolean contains(Object o) {
        return unwrap().contains(o);
    }

    @Override
    default Iterator<T> iterator() {
        return unwrap().iterator();
    }

    @Override
    default Object[] toArray() {
        return unwrap().toArray();
    }

    @Override
    default <T1 extends Object> T1[] toArray(T1[] a) {
        return unwrap().toArray(a);
    }

    @Override
    default boolean add(T t) {
        return unwrap().add(t);
    }

    @Override
    default boolean remove(Object o) {
        return unwrap().remove(o);
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        return unwrap().containsAll(c);
    }

    @Override
    default boolean addAll(Collection<? extends T> c) {
        return unwrap().addAll(c);
    }

    @Override
    default boolean addAll(int index, Collection<? extends T> c) {
        return unwrap().addAll(index, c);
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        return unwrap().removeAll(c);
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        return unwrap().retainAll(c);
    }

    @Override
    default void clear() {
        unwrap().clear();
    }

    @Override
    default T get(int i) {
        return unwrap().get(i);
    }

    @Override
    default T set(int index, T element) {
        return unwrap().set(index, element);
    }

    @Override
    default void add(int index, T element) {
        unwrap().add(index, element);
    }

    @Override
    default T remove(int index) {
        return unwrap().remove(index);
    }

    @Override
    default int indexOf(Object o) {
        return unwrap().indexOf(o);
    }

    @Override
    default int lastIndexOf(Object o) {
        return unwrap().lastIndexOf(o);
    }

    @Override
    default ListIterator<T> listIterator() {
        return unwrap().listIterator();
    }

    @Override
    default ListIterator<T> listIterator(int index) {
        return unwrap().listIterator(index);
    }

    @Override
    default List<T> subList(int fromIndex, int toIndex) {
        return unwrap().subList(fromIndex, toIndex);
    }
}
