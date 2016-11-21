import sofia.battleship.*;
import static sofia.battleship.ShipType.*;
import sofia.battleship.ShipPlacementMove.*;
import sofia.battleship.Ship.*;
import sofia.micro.TestCase;
import sofia.util.Random;

//-------------------------------------------------------------------------
/**
 *  tests that all the methods in MyStrategy are 
 *  working properly
 *
 *  @author jackrg94
 *  @version 2015.03.05
 */
public class MyStrategyTest extends TestCase
{
    //~ Fields ................................................................

    // instance fields--replace the example below with your own
    private TestableGameState gameState;
    private MyStrategy strategy;

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new TestMyStrategy object.
     */
    public MyStrategyTest()
    {
        //nothing cons
    }

    //~ Methods ...............................................................
    /**
     * initializes fields and resets game
     * also calls canPlayDeviously, which is set to false
     */
    public void setUp()
    {
        gameState = new TestableGameState();
        strategy = new MyStrategy();
        gameState.getMyBoard().showShips();
        strategy.newGame();
        strategy.canPlayDeviously();
    }
    // ----------------------------------------------------------
    /**
     * tests that the ships are being placed correctly
     * and that there are a total of 5
     */
    public void testPlaceShips()
    {
        ShipPlacementMove placement = strategy.placeShips(gameState);
        gameState.getMyBoard().applyMove(placement);        
        assertEquals(placement.getShips().size(), 5);
    }

    /**
     * tests that shots are called properly on the 
     * correct location.
     */
    public void testCallShot()
    {
        CallShotMove callShot;
        int x = 5;
        int y = 8;
        callShot = new CallShotMove(x, y);
        gameState.getOpponentsBoard().applyMove(callShot);
        assertEquals(gameState.getOpponentsBoard().getLastAttackX(), x);
        assertEquals(gameState.getOpponentsBoard().getLastAttackY(), y);
    }

    /**
     * 
     */
    public void testCallNextShot()
    {        
        Random.setNextInts(6, 8, 6, 8);        
        CallShotMove shot = strategy.callNextShot(gameState);
        gameState.getOpponentsBoard().applyMove(shot);        
        assertEquals(gameState.getOpponentsBoard().getLastAttackX(), 6);
        assertEquals(gameState.getOpponentsBoard().getLastAttackY(), 8);
        shot = strategy.callNextShot(gameState);
        gameState.getOpponentsBoard().applyMove(shot);
    }
}
