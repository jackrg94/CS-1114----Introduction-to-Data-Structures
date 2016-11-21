import sofia.micro.*;
import java.util.*;
import sofia.util.Random;

//-------------------------------------------------------------------------
/**
 *  houses all of the bees. releases bees
 *  on timed delay.
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class Hive extends Zones
{
    //~ Fields ................................................................
    private List<Bee> bees;
    private int hiveSize;
    /**
     * keeps track of released bees
     */
    protected int beeCounter;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new BeeHive object.
     */
    public Hive()
    {
        super();
        hiveSize = 0;
        bees = new ArrayList<Bee>(30);
        beeCounter = 30;
        setBees(30);
        this.setTimerPeriod(400);
        this.resetTimer();
    }

    //~ Methods ...............................................................
    /**
     * returns the number of bees in the hive.
     * @return int  number of bees in hive
     */
    public int getBees()
    {
        // returns number of bees in the hive
        return hiveSize;
    }

    /**
     * @param n     number of bees to set in Hive
     */
    public void setBees(int n)
    {
        hiveSize = n;
    }
    
    /**
     * on time 0, this method is executed.
     */
    public void onTimer()
    {
        if (getBeeCounter() > 0)
        {
            releaseTheBees();
        }
    }
    
    /**
     * is called by onTimer(), random
     * delay release for bees.
     */
    public void releaseTheBees()
    {
        int randTurn = Random.generator().nextInt(80, 400);
        Bee b = new Bee();
        int y = Random.generator().nextInt(1, 5);

        this.setTimerPeriod(randTurn);
        this.resetTimer();
        bees.add(b);
        beeCounter = beeCounter - 1;
        hiveSize = hiveSize - 1;
        this.getWorld().add(b, 9, y);
    }
    
    /**
     * returns the list that keeps track
     * of number of bees released.
     * @return int      number of bees released
     */
    public int getBeeCounter()
    {
        return beeCounter;
    }
    
    /**
     * sets beeCounter for testing purposes
     * 
     * @return int  bee counter
     * @param n   number to set
     */
    public int setBeeCounter(int n)
    {
        beeCounter = n;
        return beeCounter;
    }
}
