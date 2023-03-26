import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * An element is transferred from sender to receiver only when [send] and [receive]
 * invocations meet in time (rendezvous), so [send] suspends until another coroutine
 * invokes [receive] and [receive] suspends until another coroutine invokes [send].
 */
class SynchronousQueue<E> {
    private val head: AtomicRef<Node<E>>
    private val tail: AtomicRef<Node<E>>

    init {
        val dummy = Node<E>(null, Operation.DUMMY)
        head = atomic(dummy)
        tail = atomic(dummy)
    }

    /**
     * Sends the specified [element] to this channel, suspending if there is no waiting
     * [receive] invocation on this channel.
     */
    suspend fun send(element: E): Unit {
        return operation(Node(element, Operation.SEND)) as Unit
    }

    /**
     * Retrieves and removes an element from this channel if there is a waiting [send] invocation on it,
     * suspends the caller if this channel is empty.
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun receive(): E {
        return operation(Node(null, Operation.RECEIVE)) as E
    }

    private suspend fun operation(node: Node<E>): Any {
        while (true) {
            val curHead = head.value
            val curTail = tail.value
            if (curHead == curTail || curTail.operation == node.operation) {
                if (enqueueAndSuspend(curTail, node)) {
                    if (node.isSend()) {
                        return Unit
                    } else if (node.isReceive()){
                        return node.element.value!!
                    }
                }
            } else {
                val next = curHead.next.value ?: continue
                if (dequeueAndResume(node.element.value, curHead, next)) {
                    if (node.isSend()) {
                        return Unit
                    } else if (node.isReceive()){
                        return next.element.value!!
                    }
                }
            }
        }
    }

    private suspend fun enqueueAndSuspend(curTail: Node<E>, node: Node<E>): Boolean {
        return !suspendCoroutine sc@{ cont ->
            node.coroutine = cont
            if (curTail.next.compareAndSet(null, node)) {
                tail.compareAndSet(curTail, node)
            } else {
                tail.compareAndSet(curTail, curTail.next.value!!)
                cont.resume(true)
                return@sc
            }
        }
    }

    private fun dequeueAndResume(element: E?, curHead: Node<E>, next: Node<E>): Boolean {
        if (next.coroutine != null && head.compareAndSet(curHead, next)) {
            if (element != null) {
                next.element.compareAndSet(next.element.value, element)
            }
            next.coroutine!!.resume(false)
            return true
        }
        return false
    }
}

private enum class Operation {
    SEND, RECEIVE, DUMMY
}

private class Node<E>(element: E?, val operation: Operation) {
    val element = atomic(element)
    val next = atomic<Node<E>?>(null)
    var coroutine: Continuation<Boolean>? = null

    fun isSend(): Boolean {
        return operation == Operation.SEND
    }

    fun isReceive(): Boolean {
        return operation == Operation.RECEIVE
    }

}