
/**
 * Write a description of class Trip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trip
{
    private final int PRICE_PER_DAY = 250;
    private final int PRICE_PER_COUNTRY = 100;
    private final int BUS_MAX_CAPACITY = 10;
    private final int DEFAULT_TRAVELLERS=10;
    private final int DEFAULT_COUNTRIES=1;
    private final int MAX_TRAVELLERS=50;
    private final int MAX_COUNTRIES=10;

    private final int DEFAULT_DAY=1;
    private final int DEFAULT_MONTH=1;
    private final int DEFAULT_YEAR=2000;

    private String _guideName;
    private int _noOfCountries;

    private Date _departureDate;
    private Date _returningDate;
    private int _noOfTravellers;

    //constructors:
    /**
     *   creates a new Trip object
     *   @param name of the guide of the trip
     *   @param depDay the day of the departure date
     *   @param depMonth the month of the departure date
     *   @param depYear the year of the departure date
     *   @param retDay the day of the returning date
     *   @param retMonth the month of the returning date
     *   @param retYear the year of the returning date
     *   @param noOfCountries the number of countries to visit in the trip
     *   @param noOfTravellers the number of travellers
     */
    public Trip (String name, int depDay, int depMonth, int depYear, int retDay, int retMonth, int retYear, int noOfCountries, int noOfTravellers){

        if(name != null)
         _guideName= new String(name);
        else
          _guideName= new String("NoName");
          
        _departureDate = new Date(depDay, depMonth, depYear);     
        _returningDate = new Date(retDay, retMonth, retYear); 
    
        if(_departureDate.after(_returningDate))
        {
           _departureDate = new Date(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);
           _returningDate = new Date(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);             
        }  

        if(noOfCountriesInRange(noOfCountries))
            _noOfCountries=noOfCountries;
        else
            _noOfCountries=DEFAULT_COUNTRIES;
        if(noOfTravellersInRange(noOfTravellers))
            _noOfTravellers=noOfTravellers;
        else
            _noOfTravellers=DEFAULT_TRAVELLERS;            
    }

    /**
     *   creates a new Trip object
     *   @param otherTrip trip to be copied
     */    
    public Trip (Trip otherTrip){

        if(otherTrip!=null){
            _guideName = new String(otherTrip._guideName);
            _noOfCountries = otherTrip._noOfCountries;
            _noOfTravellers = otherTrip._noOfTravellers;
            _departureDate = new Date(otherTrip._departureDate);
            _returningDate = new Date(otherTrip._returningDate); 
        }
        else {
            _departureDate = new Date(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);     
            _returningDate = new Date(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);
            _guideName= new String("NoName");
            _noOfCountries=DEFAULT_COUNTRIES;
            _noOfTravellers=DEFAULT_TRAVELLERS;   
        }
    }

    //get methods    
    /** 
     *   gets the guide name 
     *   @return the guide name
     */    
    public String getGuideName(){

        return new String(_guideName);
    }

    /** 
     *   gets the number of countries to be visit in the trip
     *   @return the number of countries
     */    
    public int getNoOfCountries(){

        return _noOfCountries;
    }

    /** 
     *   gets the number of travellers in the trip 
     *   @return the number of travellers
     */   
    public int getNoOfTravellers(){

        return _noOfTravellers;
    }

    /** 
     *   gets the trip departure date 
     *   @return the departure date
     */   
    public Date getDepartureDate(){
        return new Date(_departureDate);
    }

    /** 
     *   gets the trip returning date 
     *   @return the returning  date
     */   
    public Date getReturningDate(){
        return new Date(_returningDate);
    }

    //set methods        
    /** 
     *  sets the guide name
     *  @param othername the value to be set
     */    
    public void setGuideName(String otherName){
        
         if(otherName!=null)
           _guideName = otherName;
    }

    /** 
     *  sets the number of countries
     *  @param otherNumberOfCountries the value to be set
     */    
    public void setNoOfCountries(int otherNoOfCountries){

        if(noOfCountriesInRange(otherNoOfCountries))
            _noOfCountries = otherNoOfCountries;
    }

    /** 
     *  sets the number of travellers
     *  @param otherNumberOfTravellers the value to be set
     */    
    public void setNoOfTravellers(int otherNoOfTravellers){

        if(noOfTravellersInRange(otherNoOfTravellers))
            _noOfTravellers=otherNoOfTravellers;
    }

    /** 
     *  sets the trip departure day
     *  Set will execute only if the new departure date is before the _returningDate or is equal to it.
     *  @param newDepDay the value to be set. 
     */
    public void setDepartureDate(Date newDepDate){
        
         if(newDepDate!=null && (newDepDate.before(_returningDate) || newDepDate.equals(_returningDate)) )
            _departureDate = new Date(newDepDate);
    }


    /** 
     *  sets the trip returning day
     *  Set will execute only if the new return date is after departureDate or is equal to it.
     *  @param newRetDay the value to be set.
     */    

    public void setReturningDate(Date newRetDate){
  
        if(newRetDate!=null && (newRetDate.after(_departureDate) || newRetDate.equals(_departureDate)) )
            _returningDate = new Date(newRetDate);
    }
   
    /** 
     *  check if 2 trips are the same
     *  @param other the trip to compare this trip to
     *  @return true if the trips are the same
     */    
    public boolean equals(Trip other){

        return (other != null &&
            this.getGuideName().equals(other.getGuideName()) &&
            this.getNoOfCountries()== other.getNoOfCountries() &&
            this.getNoOfTravellers()== other.getNoOfTravellers() &&
            this.getDepartureDate().equals(other.getDepartureDate()) &&
            this.getReturningDate().equals(other.getReturningDate()));
    }

    /** 
     *  check if two trips have the same departure date
     *  @param otherTrip the trip to compare to
     *  @return true if the two trip have the same departure date otherwise false 
     */        
    public boolean sameDepartureDate(Trip otherTrip){

        return (otherTrip!=null && _departureDate.equals(otherTrip._departureDate));
    }

    /** 
     *  check if two trips have the same returning date
     *  @param otherTrip the trip to compare to
     *  @return true if the two trip have the same returning date otherwise false 
     */        
    public boolean sameReturningDate(Trip otherTrip){

        return  (otherTrip!=null && _returningDate.equals(otherTrip._returningDate));
    }

    /** 
     *  calculates the trip number of days
     *  @return the number of days in the trip
     */ 
    public int tripDuration(){
        return _returningDate.difference(_departureDate)+1;        
    }

    /** 
     *  check if two trips overlpas with their dates
     *  @param otherTrip the trip to check if overlaps with this trip
     *  @return true if the two trip have overlap dates; otherwise return false
     */ 
    public boolean overlap(Trip otherTrip){

        if(otherTrip == null) return false;
        // overlap of one day only
        if(_departureDate.equals(otherTrip._departureDate) || _departureDate.equals(otherTrip._returningDate) || _returningDate.equals(otherTrip._departureDate) || _returningDate.equals(otherTrip._returningDate))
            return true;    
        if((_departureDate.after(otherTrip._departureDate) && _departureDate.before(otherTrip._returningDate)) || (otherTrip._departureDate.after(_departureDate) && otherTrip._departureDate.before(_returningDate)))
            return true;
        else if((_returningDate.before(otherTrip._returningDate) && _returningDate.after(otherTrip._departureDate)) || (otherTrip._returningDate.before(_returningDate) && otherTrip._returningDate.after(_departureDate)))
            return true;            
        return false;
    }

    /** 
     * 
     *  check if trip is loaded
     *  @return true if the number of countries to visit is greater then trip duration - else return false
     */     
    public boolean isLoaded(){
        return getNoOfCountries() > tripDuration() ? true : false;
    }  

    /** 
     *  calculates the minimum number of buses needed for the trip
     *  @return the number of buses needed for the trip
     */ 
    public int howManyCars(){
        return ((_noOfTravellers%BUS_MAX_CAPACITY) !=0) ? ((_noOfTravellers/BUS_MAX_CAPACITY)+1):(_noOfTravellers/BUS_MAX_CAPACITY);       
    } 

    /** 
     *  calculates total price of the trip according to days of trip and number of countries visited 
     *  @return the total price of the trip
     */ 
    public int calculatePrice()
    {  
        int tripPrice = tripDuration() * PRICE_PER_DAY + getNoOfCountries() * PRICE_PER_COUNTRY;
        if(getDepartureDate().getMonth() == 7 || getDepartureDate().getMonth() == 8 )
            return (int)(tripPrice* 1.2);
        return tripPrice;    
    }

    /**
     *  Return a string representation of this trip.
     * @return representation of trip.
     */
    public String toString()
    {  
        return "TRIP: " + _guideName + " | " + _departureDate + " | " + _returningDate + " | " + _noOfCountries + " | " + _noOfTravellers;
    }   

    private boolean noOfCountriesInRange(int nc){
        return (nc>=0 && nc <=MAX_COUNTRIES)? true:false;

    }
   
    private boolean noOfTravellersInRange(int nt){
        return (nt>=0 && nt <=MAX_TRAVELLERS)? true:false;

    }

}
