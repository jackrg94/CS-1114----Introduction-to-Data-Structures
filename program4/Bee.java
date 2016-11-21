import sofia.micro.*;
import java.util.*;

//-------------------------------------------------------------------------
/**
 *  bee moves continuously
 *  unless obstructed by ant, in that case
 *  it attacks every 40 turns.
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class Bee extends AntsAndBees
{
    //~ Fields ................................................................
    private boolean moveFlag;
    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Bees object.
     */
    public Bee()
    {
        super();
        setHealth(3);
        setRotation(180);
        setTimerPeriod(40);
        moveFlag = false;
    }

    //~ Methods ...............................................................
    /**
     * on time 0, this method is executed.
     */
    public void onTimer()
    {
        sting();
    }

    /**
     * attacks the ant that has made contact
     * with bee
     */
    public void sting()
    {
        if (this.getIntersectingObjects(Ants.class).isEmpty())
        {
            moveFlag = false;
        }
        if (moveFlag)
        {
            (this.getIntersectingObjects(Ants.class).get(0)).injure(1);
            resetTimer();
        }
    }

    /**
     * moves across world unless obstructed.
     */
    public void movement()
    {
        if (this.getIntersectingObjects(Ants.class).isEmpty())
        {
            this.move(0.0125);
        }
        else
        {
            moveFlag = true;
            resetTimer();
        }
    }

    /**
     * called every turn for movement purposes
     */
    public void act()
    {
        super.act();
        if (!moveFlag)
        {
            this.movement();
        }
    }

    /**
     * getter for variable moveFlag,
     * which is set to true when something
     * is obstructing bee's movement
     * @return boolean  current state of moveFlag
     */
    public boolean getMoveFlag()
    {
        return moveFlag;
    }

    /**
     * sets moveFlag to true for testing
     * purposes.
     * @param x   set next bool
     * @return boolean  current state of moveFlag
     */
    public boolean setMoveFlag(boolean x)
    {
        moveFlag = x;
        return moveFlag;
    }
}
