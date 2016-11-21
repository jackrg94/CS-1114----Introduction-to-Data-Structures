import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  controls timer for all actors
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class GameEntity extends Actor
{
    //~ Fields ................................................................

    private int timer;
    private int period;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new GameEntity object.
     */
    public GameEntity()
    {
        super();
        setTimerPeriod(80);
    }


    //~ Methods ...............................................................
    /**
     * runs every turn, checks timer
     */
    public void act()
    {
        if (isTimerRunning())
        {
            timer--;
            if (!isTimerRunning())
            {
                onTimer();
            }
        }
    }
    
    /**
     * when timer hits 0, this method is invoked
     */
    public void onTimer()
    {
        // For subclasses to override
    }
    
    /**
     * returns the timer
     * @return  timer
     */
    public int getTimer()
    {
        return timer;
    }
    
    /**
     * returns the timer period
     * @return timer period
     */
    public int getTimerPeriod()
    {
        return period;
    }
    
    /**
     * @param p   int to set period
     * sets timer p
     */
    public void setTimerPeriod(int p)
    {
        this.period = p;
    }
    
    /**
     * resets the timer to equal the period
     * 
     */
    public void resetTimer()
    {
        timer = period;
    }
    
    /**
     * checks to see if timer is greater than 0
     * @return  if timer is greater than 0
     */
    public boolean isTimerRunning()
    {
        return timer > 0;
    }
}
