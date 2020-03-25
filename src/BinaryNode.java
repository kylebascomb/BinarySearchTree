
public class BinaryNode<AnyType extends Comparable<? super AnyType>>
{
    // Constructors
    BinaryNode( AnyType theElement )
    {
        this( theElement, null, null, null );
    }

    BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt )
    {
        element  = theElement;
        left     = lt;
        right    = rt;
        parent   = pt;
    }

    AnyType element;            // The data in the node
    BinaryNode<AnyType> left;   // Left child
    BinaryNode<AnyType> right;  // Right child
    BinaryNode<AnyType> parent;
}
