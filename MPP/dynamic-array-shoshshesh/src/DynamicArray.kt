package mpp.dynamicarray

import kotlinx.atomicfu.*

interface DynamicArray<E> {
    /**
     * Returns the element located in the cell [index],
     * or throws [IllegalArgumentException] if [index]
     * exceeds the [size] of this array.
     */
    fun get(index: Int): E

    /**
     * Puts the specified [element] into the cell [index],
     * or throws [IllegalArgumentException] if [index]
     * exceeds the [size] of this array.
     */
    fun put(index: Int, element: E)

    /**
     * Adds the specified [element] to this array
     * increasing its [size].
     */
    fun pushBack(element: E)

    /**
     * Returns the current size of this array,
     * it increases with [pushBack] invocations.
     */
    val size: Int
}

class DynamicArrayImpl<E> : DynamicArray<E> {
    private val core = atomic(Core<E>(INITIAL_CAPACITY, 0))

    override fun get(index: Int): E = core.value.get(index)

    override fun put(index: Int, element: E) = core.value.put(index, element, core.value)

    override fun pushBack(element: E) = core.value.pushBack(element, this)

    override val size: Int
        get() {
            return core.value.getSize()
        }

    fun getCore(): Core<E> {
        return core.value
    }

    fun setCore(prevCore: Core<E>, newCore: Core<E>) {
        core.compareAndSet(prevCore, newCore)
    }
}

private const val INITIAL_CAPACITY = 1 // DO NOT CHANGE ME

class Core<E>(capacity: Int, initSize: Int) {
    private val array = atomicArrayOfNulls<Cell<E>>(capacity)
    private val size: AtomicInt = atomic(initSize)
    private val nextCore: AtomicRef<Core<E>?> = atomic(null)

    @Suppress("UNCHECKED_CAST")
    fun get(index: Int): E {
        require(index < size.value)
        return array[index].value?.element as E
    }

    fun put(index: Int, element: E, core: Core<E>) {
        require(index < size.value)
        var curCore = core
        while (true) {
            if (curCore.array[index].compareAndSet(curCore.array[index].value, Cell(element))) {
                break
            }
        }
        while (true) {
            val nextCore = curCore.getNextCore() ?: break
            val cell = curCore.getCell(index) ?: break
            while (true) {
                if (nextCore.array[index].compareAndSet(nextCore.array[index].value, cell)) {
                    break
                }
            }
            curCore = nextCore
        }
    }

    fun pushBack(element: E, dynamicArray: DynamicArrayImpl<E>) {
        while (true) {
            val curCore = dynamicArray.getCore()
            val curSize = curCore.getSize()
            if (curSize < curCore.array.size) {
                if (curCore.array[curSize].compareAndSet(null, Cell(element))) {
                    curCore.incSize(curSize)
                    break
                }
                else {
                    curCore.incSize(curSize)
                }
            } else {
                val nextCore = Core<E>(curCore.array.size * 2, curCore.getSize())
                if (curCore.setNextCore(nextCore)) {
                    transfer(curCore, nextCore, dynamicArray)
                } else {
                    transfer(curCore, curCore.getNextCore(), dynamicArray)
                }
            }
        }
    }

    private fun transfer(curCore: Core<E>, nextCore: Core<E>?, dynamicArray: DynamicArrayImpl<E>) {
        if (nextCore == null) {
            return
        }
        for (i in 0 until curCore.array.size) {
            nextCore.array[i].compareAndSet(null, curCore.getCell(i))
        }
        dynamicArray.setCore(curCore, nextCore)
    }

    private fun getCell(index: Int): Cell<E>? {
        return array[index].value
    }

    private fun getNextCore(): Core<E>? {
        return nextCore.value
    }

    private fun setNextCore(newNextCore: Core<E>): Boolean {
        return nextCore.compareAndSet(null, newNextCore)
    }

    fun getSize(): Int {
        return size.value
    }

    private fun incSize(prevSize: Int): Boolean {
        return size.compareAndSet(prevSize, prevSize + 1)
    }
}

enum class Status {
    NORMAL, FIXED, MOVED
}

class Cell<E> (var element: E) {
    private var status: Status = Status.NORMAL

    fun getStatus(): Status {
        return status
    }

    fun createNormal(): Cell<E> {
        return Cell(element)
    }
    fun createFixed(): Cell<E> {
        val fixed = Cell(element)
        fixed.status = Status.FIXED
        return fixed
    }

    fun createMoved(): Cell<E> {
        val moved = Cell(element)
        moved.status = Status.MOVED
        return moved
    }
}