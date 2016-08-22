/**
 * This class represents a Date Object 
 * 
 * @author 
 * @version (2013)
 */

public class Date {

    private int _day;
    private int _month;
    private int _year;

    private final int DEFAULT_DAY=1;
    private final int DEFAULT_MONTH=1;
    private final int DEFAULT_YEAR=2000;

    //constructors:
    /**
     *   creates a new Date object
     *   @param day the day in the month(1-31)
     *   @param month the month in the year(1-12)
     *   @param year the year ( 4 digits)
     */

    public Date(int day, int month, int year) {
        if(isValid(day,month,year)){
            _day = day;
            _month = month;
            _year = year;
        }
        else{
            _day=DEFAULT_DAY;
            _month=DEFAULT_MONTH;
            _year=DEFAULT_YEAR;
        }
    }

    /**
     *   Copy Constructor
     *   @param date to be copied
     */
    public Date(Date other){
        if(other!=null){
            _day = other._day;
            _month = other._month;
            _year = other._year;
        } 
        else{
            _day=DEFAULT_DAY;
            _month=DEFAULT_MONTH;
            _year=DEFAULT_YEAR;
        }
    }

    /** 
     *   gets the year 
     *   @return the year
     */
    public int getYear(){
        return _year;
    }

    /** 
     *  gets the month 
     *   @return the month
     */
    public int getMonth(){
        return _month;
    }

    /** 
     *  gets the Day
     *  @return the day
     */
    public int getDay(){
        return _day;
    }

    /** 
     *  sets the year
     *  @param yearToSet the value to be set
     */
    public void setYear(int yearToSet){

        if(isValid(_day,_month,yearToSet))
            _year = yearToSet;
    }

    /** 
     *  set the month
     *  @param monthToSet the value to be set
     */
    public void setMonth(int monthToSet){

        if(isValid(_day,monthToSet,_year))
            _month = monthToSet;
    }

    /** 
     *  sets the day 
     *  @param dayToSet the value to be set
     */
    public void  setDay(int dayToSet){

        if(isValid(dayToSet,_month,_year))
            _day = dayToSet;
    }

    /** 
     *  check if 2 dates are the same
     *  @param other the date to compare this date to
     *  @return true if the dates are the same
     */
    public boolean equals(Date other){

        if(other!=null && _day==other._day &&_month==other._month&&_year==other._year)
            return true;
        return false;
    }

    /** 
     *  check if this date is before other date
     *  @return true if this date is before other date
     */
    public boolean before(Date other){

        if (other!=null && (_year<other._year || (_year==other._year && _month<other._month) || (_year==other._year && _month==other._month && _day<other._day)))
            return true;
        return false;
    }

    /** 
     *  check if this date is after other date
     *  @return true if this date is after other date
     */

    public boolean after(Date other){

        return (other!=null && other.before(this));
    }

    /** 
     *  calculates the difference in days between two dates
     *  @param other the date to calculate the difference between
     *  @return the number of days that differ between the dates
     */ 
    public int difference(Date other){     
       
        if(other==null)
            return -1;
        return Math.abs( calculateDate(getDay(),getMonth(),getYear()) - calculateDate(other.getDay(),other.getMonth(),other.getYear())) ;        
    }            

    /**
     * returns a  String that represents this date
     *
     * @return String that represents this date
     * in the following format:
     * day/month/year (30/9/1917)
     */
    public String toString() {
        return _day +" / " + _month + " / " + _year;
    }

    private boolean isValid(int d,int m,int y){
        if(!dayInRange(d) || !monthInRange(m) || !yearInRange(y))
            return false;
        switch(m){
            case 1:case 3: case 5:case 7:case 8:case 10:case 12:
            return (d<=31) ? true:false;
            case 2: 
            return (leap(y)&&d<=29)||(!leap(y)&&d<=28) ? true:false;
            case 4:case 6:case 9:case 11: 
            return (d<=30)?true:false;
            default: return false;
        }
    }

    private boolean dayInRange(int d){
        return (1<=d && d<=31) ? true:false;
    }

    private boolean monthInRange(int m){
        return (1<=m && m<=12) ? true:false;
    }

    private boolean yearInRange(int y){
        return (1000<=y && y<10000) ? true:false;
    }
    //check if leap year
    private boolean leap(int y){
        return (y%4==0 && y%100!=0) || (y%400==0) ? true:false;
    }

    // The following method returns the number of days passed since 0 January 0 CE - use it to calculate trip duration
    private int calculateDate( int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        } 
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62); 
    }    
    
    private int calculateDateOption2(int day, int month,int year){ 
        // computes the day number since 0 January 0 CE (Gregorian) 
        int y=year; 
        int m=month; 
        if(month < 3) y=y-1; 
        if(month < 3) m=m+12; 
        return (int)(Math.floor(365.25*y)-Math.floor(y/100)+Math.floor(y/400)+Math.floor(30.6*(m+1))+day-62); 
    }

}