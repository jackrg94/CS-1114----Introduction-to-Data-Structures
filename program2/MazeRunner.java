import sofia.micro.jeroo.*;
import sofia.util.*;
import sofia.micro.greenfoot.Greenfoot;

//-------------------------------------------------------------------------
/**
 *  The goal of the Jeroo is to get to the SouthEast
 *  corner of the maze while clearing obstacles.
 *  The maze is generated each time thru a list of presets.
 *
 *  @author jackrg94
 *  @version 2015.02.11
 */
public class MazeRunner extends Jeroo
{
    //~ Fields ................................................................

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new MazeRunner object.
     * @param x x-coordinate of mazerunner
     * @param y y-coordinate of mazerunner
     * @param flowers # of flowers spawned with
     */
    public MazeRunner(int x, int y, int flowers)
    {
        super(x, y, flowers);
    }

    /**
     * spawns the MazeRunner at the correct location w 10 flowers
     */
    public MazeRunner()
    {
        this(1, 1, 10);
    }

    //~ Methods ...............................................................
    /**
     * runs the maze
     */
    public void myProgram()
    {
        clearFlowersAndNets();
    }

    /**
     * makes sure it is clear before hopping
     */
    public void safeHop()
    {

        if (seesNet(AHEAD))
        {
            toss();
        }            
        if (seesFlower(HERE))
        {
            pick();
        }
        hop();

    }
    /**
     * rids the islands of flowers and nets
     */
    public void clearFlowersAndNets()
    {
        while (!getWorld().getObjects(Net.class).isEmpty()
                || (!getWorld().getObjects(Flower.class).isEmpty()))
        {
            avoidWater();
        }
        while (!((getGridX() == getWorld().getWidth() - 2) 
            && (getGridY() == getWorld().getHeight() - 2)))
        {
            avoidWater();
        }
    }

    /**
     * continuously Hops until stopped by Water
     */
    public void continueHopping()
    {
        while (!seesWater(AHEAD))
        {
            if (seesNet(AHEAD))
            {
                toss();
            }            
            if (seesFlower(HERE))
            {
                pick();
            }
            if (!seesWater(RIGHT))
            {
                turn(RIGHT);
                safeHop();
            }
            if (!seesWater(LEFT))
            {
                turn(LEFT);
                safeHop();
            }
            safeHop();
        }

    }

    /**
     * turns Around if water on all sides
     */
    public void turnAround()
    {
        turn(RIGHT);
        turn(RIGHT);            
    }

    /**
     * randomly chooses a direction to go
     * at an intersection
     */
    public void turnIntersection()
    {

        int rnd = Greenfoot.getRandomNumber(2);
        if (rnd == 0)
        {   
            turn(RIGHT);
        }
        else
        {
            turn(LEFT);
        }

    }

    /**
     * uses all other methods to solve maze
     * by avoiding water
     */
    public void avoidWater()
    {        
        if (!seesWater(AHEAD))
        {
            continueHopping();
        }
        else if (!seesWater(RIGHT) && !seesWater(LEFT))
        {
            turnIntersection();
            safeHop();
        }
        else if (seesWater(RIGHT) && seesWater(LEFT))
        {
            turnAround();
            safeHop();
        }
        else if (seesWater(RIGHT))
        {
            turn(LEFT);
            safeHop();
        }
        else
        {
            turn(RIGHT);
            safeHop();
        }
    }

}
