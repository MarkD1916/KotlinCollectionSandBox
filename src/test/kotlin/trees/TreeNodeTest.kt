package trees

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class TreeNodeTest {

    lateinit var tree: TreeNode<String>

    @BeforeEach
    fun init() {
        tree = TreeNode("Drinks")
        val hot = TreeNode("Hot")
        val cold = TreeNode("Cold")
        val soda = TreeNode("Soda")
        val milk = TreeNode("Milk")
        val tea = TreeNode("Tea")
        val coffee = TreeNode("Coffee")
        val hotWine = TreeNode("Hot Wine")

        tree.add(hot)
        tree.add(cold)
        hot.add(tea)
        hot.add(coffee)
        hot.add(hotWine)
        cold.add(soda)
        cold.add(milk)
    }

    @Test
    fun forEachDepthFirst() {
        val expexted = listOf("Drinks", "Hot", "Tea", "Coffee", "Hot Wine", "Cold", "Soda", "Milk")
        val actual = mutableListOf<String>()

        tree.forEachDepthFirst {
            actual.add(it.value)
        }

        assertEquals(expexted, actual)
    }

    @Test
    fun forEachLevelOrder() {
        val expexted = listOf("Drinks", "Hot", "Cold", "Tea", "Coffee", "Hot Wine", "Soda", "Milk")
        val actual = mutableListOf<String>()

        tree.forEachLevelOrder {
            actual.add(it.value)
        }

        assertEquals(expexted, actual)
    }
}