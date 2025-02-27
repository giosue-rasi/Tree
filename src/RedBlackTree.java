public class RedBlackTree<T extends Comparable<T>> extends BinaryTree<T> {

    public RedBlackTree() {
        super();
    }

    public RedBlackTree(BinaryNode<T> root) {
        super(root);
    }

    @Override
    public RedBlackNode<T> insert(T value) {
        RedBlackNode<T> nodeToAdd = new RedBlackNode<>(value);
        RedBlackNode<T> parent = null;
        RedBlackNode<T> child = getRoot();
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
        nodeToAdd.setColor(NodeColor.RED);
        return insertFix(nodeToAdd);
    }

    @Override
    public void remove(T value) {
        RedBlackNode<T> node = (RedBlackNode<T>) search(value);
        RedBlackNode<T> substituteNode = node;
        RedBlackNode<T> child;
        NodeColor substituteNodeColor = substituteNode.getColor();
        if(node.getLeft() == null) {
            child = node.getRight();
            transplant(node, child);
        } else if(node.getRight() == null) {
            child = node.getLeft();
            transplant(node, child);
        } else {
            substituteNode = (RedBlackNode<T>) minimum(node.getRight());
            substituteNodeColor = substituteNode.getColor();
            child = substituteNode.getRight();
            if(child != null) {
                if (substituteNode.getParent() == node) {
                    child.setParent(substituteNode);
                } else {
                    transplant(substituteNode, child);
                    substituteNode.setRight(node.getRight());
                    substituteNode.getParent().setParent(substituteNode);
                }
            }
            transplant(node, substituteNode);
            substituteNode.setLeft(node.getLeft());
            substituteNode.getLeft().setParent(substituteNode);
            substituteNode.setColor(node.getColor());
        }
        if(substituteNodeColor.equals(NodeColor.BLACK) && child != null) {
            removeFix(child);
        }
    }

    private void leftRotate(RedBlackNode<T> node) {
        if(node.getLeft() == null) {
            return;
        }

        RedBlackNode<T> rightChild = node.getRight();
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

    private void rightRotate(RedBlackNode<T> node) {
        if(node.getRight() == null) {
            return;
        }

        RedBlackNode<T> leftChild = node.getLeft();
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

    private RedBlackNode<T> insertFix(RedBlackNode<T> node) {
        while(node.getParent() != null && node.getParent().isRed()) {
            if(node.getParent() == node.getParent().getParent().getLeft()) {
                RedBlackNode<T> parentBrother = node.getParent().getParent().getRight();
                if(parentBrother != null && parentBrother.isRed()) {
                    node.getParent().setColor(NodeColor.BLACK);
                    parentBrother.setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    node = node.getParent().getParent();
                } else {
                    if(node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    rightRotate(node.getParent().getParent());
                }
            } else {
                RedBlackNode<T> parentBrother = node.getParent().getParent().getLeft();
                if(parentBrother != null && parentBrother.isRed()) {
                    node.getParent().setColor(NodeColor.BLACK);
                    parentBrother.setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    node = node.getParent().getParent();
                } else {
                    if(node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(node);
                    }
                    node.getParent().setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    leftRotate(node.getParent().getParent());
                }
            }
        }

        getRoot().setColor(NodeColor.BLACK);
        return node;
    }

    private void removeFix(RedBlackNode<T> node) {
        while(node != getRoot() && node.isBlack()) {
            if(node == node.getParent().getLeft()){
                RedBlackNode<T> brother = node.getParent().getRight();
                if(brother.isRed()) {
                    brother.setColor(NodeColor.BLACK);
                    node.getParent().setColor(NodeColor.RED);
                    leftRotate(node.getParent());
                    brother = node.getParent().getRight();
                }
                if(brother.getLeft().isBlack() && brother.getRight().isBlack()){
                    brother.setColor(NodeColor.RED);
                    node = node.getParent();
                } else {
                    if(brother.getRight().isBlack()){
                        brother.getRight().setColor(NodeColor.BLACK);
                        brother.setColor(NodeColor.RED);
                        rightRotate(brother);
                        brother = node.getParent().getRight();
                    }
                    brother.setColor(node.getParent().getColor());
                    node.getParent().setColor(NodeColor.BLACK);
                    brother.getParent().setColor(NodeColor.BLACK);
                    leftRotate(node.getParent());
                    node = getRoot();
                }
            } else {
                // as before but with right and left inverted
                RedBlackNode<T> brother = node.getParent().getLeft();
                if(brother.isRed()) {
                    brother.setColor(NodeColor.BLACK);
                    node.getParent().setColor(NodeColor.RED);
                    rightRotate(node.getParent());
                    brother = node.getParent().getLeft();
                }
                if(brother.getRight().isBlack() && brother.getLeft().isBlack()){
                    brother.setColor(NodeColor.RED);
                    node = node.getParent();
                } else {
                    if(brother.getLeft().isBlack()){
                        brother.getLeft().setColor(NodeColor.BLACK);
                        brother.setColor(NodeColor.RED);
                        leftRotate(brother);
                        brother = node.getParent().getLeft();
                    }
                    brother.setColor(node.getParent().getColor());
                    node.getParent().setColor(NodeColor.BLACK);
                    brother.getParent().setColor(NodeColor.BLACK);
                    rightRotate(node.getParent());
                    node = getRoot();
                }
            }
        }
    }

    public RedBlackNode<T> getRoot() {
        return (RedBlackNode<T>) root;
    }

    public void setRoot(RedBlackNode<T> root) {
        this.root = root;
    }
}
