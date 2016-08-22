

public class Test
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
        

        // test City
         City cityLala = new City("Lala", 2,3,2,3,1000,4);
         if (!cityLala.getCityName().equals("Lala")){
                System.out.println ("error 12");
          }
          if (!cityLala.getCityCenter().equals(new Point(2,3))){
                System.out.println ("error 13");
          }
          if (!cityLala.getCentralStation().equals(new Point(2,3))){
                System.out.println ("error 14");    
          }
          if (cityLala.getNumOfResidents()!=1000){
                System.out.println ("error 15");    
          }
          if (cityLala.getNoOfNeighborhoods()!=4){
                System.out.println ("error 16");    
          }
          cityLala.setCityName("Lalo");
          cityLala.setCityCenter(new Point(6,6));
          cityLala.setCentralStation(new Point(7,7));
          cityLala.setNumOfResidents(1020);
          cityLala.setNoOfNeighborhoods(3);
           if (!cityLala.getCityName().equals("Lalo")){
                System.out.println ("error 17");
          }
          if (!cityLala.getCityCenter().equals(new Point(6,6))){
                System.out.println ("error 18");
          }
          if (!cityLala.getCentralStation().equals(new Point(7,7))){
                System.out.println ("error 19");    
          }
          if (cityLala.getNumOfResidents()!=1020){
                System.out.println ("error 20");    
          }
          if (cityLala.getNoOfNeighborhoods()!=3){
                System.out.println ("error 21");    
          }
          cityLala.addResidents(1000);
           if (cityLala.getNumOfResidents()!=2020){
                System.out.println ("error 22");    
          }
          cityLala.moveCentralStation(1,5);
           if (!cityLala.getCentralStation().equals(new Point(8,12))){
                System.out.println ("error 23");    
          }
          cityLala.setCentralStation(new Point(8,6));
          if (cityLala.distanceBetweenCenterAndStation()!=2){
                System.out.println ("error 24");    
          }
          String strCityLala = "City Name: " + "Lalo" +
                                                        "\nCity Center: " + cityLala.getCityCenter() +
                                                        "\nCentral Station: " + cityLala.getCentralStation() +
                                                        "\nNumber of Residents: " + cityLala.getNumOfResidents() +
                                                        "\nNumber of Neighborhoods: " + cityLala.getNoOfNeighborhoods();
          if (!cityLala.toString().equals(strCityLala)){
                 System.out.println ("error 25");    
          }
                                                            

 
          
          
         
         

        
    }
}
