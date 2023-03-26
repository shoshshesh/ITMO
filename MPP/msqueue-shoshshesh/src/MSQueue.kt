package mpp.msqueue

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic

class MSQueue<E> {
    private val head: AtomicRef<Node<E>>
    private val tail: AtomicRef<Node<E>>

    init {
        val dummy = Node<E>(null)
        head = atomic(dummy)
        tail = atomic(dummy)
    }

    /**
     * Adds the specified element [x] to the queue.
     */
    fun enqueue(x: E) {
        val node: Node<E> = Node(x)
        while (true) {
            val curTail: Node<E> = tail.value
            if (curTail.next.compareAndSet(null, node)) {
                tail.compareAndSet(curTail, node)
                return
            } else {
                tail.compareAndSet(curTail, curTail.next.value as Node<E>)
            }
        }
    }

    /**
     * Retrieves the first element from the queue
     * and returns it; returns `null` if the queue
     * is empty.
     */
    fun dequeue(): E? {
        while (true) {
            val curHead: Node<E> = head.value
            val curTail: Node<E> = tail.value
            val next: Node<E> = curHead.next.value ?: return null
            if (curHead == head.value) {
                if (curHead == curTail) {
                    tail.compareAndSet(curTail, next)
                } else {
                    if (head.compareAndSet(curHead, next)) {
                        return next.x
                    }
                }
            }
        }
    }

    fun isEmpty(): Boolean {
        return head.value == tail.value
    }
}

private class Node<E>(val x: E?) {
    val next = atomic<Node<E>?>(null)
}