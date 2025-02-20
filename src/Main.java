import utils.TreeType;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        for(int i = 0; i < 5; i++){
            tree.insert((int)(Math.random()*100));
        }

        tree.insert(30);

        for(int i = 0; i < 5; i++){
            tree.insert((int)(Math.random()*100));
        }

        System.out.println("searching 30: " + tree.search(30));

        System.out.println(tree);
        System.out.println("|------------------------------------|");
        tree.remove(30);
        System.out.println(tree);
    }
}