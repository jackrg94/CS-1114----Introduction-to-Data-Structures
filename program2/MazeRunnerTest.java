import sofia.micro.*;
import sofia.micro.jeroo.*;
import static sofia.micro.jeroo.CompassDirection.*;
import static sofia.micro.jeroo.RelativeDirection.*;
import sofia.micro.World.*;

// -------------------------------------------------------------------------
/**
 *  tests the maze for correctness
 *
 *  @author jackrg94
 *  @version 2015.02.11
 */
public class MazeRunnerTest extends TestCase
{
    //~ Fields ................................................................
    /**
     * field island
     */
    //MazeIsland island;
    /**
     * field jane
     */
    //MazeRunner jane;
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new MazeRunnerTest test object.
     */
    public MazeRunnerTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }

    //~ Methods ...............................................................
    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     */
    public void setUp()
    {
        //MazeRunner jane = new MazeRunner();
    }

    // ----------------------------------------------------------
    /**
     * runs the test of the maze
     */
    public void testIsland1()
    {
        Island island = new MazeIsland();
        MazeRunner jane = new MazeRunner();
        island.add(jane);
        jane.myProgram();
        assertTrue(island.getObjects(Net.class).isEmpty());
        assertTrue(island.getObjects(Flower.class).isEmpty());
        assertEquals(island.getWidth() - 2, jane.getX(), .001);
        assertEquals(island.getHeight() - 2, jane.getY(), .001);        
    }
    /**
     * runs the test of the maze
     */
    public void testIsland2()
    {
        Island island = new MazeIsland();
        MazeRunner jane = new MazeRunner();
        island.add(jane);
        jane.myProgram();
        assertTrue(island.getObjects(Net.class).isEmpty());
        assertTrue(island.getObjects(Flower.class).isEmpty());
        assertEquals(island.getWidth() - 2, jane.getX(), .001);
        assertEquals(island.getHeight() - 2, jane.getY(), .001);
    }
    /**
     * runs the test of the maze
     */
    public void testIsland3()
    {
        Island island = new MazeIsland();
        MazeRunner jane = new MazeRunner();
        island.add(jane);
        jane.myProgram();
        assertTrue(island.getObjects(Net.class).isEmpty());
        assertTrue(island.getObjects(Flower.class).isEmpty());
        assertEquals(island.getWidth() - 2, jane.getX(), .001);
        assertEquals(island.getHeight() - 2, jane.getY(), .001);
    }
    /**
     * runs the test of the maze
     */
    public void testIsland4()
    {
        Island island = new MazeIsland();
        MazeRunner jane = new MazeRunner();
        island.add(jane);
        jane.myProgram();
        assertTrue(island.getObjects(Net.class).isEmpty());
        assertTrue(island.getObjects(Flower.class).isEmpty());
        assertEquals(island.getWidth() - 2, jane.getX(), .001);
        assertEquals(island.getHeight() - 2, jane.getY(), .001);
    }
    /**
     * runs the test of the maze
     */
    public void testIsland5()
    {
        Island island = new MazeIsland();
        MazeRunner jane = new MazeRunner();
        island.add(jane);
        jane.myProgram();
        assertTrue(island.getObjects(Net.class).isEmpty());
        assertTrue(island.getObjects(Flower.class).isEmpty());
        assertEquals(island.getWidth() - 2, jane.getX(), .001);
        assertEquals(island.getHeight() - 2, jane.getY(), .001);
    }
    /**
     * runs the test of the maze
     */
    public void testIsland6()
    {
        Island island = new MazeIsland();
        MazeRunner jane = new MazeRunner();
        island.add(jane);
        jane.myProgram();
        assertTrue(island.getObjects(Net.class).isEmpty());
        assertTrue(island.getObjects(Flower.class).isEmpty());
        assertEquals(island.getWidth() - 2, jane.getX(), .001);
        assertEquals(island.getHeight() - 2, jane.getY(), .001);
    }
}
