package info.kgeorgiy.ja.bondarev.concurrent;

import info.kgeorgiy.java.advanced.concurrent.ScalarIP;
import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Implementation of ScalarIP interface. Parallelizes finding maximum and minimum in given list,
 * checking whether all values satisfies predicate in given list or at least one of them.
 *
 * @author Nikita Bondarev
 */
public class IterativeParallelism implements ScalarIP {

    private final ParallelMapper parallelMapper;

    /**
     * Constructor for creating instances of IterativeParallelism with given instance of {@link ParallelMapper}.
     * @param parallelMapper mapper that will be used for parallelization.
     */
    public IterativeParallelism(ParallelMapper parallelMapper) {
        this.parallelMapper = parallelMapper;
    }

    /**
     * Constructor for creating instances of IterativeParallelism
     * with not initialised instance of {@link ParallelMapper}. Parallelization will be implemented by splitting
     * arguments into several parts and concurrently applying function on them.
     */
    public IterativeParallelism() {
        this.parallelMapper = null;
    }

    private static <T> List<List<? extends T>> split(int lists, List<? extends T> values) {
        int valuesInOneList = values.size() / lists;
        int odd = values.size() % lists;
        List<List<? extends T>> splitLists = new ArrayList<>(lists);
        int start = 0;
        for (int i = 0; i < lists; i++) {
            int howManyToAdd = valuesInOneList + (odd-- <= 0 ? 0 : 1);
            splitLists.add(values.subList(start, start + howManyToAdd));
            start = start + howManyToAdd;
        }
        return splitLists;
    }

    private <T, E> List<E> parallelize(int threads, List<? extends T> values, Function<List<? extends T>, E> function)
            throws InterruptedException {
        threads = Math.min(threads, values.size());
        List<List<? extends T>> splitValues = split(threads, values);
        if (parallelMapper == null) {
            Thread[] threadsArray = new Thread[threads];
            List<E> result = new ArrayList<>(threads);
            for (int i = 0; i < threads; i++) {
                result.add(null);
                int finalI = i;
                threadsArray[i] = new Thread(() -> result.set(finalI, function.apply(splitValues.get(finalI))));
                threadsArray[i].start();
            }

            for (int i = 0; i < threads; i++) {
                threadsArray[i].join();
            }

            return result;
        }
        return parallelMapper.map(function, splitValues);
    }

    /**
     * Returns maximum value.
     *
     * @param threads number or concurrent threads.
     * @param values values to get maximum of.
     * @param comparator value comparator.
     * @param <T> value type.
     *
     * @return maximum of given values.
     *
     * @throws InterruptedException if executing thread was interrupted.
     * @throws java.util.NoSuchElementException if no values are given.
     */
    @Override
    public <T> T maximum(int threads, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
        Function<List<? extends T>, T> function = (list -> list.stream().max(comparator).orElse(null));
        return function.apply(parallelize(threads, values, function));
    }

    /**
     * Returns minimum value.
     *
     * @param threads number or concurrent threads.
     * @param values values to get minimum of.
     * @param comparator value comparator.
     * @param <T> value type.
     *
     * @return minimum of given values.
     *
     * @throws InterruptedException if executing thread was interrupted.
     * @throws java.util.NoSuchElementException if no values are given.
     */
    @Override
    public <T> T minimum(int threads, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
        return maximum(threads, values, comparator.reversed());
    }

    /**
     * Returns whether all values satisfies predicate.
     *
     * @param threads number or concurrent threads.
     * @param values values to test.
     * @param predicate test predicate.
     * @param <T> value type.
     *
     * @return whether all values satisfies predicate or {@code true}, if no values are given
     *
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> boolean all(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        Function<List<? extends T>, Boolean> function = (list -> list.stream().allMatch(predicate));
        return parallelize(threads, values, function).stream().allMatch(bool -> bool);
    }

    /**
     * Returns whether any of values satisfies predicate.
     *
     * @param threads number or concurrent threads.
     * @param values values to test.
     * @param predicate test predicate.
     * @param <T> value type.
     *
     * @return whether any value satisfies predicate or {@code false}, if no values are given
     *
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> boolean any(int threads, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        Function<List<? extends T>, Boolean> function = (list -> list.stream().anyMatch(predicate));
        return parallelize(threads, values, function).stream().anyMatch(bool -> bool);
    }
}