public class BlackRedTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BlackRedTree() {
        super();
    }

    public BlackRedTree(BinaryNode<T> root) {
        super(root);
    }

    @Override
    public BlackRedNode<T> insert(T value) {
        BlackRedNode<T> nodeToAdd = new BlackRedNode<>(value);
        BlackRedNode<T> parent = null;
        BlackRedNode<T> child = getRoot();
        while (child != null) {
            parent = child;
            if(value.compareTo(child.getValue()) < 0) {
                child = child.getLeft();
            } else {
                child = child.getRight();
            }
        }
        nodeToAdd.setParent(parent);
        if(parent == null) {
            setRoot(nodeToAdd);
        } else if(value.compareTo(parent.getValue()) < 0) {
            parent.setLeft(nodeToAdd);
        } else {
            parent.setRight(nodeToAdd);
        }
        nodeToAdd.setLeft(null);
        nodeToAdd.setRight(null);
        nodeToAdd.setColor(true);
        return insertFix(nodeToAdd);
    }

    @Override
    public void remove(T value) {
        BlackRedNode<T> node = new BlackRedNode<>(value);
        BlackRedNode<T> originalNode = node;
        BlackRedNode<T> child = null;
        boolean originalNodeColor = originalNode.isRed();
        if(node.getLeft() == null) {
            child = node.getRight();
            transplant(node, child);
        } else if(node.getRight() == null) {
            child = node.getLeft();
            transplant(node, child);
        } else {
            originalNode = (BlackRedNode<T>) minimum(node.getRight());
            originalNodeColor = originalNode.isRed();
            child = originalNode.getRight();
            if(originalNode.getParent() == node) {
                child.setParent(originalNode);
            } else {
                transplant(originalNode, originalNode.getParent());
                originalNode.setRight(node.getRight());
                originalNode.getParent().setParent(originalNode);
            }
            transplant(node, originalNode);
            originalNode.setLeft(node.getLeft());
            originalNode.getLeft().setParent(originalNode);
            originalNode.setColor(node.isRed());
        }
        if(originalNodeColor == false) {
            removeFix(child);
        }
    }

    private void leftRotate(BlackRedNode<T> node) {
        if(node.getLeft() == null) {
            return;
        }

        BlackRedNode<T> rightChild = node.getRight();
        rightChild.setRight(node.getLeft());

        if(node.getLeft() != null) {
            node.getLeft().setParent(rightChild);
        }

        rightChild.setParent(node.getParent());
        if(node.getParent() == null) {
            root = rightChild;
        } else if (node.getParent().getLeft() == node) {
            node.getParent().setLeft(rightChild);
        } else {
            node.getParent().setRight(rightChild);
        }

        rightChild.setLeft(node);
        node.setParent(rightChild);
    }

    private void rightRotate(BlackRedNode<T> node) {
        if(node.getRight() == null) {
            return;
        }

        BlackRedNode<T> leftChild = node.getLeft();
        leftChild.setLeft(node.getRight());

        if(node.getRight() != null) {
            node.getRight().setParent(leftChild);
        }

        leftChild.setParent(node.getParent());
        if(node.getParent() == null) {
            root = leftChild;
        } else if (node.getParent().getRight() == node) {
            node.getParent().setRight(leftChild);
        } else {
            node.getParent().setLeft(leftChild);
        }

        leftChild.setRight(node);
        node.setParent(leftChild);
    }

    private BlackRedNode<T> insertFix(BlackRedNode<T> node) {
        while(node.getParent().isRed()) {
            if(node.getParent() == node.getParent().getParent().getLeft()) {
                BlackRedNode<T> parentBrother = node.getParent().getParent().getRight();
                if(parentBrother.isRed()) {
                    node.getParent().setColor(false);
                    parentBrother.setColor(false);
                    node.getParent().getParent().setColor(true);
                    node = node.getParent().getParent();
                } else {
                    if(node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().setColor(false);
                    node.getParent().getParent().setColor(true);
                    rightRotate(node.getParent().getParent());
                }
            } else {
                BlackRedNode<T> parentBrother = node.getParent().getParent().getLeft();
                if(parentBrother.isRed()) {
                    node.getParent().setColor(false);
                    parentBrother.setColor(false);
                    node.getParent().getParent().setColor(true);
                    node = node.getParent().getParent();
                } else {
                    if(node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(node);
                    }
                    node.getParent().setColor(false);
                    node.getParent().getParent().setColor(true);
                    leftRotate(node.getParent().getParent());
                }
            }
        }

        getRoot().setColor(false);
        return node;
    }

    private BlackRedNode<T> removeFix(BlackRedNode<T> node) {
        //TODO: implement removeFix

        return null;
    }

    public BlackRedNode<T> getRoot() {
        return (BlackRedNode<T>) root;
    }

    public void setRoot(BlackRedNode<T> root) {
        this.root = root;
    }
}
