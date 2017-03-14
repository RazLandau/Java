/**
 * Represents a city. City is represented by its name, center, central station, number of residents(non negative) and number of neighborhoods(positive).
 * 
 * @auther Raz Landau 
 * @version (2013)
 */

public class City
{
    final int MIN_RESIDENTS = 0, MIN_NEIGHBORHOODS = 1;

    private String _cityName;
    private Point _cityCenter;
    private Point _centralStation;
    private long _numOfResidents;
    private int _noOfNeighborhoods;
    
    
    //constructors:
    /**
     * Constructor a city.
     * Construct a new city with name, x y coordinates of city center, x y coordinates of central station, number of residents (non negative, if negative will be set to 0), number of neighborhoods (positive, if less than 1 will be set to 1.).
     * @param cityName The city's name
     * @param centerX The x coordinate of the city's center
     * @param centerY The y coordinate of the city's center
     * @param stationX The x coordinate of the city's central station
     * @param stationY The y coordinate of the city's central station
     * @param numOfResidents number of residents
     * @param noOfNeighborhoods number of neighborhoods
     */
    
    public City (String cityName, int centerX, int centerY, int stationX, int stationY, long numOfResidents, int noOfNeighborhoods)
    {
        _cityName= new String(cityName);
        _cityCenter = new Point (centerX, centerY);
        _centralStation = new Point (stationX, stationY);
        if (numOfResidents>MIN_RESIDENTS)
            _numOfResidents = numOfResidents;
        else _numOfResidents = MIN_RESIDENTS;
        if (noOfNeighborhoods>MIN_NEIGHBORHOODS)
            _noOfNeighborhoods = noOfNeighborhoods;
        else _noOfNeighborhoods = MIN_NEIGHBORHOODS;
    }
    

    /**
     * Copy constructor for cities.
     * Construct a city with the same attributes as another city.
     * @param other The City object from which to construct the new city.
     */
    public City (City other)
    {
       this._cityName = new String(other._cityName);
       this._cityCenter = new Point (other._centralStation);
       this._centralStation = new Point (other._centralStation);
       this._numOfResidents = other._numOfResidents;
       this._noOfNeighborhoods = other._noOfNeighborhoods;
    }
    
    /** 
     * Returns the city's name.
     * @return The city's name
     */
    public String getCityName()
    {
        return _cityName;
    }
    
    /** 
     * Returns a Point object representing the city's center.
     * @return The city's center
     */
    public Point getCityCenter()
    {
        return new Point (_cityCenter);
    }
    
    /** 
     * Returns a Point object representing the city's central station.
     * @return The city's central station
     */
    public Point getCentralStation()
    {
        return new Point(_centralStation);
    }
    
    /** 
     *   Returns the number of residents in this city.
     *   @return The number of residents
     */
    public long getNumOfResidents()
    {
        return _numOfResidents;
    }
    
    /** 
     *   Returns the number of neighborhoods in this city.
     *   @return The number of neighborhoods
     */
    public int getNoOfNeighborhoods()
    {
        return _noOfNeighborhoods;
    }
    
    /** 
     *   Changes the city's name.
     *   @param cityName The city's new name
     */
    public void setCityName(String cityName)
    {
        _cityName = new String(cityName);
    }
    
    /** 
     * Changes the city's center location.
     * @param cityCenter The city's new central point
     */
    public void setCityCenter(Point cityCenter)
    {
        _cityCenter = new Point(cityCenter);
    }
    
    /** 
     *   Changes the city's central station location.
     *   @param centralStation The city's new central station location
     */
    public void setCentralStation(Point centralStation)
    {
        _centralStation = new Point(centralStation);
    }
    
    /** 
     *   Changes the city's number of residents.
     *   If a negative number is received, number of residents is set to 0.
     *   @param numOfResidents The city's new number of residents
     */
    public void setNumOfResidents(long numOfResidents)
    {
        if (numOfResidents>MIN_RESIDENTS)
            _numOfResidents = numOfResidents;
        else _numOfResidents = MIN_RESIDENTS;
    }
    
    /** 
     *   Changes the city's number of neighborhoods.
     *   If a non positive number is received, number of neighborhoods is set to 1.
     *   @param noOfNeighborhoods The city's new number of neighborhoods
     */
    public void setNoOfNeighborhoods(int noOfNeighborhoods)
    {
        if (noOfNeighborhoods>MIN_NEIGHBORHOODS)
            _noOfNeighborhoods = noOfNeighborhoods;
        else _noOfNeighborhoods = MIN_NEIGHBORHOODS;
    }
    
    /** 
     *   Return a string representation of this city.
     *   @return String representation of this city
     */
    public String toString ()
    {
        return  "City Name: " + getCityName() +
                "\nCity Center: " + getCityCenter() +
                "\nCentral Station: " + getCentralStation() +
                "\nNumber of Residents: " + getNumOfResidents() +
                "\nNumber of Neighborhoods: " + getNoOfNeighborhoods();
    }
    
    /** 
     * Add or subtract residents to this city.
     * If number of residents becomes negative, set to zero and return false, otherwise set to new number and return true.
     * @param residentsUpdate The change in the number of residents
     * @return True if new number of residents is calculated as non negative
     */
    public boolean addResidents (long residentsUpdate)
    {
        if (_numOfResidents + residentsUpdate > MIN_RESIDENTS)
        {
            _numOfResidents = _numOfResidents + residentsUpdate;
            return true;
        }
        else 
        {
            _numOfResidents = MIN_RESIDENTS;
            return false;
        }
    }
    
    /** 
     * Move the central station location.
     * If the new coordinates are negative, they will remain unchanged.
     * @param deltaX The change in the station's x location
     * @param deltaY - The change in the station's y location
     */
    public void moveCentralStation (int deltaX, int deltaY)
    {
        this._centralStation.move(deltaX, deltaY);
    }
    
    /** 
     * Calculate the distance between the city center and the central station.
     * @return double representing the distance between the city center and the central station
     */
    public double distanceBetweenCenterAndStation()
    {
        return _cityCenter.distance(_centralStation);
    }
    
     /** 
     * Returns a new city with a new name at a location dX and dY away from this city, with 0 residents and 1 neighborhood.
     * @param newCityName the new city's name
     * @param dX the x difference from this city's location
     * @param dY the y difference from this city's location
     * @return a new city
     */
    public City newCity(String newCityName, int dX, int dY)
    {
       Point newCityCenter = new Point (_cityCenter);
       newCityCenter.move(dX, dY);
       Point newCityCentralStation = new Point (_centralStation);
       newCityCentralStation.move(dX, dY);
       return new City(newCityName, newCityCenter.getX(), newCityCenter.getY(), newCityCentralStation.getX(), newCityCentralStation.getY(), MIN_RESIDENTS, MIN_NEIGHBORHOODS);
    }
}
        
   
