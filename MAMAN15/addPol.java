

public class addPol
{
    
    /**
     * The Main method of this tester 
     */
    public static void main (String[] args)
    {
        // Create two Polynoms
        Polynom p1 = new Polynom();
        p1.addNode(new PolyNode(0,2));
        p1.addNode(new PolyNode(2,4));   
        System.out.println("\nP1:");
        System.out.println(p1);
        
        Polynom p2 = new Polynom(new PolyNode(0,2));
        System.out.println("\nP2:");
        System.out.println(p2);
        
        
        // A few simple tests
        System.out.println("\n2*P1 + P2:");
        System.out.println(p2.addPol(p1));
        
        System.out.println("\nP1 * P2:");
        System.out.println(p1 + "\t\t* \n" + p2 + "\t=\n" + p1.multPol(p2));
        

    }
    
}
