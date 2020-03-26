public class BST_Tester {


    public static void main(String[] args){

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        AVLTree<Integer> avl = new AVLTree<>();

        bst.insert(100);
        bst.insert(50);
        bst.insert(20);
        bst.insert(75);
        bst.insert(200);
        bst.insert(150);
        bst.insert(170);
        bst.insert(250);

        /**
        System.out.println("Question 1");
        System.out.print("Pre Order: ");
        bst.printPreOrder();
        System.out.print("\nPost Order: ");
        System.out.println(bst.getPostOrder());
         */


        avl.insert(100);
        avl.insert(200);
        avl.insert(150);
        avl.insert(170);
        avl.insert(165);
        avl.insert(180);
        avl.insert(220);
        avl.insert(163);
        avl.insert(164);


        System.out.println("Question 8 Pre Order AVL");
        System.out.print("Pre Order: ");
        avl.printPreOrder();
        System.out.print("\nPost Order: ");
        System.out.println(avl.getPostOrder());



    }
}
