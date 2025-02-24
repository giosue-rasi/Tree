public class BinaryNode<T extends Comparable<T>> extends Node<T>{
    protected BinaryNode<T> left;
    protected BinaryNode<T> right;

    protected BinaryNode(T value){
        super(value);
    }

    protected BinaryNode(T value, BinaryNode<T> parent){
        super(value, parent);
    }

    protected BinaryNode(T value, BinaryNode<T> parent, BinaryNode<T> left, BinaryNode<T> right){
        super(value, parent);
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean hasChildren() {
        return left != null || right != null;
    }

    @Override
    public String toString() {
        String result;
        T parentValue = isRoot() ? null : parent.getValue();
        T leftChildValue = this.left == null ? null : this.left.getValue();
        T rightChildValue = this.right == null ? null : this.right.getValue();

        if(isLeaf()){
            result = "Leaf{value: " + value + " parent: " + parentValue + " }";
        } else if(isRoot()){
            result = "Root{value: " + value + " leftChild: " + leftChildValue + " rightChild: " + rightChildValue + "}";
        } else {
            result = "Node{value: " + value + " parent: " + parentValue + " leftChild: " + leftChildValue + " rightChild: " + rightChildValue + "}";
        }

        return result + "\n";
    }

    @Override
    public BinaryNode<T> getParent() {
        return (BinaryNode<T>) super.getParent();
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }
}
