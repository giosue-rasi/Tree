public class RedBlackNode<T extends Comparable<T>> extends BinaryNode<T>{
    private NodeColor color = NodeColor.BLACK;

    protected RedBlackNode(T value) {
        super(value);
    }

    protected RedBlackNode(T value, RedBlackNode<T> parent) {
        super(value, parent);
    }

    protected RedBlackNode(T value, RedBlackNode<T> parent, RedBlackNode<T> left, RedBlackNode<T> right) {
        super(value, parent, left, right);
    }

    public boolean isRed(){ return color.equals(NodeColor.RED); }

    public boolean isBlack(){ return color.equals(NodeColor.BLACK); }

    NodeColor getColor(){
        return color;
    }

    void setColor(NodeColor color){ this.color = color; }

    @Override
    public RedBlackNode<T> getParent() {
        return (RedBlackNode<T>) parent;
    }

    @Override
    public RedBlackNode<T> getLeft() { return (RedBlackNode<T>) left; }

    @Override
    public RedBlackNode<T> getRight() {
        return (RedBlackNode<T>) right;
    }

    @Override
    public String toString() {
        String result;
        T parentValue = isRoot() ? null : parent.getValue();
        T leftChildValue = this.left == null ? null : this.left.getValue();
        T rightChildValue = this.right == null ? null : this.right.getValue();

        if(isLeaf()){
            result = "Leaf{value: " + value + " color: " + color + " parent: " + parentValue + " }";
        } else if(isRoot()){
            result = "Root{value: " + value + " color: " + color + " leftChild: " + leftChildValue + " rightChild: " + rightChildValue + "}";
        } else {
            result = "Node{value: " + value + " color: " + color + " parent: " + parentValue + " leftChild: " + leftChildValue + " rightChild: " + rightChildValue + "}";
        }

        return result + "\n";
    }

}

enum NodeColor {
    RED("RED"),
    BLACK("BLACK");

    final String value;

    NodeColor(String color) {
        this.value = color;
    }

    public String getValue() {
        return value;
    }
}
