import sofia.micro.*;
import java.util.*;
import sofia.util.Random;


//-------------------------------------------------------------------------
/**
 *  Represents the world. Also populates the world with asteroids 
 *  and a ship.
 *
 *  @author jackrg94
 *  @version 2015.04.13
 */
public class Space extends World
{
    //~ Fields ................................................................
    /**
     * ship field that is used throughout world
     */
    protected Ship ship;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Space object.
     */
    public Space()
    {
        super(500, 500, 1);
        populate();
    }
    
    /**
     * Creates a new Space object
     * @param width     int for width of world
     * @param height    int for height of world
     */
    public Space(int width, int height)
    {
        super(width, height, 1);
    }


    //~ Methods ...............................................................
    /**
     * fills the world with asteroids at random locations
     * and a single ship in the middle of the world
     */
    public void populate()
    {
        //creates 1 ship in the center of world facing north
        int x = getHeight() / 2;
        int y = getWidth() / 2;
        ship = new Ship();
        
        add(ship, x, y);
        //creates 5 randomly placed asteroids, each of which has
        //a random speed between 1-5 and a random orientation of 0-359.
        for (int i = 0; i < 5; i++)
        {
            int s = Random.generator().nextInt(1, 5);
            int r = Random.generator().nextInt(0, 359);
            int astX = Random.generator().nextInt(499);
            int astY = Random.generator().nextInt(499);
            add(new Asteroid(s, r), astX, astY);
        }
    }
}
