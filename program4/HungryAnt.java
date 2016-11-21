import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  eats a bee on interaction but it takes a while to digest
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class HungryAnt extends Ants
{
    //~ Fields ................................................................
    private boolean digestFlag;

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new HungryAnt object.
     */
    public HungryAnt()
    {
        super();
        setHealth(1);
        foodCost = 5;
        setTimerPeriod(240);
        digestFlag = false;
    }

    //~ Methods ...............................................................
    /**
     * on time 0, this method executes.
     */
    public void onTimer()
    {
        digestFlag = false;
    }
    
    /**
     * runs on every turn, checking if bee is in front 
     * to eat.
     */
    public void act()
    {
        super.act();
        if (!this.getIntersectingObjects(Bee.class).isEmpty() && !digestFlag)
        {
            digestFlag = true;
            this.getWorld().remove(this.getIntersectingObjects(
                Bee.class).get(0));
            resetTimer();
        }
    }
    
    /**
     * setter for testing purposes
     * @return  returns digestFlag
     * @param x     boolean to enter
     */
    public boolean setDigestFlag(boolean x)
    {
        digestFlag = x;
        return digestFlag;
    }
    
    /**
     * getter for testing purposes
     * @return returns digestFlag
     */
    public boolean getDigestFlag()
    {
        return digestFlag;
    }
}
