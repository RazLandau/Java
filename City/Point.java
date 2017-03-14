/**
 * Represents 2 dimensional integral points on a map. Coordinates cannot be negative. 
 * 
 * @author Raz Landau 
 * @version (2013)
 */

public class Point
{
    private int _x, _y;
    private final int DEFAULT=0, MIN_X=0, MIN_Y=0;
    
    //constructors:
    /**
     *   Constructs a point.
     *   Construct a new point with the specified x y coordinates. If either coordinate is negative the coordinate will be 0.
     *   @param x The x coordinate
     *   @param y The y coordinate
     */
    
    public Point (int x, int y)
    {
        if (xValid(x))
            _x=x;
        else _x=DEFAULT;
        if (yValid(y))
            _y=y;
        else _y=DEFAULT;
    }
    
    /**
     *   Copy constructor for Points.
     *   Construct a point with the same coordinates as another point.
     *   @param other The point object from which to construct the new point
     */
    public Point (Point other)
    {
        _x = other._x;
        _y = other._y;
    }
    
    /** 
     *   Returns The x coordinate of the point.
     *   @return The x coordiante of the point
     */
    public int getX()
    {
        return _x;
    }
    
    /** 
     *   Returns The y coordinate of the point.
     *   @return The y coordiante of the point
     */
    public int getY()
    {
        return _y;
    }
    
    /** 
     *  Changes the x coordinate of the point. If a negative number is received x will be set to 0.
     *  @param x The new x coordinate
     */
    public void setX(int x)
    {
        if (xValid(x)) 
            _x=x;
        else _x= DEFAULT;
    }
    
    /** 
     *  Changes the y coordinate of the point. If a negative number is received y will be set to 0.
     *  @param y The new y coordinate
     */
    public void setY(int y)
    {
       if (yValid(y))
            _y=y;
       else _y= DEFAULT;
    }
    
     /** 
     *  Return a string representation of this point.
     *  @return String representation of this point
     */ 
    public String toString ()
    {
    return "("+_x+","+_y+")";
    }
    
     /** 
     *  Check if the received point is equal to this point.
     *  @param Point 
     */ 
    public boolean equals (Point other)
    {
        return (this._x==other._x && this._y==other._y);
    }
    
     /** 
     *  Check if this point is above a received point.
     *  @param other The point to check if this point is above
     *  @return True if this point is above other point
     */
    public boolean isAbove (Point other)
    {
        return (this._y>other._y);
    }
    
    /** 
     *  Check if this point is below a received point.
     *  @param other The point to check if this point is below
     *  @return True if this point is below other point
     */
     public boolean isUnder (Point other)
    {
        return (other.isAbove(this));
    }
    
    /** 
     *  Check if this point is left of a received point.
     *  @param other The point to check if this point is left of
     *  @return True if this point is left of other point
     */
    public boolean isLeft (Point other)
    {
        return (this._x<other._x);
    }
     
     /** 
     *  Check if this point is right of a received point.
     *  @param other The point to check if this point is right of
     *  @return True if this point is right of other point
     */
    public boolean isRight (Point other)
    {
        return (other.isLeft(this));
    }
    
    /** 
     * Check the distance between this point and a received point.
     * @param p The point to check distance from
     *  @return double representing the distance
     */ 
    public double distance (Point p)
    {
        return Math.sqrt( (Math.pow((p.getX()-this.getX()),2)) + (Math.pow((p.getY()-this.getY()),2)) );
    }
    
    /** 
     *  Move the x coordinate dX and the y coordinate dY. If the new coordinates are negative they will be set to 0.
     *  @param dX The amount to move x
     *  @param dY - The amount to move yt
     */ 
    public void move (int dX, int dY)
    {
        if (xValid(_x+dX) && yValid(_y+dY))
        {
            setX(_x+dX);
            setY(_y+dY);
        }
    }
    
    /** 
     *  Return a new point in between this point and a received point.
     *  @param other a received point
     *  @return new Point in between this point and the received point
     */ 
    public Point middle (Point other)
    {
        return new Point ((_x+other._x)/2, (_y+other._y)/2);
    }
    
    // Checks if x cordinate is in range (positive)
    private boolean xValid (int x)
    {
        return (x>=MIN_Y);
        }
    
    // Checks if y cordinate is in range (positive)
    private boolean yValid (int y)
    {
        return (y>=MIN_Y);
        }
    
    // Checks if point is in range (1st quarter)
    private boolean pointIsValid (int x, int y)
    {
        return (xValid(x) && yValid(y));
    }
}
        
   
