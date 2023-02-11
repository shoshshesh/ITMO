package queue;

public class Main {

    public static void main(String[] args) {
        testModule();
        testADT();
        testClass();
    }

    public static void testModule() {
        System.out.println("Module test");
        fillArrayQueueModule();
        dumpArrayQueueModule();
        System.out.println();
    }

    public static void testADT() {
        System.out.println("ADT test");
        ArrayQueueADT queue = new ArrayQueueADT();
        fillArrayQueueADT(queue);
        dumpArrayQueueADT(queue);
        System.out.println();
    }

    public static void testClass() {
        System.out.println("Class test");
        ArrayQueue queue = new ArrayQueue();
        fillArrayQueue(queue);
        dumpArrayQueue(queue);
        System.out.println();
    }

    public static void fillArrayQueueModule() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i);
        }
        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.dequeue();
        }
        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dumpArrayQueueModule() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                    ArrayQueueModule.element() + " " +
                    ArrayQueueModule.dequeue());
        }
    }

    public static void fillArrayQueueADT(ArrayQueueADT queue) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
        for (int i = 0; i < 5; i++) {
            ArrayQueueADT.dequeue(queue);
        }
        for (int i = 0; i < 5; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
    }

    public static void dumpArrayQueueADT(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(
                    ArrayQueueADT.size(queue) + " " +
                            ArrayQueueADT.element(queue) + " " +
                            ArrayQueueADT.dequeue(queue)
            );
        }
    }

    private static void fillArrayQueue(ArrayQueue queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
        }
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
    }

    private static void dumpArrayQueue(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(queue.size() + " " + queue.element() + " " + queue.dequeue());
        }
    }


}
