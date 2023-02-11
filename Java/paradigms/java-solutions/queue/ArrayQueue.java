package queue;

import java.util.Arrays;

public class ArrayQueue extends AbstractQueue{
    private Object[] elements = new Object[0];
    private int head = 0;
    private int tail = 0;

    @Override
    protected void clearImpl() {
        head = 0;
        tail = 0;
        size = 0;
        Arrays.fill(elements, null);
    }

    @Override
    protected void remove() {
        elements[head] = null;
        head++;
        if (head == elements.length) {
            head = 0;
        }
    }

    @Override
    protected Object elementImpl() {
        return elements[head];
    }

    @Override
    protected void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        if (tail == elements.length) {
            tail = 0;
        }
        elements[tail++] = element;
    }


    @Override
    protected Object[] toArrayImpl(Object[] objects) {
        int current = head;
        for (int i = 0; i < size; i++) {
            objects[i] = elements[current++];
            if (current == elements.length) {
                current = 0;
            }
        }
        return objects;
    }

    private void ensureCapacity(int capacity) {
        if (size >= elements.length) {
            elements = toArray();
            elements = Arrays.copyOf(elements, capacity * 2);
            head = 0;
            tail = size;
        }
    }
}
