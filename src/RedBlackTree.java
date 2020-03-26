import org.omg.CORBA.Any;

public class RedBlackTree<AnyType extends Comparable<? super AnyType>> {

    /**
     * Construct the tree.
     */
    public RedBlackTree()
    {
        root = null;
    }

    public void insert(AnyType toAdd) {
        RedBlackNode<AnyType> nodeToAdd = new RedBlackNode<AnyType>(toAdd);
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

    private void insert(RedBlackNode<AnyType> toAdd, RedBlackNode<AnyType> root) {

        if(toAdd.element.compareTo(root.element) <= 0) {
            if(root.left == null) {
                root.left = toAdd;
                toAdd.parent = root;
                if(getHeight() >= 2) {
                    insertRebalance(toAdd);
                }
            }else {
                insert(toAdd, root.left);
            }
        }
        else {
            if(root.right == null) {
                root.right = toAdd;
                toAdd.parent = root;
                if(getHeight() >= 2) {
                    insertRebalance(toAdd);
                }
            }else {
                insert(toAdd, root.right);
            }
        }
    }

    private RedBlackNode<AnyType> findMin(RedBlackNode<AnyType> root) {
        if(root == null) {
            return null;
        }
        else if(root.left == null){
            return root;
        }
        return findMin(root.left);
    }

    private RedBlackNode<AnyType> findMax(RedBlackNode<AnyType> root) {
        if(root == null) {
            return null;
        }
        else if(root.right == null){
            return root;
        }
        return findMax(root.right);
    }

    private boolean contains(AnyType x, RedBlackNode<AnyType> root) {
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
    private int getHeight(RedBlackNode<AnyType> root, int height) {
        if(root == null) {
            return height - 1;
        }
        int leftHeight = getHeight(root.left,  height+1);
        int rightHeight =  getHeight(root.right, height+1);
        return Math.max(leftHeight, rightHeight);
    }

    //QUESTION 4
    private boolean passedTest(RedBlackNode<AnyType> node) {
        int leftHeight = getHeight(node.left, 0);
        int rightHeight = getHeight(node.right, 0);
        return (Math.abs(leftHeight - rightHeight) <= 1);
    }

    //QUESTION 5
    private boolean allPassed(RedBlackNode<AnyType> root) {
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
    private void printInOrder(RedBlackNode<AnyType> root) {

        if(root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.element + ", ");
        printInOrder(root.right);

    }

    private void printPreOrder(RedBlackNode<AnyType> root){
        if(root == null){
            return;
        }
        //prints a * is the node is black
        if(!root.isRed){
            System.out.print("*");
        }
        System.out.print(root.element + ", ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    // QUESTION 7
    private StringBuilder getPostOrder(RedBlackNode<AnyType> root, StringBuilder postOrder) {
        if(root == null) {
            return postOrder;
        }
        postOrder = getPostOrder(root.left, postOrder);
        postOrder = getPostOrder(root.right, postOrder);
        if(!root.isRed){
            postOrder.append("*");
        }
        postOrder.append(root.element + " ");
        return postOrder;
    }

    // QUESTION 8
    private Integer getNumberOfNodes(RedBlackNode<AnyType> root, Integer count) {
        if(root == null) {
            return count;
        }
        count = getNumberOfNodes(root.left, count);
        count = getNumberOfNodes(root.right,count);
        return count + 1;
    }


    private void leftRotate(RedBlackNode<AnyType> node){
        RedBlackNode<AnyType> child = node.right;
        RedBlackNode<AnyType> temp = child.left;             // left side of child
        child.left = node;
        child.parent = node.parent;                         // link parent of child to parent of node
        //if node is root
        if(node.parent == null) {                           //relink parent of node
            this.root = child;
        }else if(node.parent.right == node){
            node.parent.right = child;
        } else{
            node.parent.left = child;
        }
        node.parent = child;
        node.right = temp;
        if(node.right != null) {
            node.right.parent = node;                    // relink parent of temp
        }
    }

    private void rightRotate(RedBlackNode<AnyType> node){
        RedBlackNode<AnyType> child = node.left;
        RedBlackNode<AnyType> temp = child.right;             // right side of child
        child.right = node;
        child.parent = node.parent;                         // link parent of child to parent of node

        if(node.parent == null) {                           // relink parent ofnode
            this.root = child;
        } else if(node == node.parent.right){
            node.parent.right = child;
        }else{
            node.parent.left = child;
        }
        node.parent = child;
        node.left = temp;
        if(node.left != null) {
            node.left.parent = node;                    // relink parent of temp
        }
    }

    private void insertRebalance(RedBlackNode<AnyType> node){
        while(node != root && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {                           //node is on the left
                RedBlackNode<AnyType> aunt = node.parent.parent.right;
                if(aunt != null && aunt.isRed){                                     //case 1
                    node.parent.isRed = false;
                    aunt.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                }else {
                    if (node == node.parent.right) {                                // case 2
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.isRed = false;                                      // case 3
                    node.parent.parent.isRed = true;
                    rightRotate(node.parent.parent);
                }
            } else if (node.parent == node.parent.parent.right) {                   //node is on the right
                RedBlackNode<AnyType> aunt = node.parent.parent.left;
                if (aunt != null && aunt.isRed) {                                   // case 1
                    node.parent.isRed = false;
                    aunt.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {                                  // case 2
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.isRed = false;                                       // case 3
                    node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = false;
    }


    private RedBlackNode<AnyType> root;
}

