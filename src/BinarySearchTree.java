
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    public void insert(AnyType toAdd) {
        BinaryNode<AnyType> nodeToAdd = new BinaryNode<AnyType>(toAdd);
        if(root == null) {
            root = nodeToAdd;
        }else {
            insert(nodeToAdd, root);
        }

    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }


    public AnyType findMin() {
        return findMin(root).element;
    }

    public AnyType findMax(){
        return findMax(root).element;
    }


    //QUESTION 3
    public int getHeight() {
        return getHeight(root, 0);
    }

    //QUESTION 6
    public void printInOrder() {
        printInOrder(root);
    }

    public void printPreOrder(){
        printPreOrder(root);
    }

    //QUESTION 7
    public String getPostOrder() {
        StringBuilder postOrder = new StringBuilder("");
        postOrder = getPostOrder(root, postOrder);
        return postOrder.toString();
    }

    //QUESTION 8
    public int getNumberOfNodes() {
        return getNumberOfNodes(root, 0);
    }

    public boolean allPassed() {
        return allPassed(root);
    }

    private void insert(BinaryNode<AnyType> toAdd, BinaryNode<AnyType> root) {

        if(toAdd.element.compareTo(root.element) <= 0) {
            if(root.left == null) {
                root.left = toAdd;
                toAdd.parent = root;
            }else {
                insert(toAdd, root.left);
            }
        }
        else {
            if(root.right == null) {
                root.right = toAdd;
                toAdd.parent = root;
            }else {
                insert(toAdd, root.right);
            }
        }
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> root) {
        if(root == null) {
            return null;
        }
        else if(root.left == null){
            return root;
        }
        return findMin(root.left);
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> root) {
        if(root == null) {
            return null;
        }
        else if(root.right == null){
            return root;
        }
        return findMax(root.right);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> root) {
        if(root == null) {
            return false;
        }
        int compareResult = x.compareTo(root.element);

        if(compareResult < 0) {
            return contains(x,root.left);
        }
        else if(compareResult > 0) {
            return contains(x,root.right);
        }
        else
            return true;
    }



    // QUESTION 3
    private int getHeight(BinaryNode<AnyType> root, int height) {
        if(root == null) {
            return height - 1;
        }
        int leftHeight = getHeight(root.left,  height+1);
        int rightHeight =  getHeight(root.right, height+1);
        return Math.max(leftHeight, rightHeight);
    }

    //QUESTION 4
    private boolean passedTest(BinaryNode<AnyType> node) {
        int leftHeight = getHeight(node.left, 0);
        int rightHeight = getHeight(node.right, 0);
        return (Math.abs(leftHeight - rightHeight) <= 1);
    }

    //QUESTION 5
    private boolean allPassed(BinaryNode<AnyType> root) {
        if(root == null) {
            return true;
        }
        boolean check = true;
        check = allPassed(root.left);
        if(!check)
            return false;
        check = allPassed(root.right);
        if(!check)
            return false;
        return passedTest(root);
    }

    //QUESTION 6
    private void printInOrder(BinaryNode<AnyType> root) {

        if(root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.element + ", ");
        printInOrder(root.right);

    }

    private void printPreOrder(BinaryNode<AnyType> root){
        if(root == null){
            return;
        }
        System.out.print(root.element + ", ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    // QUESTION 7
    private StringBuilder getPostOrder(BinaryNode<AnyType> root, StringBuilder postOrder) {
        if(root == null) {
            return postOrder;
        }
        postOrder = getPostOrder(root.left, postOrder);
        postOrder = getPostOrder(root.right, postOrder);
        postOrder.append(root.element + " ");
        return postOrder;
    }

    // QUESTION 8
    private Integer getNumberOfNodes(BinaryNode<AnyType> root, Integer count) {
        if(root == null) {
            return count;
        }
        count = getNumberOfNodes(root.left, count);
        count = getNumberOfNodes(root.right,count);
        return count + 1;
    }


    private BinaryNode<AnyType> root;
}


 