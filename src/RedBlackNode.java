public class RedBlackNode<AnyType> extends BinaryNode {


    RedBlackNode(Comparable theElement) {
        super(theElement);
        isRed = true;
    }

    RedBlackNode(Comparable theElement, BinaryNode lt, BinaryNode rt, BinaryNode pt) {
        super(theElement, lt, rt, pt);
        isRed = true;
    }

    private boolean isRed;
}
