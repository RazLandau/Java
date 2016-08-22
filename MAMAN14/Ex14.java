/**
 * Class Ex14
 * @author Raz Landau 
 * @version (2013)
 */

public class Ex14
{
    
//====================================================================================================================================================================================
                                                                        //Question 1
//====================================================================================================================================================================================

    /**
     * This method recieves a Matrix filled with 0 and 1, and checks if there is a "sink" in that Matrix.
     * A "sink" is when an entire row is filled with 0 and the same entire column is filled with 1 (except for the intersect sqaure, which is the "sink").
     * This method returns the sink in the given Matrix, and -1 if there isn't a sink in the Matrix.
     * This methods uses 2 private methods.
     * The complexity of this method is O(n).
     * The memory complexity of this method is O(1), since none of the memory we use is relative to n.
     * @param mat The given Matrix
     * @return The sink (returns -1 if there isn't a sink)
     */

    public static int isSink (int [][] mat)
    {
        int exit = escapeMatrix (mat);
        if (exit == -1)
            return -1;
        else return checkPotentialSink (mat, exit);
    }

    /**
     * This method "escapes" the array "diagnoly" like right-downard steps, in the following way:
       starts with the upper leftmost corner, then if index is 0- moves 1 column right; and if it's 1- moves 1 row down, until it reaches the length of the Matrix.
     * The logic is that if the index square is 0- The whole column is dismissed, and if it's 1- The whole row is dismissed, according to the defiinition of "sink".
     * The runtime complexity of this method is O(n) (Worst case = O(2n))
     * The memory complexity of this method is O(1), since none of the memory we use is relative to n.
     * @param mat The given Matrix
     * @return The row we exited from (-1 if we exited the Matrix from the bottom)
     */
    
    private static int escapeMatrix (int [][] mat)
    {
        int i = 0, j = 0;
        // End of loop- Any boundries of the Matrix = The exit point
        while (i < mat.length  &&  j < mat.length)
        {
            if (mat[i][j] == 0)
                j++;
            else i++;    
        }
        // If we escaped the Matrix from the last row (down), by definition there isn't a sink in the array, doesn't matter which column we exited from exactly
        if (i == mat.length)
            return -1;
        // However, if we escaped the Matrix from the last column (right), there MIGHT be a sink in the row we exited from, and we need to check that
        else return i;
    }

    /**
     * This method completes method escapeMatrix and checks if potential sink is indeed a valid sink by checking the sum of its row and column.
     * The runtime complexity of this method is O(n), since we use a loop to sum up 1 column and 1 row (2n).
     * The memory complexity of this method is O(1), since none of the memory we use is relative to n.
     * @param mat The given Matrix
     * @param potentialSink the potentialSink
     * @return confirmed sink (-1 if potential sink is NOT valid)
     */   
    
    private static int checkPotentialSink (int [][] mat, int potentialSink)
    {
            int sum1 = 0, sum2 = 0;
            for (int k = 0; k < mat.length; k++)
            {
                sum1 = sum1 + mat[k][potentialSink];
                sum2 = sum2 + mat[potentialSink][k];
            }
            if (sum1 == mat.length - 1  &&  sum2 == 0) // If sink, sum of row should be 0, 
                                                   //and sum of column should be the array's length minus 1 (the sink)
                  return potentialSink;
        // If ANY ONE of the sums isn't right- not only is the potential sink is NOT valid, but there CAN'T BE ANOTHER SINK IN THE MATRIX!     
        return -1;
    }
    
//====================================================================================================================================================================================
                                                                        //Question 2
//====================================================================================================================================================================================
    
    /**
     * This method recieves a sqaured Matrix the size of 2^k for a neutral k, filled with numbers, ordered by size recursivly clock-wise, and checks if given
       number is in that Matrix.
     * This method uses Binary Search to find the number in the ordered array, there for its complexity is O(longn).
     * The memory complexity of this method is O(1), since none of the memory we use is relative to n.
     * @param mat The given Matrix
     * @param x The searched number
     * @return True if number is in the Matrix, false if not
     */

