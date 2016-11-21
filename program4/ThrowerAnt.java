import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  throws a leaf at bees every 120 turns
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class ThrowerAnt extends Ants
{
    //~ Fields ................................................................


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ThrowerAnt object.
     */
    public ThrowerAnt()
    {
        super();
        foodCost = 4;
        setHealth(1);
        setTimerPeriod(120);
        resetTimer();
    }


    //~ Methods ...............................................................
    /**
     * on time 0, this method executes.
     */
    public void onTimer()
    {
        this.getWorld().add(new Leaf(), this.getGridX(), this.getGridY());
        resetTimer();
    }

}
