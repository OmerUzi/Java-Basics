
/**
 * This class represents a Company object
 *
 * @author Omer Uziel
 * @version 26.01.23
 */
public class Company
{
    //declaration
    private RentNode _head;
    
    /**
     * Creates a new Company object
     * set the head to null 
     */
    public Company()
    {
        _head = null;
    }
    
    /**
     * Add a new RentNode object to the company
     * @param name - the client's name
     * @param car - the rented car
     * @param pick - the pick up date
     * @param ret - the return date
     */
    public boolean addRent(String name, Car car, Date pick, Date ret) 
    {
        Rent rent = new Rent(name, car, pick, ret);

        //check if there is already this rent in the list
        RentNode temp = _head;
        while(temp != null)
        {
            if(rent.equals(temp.getRent())) return false;
            temp = temp.getNext();
        }
        temp = _head;
        //insert to the head beacuse list is empty
        if(temp == null) 
        {
            _head = new RentNode(rent);
            return true;
        }

        //insert to the right place
        while(temp != null)
        {
            if(pick.before(temp.getRent().getPickDate()))// the input rent is before the current rent 
            {
                Insert(rent, temp);
                return true;
            }
            else if(pick.after(temp.getRent().getPickDate()))// the input rent is after the current rent 
            {
                temp = temp.getNext();
            }
            else // the input rent is at the same date as the current rent
            {
                if(rent.howManyDays() > temp.getRent().howManyDays())// the longer rent is before
                {
                    Insert(rent, temp);
                    return true;
                }
                temp = temp.getNext();
            }
        }
        Insert(rent, temp); //insert at the end;
        return true;
    }
    
    
    // this function get a rent and a RentNode, and insert the rent before the node
    private void Insert(Rent insertRent, RentNode beforeRent)
    {
        RentNode current = _head;
        RentNode rent = new RentNode(insertRent);

        if(current == beforeRent)//insert to the first place
        {
            rent.setNext(_head);
            _head = rent;
        }
        else // insert before the specific node
        { 
            while(current.getNext() != beforeRent)
            {
                current = current.getNext();
            }
            rent.setNext(current.getNext());
            current.setNext(rent);
        } 

    }

    /**
     * Remove the first rent that the return date is the same like the input
     * @param ret - the return date we want to remove
     * @return true if the removal happend , false - there is no rent with this return date
     */
    public boolean removeRent(Date ret) 
    {
        if(_head != null) // the list is empty so return false
        {
            RentNode temp = _head;
            //is the first rent
            if(temp.getRent().getReturnDate().equals(ret))//remove the first rent if needed
            {
                _head = _head.getNext();
                return true;
            }
            //remove the specefic rent
            while(temp.getNext() != null)
            {
                if(temp.getNext().getRent().getReturnDate().equals(ret))
                {
                    temp.setNext(temp.getNext().getNext());
                    return true;
                }
                temp = temp.getNext();
            }
            return false; //there is not rent with the return date like input
        }
        return false;
    }
    
    /**
     * Calculate and return the numbers of rents of the company
     * @return numbers of rents
     */
    public int getNumOfRents()
    {
        int counter = 0; // counter of rents
        //calculate by running till the end of the list
        RentNode temp = _head;
        while(temp != null)
        {
            counter++;
            temp = temp.getNext();
        }
        return counter;
    }
    
    /**
     * Calculate and return the sum of prices of all the rents of the company
     * @return sum of prices of all the rents of the company
     */
    public int getSumOfPrices()
    {
        int sum = 0;//sum of prices
        //calculate by running till the end of the list
        RentNode temp = _head;
        while(temp != null)
        {
            sum += temp.getRent().getPrice();
            temp = temp.getNext();
        }
        return sum;
    }
    
    /**
     * Calculate and return the sum of rent days of all the rents of the company
     * @return sum of rent days
     */

    public int getSumOfDays()
    {
        int sum = 0;//sum of rent days
        //calculate by running till the end of the list
        RentNode temp = _head;
        while(temp != null)
        {
            sum += temp.getRent().howManyDays();
            temp = temp.getNext();
        }
        return sum;
    }

