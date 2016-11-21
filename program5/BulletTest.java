import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  tests bullet class' methods
 *
 *  @author jackrg94
 *  @version 2015.04.23
 */
public class BulletTest extends TestCase
{
    //~ Fields ................................................................
    private Bullet bullet;
    private Space space;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new BulletTest test object.
     */
    public BulletTest()
    {
        space = new Space();
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        bullet = new Bullet(10, 90);
    }


    // ----------------------------------------------------------
    /**
     * tests movement of bullet
     */
    public void testMove()
    {
        space.add(bullet, 200, 200);
        bullet.act();
        assertEquals(210, bullet.getGridY());        
    }
    
    /**
     * tests movement of bullet
     */
    public void testMove2()
    {
        space.add(bullet, 200, 499);
        bullet.move();
        assertEquals(0, space.getObjects(Bullet.class).size());
    }
    
    /**
     * tests the movement of bullet
     */
    public void testMove3()
    {
        space.add(bullet, 0, 1);
        bullet.move();
        assertEquals(0, space.getObjects(Bullet.class).size());

        space.add(bullet, 500, 1);
        bullet.move();
        assertEquals(0, space.getObjects(Bullet.class).size());

        space.add(bullet, 100, 0);
        bullet.move();
        assertEquals(0, space.getObjects(Bullet.class).size());
        
        space.add(bullet, 100, 500);
        bullet.move();
        assertEquals(0, space.getObjects(Bullet.class).size());
    }
    
    /**
     * tests the bullets collision
     */
    public void testCollision()
    {
        Space space1 = new Space(500, 500);
        bullet = new Bullet(10, 90);
        space1.add(bullet, 200, 200);
        Asteroid ast = new Asteroid(2, -90);
        space1.add(ast, 200, 205);
        bullet.act();
        assertEquals(0, space1.getObjects(Bullet.class).size());
        assertEquals(0, space1.getObjects(Asteroid.class).size());
    }

}
