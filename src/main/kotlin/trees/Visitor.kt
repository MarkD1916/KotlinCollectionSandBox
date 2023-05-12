package trees

typealias Visitor<T> = (TreeNode<T>) -> Unit
typealias BinaryVisitor<T> = (T) -> Unit