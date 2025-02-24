public class BlackRedNode<T extends Comparable<T>> extends BinaryNode<T>{

    // false == BLACK | true == RED
    private boolean isRed = false;

    protected BlackRedNode(T value) {
        super(value);
    }

    protected BlackRedNode(T value, boolean isRed) {
        super(value);
        this.isRed = isRed;
    }

    protected BlackRedNode(T value, BlackRedNode<T> parent) {
        super(value, parent);
    }

    protected BlackRedNode(T value, BlackRedNode<T> parent, boolean isRed) {
        super(value, parent);
        this.isRed = isRed;
    }

    protected BlackRedNode(T value, BlackRedNode<T> parent, BlackRedNode<T> left, BlackRedNode<T> right) {
        super(value, parent, left, right);
    }

    protected BlackRedNode(T value, BlackRedNode<T> parent, BlackRedNode<T> left, BlackRedNode<T> right, boolean isRed) {
        super(value, parent, left, right);
        this.isRed = isRed;
    }

    public boolean isRed(){
        return isRed;
    }

    public void setColor(boolean color){
        this.isRed = color;
    }

    @Override
    public BlackRedNode<T> getParent() {
        return (BlackRedNode<T>) parent;
    }

    @Override
    public BlackRedNode<T> getLeft() {
        return (BlackRedNode<T>) left;
    }

    @Override
    public BlackRedNode<T> getRight() {
        return (BlackRedNode<T>) right;
    }
}
