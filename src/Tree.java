import java.util.List;

public abstract class Tree<T extends Comparable<T>> {
    protected Node<T> root;

    protected Tree() {
        root = null;
    }

    protected Tree(Node<T> root) {
        this.root = root;
    }

    public abstract Node<T> search(T value);
    public abstract Node<T> insert(T value);
    public abstract void remove(T value);
    public abstract Node<T> minimum();
    public abstract Node<T> maximum();

}
