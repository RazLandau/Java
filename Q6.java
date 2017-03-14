public class Q6
{
    public static void main (String [] arg)
    {
        int [][] arr = {{1,2,3,4},{5,6,7,8}};
        f(arr, 0, 0, arr.length-1, arr[0].length-1);
        printArray(arr);
    }
    
    public static void f (int [][] a, int a1, int b1, int a2, int b2)
    {
    int temp = a[a1][b1];
    a[a1][b1] = a[a2][b2];
    a[a2][b2] = temp;
    if (b1 < a[0].length-1)
        f(a, a1, b1+1, a2, b2-1);
    else if (a1+1 < a2-1)
        f(a, a1+1, 0, a2-1, a[0].length-1);
    }
    
    public static void printArray (int[][] a)
    {
        for (int i=0; i<a.length; i++)
        {
            for (int j=0; j<a[i].length; j++)
                System.out.print (a[i][j] + "\t");
            System.out.println();
        }
    }
}
