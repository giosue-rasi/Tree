import utils.TreeType;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new RedBlackTree<>();

        tree.insert(30);
        tree.insert(25);
        tree.insert(10);
        tree.insert(68);

        System.out.println("searching 30: " + tree.search(30));

        System.out.println(tree);
        System.out.println("|------------------------------------|");
        tree.remove(68);
        System.out.println(tree);
    }
}