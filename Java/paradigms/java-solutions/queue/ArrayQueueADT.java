package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    private int size = 0;
    private Object[] elements = new Object[0];
    private int head = 0;
    private int tail = 0;

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue, queue.size+1);
        if (queue.tail == queue.elements.length) {
            queue.tail = 0;
        }
        queue.elements[queue.tail++] = element;
        queue.size++;
    }

    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.head];
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object result = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head++;
        if (queue.head == queue.elements.length) {
            queue.head = 0;
        }
        queue.size--;
        return result;
    }

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.head = 0;
        queue.tail = 0;
        queue.size = 0;
        Arrays.fill(queue.elements, null);
    }

    public static Object[] toArray(ArrayQueueADT queue) {
        Object[] newArray = new Object[queue.size];
        int current = queue.head;
        for (int i = 0; i < queue.size; i++) {
            newArray[i] = queue.elements[current++];
            if (current == queue.elements.length) {
                current = 0;
            }
        }
        return newArray;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (queue.size >= queue.elements.length) {
            queue.elements = toArray(queue);
            queue.elements = Arrays.copyOf(queue.elements, capacity * 2);
            queue.head = 0;
            queue.tail = queue.size;
        }
    }
}
