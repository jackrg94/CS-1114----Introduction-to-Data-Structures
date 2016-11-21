import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  Leaf is thrown by ThrowerAnt. deals one damage
 *  to bees.
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class Leaf extends Weapon
{
    //~ Fields ................................................................

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Leaf object.
     */
    public Leaf()
    {
        super();
    }

    //~ Methods ...............................................................
    /**
     * checks boundaries and any interaction with bees
     * before dealing damage and then removing self.
     */
    public void movement()
    {
        if (this.getIntersectingObjects(Bee.class).isEmpty())
        {
            if (this.getGridX() < 9)
            {
                this.move(.025);
            }
            else
            {
                this.getWorld().remove(this);
            }
        }
        else
        {
            ((Bee)(this.getIntersectingObjects(Bee.class).get(0))).injure(1);
            this.getWorld().remove(this);
        }

    }

    /**
     * continously runs the movement method so it
     * moves across world.
     */
    public void act()
    {
        this.movement();
    }

}
