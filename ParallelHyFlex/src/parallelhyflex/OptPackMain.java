package parallelhyflex;

import parallelhyflex.algebra.collections.Tree;

/**
 *
 * @author kommusoft
 */
public class OptPackMain {
    
    public static void main (String[] args) {
        Tree<Integer> tree = new Tree<Integer>();
        System.out.println(tree);
        printlist(tree);
        tree.add(1);
        System.out.println(tree);
        printlist(tree);
        tree.add(3);
        System.out.println(tree);
        printlist(tree);
        tree.add(5);
        System.out.println(tree);
        printlist(tree);
        tree.add(7);
        System.out.println(tree);
        printlist(tree);
        tree.add(9);
        System.out.println(tree);
        printlist(tree);
        tree.add(10);
        System.out.println(tree);
        printlist(tree);
        tree.add(12);
        System.out.println(tree);
        printlist(tree);
        tree.add(14);
        System.out.println(tree);
        printlist(tree);
        tree.add(16);
        System.out.println(tree);
        printlist(tree);
        tree.remove(9);
        System.out.println(tree);
        printlist(tree);
    }

    private static void printlist(Tree<Integer> tree) {
        System.out.print("[");
        for(Integer i : tree) {
            System.out.print(String.format(" %s ",i));
        }
        System.out.println("]");
    }
    
}
