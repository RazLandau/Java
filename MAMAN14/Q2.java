public class Q2
{
    public static void main (String[] args)
    {
        {int[][] findInMat={
        {-4,-2,5,9},
        {2,5,12,13},
        {13,20,25,25},
        {22,24,49,57}};

         System.out.println("Question 2\n=============");              
        System.out.println("2) find method output is: " +  A2.find2(findInMat,48) + "  while the answer is: true\n");
        
        
        /**
        int [] a = new int [16];
        int k=0;
        for (int i=0; i<findInMat.length; i++)
            for (int l=0; l<findInMat.length; l++)
            {
                a[k] = findInMat[i][l];
                k++;
            }
                
        for (int m=0; m<a.length; m++)
            System.out.println(A2.find2(findInMat, a[m]));
            */
    }
}
}
