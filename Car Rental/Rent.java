
/**
 * This class represents a Rent object
 *
 * @author Omer Uziel
 * @version 29.11.22
 */

public class Rent
{
    //declarations
    private String _name;
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;
    private final int PRICE_A = 100;
    private final int PRICE_B = 150;
    private final int PRICE_C = 180;
    private final int PRICE_D = 240;
    private final double PERCENTAGE_DISCOUNT = 0.1;    

    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date, otherwise set it to one day after the pick up date.
     * @param name the client's name
     * @param car - the rented car
     * @param car - the rented car
     * @param ret - the return date
     */
    public Rent (String name, Car car, Date pick, Date ret)
    {
        _name = name;
        _car = new Car(car);
        if (pick.before(ret))// The return date must be at least one day after the pickup date
        {
            _pickDate = new Date(pick);
            _returnDate = new Date(ret);
        }
        else{
            _pickDate = new Date(pick);
            _returnDate = _pickDate.tomorrow();
        }
    }

    /**
     * Copy constructor, Creates a new Rent object like the other
     * @param other - the rent to be copied
     */
    public Rent (Rent other){
        _name = other._name;
        _car = new Car(other._car);
        _pickDate = new Date(other._pickDate);
        _returnDate = new Date(other._returnDate);
    }

    /**
     * Gets the name  
     * @return the name
     */
    public String getName(){
        return _name;
    }

    /**
     * Gets the car  
     * @return the car
     */
    public Car getCar(){
        return new Car(_car);
    }

    /**
     * Gets the pick up date  
     * @return the pick up date 
     */
    public Date getPickDate(){
        return new Date(_pickDate);
    }

    /**
     * Gets the return date  
     * @return the return date
     */
    public Date getReturnDate(){
        return new Date(_returnDate);
    }

    /**
     * Sets the client name
     * @param name - the client name 
     */
    public void setName(String name){
        _name = name;
    }

    /**
     * Sets the rented car
     *@param car - the rented car 
     */
    public void setCar(Car car){
        _car = new Car(car);
    }

    /**
     * Sets the pick up date, The pick up date must be at least one day before the return date, otherwise - don't change the pick up date
     *@param pick - the pick up date
     */
    public void setPickDate(Date pick){
        if (pick.before(_returnDate)) _pickDate = new Date(pick);
    }

    /**
     * Sets the return date, The return date must be at least one day after the pick up date, otherwise - don't change the return date
     *@param ret - the return date
     */
    public void setReturnDate(Date ret){
        if (_pickDate.before(ret)) _returnDate = new Date(ret);
    }

    /**
     *Check if 2 rents are the same
     * @param other - the rent to compare this rent to
     * @return true true if the rents are the same
     */
    public boolean equals(Rent other){
        return ((_name.equals(other._name)) && (_car.equals(other._car)) 
            && (_pickDate.equals(other._pickDate)) && (_returnDate.equals(other._returnDate)));       
    }

    /**
     * Returns the number of rent days,
     * @return the number of rent days
     */
    public int howManyDays(){
        return (_returnDate.difference(_pickDate));
    }

    /**
     * Returns the rent total price
     * @return Returns the rent total price
     */
    public int getPrice(){       

        int allTheDays = this.howManyDays();
        int weeks = allTheDays / 7;
        int pricePerDay;
        //set the price by the type
        if(_car.getType() == 'A')  pricePerDay =  PRICE_A;   
        else if(_car.getType() == 'B')  pricePerDay = PRICE_B;
        else if(_car.getType() == 'C')  pricePerDay = PRICE_C;
        else pricePerDay = PRICE_D;
        // calculate the regular price and make the discount,keep the price variable int because the price is always integer 
        int price = (int)((allTheDays * pricePerDay) - (weeks * 7 * pricePerDay * PERCENTAGE_DISCOUNT)); 
        return price;
    }

    /**
     * Try to upgrade the car to a better car
     * if the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost,
     * otherwise - don't upgrade
     * @param car - the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade (Car newCar){
        int priceBefore;
        int priceAfter;
        if(newCar.better(_car))//check if the new car is better
        {
            priceBefore = this.getPrice();// calculate the price before upgrade
            _car= new Car(newCar); // make the upgarde
            priceAfter = this.getPrice(); // calculate the price after upgrade
        }
        else return 0;

        return priceAfter - priceBefore;
    }

    /**
     * Check if there is a double listing of a rent for the same person and car with an overlap in the rental days,
     * If there is - return a new rent object with the unified dates, otherwise - return null.
     * @param other - the other rent
     * @return the unified rent or null
     */
    public Rent overlap (Rent other){
        if(!(_name.equals(other._name)) || !(_car.equals(other._car))) return null; // not the same rent

        if((other._returnDate.after(_returnDate)) && (other._pickDate.before(_pickDate))) return new Rent(other); //other cover all the dates

        if(((other._pickDate.equals(_pickDate)) ||(other._pickDate.after(_pickDate))) && // other pick up is between this pick up and return date
        ((other._pickDate.before(_returnDate)) || (other._pickDate.equals(_returnDate)))){
            //return a new rent with the later on return
            if (other._returnDate.after(_returnDate)) return new Rent(_name, _car, _pickDate , other._returnDate);
            return new Rent(this);
        }
        if(((other._returnDate.equals(_pickDate)) ||(other._returnDate.after(_pickDate))) &&// other return is between this pick up and return date
        ((other._returnDate.before(_returnDate)) || (other._returnDate.equals(_returnDate)))){
            //return a new rent with the earlier pick date
            if (other._pickDate.before(_pickDate)) return new Rent(_name, _car, other._pickDate , _returnDate);
            return new Rent(this);
        }
        return null;
    }

    /**
     * Returns a String that represents this rent 
     * @return String that represents this rent in the following format: 
     * Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString(){
        return ("Name:" + _name + " From:" + _pickDate + " To:" + _returnDate + " Type:" + _car.getType() + " Days:"
            + this.howManyDays() + " Price:" + this.getPrice());
    }

    
    
    
    
}

