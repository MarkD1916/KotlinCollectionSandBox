package stack

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class StackImplTest {

    lateinit var stack: Stack<Int>

    @BeforeEach
    fun init() {
        stack = StackImpl.stackOf(1, 2, 3)
    }


    @Test
    fun push() {
        stack.push(4)

        val expected = 4
        val actual = stack.peek()

        assertEquals(expected, actual)
    }

    @Test
    fun pop() {
        stack.pop()

        val expected = 2
        val actual = stack.peek()

        assertEquals(expected, actual)
    }

    @Test
    fun peek() {
        val expected = 3
        val actual = stack.peek()

        assertEquals(expected, actual)
    }

}