/**
 * This class represents a Car object
 *
 * @author Omer Uziel
 * @version 29.11.22
 */
public class Car
{
    //declarations
    private int _id;
    private char _type;
    private String _brand;
    private boolean _isManual;
    private final int DEFAULT_ID = 9999999;
    private final int MIN_NUM= 1000000;
    private final char DEFAULT_TYPE = 'A';

    /**
     * Creates a new Car object
     * @param id The license number of this car. license number should be a 7 digits number, otherwise set it to 9999999.
     * @param type The type of this car. Type should be 'A','B','C' or 'D', otherwise set it to 'A'.
     * @param brand The brand of this car.
     * @param isManual The gear of the car is manual or auto.
     */

    public Car(int id, char type, String brand, boolean isManual){
        if ((id >= MIN_NUM) && (id <= DEFAULT_ID)) // check if the id is 7 digits
            _id = id;
        else
            _id = DEFAULT_ID;
        if ((type == 'A') || (type == 'B') || (type == 'C') || (type == 'D'))// check if the type is valid
            _type = type;
        else 
            _type = DEFAULT_TYPE;
        _brand = brand;
        _isManual = isManual;       
    }

    /**
     * Copy constructor, Creates a new Car object like the other.  
     * @param other the car to be copied.
     */
    public Car(Car other){
        this(other._id, other._type, other._brand, other._isManual);
    }

    /**
     * Gets the id  
     * @return the id
     */
    public int getId(){
        return _id;
    }

    /**
     * Gets the type  
     * @return the type
     */
    public char getType(){
        return _type;
    }

    /**
     * Gets the brand  
     * @return the brand
     */
    public String getBrand(){
        return _brand;
    }

    /**
     * Gets the isManual flag  
     * @return the isManual flag
     */
    public boolean isManual(){
        return _isManual;
    }

    /**
     * Sets the id (only if the given id is valid) 
     * @param id - the id value to be set
     */
    public void setId(int id){
        if((id >= MIN_NUM) && (id <= DEFAULT_ID))// check if the id is 7 digits
            _id = id;
    }

    /**
     * Sets the type (only if the given type is valid) 
     * @param type - the type value to be set
     */
    public void setType(char type){
        if ((type == 'A') || (type == 'B') || (type == 'C') || (type == 'D'))// check if the type is valid
            _type = type;
    }

    /**
     * Sets the brand of the car 
     * @param brand - the brand value to be set
     */
    public void setBrand(String brand){
        if (brand != null )
            _brand = brand;
    }

    /**
     * Sets the isManual flag of the car 
     * @param isManual - the isManual flag to be set
     */
    public void setIsManual(boolean isManual){
        _isManual = isManual;
    }

    /**
     * Returns a String object that represents this car  
     * @return String that represents this car in the following format:
     * id:1234567 type:B brand:Toyota gear:manual (or auto)
     */
    public String toString(){
        if (_isManual)
            return ("id:" + _id + " type:" + _type + " brand:" + _brand + " gear:manual");
        else
            return ("id:" + _id + " type:" + _type + " brand:" + _brand + " gear:auto");
    }

    /**
     * Check if two cars are the same,
     * Cars are considered the same if they have the same type, brand and gear
     * @param other - the car to compare this car to
     * @return true if the cars are the same, otherwise false
     */
    public boolean equals(Car other){
        return ((_type == other._type) && (_brand.equals(other._brand)) && (_isManual == other._isManual));
    }

    /**
     * Check if this car is better than the other car,  
     * A car is considered better than another car if its type is higher,
     * If both cars have the same type, an automated car is better than a manual car
     * @param other - car to compare this car to
     * @return true if this car is better than the other car, otherwise false
     */
    public boolean better (Car other){
        if (_type > other._type) return true;
        else if ((_type == other._type) && (!_isManual) && (other._isManual)) return true;
        return false;
    }

    /**
     * Check if this car is worse than the other car  
     * @param other - the car to compare this car to
     * @return true if this car is worse than the other car, otherwise false
     */
    public boolean worse (Car other){
        return other.better(this);
    }
}
