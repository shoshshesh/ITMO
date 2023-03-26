import kotlinx.atomicfu.AtomicBoolean
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.atomicArrayOfNulls
import java.util.*

class FCPriorityQueue<E : Comparable<E>> {
    private val q = PriorityQueue<E>()
    private val fcArray = atomicArrayOfNulls<Task<E>>(FC_ARRAY_SIZE)
    private val lock: AtomicBoolean = atomic(false)


    /**
     * Retrieves the element with the highest priority
     * and returns it as the result of this function;
     * returns `null` if the queue is empty.
     */
    fun poll(): E? {
        if (lock.compareAndSet(false, true)) {
            val result = q.poll()
            help()
            return result
        } else {
            val index = publish(Task(Operation.POLL, null))
            while (true) {
                if (lock.compareAndSet(false, true)) {
                    val result: E? = if (fcArray[index].value?.operation == Operation.DONE) {
                        fcArray[index].value?.element
                    } else {
                        q.poll()
                    }
                    fcArray[index].value = null
                    help()
                    return result
                }
                if (fcArray[index].value?.operation == Operation.DONE) {
                    val result = fcArray[index].value?.element
                    fcArray[index].value = null
                    return result
                }
            }
        }
    }

    private fun help() {
        for (i in 0 until FC_ARRAY_SIZE) {
            if (fcArray[i].value == null) {
                continue
            }
            when (fcArray[i].value?.operation) {
                Operation.POLL -> fcArray[i].value = Task(Operation.DONE, q.poll())
                Operation.ADD  -> {
                    q.add(fcArray[i].value?.element)
                    fcArray[i].value = Task(Operation.DONE, null)
                }
                Operation.PEEK -> fcArray[i].value = Task(Operation.DONE, q.peek())
                else -> { /* unreachable */ }
            }
        }
        lock.value = false
    }

    private fun publish(task: Task<E>): Int {
        val random = Random()
        var index: Int
        while (true) {
            index = random.nextInt(FC_ARRAY_SIZE)
            if (fcArray[index].compareAndSet(null, task)) {
                break
            }
        }
        return index
    }

    /**
     * Returns the element with the highest priority
     * or `null` if the queue is empty.
     */
    fun peek(): E? {
        if (lock.compareAndSet(false, true)) {
            val result = q.peek()
            help()
            return result
        } else {
            val index = publish(Task(Operation.PEEK, null))
            while (true) {
                if (lock.compareAndSet(false, true)) {
                    val result: E? = if (fcArray[index].value?.operation == Operation.DONE) {
                        fcArray[index].value?.element
                    } else {
                        q.peek()
                    }
                    fcArray[index].value = null
                    help()
                    return result
                }
                if (fcArray[index].value?.operation == Operation.DONE) {
                    val result = fcArray[index].value?.element
                    fcArray[index].value = null
                    return result
                }
            }
        }
    }

    /**
     * Adds the specified element to the queue.
     */
    fun add(element: E) {
        if (lock.compareAndSet(false, true)) {
            q.add(element)
            help()
        } else {
            val index = publish(Task(Operation.ADD, element))
            while (true) {
                if (lock.compareAndSet(false, true)) {
                    if (fcArray[index].value?.operation != Operation.DONE) {
                        q.add(element)
                    }
                    fcArray[index].value = null
                    help()
                    return
                }
                if (fcArray[index].value?.operation == Operation.DONE) {
                    fcArray[index].value = null
                    return
                }
            }
        }
    }
}

private enum class Operation {
    POLL, PEEK, ADD, DONE
}
private data class Task<E>(val operation: Operation, val element: E?)

private const val FC_ARRAY_SIZE = 8