import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  tests SpaceObjects class' methods
 *
 *  @author jackrg94
 *  @version 2015.04.23
 */
public class SpaceObjectsTest extends TestCase
{
    //~ Fields ................................................................
    private Space space;
    private Ship ship;
    

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SpaceObjectsTest test object.
     */
    public SpaceObjectsTest()
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
    }


    // ----------------------------------------------------------
    
    /**
     * tests movement of space objects
     */
    public void testMove()
    {
        space.add(ship, 1, 200);
        ship.move();
        assertEquals(ship.getGridX(), 499);
        space.remove(ship);
        
        space.add(ship, 499, 200);
        ship.move();
        assertEquals(ship.getGridX(), 0);
        space.remove(ship);
        
        space.add(ship, 200, 499);
        ship.move();
        assertEquals(ship.getGridY(), 0);
        space.remove(ship);
        
        space.add(ship, 200, 1);
        ship.move();
        assertEquals(ship.getGridY(), 499);
        space.remove(ship);
        
        space.add(ship, 200, 200);
        ship.speed = 10;
        ship.move();
        assertEquals(ship.getGridY(), 190);
    }
}
