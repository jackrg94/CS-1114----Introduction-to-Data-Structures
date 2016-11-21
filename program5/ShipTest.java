import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  tests Ship class' methods.
 *
 *  @author jackrg94
 *  @version 2015.04.23
 */
public class ShipTest extends TestCase
{
    //~ Fields ................................................................
    private Space space;
    private Ship ship;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ShipTest test object.
     */
    public ShipTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        space = new Space(500, 500);        
        ship = new Ship();
        space.add(ship, 200, 200);
    }


    // ----------------------------------------------------------
    /**
     * tests d pad north is down method
     */
    public void testDPadNorthIsDown()
    {
        ship.dpadNorthIsDown();
        assertEquals(1, ship.getSpeed());
    }
    
    /**
     * tests d pad east is down method
     */
    public void testDPadEastIsDown()
    {
        ship.dpadEastIsDown();
        assertEquals(ship.getRotation(), -85.0, .01);
    }
    
    /**
     * tests d pad south is down method
     */
    public void testDPadSouthIsDown()
    {
        ship.dpadSouthIsDown();
        assertEquals(0, ship.getSpeed());
        ship.dpadSouthIsDown();
        assertEquals(0, ship.getSpeed());
        ship.speed = 3;
        ship.dpadSouthIsDown();
        assertEquals(2, ship.getSpeed());
        ship.speed = -1;
        ship.dpadSouthIsDown();
        assertEquals(-1, ship.getSpeed());
    }

    /**
     * tests d pad west is down method
     */
    public void testDPadWestIsDown()
    {
        ship.dpadWestIsDown();
        assertEquals(ship.getRotation(), -95, .01);
    }
    
    /**
     * tests ships act method
     */
    public void testAct()
    {
        ship.dpadNorthIsDown();
        ship.act();
        assertEquals(1, space.getObjects(Ship.class).size());
    }
    
    /**
     * tests on screen touch down method
     */
    public void testOnScreenTouchDown()
    {
        ship.onScreenTouchDown();
        assertEquals(1, space.getObjects(Bullet.class).size());
    }
    
    /**
     * tests fire bullet method
     */
    public void testFireBullet()
    {
        ship.fireBullet();
        assertEquals(1, space.getObjects(Bullet.class).size());
    }
    
    /**
     * tests collision with asteroid
     */
    public void testCollision()
    {
        Asteroid ast = new Asteroid(2, 90);
        space.add(ast, 200, 190);
        ship.speed = 10;
        ship.act();
        assertEquals(0, space.getObjects(Ship.class).size());
        assertEquals(0, space.getObjects(Asteroid.class).size());
    }
    
    /**
     * tests collision with bullet
     */
    public void testCollision2()
    {
        Bullet bullet = new Bullet(10, 90);
        space.add(bullet, 200, 190);
        bullet.move();
        ship.collide();
        assertEquals(1, space.getObjects(Bullet.class).size());
        assertEquals(1, space.getObjects(Ship.class).size());
    }
}
