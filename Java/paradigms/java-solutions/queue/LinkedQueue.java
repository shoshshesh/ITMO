package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head;
    private Node tail;

    @Override
    protected void clearImpl() {
        head.next = null;
        head = null;
        size = 0;
    }

    @Override
    protected void remove() {
        if (size == 1) {
           head.next = null;
           head = null;
        } else {
            head = head.next;
        }
    }

    @Override
    protected Object elementImpl() {
        return head.value;
    }

    @Override
    protected void enqueueImpl(Object element) {
        if (size == 0) {
            head = new Node(element,null);
            tail = head;
        } else {
            tail.next = new Node(element, null);
            tail = tail.next;
        }
    }

    @Override
    protected Object[] toArrayImpl(Object[] objects) {
        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            objects[i] = currentNode.value;
            currentNode = currentNode.next;
        }
        return objects;
    }

    private static class Node {
        private final Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;
            this.value = value;
            this.next = next;
        }
    }
}
