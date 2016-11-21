import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  produces one unit of food for colony every 40 turns
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class HarvesterAnt extends Ants
{
    //~ Fields ................................................................


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new HarvesterAnt object.
     */
    public HarvesterAnt()
    {
        super();
        foodCost = 2; 
        setHealth(1);
        setTimerPeriod(40);
        resetTimer();
    }

    //~ Methods ...............................................................
    /**
     * on time 0 this method executes.
     */
    public void onTimer()
    {
        produceFood();
        resetTimer();
    }
    
    /**
     * adds food to the supply
     */
    public void produceFood()
    {
        ((Colony)(this.getWorld())).addFood(1);
    }
}
