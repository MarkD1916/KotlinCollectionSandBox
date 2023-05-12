package trees

class BinaryNode<T>(val value: T) {

    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    fun traversInOrder(visit: BinaryVisitor<T>) {
        leftChild?.traversInOrder(visit)
        visit(value)
        rightChild?.traversInOrder(visit)
    }

    fun traversePreOrder(visit: BinaryVisitor<T>) {
        visit(value)
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
    }

    fun traversePostOrder(visit: BinaryVisitor<T>) {
        leftChild?.traversePostOrder(visit)
        rightChild?.traversePostOrder(visit)
        visit(value)
    }

}