    /**
     * Calculate and return the average number of rent days
     * @return average number of rent days
     */
    public double averageRent()
    {
        return((double)this.getSumOfDays() /(double)this.getNumOfRents());
    }

    /**
     * return the car whose return date is latest
     * @return car whose return date is latest, null if there is no rents
     */
    public Car lastCarRent() 
    {
        if(_head == null) return null; // there is no rent
        //finding the car by running till the end of the list
        RentNode lastCar = _head; 
        RentNode temp = _head;
        while(temp != null)
        {
            if(temp.getRent().getReturnDate().after(lastCar.getRent().getReturnDate())) //check if current car is with the latest return date
            {
                lastCar =  temp;
            }
            temp = temp.getNext();
        }
        return lastCar.getRent().getCar();

    }

    /**
     * return the longest rent
     * @return longest rent
     */
    public Rent longestRent() 
    {
        if(_head == null) return null;// there is no rent
        //finding the longest by running till the end of the list
        RentNode longestRent = _head;
        RentNode temp = _head;
        while(temp != null)
        {
            if(temp.getRent().howManyDays() > longestRent.getRent().howManyDays())//check if current rent is the longest
            {
                longestRent =  temp;
            }
            temp = temp.getNext();
        }
        return longestRent.getRent();

    }

    /**
     * Calculate and return the longest rent
     * @return longest rent
     */
    public char mostCommonRate()
    {
        if(_head == null) return 'N'; //// there is no rent
        //daclaration
        int counterA = 0;
        int counterB = 0;
        int counterC = 0;
        int counterD = 0;
        //countinf by running till the end 
        RentNode temp = _head;
        while(temp != null)
        {

            if(temp.getRent().getCar().getType() == 'A')
                counterA++;
            if(temp.getRent().getCar().getType() == 'B')
                counterB++;
            if(temp.getRent().getCar().getType() == 'C')
                counterC++;
            if(temp.getRent().getCar().getType() == 'D')
                counterD++;

            temp = temp.getNext();
        }
        // finding the most common
        int max = Math.max(Math.max(counterA, counterB),Math.max(counterC,counterD));  
        if(max == counterD) return 'D';
        if(max == counterC) return 'C';
        if(max == counterB) return 'B';
        return 'A';
    }

    /**
     * Check if a given company is inlude in this company
     * @param other - the other company
     * @return true - if include , false - if not include
     */
    public boolean includes(Company other)
    {
        if(other.getNumOfRents() == 0) return true;//if the input is empty return true
        if(this.getNumOfRents() == 0) return false;//if this company is empty return false
        //running till the end looking if other is include
        RentNode temp1 = _head;
        RentNode temp2 = other._head;
        while(temp1 != null)
        {
            if(temp2 == null) return true;//other is include
            if(temp1.getRent().equals(temp2.getRent())) //the current rent is equal, search the next rent
                temp2 = temp2.getNext();
            else//the current rent is not equal
                temp2 = other._head;
            temp1 = temp1.getNext();
        }
        return false;   
    }

    /**
     * Add a given company to this company
     * @param other - the other company
     */
    public void merge(Company other)
    {
        RentNode temp = other._head;
        RentNode prevTemp;
        //running till the end and add any rent from other to this company
        while(temp != null)
        {
            prevTemp = temp;
            temp = temp.getNext();
            addRent(prevTemp.getRent().getName(), prevTemp.getRent().getCar(), prevTemp.getRent().getPickDate(), prevTemp.getRent().getReturnDate());

        }
    }

    /**
     * Returns a String that represents this company 
     * @return String that represents this company 
     */
    public String toString()
    {
        if(this.getNumOfRents() == 0) return "The company has 0 rents."; // the company is empty
        String str = "The company has " + this.getNumOfRents() + " rents:\n" ;
        RentNode temp = _head;
        while(temp != null)
        {
            str += temp.getRent().toString() + "\n";
            temp = temp.getNext();
        }
        return str;
    }
}
