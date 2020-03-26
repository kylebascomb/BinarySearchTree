public class RedBlackNode<AnyType extends Comparable<? super AnyType>> {


    // Constructors
    RedBlackNode( AnyType theElement )
    {
        this( theElement, null, null, null );
    }

    RedBlackNode( AnyType theElement, RedBlackNode<AnyType> lt, RedBlackNode<AnyType> rt, RedBlackNode<AnyType> pt )
    {
        element  = theElement;
        left     = lt;
        right    = rt;
        parent   = pt;
        isRed = true;
    }

    AnyType element;            // The data in the node
    RedBlackNode<AnyType> left;   // Left child
    RedBlackNode<AnyType> right;  // Right child
    RedBlackNode<AnyType> parent;
    boolean isRed;
}
