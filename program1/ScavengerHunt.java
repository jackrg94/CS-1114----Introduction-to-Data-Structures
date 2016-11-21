import sofia.micro.jeroo.*;

//-------------------------------------------------------------------------
/**
 *  the Smart Jeroo, tim, will navigate the island and pick up all 7 flowers
 *
 *  @author jackrg94
 *  @version 2015-01-30
 */
public class ScavengerHunt extends LongIsland
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ScavengerHunt object.
     */
    public ScavengerHunt()
    {
        // Nothing to initialize, leaving the world a default size
    }


    //~ Methods ...............................................................
    /**
     * clears the island of flowers
     */
    public void myProgram()
    {
        SmartJeroo tim = new SmartJeroo(2, 2);
        add(tim);
        tim.firstCorner();
        tim.firstRow();
        tim.downSecondRow();
        tim.lastFlower();
    }

}
