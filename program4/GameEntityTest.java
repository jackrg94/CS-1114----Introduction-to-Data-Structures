import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  This test class tests the functionality of the GameEntity class. It checks
 *  whether the timer works properly, as well as the functionality of the act()
 *  method.
 *
 *  @author Dante Wiggins (danwte90)
 *  @version 2015.03.18
 */
public class GameEntityTest extends TestCase
{
    //~ Fields ................................................................
    private GameEntity gameEntity;
    private Hive hive;
    private QueensChamber queensChamber;
    private Colony colony;
    private Bee bee;
    private Bee bee2;
    private HarvesterAnt harvy;
    private Leaf leaf;
    private FireAnt firey;
    private WallAnt wally;
    private ThrowerAnt throwy;
    private HungryAnt hungry;
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new GameEntityTest test object.
     */
    public GameEntityTest()
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
        gameEntity = new GameEntity();
        colony = new Colony();
        hive = colony.getHive();
        queensChamber = colony.getQueensChamber();
        harvy = new HarvesterAnt();
        bee = new Bee();
        bee2 = new Bee();
        leaf = new Leaf();
        firey = new FireAnt();
        hungry = new HungryAnt();
        wally = new WallAnt();
        throwy = new ThrowerAnt();
    }

    // ----------------------------------------------------------

    /**
     * Tests whether the GameEntity object was properly initialized.
     * The default timer period is 80, and the intial timer value is 0.
     */
    public void testConstructor() 
    {
        assertEquals(80, gameEntity.getTimerPeriod());
        assertEquals(0, gameEntity.getTimer());
    }

    /**
     * Tests if setting and getting the period of the timer functions properly.
     */
    public void testTimerPeriod() 
    {
        gameEntity.setTimerPeriod(50);
        assertEquals(50, gameEntity.getTimerPeriod());
    }

    /**
     * Tests if getting and resetting the timer functions properly.
     * The timer value should reset to be equal to the period.
     */
    public void testTimer() 
    {
        assertEquals(0, gameEntity.getTimer());
        gameEntity.resetTimer();
        assertEquals(80, gameEntity.getTimer());
    }

    /**
     * Tests whether the isTimerRunning() method functions properly.
     * It should be false when 0, and true otherwise.
     */
    public void testIsTimerRunning() 
    {
        assertFalse(gameEntity.isTimerRunning());
        gameEntity.resetTimer();
        assertTrue(gameEntity.isTimerRunning());
    }

    /**
     * Tests the act() method when the timer is running. The only
     * action is that the timer gets decremented.
     */
    public void testActWhenTimerIsRunning() 
    {
        gameEntity.resetTimer();
        assertEquals(80, gameEntity.getTimer());
        gameEntity.act();
        assertEquals(79, gameEntity.getTimer());        
    }

    /**
     * Tests the act() method when the timer is not running. The
     * timer should not be decremented when already at 0.
     */
    public void testActWhenTimerIsNotRunning() 
    {
        assertEquals(0, gameEntity.getTimer());
        gameEntity.act();
        assertEquals(0, gameEntity.getTimer());
    }

    /**
     * Tests the act() method when the timer runs out, i.e. when
     * it decreases from 1 to 0. The onTimer() method should be called,
     * though it does nothing.
     */
    public void testActWhenTimerRunsOut() 
    {
        gameEntity.resetTimer();
        for (int i = 0; i < 79; i++) {
            gameEntity.act();
        }
        assertEquals(1, gameEntity.getTimer());
        gameEntity.act();
        assertEquals(0, gameEntity.getTimer());
        // If the onTimer() method was non-empty, its functionality should be
        // checked here as well
    }

    /**
     * spawn and movement tests for bee.
     */
    public void testHiveAct()
    {
        hive.getBees();
        assertEquals(30, hive.getBees());
        hive.setBeeCounter(0);
        hive.onTimer();
        assertEquals(0, hive.getBeeCounter());
        gameEntity.setTimerPeriod(400);
        gameEntity.resetTimer();
        hive.onTimer();
        assertEquals(400, gameEntity.getTimer());
        hive.setBeeCounter(30);
        hive.onTimer();
        assertEquals(400, gameEntity.getTimer());
    }

    /**
     * tests wall ant
     */
    public void testWallAnt()
    {
        colony.add(wally, 8, 3);
        colony.add(bee, 8, 3);
        assertEquals(1, colony.getObjects(WallAnt.class).size());

    }

    /**
     * tests the harvester's food production
     */
    public void testHarvesterAnt()
    {
        colony.add(harvy, 1, 3);
        Colony.food = Colony.food - 2;
        harvy.produceFood();
        assertEquals(Colony.food, 9);
        gameEntity.setTimerPeriod(40);
        gameEntity.resetTimer();
        harvy.onTimer();
        assertEquals(40, gameEntity.getTimer());
    }

    /**
     * tests the firey ant's death
     */
    public void testFireAnt()
    {
        colony.add(firey, 3, 3);
        colony.add(bee, 4, 3);
        colony.add(new Bee(), 4, 4);
        colony.add(new Bee(), 4, 2);
        colony.add(new Bee(), 3, 4);
        colony.add(new Bee(), 3, 2);
        colony.add(new Bee(), 2, 4);
        colony.add(new Bee(), 2, 2);
        colony.add(new Bee(), 2, 3);
        firey.death();
        assertEquals(0, colony.getObjects(Bee.class).size());
        colony.add(firey, 3, 3);
        colony.add(bee, 7, 5);
        firey.death();
        assertEquals(1, colony.getObjects(Bee.class).size());
        colony.add(firey, 3, 3);
        colony.add(bee, 3, 3);
        firey.death();
        assertEquals(0, colony.getObjects(Bee.class).size());
    }

    /**
     * tests the hungry ant's eating method
     */
    public void testHungryAnt()
    {
        colony.add(hungry, 3, 3);
        colony.add(bee, 3, 3);
        colony.add(new Bee(), 4, 3);
        hungry.act();
        assertEquals(1, colony.getObjects(Bee.class).size());
        colony.remove(hungry);
        colony.remove(bee);
        colony.add(hungry, 2, 2);
        colony.add(bee, 5, 2);
        hungry.setDigestFlag(false);
        hungry.act();
        assertEquals(2, colony.getObjects(Bee.class).size());
        gameEntity.setTimerPeriod(240);
        gameEntity.resetTimer();
        hungry.onTimer();
        assertEquals(240, gameEntity.getTimer());
        colony.remove(hungry);
        colony.add(hungry, 8, 4);
        hungry.setDigestFlag(true);
        hungry.act();
        assertEquals(240, gameEntity.getTimer());
        colony.add(bee, 8, 4);
        hungry.setDigestFlag(true);
        hungry.act();
        assertEquals(true, hungry.getDigestFlag());
        colony.remove(bee);
        colony.remove(hungry);
        colony.add(hungry, 1, 1);
        colony.add(bee, 1, 1);
        hungry.setDigestFlag(false);
        hungry.act();
        assertEquals(true, hungry.getDigestFlag());

    }

    /**
     * tests thrower ant
     */
    public void testThrowerAnt()
    {
        colony.add(throwy, 2, 2);
        assertEquals(1, colony.getObjects(ThrowerAnt.class).size());
        gameEntity.setTimerPeriod(120);
        gameEntity.resetTimer();
        throwy.onTimer();
        assertEquals(1, colony.getObjects(Leaf.class).size());
        assertEquals(120, gameEntity.getTimer());
    }

    /**
     * tests leaf movement
     */
    public void testLeaf()
    {
        colony.add(new Leaf(), 1, 3);
        assertEquals(1, colony.getObjects(Leaf.class).size());
        colony.add(new Leaf(), 4, 4);
        colony.add(bee, 4, 4);
        assertEquals(2, colony.getObjects(Leaf.class).size());
        colony.add(leaf, 8, 4);
        leaf.movement();
        assertEquals(8, leaf.getGridX());
        colony.remove(leaf);
        colony.add(leaf, 7, 4);
        leaf.movement();
        assertEquals(3, colony.getObjects(Leaf.class).size());
        colony.remove(leaf);
        colony.add(leaf, 9, 4);
        leaf.movement();
        assertEquals(2, colony.getObjects(Leaf.class).size());
        colony.remove(leaf);
        colony.remove(bee);
        colony.add(bee, 7, 4);
        colony.add(leaf, 7, 4);
        leaf.movement();
        assertEquals(2, colony.getObjects(Leaf.class).size());
        colony.remove(leaf);
        colony.remove(bee);
        colony.add(leaf, 7, 4);
        leaf.act();
        assertEquals(3, colony.getObjects(Leaf.class).size());

    }

    /**
     * tests sting method
     */
    public void testBee()
    {
        colony.add(harvy, 1, 3);
        colony.add(bee, 1, 3);
        bee.setMoveFlag(true);
        bee.sting();
        assertEquals(0, colony.getObjects(HarvesterAnt.class).size());
        colony.remove(bee);
        colony.remove(harvy);
        gameEntity.setTimerPeriod(40);
        gameEntity.resetTimer();
        colony.add(bee, 4, 4);
        bee.onTimer();
        assertEquals(40, gameEntity.getTimer());
        bee.sting();
        assertEquals(false, bee.getMoveFlag());
        bee.setMoveFlag(true);
        colony.add(harvy, 4, 4);
        bee.sting();
        assertEquals(true, bee.getMoveFlag());

        colony.remove(harvy);
        bee.movement();
        assertEquals(1, colony.getObjects(Bee.class).size());
        colony.add(harvy, 4, 4);
        colony.remove(bee);
        colony.add(bee, 4, 4);
        bee.movement();
        assertEquals(true, bee.getMoveFlag());
        assertEquals(40, gameEntity.getTimer());
        colony.remove(harvy);
        bee.setMoveFlag(false);
        bee.act();        
        assertEquals(false, bee.getMoveFlag());
        bee.setMoveFlag(true);
        bee.act();
        assertEquals(true, bee.getMoveFlag());
    }

    /**
     * tests on touch down method
     */    
    public void testOnTouchDown()
    {
        //set actor, good x&y, good food supply, no other actor occupying
        assertEquals(null, colony.getOneObjectAt(4, 3, Ants.class));
        colony.setSelectedActor(HarvesterAnt.class);
        colony.setFood(2);
        colony.onTouchDown(4, 3);
        assertEquals(1, colony.getObjectsAt(4, 3, Ants.class).size());

        //null actor, good x&y, good food, no other actor
        colony.setSelectedActor(null);
        colony.setFood(10);
        colony.onTouchDown(2, 2);
        assertEquals(null, colony.getOneObjectAt(2, 2, AntsAndBees.class));

        //set actor, bad x&y, good food, no other actor
        colony.setSelectedActor(FireAnt.class);
        colony.setFood(10);
        colony.onTouchDown(9, 6);
        assertEquals(0, colony.getObjects(FireAnt.class).size());

        //set actor, good x&y, bad food, no other actor
        colony.setFood(0);
        colony.setSelectedActor(ThrowerAnt.class);
        colony.onTouchDown(6, 3);
        assertEquals(null, colony.getOneObjectAt(6, 3, AntsAndBees.class));

        //set actor, good x&y, good food, other actor already occupying
        colony.setFood(2);
        colony.add(harvy, 5, 5);
        colony.setSelectedActor(HarvesterAnt.class);
        colony.onTouchDown(5, 5);
        assertEquals(1, colony.getObjectsAt(5, 5, HarvesterAnt.class).size());

        //set actor, good x, bad y, good food, no other actor
        colony.setFood(2);
        colony.setSelectedActor(HarvesterAnt.class);
        colony.onTouchDown(9, 4);
        assertEquals(0, colony.getObjectsAt(9, 4, HarvesterAnt.class).size());

        //set actor, bad x, good y, good food, no other actor
        colony.setFood(2);
        colony.setSelectedActor(HarvesterAnt.class);
        colony.onTouchDown(6, 6);
        assertEquals(0, colony.getObjectsAt(6, 6, HarvesterAnt.class).size());
    }

    /**
     * tests colony's act, win lose methods
     */
    public void testAct()
    {
        colony.add(bee, 1, 3);
        bee.move(1);
        assertEquals(queensChamber.getGridX(), bee.getGridX());
        hive.setBeeCounter(30);
        assertEquals(1, colony.getObjects(Bee.class).size());
    }

    /**
     * tests colony methods
     */
    public void testColony1()
    {
        colony.add(bee, 0, 3);
        colony.act();
        assertEquals(colony.isGameOver(), true);
        colony.remove(bee);

        hive.setBeeCounter(0);
        hive.setBees(0);
        colony.act();
        assertEquals(0, colony.getObjects(Bee.class).size());
        assertEquals(true, colony.isGameWon());        
    }

    /**
     * tests negated colony methods
     */
    public void testColony2()
    {
        colony.add(bee, 3, 3);
        hive.setBeeCounter(0);
        colony.act();
        assertEquals(colony.isGameWon(), false);
        colony.remove(bee);

        hive.setBeeCounter(0);
        hive.setBees(0);
        colony.act();
        assertEquals(true, colony.isGameWon());
    }

    /**
     * tests negated colony methods
     * 
     */
    public void testColony3()
    {
        colony.add(bee, 2, 2);
        hive.setBeeCounter(30);
        hive.setBees(0);
        colony.act();
        assertEquals(false, colony.isGameOver());
    }

    /**
     * tests if bee sting is dealing damage
     */
    public void testSting()
    {
        colony.add(bee, 4, 3);
        colony.add(wally, 3, 3);
        bee.move(1);
        bee.setMoveFlag(true);
        bee.sting();
        assertEquals(wally.getHealth(), 3);
    }

    /**
     * tests hungry ants vulnerableness after 
     * digesting first bee
     */
    public void testDigest()
    {
        colony.add(bee, 4, 4);
        colony.add(bee2, 5, 4);
        colony.add(hungry, 4, 4);
        hungry.act();
        bee2.move(1);
        bee2.setMoveFlag(true);
        bee2.sting();
        assertEquals(0, colony.getObjects(HungryAnt.class).size());
    }

    /**
     * tests when bees are all gone if ants win
     */
    public void testVictory()
    {
        hive.setBeeCounter(0);
        hive.setBees(0);
        colony.act();
        assertEquals(0, colony.getObjects(Bee.class).size());
        assertEquals(true, colony.isGameWon());
    }

    /**
     * test consumeFood
     */
    public void testConsumeFood()
    {
        colony.setFood(1);
        colony.consumeFood(2);
        assertEquals(1, colony.getFood());

        colony.setFood(2);
        colony.consumeFood(2);
        assertEquals(0, colony.getFood());

        colony.setFood(3);
        colony.consumeFood(2);
        assertEquals(1, colony.getFood());
    }

    /**
     * tests sting on all types of ants
     */
    public void testAllSting()
    {
        colony.add(bee, 3, 3);
        colony.add(harvy, 3, 3);
        harvy.setHealth(2);
        gameEntity.setTimerPeriod(0);
        bee.setMoveFlag(true);
        bee.sting();
        assertEquals(1, harvy.getHealth());

        colony.remove(harvy);
        colony.add(throwy, 3, 3);
        throwy.setHealth(2);
        gameEntity.setTimerPeriod(0);
        bee.setMoveFlag(true);
        bee.sting();
        assertEquals(1, throwy.getHealth());

        colony.remove(throwy);
        colony.add(firey, 3, 3);
        firey.setHealth(2);
        gameEntity.setTimerPeriod(0);
        bee.setMoveFlag(true);
        bee.sting();
        assertEquals(1, firey.getHealth());

        colony.remove(firey);
        colony.add(wally, 3, 3);
        gameEntity.setTimerPeriod(0);
        bee.setMoveFlag(true);
        bee.sting();
        assertEquals(3, wally.getHealth());

        colony.remove(wally);
        colony.add(hungry, 3, 3);
        hungry.setHealth(2);
        gameEntity.setTimerPeriod(0);
        bee.setMoveFlag(true);
        bee.sting();
        assertEquals(1, hungry.getHealth());

    }
}

