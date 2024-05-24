    /**
 * This class represents a Rent object
 *
 * @author Omer Uziel
 * @version 29.11.22
 */
public class Date
{
    //declarations
    private int _day;
    private int _month;
    private int _year;  
    private int DEFAULT_DAY = 1;
    private int DEFAULT_MONTH = 1;
    private int DEFAULT_YEAR = 2000;   
    private final int MIN_YEAR = 1000;
    private final int MAX_YEAR = 9999;
    private final int MAX_DAY = 31;
    private final int MAX_MONTH = 12;
    private final int LEAP_YEAR1 = 4;
    private final int LEAP_YEAR2 = 100;
    private final int LEAP_YEAR3 = 400;

    
    /**
     * if the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * @param day - the day in the month (1-31)
     * @param month - the month in the year (1-12)
     * @param year - the year (4 digits)
     * @param isManual The gear of the car is manual or auto.
     */
    public Date(int day, int month, int year)
    {
        if(isValid(day, month, year))//check if the date is valid
        { 
            _day = day;
            _month = month;
            _year = year;
        }
        else//set the date for default    
        {
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }

    }

    /**
     * Copy constructor, Creates a new Date object like the other
     * @param other - the date to be copied
     */
    public Date (Date other){
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    /**
     * Gets the day  
     * @return the day
     */
    public int getDay(){
        return _day;
    }

    /**
     * Gets the month  
     * @return the month
     */
    public int getMonth(){
        return _month;
    }

    /**
     * Gets the year  
     * @return the year
     */
    public int getYear(){
        return _year;
    }

    /**
     * Set the day (only if date remains valid) 
     *@param dayToSet - the day value to be set
     */
    public void setDay(int dayToSet){
        if (isValid(dayToSet, _month, _year)) _day = dayToSet;
    }

    /**
     * Set the month (only if date remains valid) 
     *@param monthToSet - the month value to be set
     */
    public void setMonth(int monthToSet){
        if (isValid(_day, monthToSet, _year)) _month = monthToSet;
    }

    /**
     * Set the year (only if date remains valid) 
     *@param yearToSet - the year value to be set
     */
    public void setYear(int yearToSet){
        if (isValid(_day, _month, yearToSet)) _year = yearToSet;
    }

    /**
     * Check if 2 dates are the same,
     * @param other - the date to compare this date to
     * @return true if the dates are the same, otherwise false
     */
    public boolean equals (Date other){
        return ((_day == other._day) && (_month == other._month) && (_year == other._year));
    }

    /**
     * Check if this date is before other date,
     * @param other - date to compare this date to
     * @return true if this date is before other date, otherwise false
     */
    public boolean before (Date other){
        if (_year < other._year) return true;
        if (_year > other._year) return false;
        if (_month < other._month) return true;
        if (_month > other._month) return false;
        if (_day < other._day) return true;
        if (_day > other._day) return false;
        return false;
    }

    /**
     * Check if this date is after other date,
     * @paramother - date to compare this date to
     * @return true if this date is after other date, otherwise false
     */
    public boolean after (Date other){
        return other.before(this);
    }

    /**
     * Calculates the difference in days between two dates
     * @param other - the date to calculate the difference between
     * @return the number of days between the dates (non negative value)
     */
    public int difference (Date other){
        return (Math.abs(calculateDate(_day, _month, _year) - calculateDate(other._day,other._month, other._year)));
    }

    /**
     * Returns a String that represents this date  
     * @return String that represents this date in the following format: 
     * day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString(){
        if ((_day < 10) && (_month < 10))  return ("0" +_day + "/" + "0" + _month + "/" + _year);//add 0 if the day and month are under 10
        if (_day < 10) return ("0" +_day + "/" + _month + "/" + _year);//add 0 if only the day is numbers under 10.
        if (_month < 10) return (_day + "/" + "0" + _month + "/" + _year);//add 0 if only the month is numbers under 10.
        return (_day + "/" + _month + "/" + _year); 
    }

    /**
     * Calculate the date of tomorrow  
     * @return the date of tomorrow
     */
    public Date tomorrow(){
        Date d1;
        if (isValid(_day + 1, _month, _year))  d1 = new Date(_day + 1, _month, _year);// check if one more day is valid
        else if (isValid(1, _month + 1, _year))  d1 = new Date(1, _month + 1, _year);//check if first to the next month is valid
        else d1 = new Date(1, 1, _year +1); // tomorrow is a new year
        return d1;
    }

    private int calculateDate ( int day, int month, int year) // copy from the task
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } 

    private boolean isLeapYear(int year) //check if it is a leap year
    { 
        if (year % LEAP_YEAR1 != 0){ //if year is not divide by 4 is not leap year
            return false;
        } 
        else if (year % LEAP_YEAR3 == 0){//if year is divide by 400 is leap year
            return true;
        } 
        else if (year % LEAP_YEAR2 == 0){//if year is divide by 100 is not leap year
            return false;
        } 
        else {
            return true;//if year is divide by 4 and not divide by 100 is leap year
        }        
    }    

    private boolean isValid(int day, int month, int year) {
        if ((year < MIN_YEAR) || (year > MAX_YEAR)) return false; //year must be 4 digits
        if ((month < 1) || (month > MAX_MONTH)) return false; //month must be 1-12
        if ((day < 1) || (day > MAX_DAY)) return false;//month must be 1-31
        switch (month) {
            case 1: return true;
            case 2: return ((isLeapYear(year)) ? day <= 29 : day <= 28);//check if it is a leap year and compare the day to 28/29
            case 3: return true;
            case 4: return day < MAX_DAY;
            case 5: return true;
            case 6: return day < MAX_DAY;
            case 7: return true;
            case 8: return true;
            case 9: return day < MAX_DAY;
            case 10: return true;
            case 11: return day < MAX_DAY;
            default: return true;
        }
    }
}
