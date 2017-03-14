/**
 * This method recieves a Matrix filled with 0 and 1. A "sink" is when a row is filled with 0 and the same
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

public class A2
{
    public static boolean find2 (int [][] mat, int x)
    {
        int 
        firstRow = 0,
        lastRow = mat.length-1,
        firstCol = 0,
        lastCol = mat[0].length-1,
        index = 0;
        if (x < mat[firstRow][firstCol] || x > mat[lastRow][lastCol])
            return false;
        else do
        {
            int middleRow = (firstRow + lastRow) / 2;
            int middleCol = (firstCol + lastCol) / 2;
            index = mat[middleRow][lastCol];
            if(x > index)
                {
                    firstRow = middleRow + 1;
                    index = mat[lastRow][middleCol];
                    if(x > index)
                    {
                        firstCol= middleCol + 1;
                        index = mat[lastRow][firstCol];
                    }
                    else if (x < index)
                    {
                        lastCol = middleCol;
                        index = mat[lastRow][lastCol];
                    }
                }
            else if (x < index)
                {
                    lastRow = middleRow;
                    index = mat[lastRow][middleCol];
                    if (x > index)
                    {
                        firstCol= middleCol + 1;
                        index = mat[lastRow][firstCol];
                    }
                    else if (x < index)
                    {
                        lastCol = middleCol;
                        index = mat[lastRow][firstCol];
                    }
                }
        } while (index != x && firstCol != lastCol);
        
        if(index == x)
            return true;
        else return false;
            }
        }
        
        /**
         * 
         int 
        firstRow = 0,
        lastRow = mat.length-1,
        firstCol  = 0,
        lastCol = mat[0].length-1,
        middle = 0;
        if (x < mat[firstRow][firstCol] || x > mat[lastRow][lastCol])
            return false;
        else do
        {
            int middleRow = (firstRow + lastRow) / 2 + 1;
            int middleCol = (firstCol + lastCol) / 2 + 1;
            if (x<middle)
                    lastRow = middleRow - 1;
            if (x>middle)
                firstCol = lastCol
    
            if (firstRow == lastRow)
            {
                int middleCol = (firstCol + lastCol) / 2;
                middle = mat [firstRow][middleCol];
                if (x < middle)
                    lastCol = middleCol;
                if (x > middle)
                    firstCol = middleCol + 1;
            }
                
            else 
            {
                int middleRow = (firstRow + lastRow) / 2 + 1;
                middle = mat[middleRow][firstCol];
                if (x<middle)
                    lastRow = middleRow - 1;
                    lastCol = 
                if (x>middle)
                    firstRow = middleRow;
            }
        } while (middle != x && firstCol <= lastCol);
        
        if (middle == x)
            return true;
        else return false;
        }
    }
    **/
