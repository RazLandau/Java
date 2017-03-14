/**
 * Represents 1 node of a Polynom.
 * This class has 2 constructors plus a copy constructor, and methods of get, set and String.
 * @author Raz Landau 
 * @version (2013)
 */

public class PolyNode
{
    private int _power;
    private double _coefficient;
    private PolyNode _next;
    
    //Constructors:
    
    /**
     * Constructor for a PolyNode
     * Constructs a new PolyNode with given power and coefficient, and sets next indicator to null.
     * If given power is negative, sets both power and coefficient to 0.
     * Both the runtime and the memory complexity of this methods is O(1).
     * @param power
     * @param coefficient
     */
    public PolyNode (int power, double coefficient)
    {
        if (power < 0)
        {
            _power = 0;
            _coefficient = 0;
        }
        else
        {
            _power = power;
            _coefficient = coefficient;
        }
        _next = null;
    }
    
    /**
     * Constructor for a PolyNode
     * Constructs a new PolyNode with given power, coefficient and next indicator.
     * Both the runtime and the memory complexity of this methods is O(1).
     * @param power
     * @param coefficient
     * @param next the PolyNode this PolyNode is linked to
     */
    public PolyNode (int power, double coefficient, PolyNode next)
    {
       this(power, coefficient);  // Calls PolyNode(int,double) constructor
       _next = next;
    }
    
    /**
     * Copy constructor for PolyNode
     * Constructs a new PolyNode with the same attributes as another PolyNode
     * Both the runtime and the memory complexity of this methods is O(1).
     * @param p the copied PolyNode
     */
    public PolyNode (PolyNode p)
    {
        _power = p._power;
        _coefficient = p._coefficient;
        _next = p._next;
    }
    
    
    //Methods:
    
    /**
     * Returns the power of the PolyNode
     * Both the runtime and the memory complexity of this methods is O(1).
     * @return the power of this PolyNode
     */
    public int getPower()
    {
        return _power;
    }
    
    /**
     * Returns the coefficient of the PolyNode
     * Both the runtime and the memory complexity of this methods is O(1).
     * @return the coefficient of this PolyNode
     */
    public double getCoefficient()
    {
        return _coefficient;
    }
    
    /**
     * Returns the PolyNode the PolyNode is linked to
     * Both the runtime and the memory complexity of this methods is O(1).
     * @return the PolyNode this PolyNode is linked to
     */
    public PolyNode getNext()
    {
        return _next;
    }
    
    /**
     * Changes the power of the PolyNode
     * If given power is negative, sets both power and coefficient to 0.
     * Both the runtime and the memory complexity of this methods is O(1).
     * @param power the new power
     */
    public void setPower (int power)
    {
        if (power < 0)
        {
            _power = 0;
            _coefficient = 0;
        }
        else _power = power;
    }
    
    /**
     * Changes the coefficient of the PolyNode
     * Both the runtime and the memory complexity of this methods is O(1).
     * @param coefficient the new coefficient
     */
    public void setCoefficient (double coefficient)
    {
        _coefficient = coefficient;
    }
    
    /**
     * Changes the next indicator of the PolyNode
     * Both the runtime and the memory complexity of this methods is O(1).
     * @param next the new next indicator
     */
    public void setNext (PolyNode next)
    {
        _next = next;
    }
    
    /**
     * Returns a string representation of the PolyNode
     * Both the runtime and the memory complexity of this methods is O(1).
     * @return String representation of this PolyNode
     */
    public String toString()
    {
        // If coefficient equals 0 returns empty string
        if (_coefficient == 0)
            return "";
        // If power equals 0 return coefficient alone
            else if (_power == 0)
            return ""+_coefficient;
        // If power equals 1
        else if (_power == 1)
        {
            // Special Case: If coefficient equals 1 return x
            if (_coefficient == 1)
                return "x";
            //  Special Case: If coefficient equals 1 return -x
            else if (_coefficient == -1)
                return "-x";
            // General Case: return coefficient
            else return _coefficient + "x";
        }
        // If power equals -1: The exact negative of the above (if power equals 1)
        else if (_power == -1)
        {
            if (_coefficient == 1)
                return "-x";
            else if (_coefficient == -1)
                return "x";
            else return "-" + _coefficient + "x";
        }
        // If coefficient equals 1 return power
        else if (_coefficient == 1)
            return "x^" + _power;
        // If coefficient equals -1 return the exact negative of the above (if coefficient equals 1)
        else if (_coefficient == -1)
            return "-x^" + _power;
        // End of special cases
        // General Case: simple string
        else return _coefficient + "x^" + _power;
    }
}   
        
    

    
    
    
    
    
