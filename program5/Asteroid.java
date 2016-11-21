import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  Represents the "hazard" in the game. It is simply a rock
 *  floating through space.
 *
 *  @author jackrg94
 *  @version 2015.04.13
 */
public class Asteroid extends SpaceObjects
{
    //~ Fields ................................................................

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Asteroid object.
     * @param s    int for how fast object moves in pixles per turn
     * @param r  which way the object is oriented. in degrees
     */
    public Asteroid(int s, int r)
    {
        super(s, r);
    }

    //~ Methods ...............................................................
    
    /**
     * moves asteroid every turn
     */
    public void act()
    {
        super.move();
    }

    /**
     * overrides collision to exempt other asteroids
     */
    public void collide()
    {
        if (this.getOneIntersectingObject(Asteroid.class)  == null)
        {
            super.collide();
        }
    }

}
