import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  encompasses all ants and bees in the world
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class AntsAndBees extends GameEntity
{
    //~ Fields ................................................................
   /**
    * field used to keep track of health
    */
    private int health;

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new AntsAndBees object.
     */
    public AntsAndBees()
    {
        super();
    }

    //~ Methods .............................................................
    /**
     * @param n     the num to injure by
     */
    public void injure(int n)
    {
        health = health - n;
        if (this.getHealth() == 0)
        {            
            this.death();
        }
    }
    
    /**
     * returns the current health of an actor
     * @return int  current health
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * used by specific ants in constructors
     * to set their individual healths.
     * @param n     int for health setting
     */
    public void setHealth(int n)
    {
        health = n;
    }
    
    /**
     * removes the actor from world when health
     * reaches 0.
     */
    public void death()
    {
        this.getWorld().remove(this);
    }

}
