
/**
 * This class represents a Rent Node object
 *
 * @author Omer Uziel
 * @version 26.01.23
 */
public class RentNode
{
    // declarations
    private Rent _rent;
    private RentNode _next;
   
    /**
     * Creates a new RentNode object
     * @param r the Rent Object 
     * set the next to null 
     */
    public RentNode (Rent r)
    {
        _rent = new Rent(r);
        _next = null;
    }
    
    /**
     * Creates a new RentNode object
     * @param r the Rent Object 
     * @param next  the next RentNode
     */
    public RentNode (Rent r, RentNode next)
    {
        _rent = new Rent(r);
        _next = next;
    }
   
    /**
     * Copy constructor, Creates a new RentNode object like the other
     * @param other the RentNode to be copied 
     */
    public RentNode (RentNode other) 
    {
        
        _rent = other._rent;
        _next = other._next;
    }
    
    /**
     * Gets the Rent  
     * @return the Rent
     */
    public Rent getRent()
    {
        return new Rent(_rent);        
    }
    
    /**
     * Gets the next  
     * @return the next
     */
    public RentNode getNext()
    {
        return _next;
    }
    
    /**
     * Sets the Rent
     * @param r the Rent object
     */
    public void setRent(Rent r)
    {
        _rent = new Rent(r);        
    }
    
    /**
     * Sets the next
     * @param name the next RentNode 
     */
    public void setNext(RentNode next)
    {
        _next = next;
    }
    
    
}
