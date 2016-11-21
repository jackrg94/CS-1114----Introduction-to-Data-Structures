import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  encompasses all of the ants in the game.
 *  includes the cost to produce each ant.
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class Ants extends AntsAndBees
{
    //~ Fields ................................................................
    /**
     * food cost field for ant spawns
     */
    protected int foodCost;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Ants object.
     */
    public Ants()
    {
        super();
    }


    //~ Methods ...............................................................
    /**
     * returns the food cost
     * @return int   food cost
     */
    public int getFoodCost()
    {
        return foodCost;
    }
    
}