    public static boolean find (int[][] mat, int x)
    {
        int 
        firstRow = 0,
        lastRow = mat.length - 1,
        firstCol = 0,
        lastCol = mat[0].length - 1,
        index = 0;
        // No need to work hard for numbers outside the array, and since the array is ordered the check is simple
        if (x < mat[firstRow][firstCol]  ||  x > mat[lastRow][lastCol])
            return false;
        // Else we use a modified Binary Search, with "index" representing "middle"
        else do
        {
            int middleRow = (firstRow + lastRow) / 2;
            int middleCol = (firstCol + lastCol) / 2;
            index = mat[middleRow][lastCol];
            if(x > index) //Check the big half
                {
                    firstRow = middleRow + 1;
                    index = mat[lastRow][middleCol];
                    if(x > index) // Check the bigger half
                    {
                        firstCol= middleCol + 1;
                        index = mat[lastRow][firstCol];
                    }
                    else if (x < index) // Check the smaller half
                    {
                        lastCol = middleCol;
                        index = mat[lastRow][lastCol];
                    }
                }
            else if (x < index) // Check the small half
                {
                    lastRow = middleRow;
                    index = mat[lastRow][middleCol];
                    if (x > index) // Check the bigger half
                    {
                        firstCol= middleCol + 1;
                        index = mat[lastRow][firstCol];
                    }
                    else if (x < index) // Check the smaller half
                    {
                        lastCol = middleCol;
                        index = mat[lastRow][firstCol];
                    }
                }
        // Boundries of loop:
        // Intentional Bound = index is "on" given number
        // Necessary Bound = the two column indexes point to the same point (and that point ISN'T the given number)
        } while (index != x  &&  firstCol != lastCol); 
        
        if(index == x) // Index is "on" given number = the number is in the array
            return true;
        else // Two column indexes point to the same point = number is NOT in the array
        return false;
    }
    
//=======================================================================================================================================================================================
                                                                        //Question 3
//=======================================================================================================================================================================================
    
    /**
     * This method recieves a Matrix filled with x's and blanks, and returns the stain size of given specific square in that Matrix.
     * A stain is a group of adjacent x sqaures (diagnoals count) of given coordinate.
     * @param mat The given Matrix
     * @param row The y coordinate of given point (starts with 0)
     * @param col The x coordinate of given point (starts with 0)
     * @return The stain size
     */

    public static int stain (char [][] mat, int row, int col)
    {
        // Base case of recursivity is any bound of the Matrix or an empty square (order matters)
        // (Impotant to mention: Coordinateds given outside Matrix boundries will return 0 as well [Didn't know whether I should add a failure message])
        if (row > mat.length-1  ||  row < 0  ||  col > mat[0].length-1  ||  col < 0  ||  mat[row][col] != 'x')
            return 0;
        else 
        {
            mat[row][col] = ' '; // To prevent endless recursivity
            return (1 +
            // Recursive call for all squares around given coordinate (including diagnols)
                stain (mat, row, col+1) +
                stain (mat, row, col-1) +
                stain (mat, row+1, col) +
                stain (mat, row-1, col) +
                stain (mat, row+1, col+1) +
                stain (mat, row+1, col-1) +
                stain (mat,row-1, col-1) +
                stain (mat, row-1, col+1));
            }
    }
    
//=======================================================================================================================================================================================
                                                                        //Question 4
//=======================================================================================================================================================================================    //================================================================================================================================================================================
    
    /**
     * This method recieves an array of numbers and a single number, and checks if any combination of the numbers
     in the array can add up to the given number. 
     * If there is such combination- Prints the numbers in the array that were used.
     * This method uses an overloading private method.
     * @param arr Given array
     * @param num The given inspired sum
     * @return True if sucessful, false if not
     */
    
    public static boolean isFillBag (int[] arr , int num)
    {
        return isFillBag (arr , 0 , num);
    } 
    
    /**
     * This method uses recursivity and backtracking by always checking 2 situations: 
       "Taking" the index number (and substracting it from the inspired sum), or NOT taking it;
       then uses a recursive call on the array without that number.
       Finally, prints the numbers that were used.
     * @param values The given array
     * @param i The index
     * @param amount The given number (inspired sum)
     * @return True if sucessful, false if not
     */
    private static boolean isFillBag (int[] values , int i ,int amount)
    {
        // Basic case of recursivity- Since we're talking about actual weights, there's no point of start/keep checking if the given/decreased sum is below zero
        if (amount < 0)
            return false;
        // Base case of recursivity- Reached end of array
        if (i == values.length)
            return false;
        // Base of case recursivity- Succesful recursivity (or special case: given targeted sum is 0)
        if (amount == 0)
            return true;
        // Rescursive calls that always check 2 situations: Taking the index number, or not taking it.
        if (isFillBag (values, i+1, amount-values[i])) 
        {
            System.out.print(values[i]+" "); // The print occurs in the backtracking, after we know the number HAS been used
                                            // (that's why the numbers are printed backwards)
            return true;
        }
        if (isFillBag (values, i+1, amount)) 
            return true;
            
        return false;
    }
}
