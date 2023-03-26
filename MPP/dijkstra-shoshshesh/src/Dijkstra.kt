package dijkstra

import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.locks.ReentrantLock
import java.util.*
import java.util.concurrent.Phaser
import kotlin.concurrent.thread

private val NODE_DISTANCE_COMPARATOR = Comparator<Node> { o1, o2 -> Integer.compare(o1!!.distance, o2!!.distance) }
private val activeNodes: AtomicInt = atomic(1)

// Returns `Integer.MAX_VALUE` if a path has not been found.
fun shortestPathParallel(start: Node) {
    val workers = Runtime.getRuntime().availableProcessors()
    val queue = PriorityMQ(workers, NODE_DISTANCE_COMPARATOR)
    val onFinish = Phaser(workers + 1)
    start.distance = 0
    queue.insert(start)
    activeNodes.getAndSet(1)
    repeat(workers) {
        thread {
            while (activeNodes.value > 0) {
                val u: Node = queue.delete() ?: continue
                for (edge in u.outgoingEdges) {
                    val v: Node = edge.to
                    while (true) {
                        val curDist: Int = v.distance
                        val newDist: Int = u.distance + edge.weight
                        if (newDist < curDist) {
                            if (v.casDistance(curDist, newDist)) {
                                activeNodes.incrementAndGet()
                                queue.insert(v)
                                break
                            }
                        } else {
                            break
                        }
                    }
                }
                activeNodes.decrementAndGet()
            }
            onFinish.arrive()
        }
    }
    onFinish.arriveAndAwaitAdvance()
}

private class PriorityMQ(workers: Int, private val comparator: Comparator<Node>) {
    private val queues: MutableList<PriorityQueue<Node>> = Collections.nCopies(workers, PriorityQueue(comparator))
    private val locks: MutableList<ReentrantLock> = Collections.nCopies(workers, ReentrantLock())

    fun insert(node: Node) {
        val random: Random = Random()
        while (true) {
            val index: Int = random.nextInt(queues.size)
            val queue: PriorityQueue<Node> = queues[index]
            val lock: ReentrantLock = locks[index]
            if (!lock.tryLock()) {
                continue
            }
            queue.add(node)
            lock.unlock()
            return
        }
    }

    fun delete(): Node? {
        while (true) {
            val indexes: IntArray = getIndexes(queues.size)
            val i1 = indexes[0]
            val i2 = indexes[1]
            if (locks[i1].tryLock()) {
                if (locks[i2].tryLock()) {
                    val q1: PriorityQueue<Node> = queues[i1]
                    val q2: PriorityQueue<Node> = queues[i2]
                    try {
                        val el1: Node? = q1.peek()
                        val el2: Node? = q2.peek()
                        return if (el1 == null && el2 == null) {
                            null
                        } else if (el1 == null) {
                            q2.poll()
                        } else if (el2 == null) {
                            q1.poll()
                        } else {
                            if (comparator.compare(el1, el2) < 0) q1.poll() else q2.poll()
                        }
                    } finally {
                        locks[i1].unlock()
                        locks[i2].unlock()
                    }
                } else {
                    locks[i1].unlock()
                    continue
                }
            } else {
                continue
            }
        }
    }

    private fun getIndexes(max: Int): IntArray {
        val random: Random = Random()
        val index1: Int = random.nextInt(max)
        var index2: Int = random.nextInt(max)
        while (index1 == index2) {
            index2 = random.nextInt(max)
        }
        val result: IntArray = IntArray(2)
        result[0] = index1
        result[1] = index2
        return result
    }
}