public class Q7
{
    public static void main (String [] arg)
    {
        System.out.print(foo (4,5));
    }
    
    public static int foo (int a, int b)
    {
        if (a>3)
            return 2 + foo (b-1, a+1);
        if (b<=4)
            return 1+foo(a-1, b+1);
        return 0;
    }
}