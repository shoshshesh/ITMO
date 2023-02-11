package queue;

import java.util.Arrays;

public class ArrayQueueModule {
    private static int size = 0;
    private static Object[] elements = new Object[0];
    private static int head = 0;
    private static int tail = 0;

    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size+1);
        if (tail == elements.length) {
            tail = 0;
        }
        elements[tail++] = element;
        size++;
    }

    public static Object dequeue() {
        assert size > 0;
        Object result = elements[head];
        elements[head] = null;
        head++;
        if (head == elements.length) {
            head = 0;
        }
        size--;
        return result;
    }

    public static Object element() {
        assert size > 0;
        return elements[head];
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void clear() {
        head = 0;
        tail = 0;
        size = 0;
        Arrays.fill(elements, null);
    }

    public static Object[] toArray() {
        Object[] newArray = new Object[size];
        int current = head;
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[current++];
            if (current == elements.length) {
                current = 0;
            }
        }
        return newArray;
    }

    private static void ensureCapacity(int capacity) {
        if (size >= elements.length) {
            elements = toArray();
            elements = Arrays.copyOf(elements, capacity* 2);
            head = 0;
            tail = size;
        }
    }
}
