package linked_list

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LinkedListTest {

    @Test
    fun getSize() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            push(1)
            push(2)
            push(3)
        }

        val expected = 3
        val actual = inputList.size

        assertEquals(expected, actual)
    }

    @Test
    fun isEmpty() {
        val inputList = LinkedList<Int>()
        val expected = true
        val actual = inputList.isEmpty()

        assertEquals(expected, actual)
    }

    @Test
    fun contains() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            push(1)
            push(2)
            push(3)
        }

        val expected = true
        val actual = inputList.contains(3)

        assertEquals(expected, actual)
    }

    @Test
    fun notContains() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            push(1)
            push(5)
            push(3)
        }

        val expected = false
        val actual = inputList.contains(2)

        assertEquals(expected, actual)
    }

    @Test
    fun containsAll() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            push(1)
            push(5)
            push(3)
        }

        val inputList_2 = LinkedList<Int>()
        inputList_2.apply {
            push(1)
            push(5)
            push(3)
        }

        val expected = true
        val actual = inputList.containsAll(inputList_2)

        assertEquals(expected, actual)
    }

    @Test
    fun notContainsAll() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            push(1)
            push(5)
            push(3)
        }

        val inputList_2 = LinkedList<Int>()
        inputList_2.apply {
            push(1)
            push(6)
            push(3)
        }

        val expected = false
        val actual = inputList.containsAll(inputList_2)

        assertEquals(expected, actual)
    }

    @Test
    fun add() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            add(1)
            add(2)
            add(3)
        }
        val inputList_2 = LinkedList<Int>()
        inputList_2.apply {
            push(1)
            push(2)
            push(3)
        }

        val expected = true
        val actual = inputList.containsAll(inputList_2)

        assertEquals(expected, actual)
    }

    @Test
    fun addAll() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            add(1)
            add(2)
            add(3)
        }
        val inputList_2 = LinkedList<Int>()
        inputList_2.apply {
            append(1)
            append(2)
            append(3)
        }

        inputList.addAll(inputList_2)
        val expected = listOf(1, 2, 3, 1, 2, 3)

        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun clear() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            add(1)
            add(2)
            add(3)
        }

        inputList.clear()
        assertTrue(inputList.isEmpty())
    }

    @Test
    fun remove() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            add(1)
            add(2)
            add(3)
        }
        println(inputList)

        for (item in inputList) {
            if (item == 3) {
                inputList.remove(item)
            }
        }

        val expected = listOf(1, 2)
        println(inputList)
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun removeAll() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            add(1)
            add(2)
            add(3)
        }

        val inputListRemovedElements = LinkedList<Int>()
        inputListRemovedElements.apply {
            add(1)
            add(2)
        }

        inputList.removeAll(inputListRemovedElements)
        val expected = listOf(3)
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun retainAll() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            add(1)
            add(2)
            add(3)
        }

        val inputListRemovedElements = LinkedList<Int>()
        inputListRemovedElements.apply {
            add(1)
            add(3)
            add(3)
            add(3)
            add(6)
        }

        inputList.retainAll(inputListRemovedElements)
        val expected = listOf(1, 3)
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun push() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            push(1)
            push(2)
            push(3)
        }

        val expected = listOf(3, 2, 1)
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun append() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            append(1)
            append(2)
            append(3)
        }

        val expected = listOf(1, 2, 3)
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun nodeAt() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            append(1)
            append(2)
            append(3)
        }

        val expected = 2
        val actual = inputList.nodeAt(1)?.value
        assertEquals(expected, actual)
    }

    @Test
    fun insert() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            append(1)
            append(2)
            append(3)
        }
        val inputNode = inputList.nodeAt(1)!!

        val expected = listOf(1, 2, 1, 3)
        inputList.insert(1, inputNode)
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun pop() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            append(1)
            append(2)
            append(3)
        }

        val expected = listOf(2, 3)
        inputList.pop()
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun removeLast() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            append(1)
            append(2)
            append(3)
        }

        val expected = listOf(1, 2)
        inputList.removeLast()
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

    @Test
    fun removeAfter() {
        val inputList = LinkedList<Int>()
        inputList.apply {
            append(1)
            append(2)
            append(3)
        }
        val inputNode = inputList.nodeAt(1)!!

        val expected = listOf(1, 2)
        inputList.removeAfter(inputNode)
        inputList.forEachIndexed { index, value ->
            assertEquals(expected[index], value)
        }
    }

}