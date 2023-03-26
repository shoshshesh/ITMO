package mpp.linkedlistset

import kotlinx.atomicfu.*

class LinkedListSet<E : Comparable<E>> {
    private val first = Node<E>(prev = null, element = null, next = null)
    private val last = Node(prev = first, element = null, next = null)
    private val head = atomic(first)

    init {
        first.setNext(last)
    }

    /**
     * Adds the specified element to this set
     * if it is not already present.
     *
     * Returns `true` if this set did not
     * already contain the specified element.
     */
    fun add(element: E) = operation(element, ::isNotThisElement, ::addNode)

    /**
     * Removes the specified element from this set
     * if it is present.
     *
     * Returns `true` if this set contained
     * the specified element.
     */
    fun remove(element: E) = operation(element, ::isThisElement, ::killAndRemove)

    /**
     * Returns `true` if this set contains
     * the specified element.
     */
    fun contains(element: E): Boolean =
        isThisElement(element, findNodes(element).second)

    private fun operation(element: E, condition: (element: E, node: Node<E>) -> Boolean,
                          action: (element: E, cur: Node<E>, next: Node<E>) -> Boolean): Boolean {
        while (true) {
            val (cur, next) = findNodes(element)
            if (condition(element, next)) {
                if (action(element, cur, next)) {
                    return true
                }
            } else {
                return false
            }
        }
    }

    private fun findNodes(element: E): Pair<Node<E>, Node<E>> {
        var cur = head.value
        var next = cur.next!!
        while (next != last && next.element < element) {
            if (next.alive) {
                cur = next
                next = next.next!!
            } else {
                cur.casNext(next, next.next!!)
                next = cur.next!!
            }
        }
        return Pair(cur, next)
    }

    private fun isThisElement(element: E, candidate: Node<E>) =
        candidate != last && candidate.element == element

    private fun isNotThisElement(element: E, candidate: Node<E>) =
        !isThisElement(element, candidate)

    @Suppress("UNUSED_PARAMETER")
    private fun killAndRemove(element: E, cur: Node<E>, next: Node<E>): Boolean {
        return if (next.casAlive(true, false)) {
            cur.casNext(next, next.next!!)
            true
        } else {
            false
        }
    }

    private fun addNode(element: E, cur: Node<E>, next: Node<E>) =
        cur.casNext(next, Node(cur, element, next))

}

private class Node<E : Comparable<E>>(prev: Node<E>?, element: E?, next: Node<E>?) {
    private val _element = element // `null` for the first and the last nodes
    private val _prev = atomic(prev)
    private val _next = atomic(next)
    private val _alive = atomic(true)
    val prev get() = _prev.value
    val element get() = _element!!
    val next get() = _next.value
    val alive get() = _alive.value

    fun casAlive(expected: Boolean, update: Boolean) =
        _alive.compareAndSet(expected, update)

    fun setPrev(value: Node<E>?) {
        _prev.value = value
    }

    fun casPrev(expected: Node<E>?, update: Node<E>?) =
        _prev.compareAndSet(expected, update)

    fun setNext(value: Node<E>?) {
        _next.value = value
    }

    fun casNext(expected: Node<E>?, update: Node<E>?) =
        _next.compareAndSet(expected, update)
}