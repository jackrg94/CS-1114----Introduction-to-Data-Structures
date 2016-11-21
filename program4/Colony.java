import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  The world for a battlefield between the defenders (ants)
 *  and the attackers (bees). Bees win if they reach the 
 *  Queens Chamber. Ants win as soon as all bees are killed.
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class Colony extends HomeBase
{
    //~ Fields ................................................................
    private QueensChamber queensChamber;
    private Hive hive;
    /**
     * keeps track of colony's food supply.
     */
    static int food;
    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Colony object.
     */
    public Colony()
    {
        super();

        queensChamber = new QueensChamber();
        super.add(queensChamber, 0, 3);
        hive = new Hive();
        super.add(hive, 9, 3);
        food = 10;

        setActorChoices(HarvesterAnt.class, ThrowerAnt.class, 
            WallAnt.class, FireAnt.class, HungryAnt.class);

    }
    //~ Methods ...............................................................
    // ----------------------------------------------------------
    /**
     * Add an ant to the screen when a specific location is clicked.
     * This method is automatically called while the simulation is
     * running, whenever the user clicks on (or taps on) a location
     * in the colony (one that isn't covered by an actor already).
     * 
     * @param x The x-coordinate of the click
     * @param y The y-coordinate of the click
     */
    public void onTouchDown(int x, int y)
    {
        Ants ant1 = ((Ants)newActorOfSelectedType());
        if ((x >= 1 && x <= 8 && y >= 1 && y <= 5) && (ant1 != null) 
            && (getFood() - ant1.getFoodCost() >= 0)            
            && (this.getOneObjectAt(((float)x), ((float)y)) == null))
        {
            this.add(ant1, x, y);
            this.consumeFood(ant1.getFoodCost());
        }
    }

    /**
     * returns the queensChamber object for testing purposes.
     * @return QueensChamber queensChamber instantiation
     */
    public QueensChamber getQueensChamber()
    {
        return queensChamber;
    }

    /**
     * returns the beeHive object for testing purposes.
     * @return BeeHive    beeHive instantiation
     */
    public Hive getHive()
    {
        return hive;
    }

    /**
     * adds n food to the food supply
     * @param n     adds harvester's food
     */
    public void addFood(int n)
    {
        food = food + n;
    }

    /**
     * runs every turn, checking for end of game.
     */
    public void act()
    {
        super.act();
        if (!queensChamber.getIntersectingObjects(Bee.class).isEmpty())
        {
            lose();
        }
        if (hive.getBees() == 0 && hive.beeCounter == 0
            && this.getObjects(Bee.class).size() == 0)
        {
            win();
        }
    }

    /**
     * sets Food supply for testing purposes.
     * @param n     int to test
     * @return      food
     */
    public int setFood(int n)
    {
        food = n;
        return food;
    }

    /**
     * returns the food supply
     * @return      food supply
     */
    public int getFood()
    {
        return food;
    }

    /**
     * @param n     amount of food to consume
     * finds selected amount of food cost per ant
     * then removes that from the food supply when
     * ant is added
     */
    public void consumeFood(int n)
    {
        if (this.getFood() - n >= 0)
        {
            food = food - n;
        }
    }

}
