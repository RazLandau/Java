/**
 * Represents Polynom by using a linked list.
 * @author Raz Landau 
 * @version (2013)
 */

public class Polynom
{
    private PolyNode _head;
    
    
    //Constructors:
    
    /**
     * Constructor for a Polynom
     * Constructs a new null Polynom
     */
    public Polynom ()
    {
        _head = null;
    }
    
    /**
     * Constructor for a Polynom
     * Construct a new Polynom by setting given PolyNode as the head of the new Polynom
     * @param p the given PolyNode
     */
    public Polynom (PolyNode p)
    {
        _head = p;
    }
    
    
    //Methods:
    
    /**
     * Adds a node to the Polynom in its proper place
     * The runtime complexity of this methods is O(n).
     * The memory complexity of this method is O(1).
     * @param p the new PolyNode
     * @return Polynom the new Polynom
     */
    public Polynom addNode (PolyNode p)
    {
        // If this Polynom is null, the addition is simple (add the PolyNode to the head of the Polynom)
        if (this._head == null)
            _head = p;
        else
        {
            PolyNode index = _head;
            // A loop that runs on the length of this Polynom [O(n)]
            while (index.getNext() != null && p.getPower() <= index.getNext().getPower()) 
                index = index.getNext();
            // Why did the loop end?:
            // Special Case: If there is already a Node in the Polynom with the same coefficient:
            if (p.getPower() == index.getPower())
                // Change the existing Node to the sum of the two Nodes
                index.setCoefficient(index.getCoefficient()+p.getCoefficient());
            // Special Case: If we've reached the end of the Polynom, and the added Node is smaller than the last Node
            else if (index.getNext() == null && p.getPower() < index.getPower())
                // Just add is to the end ("tail")
                index.setNext(p);
            // Special Case: If the added Node is bigger than the head of the Polynom
            else if (index == _head)
            {
                // Just set the added Node as the new head of the Polynom
                p.setNext(index);
                _head = p;
            }
            // End of special cases
            // General Case: Add the node in the middle of the Polynom
            else
            {    
                p.setNext(index.getNext()); // Link the tail added Node to the Polynom
                index.setNext(p); // Link the head of the added Node to the Polynom
            }
        }
        return this;
    }
    
    /**
     * Multiplys the Polynom by a scalar
     * The runtime complexity of this method is O(n).
     * The memory complexity of this method is O(1).
     * @param num the scalar
     * @return Polynom the mulitplied Polynom
     */
    public Polynom multByScalar (int num)
    {
        // Worth mentioning- Special Case num=0: The returned String will be empty
        PolyNode index = _head;
        // A loop that runs on the length of this Polynom [O(n)]
        while (index != null)
        {
            index.setCoefficient(index.getCoefficient() * num);
            index = index.getNext();
        }
        return this; 
    }
    
