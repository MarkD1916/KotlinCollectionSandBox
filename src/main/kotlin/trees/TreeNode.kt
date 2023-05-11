package trees

import queues.StackQueue

typealias Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {

    private val children: MutableList<TreeNode<T>> = mutableListOf()

    fun add(child: TreeNode<T>) = children.add(child)

    // depth-first traversal go from node to the depth and after visit new high level node
    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    // level-order traversal - visit each node by level
    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)
        val queue = StackQueue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }

        var node = queue.dequeue()
        while (node != null) {
            visit(node)
            node.children.forEach { queue.enqueue(it) }
            node = queue.dequeue()
        }
    }

}

class Example {

    private var tree: TreeNode<String> = TreeNode("Drinks")

    init {
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

    fun eachDepthFirstDemo() {
        println("eachDepthFirstDemo")
        tree.forEachDepthFirst { println(it.value) }
    }

    fun eachLevelOrderDemo() {
        println("eachLevelOrderDemo")
        tree.forEachLevelOrder { println(it.value) }
    }

}