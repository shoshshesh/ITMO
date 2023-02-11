package info.kgeorgiy.ja.bondarev.arrayset;

import java.util.*;

public class ArraySet<T> extends AbstractSet<T> implements SortedSet<T> {
    private final List<T> arraySet;
    private final Comparator<? super T> comparator;
    private final String UNSUPPORTED_OPERATION_MESSAGE = "Unsupported operation. ArraySet is an immutable collection.";
    private final String NO_SUCH_ELEMENT_MESSAGE = "This ArraySet is empty.";


    public ArraySet(Collection<? extends T> collection, Comparator<? super T> comparator) {
        TreeSet<T> temp = new TreeSet<>(comparator);
        temp.addAll(collection);
        this.arraySet = List.copyOf(temp);
        this.comparator = comparator;
    }

    public ArraySet() {
        this(Collections.emptyList(), null);
    }

    public ArraySet(Collection<? extends T> collection) {
        this(collection, null);
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @SuppressWarnings("unchecked")
    private int compare(T element1, T element2) {
        if (comparator == null) {
            return ((Comparable<T>) element1).compareTo(element2);
        } else {
            return comparator.compare(element1, element2);
        }
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        if (compare(fromElement, toElement) > 0) {
            throw new IllegalArgumentException("fromElement > toElement");
        }
        int start = getIndex(fromElement);
        int end = getIndex(toElement);
        return new ArraySet<>(arraySet.subList(start, end), comparator);
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return new ArraySet<>(arraySet.subList(0, getIndex(toElement)), comparator);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return new ArraySet<>(arraySet.subList(getIndex(fromElement), size()), comparator);
    }

    private int getIndex(T element) {
        int index = Collections.binarySearch(arraySet, element, comparator);
        if (index < 0) {
            index = (index + 1) * (-1);
        }
        return index;
    }

    @Override
    public T first() {
        if (size() == 0) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
        }
        return arraySet.get(0);
    }

    @Override
    public T last() {
        if (size() == 0) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
        }
        return arraySet.get(arraySet.size() - 1);
    }

    @Override
    public int size() {
        return arraySet.size();
    }

    @Override
    public boolean isEmpty() {
        return arraySet.isEmpty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        return Collections.binarySearch(arraySet, (T) o, comparator) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return arraySet.iterator();
    }

    @Override
    public Object[] toArray() {
        return arraySet.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return arraySet.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }
}
