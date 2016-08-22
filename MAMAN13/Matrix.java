/**
 * Represents a matrix. Matrix is represented by a 2-dimensional array.
 * 
 * @author Raz Landau 
 * @version (2013)
 */

public class Matrix
{
    private int [][] _matrix;
    
    
    
    //constructors:
    /**
     * Constructs a size1 by size2 Matrix of zeros.
     * @param size1 Number of rows
     * @param size2 Number of columns
     */
    public Matrix (int size1, int size2)
    {
        _matrix = new int [size1][size2];
        for (int i=0; i<_matrix.length; i++)
            for (int j=0; j<_matrix[0].length; j++)
                _matrix [i][j] = 0;
    }
    
    /**
     * Construct a Matrix from a two-dimensional array, the dimensions as well as the values of this Matrix
       will be the same as the dimensions and values of the two-dimensional array.
     * @param array The two-dimensional array
     */
    public Matrix (int[][] array)
    {
        // Assuming the given array is valid, as instructed
        _matrix = new int [array.length][array[0].length];
        for (int i=0; i<_matrix.length; i++)
            for (int j=0; j<_matrix[0].length; j++)
                _matrix[i][j] = array [i][j]; // This is not aliasing, since the array contains primitive parameters (numbers between 0-255)
    }
    
    /** 
     *   Returns a string representation of this Matrix.
     *   No unnecessary blank spaces.
     *   @return String representation of this Matrix
     */
    public String toString ()
    {
        String s = "";
        for (int i=0; i<_matrix.length; i++)
        {
            for (int j=0; j<_matrix[0].length-1; j++)
                s = s + _matrix[i][j]+"\t";
            s = s + _matrix[i][_matrix[0].length-1]+"\n";      
        }
        
        return s;    
    }
    
    /**
     * Returns a Matrix object representing the Matrix flipped on its horizontal axis.
     * The first row switches places with the last row, the 2nd row switches places with the 2nd from last row, etc.
     * @return The horizontaly-flipped Matrix
     */
    public Matrix flipHorizontal ()
    {
        Matrix horizontalMatrix = new Matrix (_matrix.length,_matrix[0].length);
        for (int i=0; i<horizontalMatrix._matrix.length; i++)
            for (int j=0; j< horizontalMatrix._matrix[0].length; j++)
                horizontalMatrix._matrix [i][j] = _matrix [_matrix.length-1-i][j];
    
        return horizontalMatrix;
    }

    /**
     * Returns a Matrix object representing the Matrix flipped on its vertical axis.
     * The first column switches places with the last clumn, the 2nd column switches places with the 2nd from last column, etc.
     * @return The verticaly-flipped Matrix
     */
    public Matrix flipVertical ()
    {
        Matrix verticalMatrix = new Matrix (_matrix.length,_matrix[0].length);
        for (int i=0; i<verticalMatrix._matrix.length; i++)
            for (int j=0; j< verticalMatrix._matrix[0].length; j++)
                verticalMatrix._matrix [i][j] = _matrix [i][_matrix[0].length-1-j];
    
        return verticalMatrix;
    }

   /**
     * Returns a Matrix object representing the Matrix rotated clockwise.
     * @return The clockwise-rotated Matrix
     */
   public Matrix rotateClockwise ()
   {
        Matrix clockwiseMatrix = new Matrix (_matrix[0].length, _matrix.length);
        for (int i=0; i<clockwiseMatrix._matrix.length; i++)
            for (int j=0; j<clockwiseMatrix._matrix[0].length; j++)
                clockwiseMatrix._matrix[i][j] = _matrix[_matrix.length-1-j][i];
                
        return clockwiseMatrix;
   }

   /**
     * Returns a Matrix object representing the Matrix rotated counter-clockwise.
     * @return The counter-clockwise-rotated Matrix
     */
   public Matrix rotateCounterClockwise ()
   {
       // Uses rotateClockwise() method 3 times for counter-clockwise rotation
       return this.rotateClockwise().rotateClockwise().rotateClockwise();

   }
}
                