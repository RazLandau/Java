/**
 * Represents a country. Country is represented by its name, array of Cities and number of Cities.
 * 
 * @author Raz Landau 
 * @version (2013)
 */

public class Country
{
    final int MAX_CITIES = 1000;
    private String _countryName;
    private City [] _cities;
    private int _noOfCities;
    
    
    
    //constructor:
    /**
     * Constructor a country.
     * Construct a new country with a name and an array of max number of null Cities.
     * @param countryName The name of the country
     */
    public Country (String countryName)
    {
        _countryName = countryName;
        _cities = new City [MAX_CITIES-1];
        _noOfCities =  0;
    }
    

    /**
     * Add city to Country.
     * Add a city, with name, x and y parameters of cityCenter, x and y parameters of CentralStation, number of residents
     and number of neighborhoods, to the array of Cities of this country. Returns true if addition is succesful, returns false if it fails.
     
     * @param cityName The name of the city
     * @param xCenter The X coordinate of the city center
     * @param yCenter The Y coordinate of the city center
     * @param xStation The X coordinate of the central station
     * @param yStation The Y coordinate of the central station
     * @param numOfResidents The number of residents
     * @param noOfNeighborhoods The number of neighborhoods
     * @return True if addition is succesful, false if fails
     */
    public boolean addCity (String cityName, int xCenter, int yCenter, int xStation, int yStation, long numOfResidents, int noOfNeighborhoods)
    {
        // Assumes cityName is valid (there isn't already a city with that name in this country), as instructed
        if (_noOfCities == MAX_CITIES)
                return false;
        else 
        { 
            _cities [_noOfCities] = new City (cityName, xCenter, yCenter, xStation, yStation, numOfResidents, noOfNeighborhoods);
            _noOfCities++;
            return true;
        }
    }

    /**
     * Returns The coutry's name.
     * @return  This country's name
     */
    public String getCountryName ()
    {
        return _countryName;
    }
    
    /**
     * Returns the number of cities in this country.
     * @return This country's number of cities
     */
    public int getNumOfCities ()
    {
        return _noOfCities;
    }
    
    /**
     * Returns the number of residents in the country.
     * @return This country's number of residents
     */
    public long getNumOfResidents ()
    {
        long totalResidents = 0;
        for (int i=0; i<_noOfCities; i++)
            totalResidents = totalResidents + _cities[i].getNumOfResidents();
        return totalResidents;
    }
    
    /**
     * Returns a City object representing the coutry's southernmost city.
     * @return This country's southmost city
     */
    public City southernmostCity ()
    {
        //Checks if this Country is a null array
        if (_noOfCities == 0)
            return null;
        else
        {   int southernmostCity = 0;
            for (int i=1; i<_noOfCities; i++)
                if ( (_cities[i].getCityCenter()).isUnder(_cities[southernmostCity].getCityCenter()))
                    southernmostCity = i;
            return new City(_cities[southernmostCity]);
        }
    }
    
    /**
     * Calculates the furthest distance between any 2 cities in the country.
     * @return double representing the furthest distance between any two cities in this country
     */
    public double longestDistance ()
    {
        double longestDistance = 0;
            for (int i=0; i<_noOfCities; i++)
            // Nested loop, excluding distances already calculated and distance from a city to itself
                for (int j=i+1; j<_noOfCities; j++)
                    if ( (_cities[i].getCityCenter()).distance(_cities[j].getCityCenter()) > longestDistance )
                        longestDistance = (_cities[i].getCityCenter()).distance(_cities[j].getCityCenter());
        
        return longestDistance;
    }
    
    /**
     * Returns a string of all cities north of given city in the country.
     * Checks if city is in this country. If true- checks if there are cities in this country north of given city. If true- returns a string of all northen cities.
     * @param String the name of this city
     * @return String string representation of all cities north of given city in this country
     */
    public String citiesNorthOf (String cityName)
    {
        String s = "";
        int northernCitiesCounter = 0;
        int cityLocation = cityLocation (cityName); // Declaration of cityLocation already checks if cityName is in the array and its location by priavte method cityLocation
        // Handles speical case- No city with given name in this country
        if (cityLocation == -1)
            s = "There is no city with the name " + cityName;
        // If there IS a city with given name in this country
        else
        {
            for (int i=0; i<_noOfCities; i++)
                if ( _cities[i].getCityCenter().isAbove(_cities[cityLocation].getCityCenter()) )
                {
                    s = s + (_cities[i].toString()+"\n");
                    northernCitiesCounter++;
                }
            // Handles special case- No cities north of given city in this country
            if (northernCitiesCounter == 0)
                s = "There are no cities north of " + cityName;
        }

        return s;
    }
        
