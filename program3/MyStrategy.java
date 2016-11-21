import sofia.battleship.*;
import static sofia.battleship.ShipType.*;
import sofia.util.Random;
import sofia.battleship.ShipPlacementMove.*;
import sofia.battleship.Ship.*;

//-------------------------------------------------------------------------
/**
 *  plans the strategy for a game of battleship.
 *  ships are placed randomly within the confines of the map
 *  and are in valid locations (not on top of each other)
 *  
 *  shots are then taken randomly that eventually fire at
 *  every location of the enemy's board without repeating.
 *
 *  @author jackrg94
 *  @version 2015.03.05
 */
public class MyStrategy implements BattleshipStrategy
{
    //~ Fields ................................................................


    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new MyStrategy object.
     */
    public MyStrategy()
    {
        //empty
    }

    //~ Methods ...............................................................
    /**
     * teaches the computer to cheat. i turned it off.
     * @return  whether or not the strategy implents cheating.
     */
    public boolean canPlayDeviously()
    {
        return false;
    }
    
    /**
     * returns name of strategy
     * @return  returns name of strategy
     */
    public String getName()
    {
        return "MyStrategy";
    }
    
    /**
     * resets the game
     */
    public void newGame()
    {
        //overrides BattleshipStrategy's new game method
    }
    
    /**
     * places all the ships in valid locations within the board
     * if move is invalid, repeats method
     * @param currentGameState      current state of game board
     * @return      returns the next move
     */
    public ShipPlacementMove placeShips(GameState currentGameState)
    {
        //battleship 4 units long
        //carrier 5 units long
        //destroyer 3 units long
        //patrol 2 units long
        //submarine 3 units long
        ShipPlacementMove move = new ShipPlacementMove(currentGameState);

        this.randomPlaceShip(CARRIER, move);
        this.randomPlaceShip(BATTLESHIP, move);
        this.randomPlaceShip(DESTROYER, move);
        this.randomPlaceShip(PATROL, move);
        this.randomPlaceShip(SUBMARINE, move);
        if (!move.isValid())
        {
            move = placeShips(currentGameState);
        }
        return move;
    }

    /**
     * trys a placement of a ship and outputs error
     * if throws exception
     * @param thisShip      stores the type of ship
     * @param theMove       initializes move
     */
    public void randomPlaceShip(ShipType thisShip, ShipPlacementMove theMove)
    {

        int coordinateX = Random.generator().nextInt(1, 10);
        int coordinateY = Random.generator().nextInt(1, 10);
        boolean horizontal = Random.generator().nextBoolean();
        try
        {
            theMove.placeShip(thisShip, coordinateX, coordinateY, horizontal);
        }
        catch (IllegalArgumentException except)
        {
            System.out.println("error");
        }
    }
    
    /**
     * chooses random coordinates, checks to make sure they havent been 
     * fired upon yet, then confirms the shot. if it has been fired upon,
     * it repeats the method.
     * @param currentGameState      current state of game board
     * @return      returns the next shot
     */
    
    public CallShotMove callNextShot(GameState currentGameState)
    {
        CallShotMove callNextShot;
        int coordinateX = Random.generator().nextInt(10);
        int coordinateY = Random.generator().nextInt(10);
        //may find useful
        if (currentGameState.getOpponentsBoard().
            canFireAt(coordinateX, coordinateY))
        {
            callNextShot = new CallShotMove(coordinateX, coordinateY);
        }
        else
        {
            callNextShot = callNextShot(currentGameState);
        }
        return callNextShot;
    }
}



