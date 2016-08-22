

public class Test1
{
    public static void main(){

        Point point1 = new Point(5,12);
        Point point2 = new Point(22,12);
        if (point1.getX()!=5 || point2.getX()!=22 || point1.getY()!=12 || point2.getY()!=12){
            System.out.println("error 1");  
        }
        point1.setX(2);
        point1.setY(2);
        if (point1.getX()!=2 || point1.getY()!=2){
            System.out.println("error 2");  
        }
        Point point3 = new Point(2,2);
        if (!point1.equals(point3)){
            System.out.println("error 3");  
        }
        if (point2.isUnder(point1)){
             System.out.println("error 4");  
        }
       if (point2.isLeft(point1)){
             System.out.println("error 5");  
        }
       if (point1.isRight(point2)){
             System.out.println("error 6");  
        }
       if (!point2.isAbove(point1)){
             System.out.println("error 7");  
        }
        if (point1.distance(point3)!=0){
             System.out.println("error 8");  
        }
        point3.move(2,2);
        if (point3.getX()!=4 || point3.getY()!=4){
            System.out.println("error 9");  
        }
        Point point4 = point3.middle(point1);
        if (point4.getX()!=3 || point4.getY()!=3){
            System.out.println("error 10");  
        }
        String strPoint4= "(" + 3 + "," + 3 + ")";
        if (!point4.toString().equals(strPoint4)){
            System.out.println("error 11");  
        }    
        
       

                                                            

 
          
          
         
         

        
    }
}