    /**
     * Adds a given Polynom to this Polynom, in the correct order
     * This method uses merge sort of two ordered linked lists, so the runtime complexity of this method is O(n+m).
     * The memory complexity of this method is O(n).
     * @param other the added Polynom
     * @return Polynom the new Polynom
     */
    public Polynom addPol (Polynom other)
    {
        // "If the given parameter is null, do not change the Polynom on which the method was activated"
        if (other._head == null)
            return this;
        PolyNode index1 = other._head; // The added Polynom index is accurate
        PolyNode dummy = new PolyNode (0, 0, this._head);
        PolyNode index2 = dummy; // Using a dummy, this Polynom index is always 1 link behind (since there's only "next" and no "tail", that's the only way we can add Nodes
        
        // A loop that runs until we've reached the end of one of the polynoms (NOT BOTH)
        while (index2.getNext() != null && index1 != null)
        {
            // As long as the added Node's power is lower that this Polynom's index power, we continue to run across this Polynom
            if (index1.getPower() < index2.getNext().getPower())
            {
                index2 = index2.getNext();
            }
            // Eureka 1: There is already a Node with the same power
            else if (index1.getPower() == index2.getNext().getPower())
            {
                // Set the existing Node to the sum of both Nodes
                index2.getNext().setCoefficient(index1.getCoefficient() + index2.getNext().getCoefficient());
                // Advance indexes:
                index1 = index1.getNext(); // Let's add another Node from the added Polynom
                index2 = index2.getNext(); // Since this Polynom is ORDERED, there is not point reseting the index. THIS IS WHY THIS METHOD RUNTIME COMPLEXITY IS NOT O(n^2)!
            }
            // Eureka 2: We found the proper place for the added Node in this Polynom
            else if (index1.getPower() > index2.getNext().getPower())
            {
                PolyNode addition = new PolyNode(index1); // This is the only way to add the Node to this Polynom without causing BAD aliasing,
                                                         // and this is why the memory complexity of this method is O(n)
                // Special Case: The added Node is bigger than the head of this Polynom (thus bigger than every Node in this Polynom)
                if (index2.getNext() == _head)
                {
                    // Set the added Node as the new head of this Polynom
                    addition.setNext(this._head);
                    _head = addition;
                }
                else
                {
                    // Add the Node, just like method AddNode
                    addition.setNext(index2.getNext());
                    index2.setNext(addition);
                }
                index1 = index1.getNext();
                index2 = index2.getNext();
            }
            
        }
        // Why did the loop end?:
        // Case 1: If this Polynom ended, but the added Polynom hasn't (there are still Nodes to be added)
        if (index2.getNext() == null)
            // The remaining Nodes are definitely smaller than the last Node, so just adding them to the "tail"
            while (index1 != null)
            {
                PolyNode addition = new PolyNode (index1); // Again, we have to add COPIES, not just "point",
                                                          // to avoid changing the added Polynom when changing this Polynom after the addition
                index2.setNext(addition);
                index1 = index1.getNext();
            }
        // If the added Polynom ended- Our work is done!
        return this;
    }
    
    /**
     * Multiply the Polynom by another Polynom
     * @param other the Polynom multplied by
     * @return Polynom the multiplied Polynom
     */
    public Polynom multPol (Polynom other)
    {
        // "If the given parameter is null, do not change the Polynom on which the method was activated"
        if (other._head == null)
            return this;
        PolyNode index1 = other._head;
        PolyNode index2 = this._head;
        Polynom multPol = new Polynom(); // We use a new Polynom to store the multiplications results
        // Loop that runs through the given Polynom
        while (index1 != null)
        {
            // Nested loop running through this Polynom
            while (index2 !=null)
            {
                // Multiplication
                PolyNode multNode = new PolyNode (index1.getPower()+index2.getPower(), index1.getCoefficient()*index2.getCoefficient());
                // Add multiplication result to new Polynom
                multPol.addNode(multNode);
                // Advance this Polynom index
                index2 = index2.getNext();
            }
            // Reset this Polynom index
            index2 = this._head;
            // Advance given Polynom index
            index1 = index1.getNext();
        }
        
        // Initialize this Polynom to the new Polynom containing the multiplication results
        this._head = multPol._head;    
        return this;
    }
    
    /**
     * Returns the differential of the Polynom
     * The runtime complexity of this method is O(n).
     * The memory complexity of this method is O(1).
     * @return Polynom the differential of given Polynom
     */
    public Polynom differential ()
    {
        PolyNode index = _head;
        // A loop that runs on the length of this Polynom [O(n)]
        while (index != null)
        {
            // Differential of index's Node
            index.setCoefficient(index.getCoefficient()*index.getPower());
            index.setPower(index.getPower()-1);
            // Advance Node
            index = index.getNext();
        }
        return this;
    }
    
    /**
     * Returns a String representation of the Polynom
     * The runtime complexity of this method is O(n).
     * The memory complexity of this method is O(1).
     * @param String the String representation of this Polynom
     */
    public String toString()
    {
        String s = "";
        // To avoid 1 Node Polynoms to String with a + sign after them
        PolyNode index = _head;
        s+= index.toString();
        index = index.getNext();
        // A loop that runs on the length of this Polynom [O(n)]
        while (index != null)
        {
            // If Node is positive adds a + sign before it
            if (index.getCoefficient() > 0)
                s+= "+" + index.toString();
            // If the Node is negative the - is already printed as the coefficient
            if (index.getCoefficient() < 0)
                s+= index.toString();
            // Advance the index
            index = index.getNext();
        }
        return s;
    }
        
}
                
        