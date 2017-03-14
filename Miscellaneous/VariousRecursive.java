
public class VariousRecursive
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
       
    /**
     * @param int[][] mat - only 0's and 1's
     * finds sinks
     * a sink is an int- k - that the k's row includes only 0's and the  colum is only 1, mat[k][k] is 0 in that case
     * @return int- k if there is a sink (the sink's index). -1 if there isn't.
     */
    public static int isSink(int[][] mat)
    {
        return isSink(mat , 0 ,0);
        
    }
    
    /**
     * @param int[][] mat- only 0's and 1's
     * @param int row- the row it starts the search in
     * @param int col- the colum it starts the search in
     * a sink is an int- k - that the k's row includes only 0's and the  colum is only 1, mat[k][k] is 0 in that case
     * @return int- k if there is a sink (the sink's index). -1 if there isn't.
     * 
     */
    public static int isSink(int[][] mat , int row , int col)
    {
        if ( mat.length-1 == row)
        {
            return checkSink(mat , col);
        }
        
            else if( mat[0].length-1 == col)
            {
                return checkSink(mat , row);
            }
        
        
                else if(mat[row][col] == 0)
                    return isSink(mat , row , col +1);
            
                        else return isSink(mat , row+1 , col);
    }
    
    /**
     *@param int[][] mat - only 0's and 1's
     *@param int k- the index
     *checks if there's a sink in mat in  index k
     * a sink is an int- k - that the k's row includes only 0's and the  colum is only 1, mat[k][k] is 0 in that case
     *@return int- k if there is a sink, -1 if there isn't
     */
    public static int checkSink(int[][] mat, int k)
    {
        int x = k;
        for (int i= 0 ; i<k; i++)
        {
            if (mat[i][k] ==0)
             x = -1;
        }
        
        for (int i= k +1 ; i<mat.length-1 ; i++)
        {
            if (mat[i][k] ==0)
             x = -1;
        }
        
        for (int i= 0 ; i<mat[0].length-1 ; i++)
        {
            if (mat[k][i] ==1)
             x = -1;
        }
        return x;
    }
    
    /**
     * 
     * @param mat - a Matrix with n rows and n columes. n=2^k, k is a netural number.it is sorted recursively-continue 
     * @param x - int
     * @return boolean- true if x's value is in the matrix, false if not.
     * 
     */
    public static boolean find(int[][] mat , int x)
    {
        int firstRow = 0;
        int lastRow = mat.length-1;
        int firstCol = 0;
        int lastCol = mat[0].length-1;
        for(int i=0 ; i< (Math.log10(mat.length ))/(Math.log10(2));i++){
            if(x > mat[(firstRow + lastRow)/2][lastCol])//first step of the binary search
                {
                    firstRow = ((lastRow + firstRow)/2)+1;
                    if(x > mat[lastRow][(lastCol+firstCol)/2])//checking the big half
                    {
                        firstCol= ((lastCol +firstCol)/2)+1;                        
                    }
                    else 
                        lastCol = (lastCol +firstCol)/2;
                }
                else
                    {
                        lastRow =(lastRow + firstRow)/2;
                        if(x > mat[lastRow][(lastCol+firstCol)/2])//checking the small half
                    {
                        firstCol= ((lastCol +firstCol)/2)+1;
                        
                    }
                        else 
                            lastCol = (lastCol +firstCol)/2;
                    }
        }
        
        if(x == mat[firstRow][firstCol])
            return true;
            else
                return false;
            
    }
    
    /**
     * 
     */
    public static int stain(char[][] mat , int row, int col)
    {
        final int EMPTY = 0;
        
        if(row <= mat.length-1 && col<= mat[0].length-1 &&row>=0&&col>=0)//if the location is within the Matrix's boundaries
        {
            if ((mat[row][col] == ' ')||(mat[row][col] == 'v'))                
                return EMPTY;
            else
                {
                    mat[row][col] = 'v';// markes the box as checked
                    return (1 + stain(mat, row +1 ,col)+ stain(mat, row -1 ,col)// applies the method recursivally on all the boxes serounding it
                    +stain(mat, row ,col+1) +stain(mat, row ,col - 1)
                    +stain(mat, row +1 ,col+1)+stain(mat, row +1 ,col - 1)
                    +stain(mat, row -1 ,col-1)+stain(mat, row -1 ,col+1));
                }
                
        }
            else return EMPTY;        
    }
    
    /**
     * 
     */
    public static boolean isFillBag(int[] arr , int num)
    {
    return isFillBag(arr , num , 0);
    } 
    
    /**
     * 
     */
    public static boolean isFillBag(int[] arr , int num,int  i) {
        if (i == arr.length)
        {
            if (num == 0) 
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        if (isFillBag(arr , num - arr[i] , i+1)) 
        {
         // some combination with item exists
         System.out.print(arr[i]+" ");
         return true;
        }
        if (isFillBag(arr , num , i+1)) 
        {
         return true;
        }
     return false;
    }
}
