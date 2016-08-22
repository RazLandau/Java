public class Test1
{
    public static void main (String[] args)
    {
        PolyNode n1 = new PolyNode (1,1);
    PolyNode n2 = new PolyNode (2,2);
    PolyNode n3 = new PolyNode (3,3);
    
    Polynom p1 = new Polynom();
    p1.addNode(n1);
    p1.addNode(n2);
    Polynom p2 = new Polynom();
    p2.addNode(n3);
    
    p1.addPol(p2);
    
    System.out.println(p1);
    System.out.println(p2);
}
}
    