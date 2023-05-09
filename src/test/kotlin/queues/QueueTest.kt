package queues

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class QueueTest {

    lateinit var stackQueue: StackQueue<Int>
    lateinit var arrayListQueue: ArrayListQueueImpl<Int>
    lateinit var ringQueue: RingQueueImpl<Int>
    lateinit var linkedListQueue: LinkedListQueueImpl<Int>

    @BeforeEach
    fun init() {
        stackQueue = StackQueue()
        arrayListQueue = ArrayListQueueImpl()
        ringQueue = RingQueueImpl(3)
        linkedListQueue = LinkedListQueueImpl()
    }

    @Test
    fun enqueue() {
        stackQueue.enqueue(4)
        arrayListQueue.enqueue(4)
        ringQueue.enqueue(4)
        linkedListQueue.enqueue(4)

        val expected = 4
        val actualStack = stackQueue.peek()
        val actualList = linkedListQueue.peek()
        val actualArray = arrayListQueue.peek()
        val actualRing = ringQueue.peek()

        assertEquals(expected, actualStack)
        assertEquals(expected, actualList)
        assertEquals(expected, actualArray)
        assertEquals(expected, actualRing)
    }

    @Test
    fun dequeue() {
        stackQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        linkedListQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        arrayListQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        ringQueue.apply {
            enqueue(4)
            enqueue(5)
        }

        val expected = 5
        stackQueue.dequeue()
        linkedListQueue.dequeue()
        arrayListQueue.dequeue()
        ringQueue.dequeue()

        val actualStack = stackQueue.peek()
        val actualList = linkedListQueue.peek()
        val actualArray = arrayListQueue.peek()
        val actualRing = ringQueue.peek()

        assertEquals(expected, actualStack)
        assertEquals(expected, actualList)
        assertEquals(expected, actualArray)
        assertEquals(expected, actualRing)
    }

    @Test
    fun getCount() {
        stackQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        linkedListQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        arrayListQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        ringQueue.apply {
            enqueue(4)
            enqueue(5)
        }

        val expected = 2

        val actualStack = stackQueue.count
        val actualList = linkedListQueue.count
        val actualArray = arrayListQueue.count
        val actualRing = ringQueue.count

        assertEquals(expected, actualStack)
        assertEquals(expected, actualList)
        assertEquals(expected, actualArray)
        assertEquals(expected, actualRing)
    }


    @Test
    fun isEmpty() {
        stackQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        linkedListQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        arrayListQueue.apply {
            enqueue(4)
            enqueue(5)
        }
        ringQueue.apply {
            enqueue(4)
            enqueue(5)
        }

        val actualStack = stackQueue.isEmpty
        val actualList = linkedListQueue.isEmpty
        val actualArray = arrayListQueue.isEmpty
        val actualRing = ringQueue.isEmpty

        assertFalse(actualStack)
        assertFalse(actualList)
        assertFalse(actualArray)
        assertFalse(actualRing)
    }

}