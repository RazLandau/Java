
/**
 * Student Tester for Maman 13
 */
public class MatrixTester
{
    /**
     */
    public static void main (String[] args)
    {

        //---------------Matrix-----------------------
        
        System.out.println("\nTesting Matrix class:\n");
        int [][] intArray = new int[4][8];
        intArray[1][7] = 2;
        intArray[0][6] = 5;
        
        Matrix matrix1= new Matrix(intArray);
        Matrix matrix2= new Matrix(7,14);
        System.out.println(matrix1);
        
        matrix1= new Matrix(intArray);
        Matrix horizontalMatrix= matrix1.flipHorizontal();
        System.out.println(horizontalMatrix);
        
        matrix1= new Matrix(intArray);
        Matrix verticalMatrix= matrix1.flipVertical();
        System.out.println(verticalMatrix);
    
        matrix1= new Matrix(intArray);
        Matrix clockwiseMatrix= matrix1.rotateClockwise();
        System.out.println(clockwiseMatrix);
        
        matrix1= new Matrix(intArray);
        Matrix counterClockwiseMatrix= matrix1.rotateCounterClockwise();
        System.out.println(counterClockwiseMatrix);
        
    }
}