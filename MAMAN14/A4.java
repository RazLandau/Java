/**
 * This method recieves an array of numbers and a single number, and checks if any combination of the numbers
 in the array can add up to the given number. If it can- returns true and prints the used numbers. If not- returns false.
 * @param arr Given array
 * @param num The given inspired sum
 */
public class A4
{
    public static boolean isFillBag2 (int [] arr, int num)
    {
        // Starts private method isFillBag with index 0 (start of the array)
        return isFillBag2 (arr, 0, num);
    }
    
    /**
     * This method uses recursivity and backtracking by always checking 2 situations: 
     "Taking" the index number (and substracting it from the inspired sum), or NOT taking it, then
     uses a recursive call on the array without that number. Finally, prints the numbers that were used.
     * @param values The given array
     * @param i the index
     * @param amount The given inspired sum
     */
    private static boolean isFillBag2 (int [] values, int i, int amount)
    {
        // Base case of recursivity- End of the array, or negative numbers
        // (Since we're talking about actualt weights, it's pointless to check after the substracted amount is below 0)
        if (i == values.length || amount<0)
            return false;
        if (amount == 0)
            return true;
        // Rescursive calls that always check 2 situations: Taking the index number, or not taking it.
        if (isFillBag2 (values, i+1, amount-values[i]))
        {
            System.out.print(values[i]+" ");
            return true;
        }
        if (isFillBag2 (values, i+1, amount))
            return true;
        return false;
     
    }
}