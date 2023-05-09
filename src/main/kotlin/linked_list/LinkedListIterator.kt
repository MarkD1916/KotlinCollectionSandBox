package linked_list

class LinkedListIterator<T>(private val list: LinkedList<T>) : Iterator<T>, MutableIterator<T> {

    private var index = 0

    override fun hasNext(): Boolean = index < list.size

    override fun next(): T {
        if (index >= list.size) throw IndexOutOfBoundsException()

        list.lastNode = if (index == 0) {
            list.nodeAt(0)
        } else {
            list.lastNode?.next
        }
        index++

        return list.lastNode!!.value
    }

    override fun remove() {
        if (index == 1) {
            list.pop()
        } else {
            val prevNode = list.nodeAt(index - 2) ?: return
            list.removeAfter(prevNode)
            list.lastNode = prevNode
        }
        index--
    }
}