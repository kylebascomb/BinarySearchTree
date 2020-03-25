public class BST_Tester {


    public static void main(String[] args){

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(100);
        bst.insert(50);
        bst.insert(20);
        bst.insert(75);
        bst.insert(200);
        bst.insert(150);
        bst.insert(170);
        bst.insert(250);

        System.out.println("Question 1");
        System.out.print("Pre Order: ");
        bst.printPreOrder();
        System.out.print("\nPost Order: ");
        System.out.println(bst.getPostOrder());
    }
}
