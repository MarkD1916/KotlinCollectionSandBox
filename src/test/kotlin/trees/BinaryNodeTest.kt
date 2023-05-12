package trees

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class BinaryNodeTest {

    lateinit var tree: BinaryNode<Int>

    @BeforeEach
    fun init() {
        val zero = BinaryNode(0)
        val one = BinaryNode(1)
        val five = BinaryNode(5)
        val seven = BinaryNode(7)
        val eight = BinaryNode(8)
        val nine = BinaryNode(9)

        seven.leftChild = one
        one.leftChild = zero
        one.rightChild = five
        seven.rightChild = nine
        nine.leftChild = eight

        tree = seven
    }

    @Test
    fun traversInOrder() {
        val expexted = listOf(0, 1, 5, 7, 8, 9)
        val actual = mutableListOf<Int>()

        tree.traversInOrder {
            actual.add(it)
        }

        assertEquals(expexted, actual)
    }

    @Test
    fun traversPreOrder() {
        val expexted = listOf(7, 1, 0, 5, 9, 8)
        val actual = mutableListOf<Int>()

        tree.traversePreOrder {
            actual.add(it)
        }

        assertEquals(expexted, actual)
    }

    @Test
    fun traversPostOrder() {
        val expexted = listOf(0, 5, 1, 8, 9, 7)
        val actual = mutableListOf<Int>()

        tree.traversePostOrder {
            actual.add(it)
        }

        assertEquals(expexted, actual)
    }

}