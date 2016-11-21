import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  Contains ship, asteroid and bullet.
 *  Stores speed and rotation for sub classes
 *  and oversees movement and collision.
 *
 *  @author jackrg94
 *  @version 2015.04.13
 */
public class SpaceObjects extends Actor
{
    //~ Fields ................................................................

    /**
     * keeps track of speed of subclass objects, including
     * asteroid, ship and bullet.
     */
    protected int speed;

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new SpaceObjects object.
     * @param s     int for how fast object moves in pixles per turn
     * @param r     which way the object is oriented. in degrees
     */
    public SpaceObjects(int s, int r)
    {
        super();
        speed = s;
        this.setRotation(r);
    }

    //~ Methods ...............................................................
    /**
     * returns speed
     * @return      speed of asteroid
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * move modified to wrap around world if passes x and y limit
     * including collision
     */
    public void move()
    {
        if (this.getGridX() <= 1)
        {
            this.setGridX(500);
        }
        else if (this.getGridX() >= 499)
        {
            this.setGridX(0);
        }
        else if (this.getGridY() >= 499)
        {
            this.setGridY(0);           
        }
        else if (this.getGridY() <= 1)
        {
            this.setGridY(500);
        }
        super.move(speed);
        this.collide();
    }

    /**
     * checks for impact between two objects and removes both (exempted
     * asteroids in asteroid class)
     */
    public void collide()
    {
        if (this.getOneIntersectingObject(null) != null)
        {
            this.getWorld().remove(this.getOneIntersectingObject(null));
            this.getWorld().remove(this);
        }
    }

}