    /**
     * Returns an array object of all cities in the country.
     * The new array's size is exactly the number of cities in this country (no "nulls"). The new array holds a copy of all the cities in this country.
     * @return City [] new array of cities
     */
    public City [] getCities ()
    {
        City [] cities = new City [_noOfCities];
        for (int i=0; i<cities.length; i++)
            cities[i] = new City (_cities[i]);
        return cities;
    }
    
    /**
     * Returns a unified city of 2 cities in the country.
     * Unifies 2 cities:
     * Unified Name = city1name-city2name.
     * Unified number of residents = sum of residents in both cities.
     * Unified number of neighborhoods = sum of neighborhoods in both cities.
     * Unified city center =  the middle point between the 2 cities' centers.
     * Unified central station = the western central station of the 2 cities.
     * The unified city replaces the the city with the greater number of residents of the 2 cities in this country. The city with fewer residents is deleted from this country.
     * @param city1 The name of the 1st city
     * @param city2 the name of the 2nd city
     * @return City the unified city
     */
    public City unifyCities (String cityName1, String cityName2)
    {
        // Assumes given city names are valid and are in the country, as instructed
        // Creates copies of the given cities from the array
        City newCity1 = new City (_cities[cityLocation(cityName1)]);
        City newCity2 =  new City (_cities[cityLocation(cityName2)]);
        // Intialize the unified city as a random city (No blank constructor for City class)
        City newCity3 = new City ("city3", 1, 2, 3, 4, 5, 6 );
        // Initialize the unified city as instructed
        newCity3.setCityName (newCity1.getCityName() + "-" + newCity2.getCityName());
        newCity3.setNumOfResidents (newCity1.getNumOfResidents() + newCity2.getNumOfResidents());
        newCity3.setNoOfNeighborhoods (newCity1.getNoOfNeighborhoods() + newCity2.getNoOfNeighborhoods());
        newCity3.setCityCenter (newCity1.getCityCenter().middle(newCity2.getCityCenter()));
        if (newCity2.getCentralStation().isLeft(newCity1.getCentralStation()))
            newCity3.setCentralStation(newCity2.getCentralStation());
        else newCity3.setCentralStation(newCity1.getCentralStation()); // If both central stations are equal, the first one is returned
        
        int bigCityLocation = 0, smallCityLocation = 0;
        // Checks which of given cities should be replaced with unified city, and replaces it
        if (newCity1.getNumOfResidents() > newCity2.getNumOfResidents())
        {
            bigCityLocation = cityLocation(cityName1);
            smallCityLocation = cityLocation (cityName2);
            _cities [bigCityLocation] = newCity3;
        }
        if (newCity1.getNumOfResidents() < newCity2.getNumOfResidents())
        {
            bigCityLocation = cityLocation(cityName2);
            smallCityLocation = cityLocation (cityName1);
            _cities [bigCityLocation] = newCity3;
        }
        // Handles special case- Both cities' number of residents are equal
        if (newCity1.getNumOfResidents() == newCity2.getNumOfResidents())
        {
            if (cityLocation(cityName1)> cityLocation(cityName2))
            {
                bigCityLocation = cityLocation(cityName2);
                smallCityLocation = cityLocation (cityName1);
            }
            else 
            {
                bigCityLocation = cityLocation(cityName1);
                smallCityLocation = cityLocation (cityName2);
            }
                _cities [bigCityLocation] = newCity3;
        }
        
        // "Shrinks" the array so it is continuous (no "holes"), as instructed
        for (int i=smallCityLocation; i<_noOfCities; i++)
        _cities[i] = _cities[i+1]; 
        // Updates number of cities in this country, after the smaller of the given cities is deleted from the array
        _noOfCities--;
        // returns the unified city, now in the bigger of given cities' address in the array
        return new City (_cities [bigCityLocation]);   
    }
    
    /**
     * Returns a string representaion of the country.
     * @return String representation of this country
     */
    public String toString ()
    {
        String s = "";
        s = s + "Cities of " + _countryName + " :\n";
        for (int i=0; i<_noOfCities; i++)
            s = s + _cities[i].toString() +"\n";
            return s;
    }
    
    
    //Private Methods:
    
    // Checks if there is a city in this country with given name
    private boolean cityInArray(String cityName)
    {
        for (int i=0; i<_noOfCities; i++)
            if (_cities[i].getCityName().equals(cityName))
                return true;
        return false;
    }
    
    // Returns the location of a given city in this array. Returns -1 if there ISN'T a city with given name in this country
    private int cityLocation (String cityName)
    {
        if (cityInArray (cityName))
        {
            for (int i=0; i<_noOfCities; i++)
                if (_cities[i].getCityName().equals(cityName))
                    return i;
        }
        return -1;
    }
}
    
