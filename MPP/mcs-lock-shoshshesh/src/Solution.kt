import java.util.concurrent.atomic.*

class Solution(private val env: Environment) : Lock<Solution.Node> {
    // todo: необходимые поля (val, используем AtomicReference)
    private val tail = AtomicReference<Node>(null)

    override fun lock(): Node {
        val my = Node()
        val pred = tail.getAndSet(my)
        if (pred != null) {
            pred.next.value = my
            while (my.locked.value) {
                env.park()
            }
        }
        return my // вернули узел
    }

    override fun unlock(node: Node) {
        if (node.next.value == null) {
            if (tail.compareAndSet(node, null)) {
                return
            } else {
                while (node.next.value == null) {
                    env.park()
                }
            }
        }
        val next = node.next.value!!
        next.locked.value = false
        env.unpark(next.thread)
    }

    class Node {
        // todo: необходимые поля (val, используем AtomicReference)
        val thread: Thread = Thread.currentThread() // запоминаем поток, которые создал узел
        val locked = AtomicReference(true)
        val next = AtomicReference<Node?>(null)
    }
}