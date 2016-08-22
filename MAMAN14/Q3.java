public class Q3
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
}