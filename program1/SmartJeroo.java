import sofia.micro.jeroo.*;

//-------------------------------------------------------------------------
/**
 * Creates a more advanced Jeroo to navigate the Island
 *
 *  @author jackrg94
 *  @version 2015-01-30
 */
public class SmartJeroo extends Jeroo
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**Creates a SmartJeroo to navigate 
     * @param x         The x-coordinate of the Jeroo's location.
     * @param y         The y-coordinate of the Jeroo's location.
     */
    public SmartJeroo(int x, int y)
    {
        super(x, y);
    }
    //~ Method ................................................................
    /**
     * while the space ahead is clear, 
     * the jeroo will hop then check the square for a flower
     */
    public void pathClear()
    {
        while (isClear(RelativeDirection.AHEAD))
        {
            hop();
            checkSquare();
        }
    }
    /**
     * navigates a right turn
     */
    public void turnLeftCorner()
    {
        hop();
        turn(LEFT);
        hop();
    }
    /**
     * navigates a left turn
     */
    public void turnRightCorner()
    {
        hop();
        turn(RIGHT);
        hop();
    }
    
    /**
     * Picks flower at current location
     */
    public void checkSquare()
    {
        if (seesFlower(RelativeDirection.AHEAD))
        {
            hop();
            pick(); 
        }
    }
    /**
     * jeroo goes around the first corner of nets
     */
    public void firstCorner()
    {
        pathClear();
        turn(RIGHT);
        hop();
        turnLeftCorner();
        turnLeftCorner();
        pathClear();
        turn(RIGHT);
        
    }
    /**
     * collects flowers along top row
     */
    public void firstRow()
    {
        pathClear();
        turn(RIGHT);
        turnLeftCorner();
        turn(RIGHT);
    }
    /**
     * navigates to bottom row of flowers
     */
    public void downSecondRow()
    {
        pathClear();
        turn(RIGHT);
        turnRightCorner();
        turn(LEFT);
        pathClear();
        turn(RIGHT);
    }
    /**
     * navigates to last flower
     */
    public void lastFlower()
    {
        turnRightCorner();
        turnLeftCorner();
        hop(2);
        pick();
    }
}
