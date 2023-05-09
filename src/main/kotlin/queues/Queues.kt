package queues

import linked_list.LinkedList
import stack.StackImpl

interface Queue<T> {

    //Insert element at the back of queue
    fun enqueue(element: T): Boolean

    //Remove element at the front of queue
    fun dequeue(): T?
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0

    fun peek(): T?

}

//Note resizing of list is O(n)
class ArrayListQueueImpl<T> : Queue<T> {

    private val list = arrayListOf<T>()

    override fun enqueue(element: T): Boolean {
        list.add(element)

        return true
    }

    //O(n) every time when remove element in front we should shift all element by one position
    override fun dequeue(): T? = if (isEmpty) null else list.removeAt(0)

    override val count: Int
        get() = list.size

    override fun peek(): T? = list.lastOrNull()

}

class LinkedListQueueImpl<T> : Queue<T> {

    private val list = LinkedList<T>()
    private var size = 0

    override fun toString(): String = list.toString()

    override fun enqueue(element: T): Boolean {
        list.append(element)
        size++

        return true
    }

    override fun dequeue(): T? {
        size--

        return list.pop()
    }

    override val count: Int
        get() = size

    override fun peek(): T? = list.head?.value

}

class RingQueueImpl<T>(size: Int) : Queue<T> {

    private val ringBuffer: RingBuffer<T> = RingBuffer(size)

    override fun enqueue(element: T): Boolean = ringBuffer.write(element)

    override fun dequeue(): T? = if (isEmpty) null else ringBuffer.read()

    override val count: Int
        get() = ringBuffer.count

    override fun peek(): T? = ringBuffer.first

    override fun toString(): String = ringBuffer.toString()

}

class StackQueue<T> : Queue<T> {

    private val leftStack = StackImpl<T>() // for dequeue
    private val rightStack = StackImpl<T>() // for enqueue

    override fun enqueue(element: T): Boolean {
        rightStack.push(element)

        return true
    }

    override fun dequeue(): T? {
        if (leftStack.isEmpty) {
            transferElements()
        }

        return leftStack.pop()
    }

    override val count: Int
        get() = leftStack.count + rightStack.count

    override fun peek(): T? {
        if (leftStack.isEmpty) {
            transferElements()
        }

        return leftStack.peek()
    }

    override val isEmpty: Boolean
        get() = leftStack.isEmpty && rightStack.isEmpty

    private fun transferElements() {
        var nextElement = rightStack.pop()
        while (nextElement != null) {
            leftStack.push(nextElement)
            nextElement = rightStack.pop()
        }
    }

    override fun toString(): String = "Left stack: \n$leftStack \n Right stack:\n$rightStack"
}