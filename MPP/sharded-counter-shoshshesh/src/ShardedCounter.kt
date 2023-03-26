package mpp.counter

import kotlinx.atomicfu.AtomicIntArray
import kotlin.random.Random

/*
* Этот счётчик линеаризуем, так как любое параллельное исполнение можно представить в виде
* последовательного выполнения команд.
* Даже если вовремя исполнения get() происходит inc() в ячейки массива, которые уже были просуммированы,
* то эту ситуацию можно представить, как будто эти вызовы inc() произошли после get().
* */

class ShardedCounter {
    private val counters = AtomicIntArray(ARRAY_SIZE)

    /**
     * Atomically increments by one the current value of the counter.
     */
    fun inc() {
        val random = Random
        val index = random.nextInt(ARRAY_SIZE)
        counters[index].getAndIncrement()
    }

    /**
     * Returns the current counter value.
     */
    fun get(): Int {
        var result = 0
        for (i in 0 until counters.size) {
            result += counters[i].value
        }
        return result
    }
}

private const val ARRAY_SIZE = 2 // DO NOT CHANGE ME