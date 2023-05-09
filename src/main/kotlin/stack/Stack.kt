package stack

interface Stack<Element> {
    val count: Int

    val isEmpty: Boolean
        get() = count == 0

    fun push(element: Element)

    fun pop(): Element?

    fun peek(): Element?
}

class StackImpl<T> : Stack<T> {

    private val storage = arrayListOf<T>()
    override val count: Int
        get() = storage.size

    override fun toString() = buildString {
        appendLine("----top----")
        storage.asReversed().forEach {
            appendLine("$it")
        }
        appendLine("-----------")
    }

    override fun push(element: T) {
        storage.add(element)
    }

    override fun pop(): T? {
        if (isEmpty) return null

        return storage.removeAt(storage.size - 1)
    }

    override fun peek(): T? = storage.lastOrNull()

    companion object {

        fun <Element> stackOf(vararg elements: Element): Stack<Element> = create(elements.asList())

        private fun <Element> create(items: Iterable<Element>): Stack<Element> {
            val stack = StackImpl<Element>()
            for (item in items) {
                stack.push(item)
            }

            return stack
        }

    }

}

class Examples {

    val stack = StackImpl<Int>()

    fun pushStackDemo() {
        stack.apply {
            push(1)
            push(2)
            push(3)
        }
        println(stack)
    }

    fun popStackDemo() {
        stack.apply {
            push(1)
            push(2)
            push(3)
        }
        println(stack)
        val poppedElement = stack.pop()
        if (poppedElement != null) {
            println("Popped: $poppedElement")
        }
        println(stack)
    }

    fun createStackDemo() {
        val stack = StackImpl.stackOf(1, 2, 3, 4)
        println(stack)
    }

}