 public class Test2
{
    public static void main(){
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