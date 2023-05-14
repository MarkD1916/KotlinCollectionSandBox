package linked_list

import sun.text.normalizer.UTF16.append

class LinkedList<T> : Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T> {

    var head: Node<T>? = null
    var tail: Node<T>? = null
    override var size = 0
        private set

    var lastNode: Node<T>? = null

    override fun isEmpty(): Boolean = size == 0

    override fun toString(): String = if (isEmpty()) "Empty list" else head.toString()

    override fun iterator(): MutableIterator<T> = LinkedListIterator(this)

    // O(n)
    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }

        return false
    }

    // O(n^2)
    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements) {
            if (!contains(searched)) return false
        }

        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            append(element)
        }
        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item == element) {
                iterator.remove()

                return true
            }
        }

        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (item in elements) {
            result = remove(item) || result
        }
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }

    // Add value at the front
    fun push(value: T) {
        head = Node(value = value, next = head)
        if (tail == null) {
            tail = head
        }
        size++
    }

    // Add value at the end
    fun append(value: T) {
        if (isEmpty()) {
            push(value)
            return
        }
        tail?.next = Node(value = value)
        tail = tail?.next
        size++
    }

    fun <T : Comparable<T>> append(result: LinkedList<T>, node: Node<T>): Node<T>? {
        result.append(node.value)
        return node.next
    }

    //Find node by index
    fun nodeAt(index: Int): Node<T>? {
        var currentNode = head
        var currentIndex = 0

        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }

        return currentNode
    }

    //Insert node after some node
    fun insert(value: T, afterNode: Node<T>): Node<T> {
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        val newNode = Node(value = value, next = afterNode.next)
        afterNode.next = newNode
        size++

        return newNode
    }

    //Removing value at the front
    fun pop(): T? {
        if (!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }

        return result
    }

    //Removing value at the end
    fun removeLast(): T? {
        val head = head ?: return null
        if (head.next == null) return pop()

        size--

        var prev = head
        var current = head
        var next = current.next
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }

        prev.next = null
        tail = prev

        return current.value
    }

    //Remove node after some node
    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value

        if (node.next == tail) {
            tail = node
        }
        if (node.next != null) {
            size--
        }

        node.next = node.next?.next

        return result
    }

}

fun <T : Comparable<T>> LinkedList<T>.mergeSorted(
    otherList: LinkedList<T>
): LinkedList<T> {
    if (this.isEmpty()) return otherList
    if (otherList.isEmpty()) return this
    val result = LinkedList<T>()
    var left = nodeAt(0)
    var right = otherList.nodeAt(0)

    while (left != null && right != null) {
        if (left.value < right.value) {
            left = append(result, left)
        } else {
            right = append(result, right)
        }
    }

    while (left != null) {
        left = append(result, left)
    }

    while (right != null) {
        right = append(result, right)
    }
    return result
}


data class Node<T>(var value: T, var next: Node<T>? = null) {
    override fun toString(): String = if (next != null) {
        "$value -> ${next.toString()}"
    } else {
        "$value"
    }
}

class Examples {

    private val list = LinkedList<Int>()

    fun pushDemo() {
        println(HEAD_TAIL)
        list.apply {
            push(3)
            push(2)
            push(1)
        }
        println(PUSH_EXAMPLE)
        println(list)
    }

    fun appendDemo() {
        println(HEAD_TAIL)
        list.apply {
            append(1)
            append(2)
            append(3)
        }
        println(APPEND_EXAMPLE)
        println(list)
    }

    fun insertDemo() {
        list.apply {
            push(3)
            push(2)
            push(1)
        }

        println("Before inserting: $list")

        var middleNode = list.nodeAt(1)!!
        for (i in 1..3) {
            middleNode = list.insert(-1 * i, middleNode)
        }
        println("After inserting: $list")
    }

    fun linkExample() {
        val list = LinkedList<Int>()
        val node_1 = Node(value = 1, next = null)
        val node_2 = Node(value = 2, next = null)
        list.head = node_1
        list.tail = node_1
        println("${node_1.hashCode()} - node_1, ${list.head.hashCode()} - head, ${list.tail.hashCode()} - tail")
        list.tail!!.next = node_2
        println("${node_2.hashCode()} - node_2, ${list.head.hashCode()} - head, ${list.tail.hashCode()} - tail")
        list.tail = node_2
        println("${node_2.hashCode()} - node_2, ${list.head.hashCode()} - head, ${list.tail.hashCode()} - tail")
    }

    fun popDemo() {
        val list = LinkedList<Int>()
        list.apply {
            push(1)
            push(2)
            push(3)
        }
        println("$list list before popping")
        val popValue = list.pop()
        println("$list poped value - $popValue")
    }

    fun removeLastDemo() {
        val list = LinkedList<Int>()
        list.apply {
            push(1)
            push(2)
            push(3)
        }
        println("$list list before remove")
        val popValue = list.removeLast()
        println("$list removed value - $popValue")
    }

    fun removeNodeAfterDemo() {
        val list = LinkedList<Int>()
        list.apply {
            push(1)
            push(2)
            push(3)
        }
        val index = 0
        val node = list.nodeAt(index)!!
        val removedValue = list.removeAfter(node)
    }

    fun printingNodeValueDemo() {
        val list = LinkedList<Int>()
        list.apply {
            push(1)
            push(2)
            push(3)
        }
        println(list)

        for (item in list) {
            println("Double: ${item * 2}")
        }
    }

    fun retainAllDemo() {
        val list = LinkedList<Int>()
        list.apply {
            push(1)
            push(2)
            push(3)
        }
        println(list)
        val list_2 = LinkedList<Int>()
        list_2.apply {
            push(4)
            push(1)
            push(3)
        }
        println(list_2)
        list.retainAll(list_2)
        println(list)

    }

    fun mergeExample() {
        val list = LinkedList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        list.add(5)
        val other = LinkedList<Int>()
        other.add(-1)
        other.add(0)
        other.add(2)
        other.add(2)
        other.add(7)
        println("Left: $list")
        println("Right: $other")
        println("Merged: ${list.mergeSorted(other)}")
    }

    companion object {
        const val PUSH_EXAMPLE = "Example of push"
        const val APPEND_EXAMPLE = "Example of append"
        const val HEAD_TAIL = "Head-tail state"
    }

}
