package queue;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    public Object dequeue() {
        assert size > 0;
        Object result = element();
        remove();
        size--;
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        clearImpl();
    }

    public Object[] toArray() {
        Object[] newArray = new Object[size];
        return toArrayImpl(newArray);
    }

    protected abstract Object[] toArrayImpl(Object[] objects);

    protected abstract void clearImpl();

    protected abstract void remove();

    protected abstract Object elementImpl();

    protected abstract void enqueueImpl(Object element);
}