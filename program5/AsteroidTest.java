import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  tests Asteroid class' methods
 *
 *  @author jackrg94
 *  @version 2015.04.23
 */
public class AsteroidTest extends TestCase
{
    //~ Fields ................................................................
    private Space space;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new AsteroidTest test object.
     */
    public AsteroidTest()
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
    }


    // ----------------------------------------------------------
    /**
     * tests the collide method with all possiblities
     */
    public void testCollide()
    {
        Asteroid ast = new Asteroid(2, 0);
        Asteroid ast1 = new Asteroid(2, 180);
        space.add(ast, 200, 200);
        space.add(ast1, 195, 200);
        ast.move();
        ast1.move();
        assertEquals(2, space.getObjects(Asteroid.class).size());
        space.remove(ast1);
        assertEquals(1, space.getObjects(Asteroid.class).size());
        ast.collide();
        assertEquals(1, space.getObjects(Asteroid.class).size());
        Ship ship = new Ship();
        ship.speed = 10;
        ship.setRotation(0);
        space.add(ship, 202, 200);
        ast.collide();
        assertEquals(0, space.getObjects(Ship.class).size());
        assertEquals(0, space.getObjects(Asteroid.class).size());
    }
    
    /**
     * tests the act method to ensure asteroids move properly
     */
    public void testAct()
    {
        Asteroid ast = new Asteroid(2, 0);
        space.add(ast, 200, 200);
        int initX = ast.getGridX();
        int initY = ast.getGridY();
        ast.act();
        assertEquals(initX + 2, ast.getGridX());
        assertEquals(initY, ast.getGridY());
    }

}
