/**0
 * This method recieves a Matrix filled with x and blanks. A stain is a group of adjacent x sqaures
 (diagnoals count). This methods recieves a specific sqaure in that Matrix and returns the stain size of that coordinate.
 * @param mat The given Matrix
 * @row The y coordinate of given point (starts with 0)
 * @col The x coordinate of given point (starts with 0)
 * @author Raz Landau 
 * @version (2013)
 */
public class A3
{
 
     public static void main (String[] args){
        
        char [][] stainMat={
        {' ','x',' ',' ','x'},
        {'x',' ',' ','x','x'},
        {' ',' ','x','x',' '},
        {'x',' ',' ',' ',' '},
        {'x','x','x',' ',' '}};

        System.out.println("Question 3\n=============");              
        System.out.println("3) stain method output is: " +  A3.stain2(stainMat,1,8) + "  while the answer is: 5\n");
    }
 
 public static int stain2 (char [][] mat, int row, int col)
    {
        // Base case of recursivity is any bound of the Matrix or an empty square (order matters)
        // (Impotant to mention: Coordinateds given outside Matrix boundries will return 0 as well [Didn't know whether I should add a failure message])
        if (row > mat.length-1 || row < 0 || col > mat[0].length-1 || col < 0 || mat[row][col] != 'x')
            return 0;
        else 
        {
            mat[row][col] = ' '; // To prevent endless recursivity
            return (1 +
            // Recursive call for all squares around given coordinate (including diagnols)
                stain2 (mat, row, col+1) +
                stain2 (mat, row, col-1) +
                stain2 (mat, row+1, col) +
                stain2 (mat, row-1, col) +
                stain2 (mat, row+1, col+1) +
                stain2 (mat, row+1, col-1) +
                stain2 (mat,row-1, col-1) +
                stain2 (mat, row-1, col+1));
            }
    }
}
