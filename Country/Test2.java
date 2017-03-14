
/**
 * Student Tester for Maman 13
 */
public class Test2
{
    /**
     */
    public static void main (String[] args)
    {

        //---------------Matrix-----------------------
        
        System.out.println("\nTesting Matrix class:\n");
        int [][] intArray = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}}; 

        Matrix matrix1= new Matrix(intArray);
        System.out.println(matrix1);
        Matrix counterClockwiseMatrix= matrix1.rotateCounterClockwise();
        System.out.println(counterClockwiseMatrix);
        System.out.println(matrix1);
        
    }
}
