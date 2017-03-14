/**
 * This method recieves a Matrix the size of n x n, filled with 0 and 1. A "sink" is when a row is filled with 0 and the same
 column is filled with 1 (except for the intersect sqaure, which is the "sink"). This method returns the sink in given Matrix.
 If there isn't a sink in the Matrix, return -1.
 * The complexity of this method is O(4n) = O(n).
 * We use a loop that moves in "steps" to reach the Matrix's boundries: Worst case = 2n.
 * Then we use a loop that sums both 1 column sum and 1 row sum = 2n.
 * @param mat The given Matrix
 * @return The sink (returns -1 if there isn't a sink)
 * @author Raz Landau 
 * @version (2013)
 */

public class A1
{
    public static int isSink2 (int [][] mat)
    {
        return isSink2 (mat, escapeArray(mat));
    }
    
    private static int escapeArray (int [][] mat)
    {
        int i=0, j = 0;
        while (i < mat.length && j < mat.length)
        {
            if (mat[i][j] == 0)
                j++;
            else i++;    
        }
        if (i == mat.length)
            return -1;
        else return i;
    }
    
    private static int isSink2 (int [][] mat, int sink)
    {
        int potentialSink = escapeArray(mat);
        if (potentialSink != -1)
        {
            int sum1 = 0, sum2 = 0;
            for (int k = 0; k<mat.length; k++)
            {
                sum1 = sum1 + mat[k][potentialSink];
                sum2 = sum2 + mat[potentialSink][k];
            }
            if (sum1 == mat.length-1 && sum2 == 0) // If sink, sum of row should be 0, 
                                                   //and sum of column should be the array's length minus 1 (the sink)
                  return potentialSink;
                }
        return -1;
    }
          
            }
