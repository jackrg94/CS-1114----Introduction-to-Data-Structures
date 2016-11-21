import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  Represents the ship in space. It uses a on-screen thumbstick
 *  to steer. 
 *
 *  @author jackrg94
 *  @version 2015.04.13
 */
public class Ship extends SpaceObjects
{
    //~ Fields ................................................................

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Ship object.
     */
    public Ship()
    {
        super(0, -90);

    }

    //~ Methods ...............................................................
    /**
     * when up arrow on keyboard is pressed
     * this method executes
     */
    public void dpadNorthIsDown()
    {
        speed++;
    }

    /**
     * when left arrow on keyboard is pressed
     * this method executes
     */
    public void dpadEastIsDown()
    {
        this.setRotation(this.getRotation() + 5);
    }

    /**
     * when down arrow on keyboard is pressed
     * this method executes
     */
    public void dpadSouthIsDown()
    {
        if (this.getSpeed() > 0)
        {
            speed = speed - 1;
        }
    }

    /**
     * when right arrow on keyboard is preseed
     * this method executes
     */
    public void dpadWestIsDown()
    {
        this.setRotation(this.getRotation() - 5);
    }

    /**
     * runs every turn
     */
    public void act()
    {
        move();
    }

    /**
     * when mouse is clicked this method executes
     */
    public void onScreenTouchDown()
    {
        fireBullet();
    }

    /**
     * creates a new bullet that moves every turn
     * and can destroy asteroids
     */
    public void fireBullet()
    {
        getWorld().add(new Bullet(10, ((int)getRotation())),
            getGridX() + 1, getGridY());
    }

    /**
     * overrides supers collide method to prevent the bullet from
     * removing the ship on spawn
     */
    public void collide()
    {
        if (this.getOneIntersectingObject(Bullet.class) == null)
        {
            super.collide();
        }
    }
}
