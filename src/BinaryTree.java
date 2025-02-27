public class BinaryTree<T extends Comparable<T>> extends Tree<T> {

    public BinaryTree() {
        super();
    }

    public BinaryTree(BinaryNode<T> root) {
        super(root);
    }

    @Override
    public BinaryNode<T> search(T value) {
        return binarySearch(value, getRoot());
    }

    @Override
    public BinaryNode<T> insert(T value) {
        BinaryNode<T> searchResult = binarySearch(value, getRoot());
        if(searchResult != null) { return searchResult; }

        BinaryNode<T> parent = null;
        BinaryNode<T> current = getRoot();
        while (current != null) {
            parent = current;
            if (value.compareTo(current.getValue()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        BinaryNode<T> newNode = new BinaryNode<T>(value, parent);

        if (parent == null) {
            setRoot(newNode);
        } else if (value.compareTo(parent.getValue()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        return newNode;
    }

    @Override
    public void remove(T value) {
        BinaryNode<T> nodeToDelete = binarySearch(value, getRoot());
        System.out.println("Node to delete: " + nodeToDelete);

        if(nodeToDelete == null) { return; }

        if (nodeToDelete.getLeft() == null) {
            transplant(nodeToDelete, nodeToDelete.getRight());
        } else if (nodeToDelete.getRight() == null) {
            transplant(nodeToDelete, nodeToDelete.getLeft());
        } else {
            BinaryNode<T> localMinimum = minimum(nodeToDelete.getRight());
            if (localMinimum.getParent() != nodeToDelete) {
                transplant(localMinimum, localMinimum.getRight());
                localMinimum.setRight(nodeToDelete.getRight());
                localMinimum.getRight().setParent(localMinimum);
            }
            transplant(nodeToDelete, localMinimum);
            localMinimum.setLeft(nodeToDelete.getLeft());
            localMinimum.getLeft().setParent(localMinimum);
        }
    }

    @Override
    public BinaryNode<T> minimum() {
        return minimum(getRoot());
    }

    BinaryNode<T> minimum(BinaryNode<T> node) {
        while(node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    @Override
    public BinaryNode<T> maximum() {
        return maximum(getRoot());
    }

    private BinaryNode<T> maximum(BinaryNode<T> node) {
        while(node.getRight() != null) {
            node = node.getRight();
        }

        return node;
    }

    public BinaryNode<T> successor(BinaryNode<T> node) {
        if(node.getRight() != null) {
            return minimum(node);
        }

        BinaryNode<T> parent = node.getParent();
        while(parent != null && node == parent.getRight()) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    public BinaryNode<T> predecessor(BinaryNode<T> node) {
        if(node.getLeft() != null) {
            return maximum(node);
        }

        BinaryNode<T> parent = node.getParent();
        while(parent != null && node == parent.getLeft()) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    private BinaryNode<T> binarySearch(T value, BinaryNode<T> node) {
        if(node == null) { return null; }
        if(node.getValue().equals(value)) { return node; }

        if(value.compareTo(node.getValue()) < 0) { return binarySearch(value, node.getLeft()); }
        else { return binarySearch(value, node.getRight()); }
    }

    void transplant(BinaryNode<T> a, BinaryNode<T> b) {
        if (a.getParent() == null) setRoot(b);
        else if (a == a.getParent().getLeft()) a.getParent().setLeft(b);
        else a.getParent().setRight(b);

        b.setParent(a.getParent());
    }

    public BinaryNode<T> getRoot(){
        return (BinaryNode<T>) root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return subtreeToString(getRoot());
    }

    private String subtreeToString(BinaryNode<T> node) {
        if (node != null) {
            return subtreeToString(node.getLeft())
                    + node.toString()
                    + subtreeToString(node.getRight());
        }

        return "";
    }
}
