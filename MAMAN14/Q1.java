public class Q1
{
    public static void main (String[] args){
        
        int[][] findSink={ //sink=1
                {0,0,0,0,0},
                {1,0,1,0,0},
                {1,0,0,0,0},
                {1,0,1,0,0},
                {1,1,1,1,0}};

        System.out.println("Question 1\n=============");        
        System.out.println("1) is Sink method output is: " +  A1.isSink2(findSink) + "  while the answer is 2\n"); 
    }
}