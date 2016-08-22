
/**
 * Students Tester for Maman 14
 * 
 * @version 10/2013
 */
public class studentTester
{
    public static void main (String[] args){
        
        int[][] findSink={ //sink=1
                {0,1,1,0,1},
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,1,0,0},
                {0,1,1,1,0}};

        System.out.println("Question 1\n=============");        
        System.out.println("1) is Sink method output is: " +  Ex14.isSink(findSink) + "  while the answer is 2\n");        
                
        int[][] findInMat={
        {-4,-2,5,9},
        {2,5,12,13},
        {13,20,25,25},
        {22,24,49,57}};

        System.out.println("Question 2\n=============");              
        System.out.println("2) find method output is: " +  Ex14.find(findInMat,12) + "  while the answer is: true\n");    

        char [][] stainMat={
        {' ','x',' ',' ','x'},
        {'x',' ',' ','x','x'},
        {' ',' ','x','x',' '},
        {'x',' ',' ',' ',' '},
        {'x','x','x',' ',' '}};

        System.out.println("Question 3\n=============");              
        System.out.println("3) stain method output is: " +  Ex14.stain(stainMat,1,4) + "  while the answer is: 5\n");    
                
        int[] bag={1,10,3,20,14};
        
        System.out.println("Question 4\n=============");                      
        System.out.println("4) if bag is: {1,10,3,20,14} and weight is 30 then the method should return true and print 20 10\nyour method isFillBag method returns: " +  Ex14.isFillBag(bag,30) );            
    }
}