public abstract class Node<T extends Comparable<T>> {
    protected T value;
    protected Node<T> parent;

    protected Node(T value) {
        this.value = value;
    }

    protected Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return !hasChildren();
    }

    protected abstract boolean hasChildren();

    @Override
    public boolean equals(Object other) {
        return other != null
                && (other.getClass() == this.getClass())
                && ((Node<?>) other).getValue().equals(this.value);
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}