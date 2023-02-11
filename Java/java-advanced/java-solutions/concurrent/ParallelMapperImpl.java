package info.kgeorgiy.ja.bondarev.concurrent;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Class implementing {@link ParallelMapper}.
 * Maps function over specified arguments. Mapping for each element performs in parallel.
 *
 * @author Bondarev Nikita
 */
public class ParallelMapperImpl implements ParallelMapper {

    private final Thread[] arrayOfThreads;
    private final List<Runnable> taskQueue;

    /**
     * Constructor for creating instances of {@link ParallelMapperImpl}.
     * @param threads number of {@link Thread threads}
     * which will concurrently map function {@code f} over arguments {@code args} given in {@link #map(Function, List)}.
     */
    public ParallelMapperImpl(int threads) {
        arrayOfThreads = new Thread[threads];
        taskQueue = new LinkedList<>();
        Runnable codeOfThread = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    getTask().run();
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        for (int i = 0; i < threads; i++) {
            arrayOfThreads[i] = new Thread(codeOfThread);
            arrayOfThreads[i].start();
        }
    }

    private Runnable getTask() throws InterruptedException {
        synchronized (taskQueue) {
            while (taskQueue.isEmpty()) {
                taskQueue.wait();
            }
            return taskQueue.remove(0);
        }
    }

    @Override
    public <T, R> List<R> map(Function<? super T, ? extends R> f, List<? extends T> args) throws InterruptedException {
        Container<R> result = new Container<>(args.size());
        for (int i = 0; i < args.size(); i++) {
            int finalI = i;
            synchronized (taskQueue) {
                taskQueue.add(() -> result.put(finalI, f.apply(args.get(finalI))));
                taskQueue.notify();
            }
        }
        return result.getMappedValues();
    }

    @Override
    public void close() {
        for (Thread thread : arrayOfThreads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                // e is ignored
            }
        }
    }


    private static class Container<T> {
        private final ArrayList<T> mappedValues = new ArrayList<>();
        private final int totalAmount;
        private int counter = 0;

        private Container(int totalAmount) {
            this.totalAmount = totalAmount;
            for (int i = 0; i < totalAmount; i++) {
                mappedValues.add(null);
            }
        }

        private synchronized void put(int index, T value) {
            mappedValues.set(index, value);
            counter++;
            if (counter == totalAmount) {
                notify();
            }
        }

        private synchronized ArrayList<T> getMappedValues() throws InterruptedException {
            while (counter < totalAmount) {
                wait();
            }
            return mappedValues;
        }
    }
}