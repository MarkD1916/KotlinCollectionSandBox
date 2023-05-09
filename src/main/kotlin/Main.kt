import queues.StackQueue

fun main(args: Array<String>) {
    val queue = StackQueue<Int>().apply {
        enqueue(1)
        enqueue(2)
        enqueue(5)
    }
    println(queue)

    queue.dequeue()
    println(queue)

    queue.enqueue(10)
    println(queue)

    queue.dequeue()
    println(queue)

    queue.dequeue()
    println(queue)
    println("Next up: ${queue.peek()}")
}
