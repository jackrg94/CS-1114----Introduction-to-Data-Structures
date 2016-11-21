import sofia.micro.*;
import java.util.*;

//-------------------------------------------------------------------------
/**
 *  blows up bees in surrounding cells on death
 *
 *  @author jackrg94
 *  @version 2015.03.26
 */
public class FireAnt extends Ants
{
    //~ Fields ................................................................

    //~ Constructor ...........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new FireAnt object.
     */
    public FireAnt()
    {
        super();
        foodCost = 4;
        setHealth(1);
    }

    //~ Methods ...............................................................
    /**
     * gets surrounding bees in neighboring cells
     * deals 3 damage to each bee on FireAnt's own death
     */
    public void death()
    {
        List<Bee> blastZone = this.getNeighbors(1, true, Bee.class);
        for (int i = 0; i < blastZone.size(); i++)
        {
            blastZone.get(i).injure(3);
        }
        if (!this.getIntersectingObjects(Bee.class).isEmpty())
        {
            this.getWorld().remove(this.getWorld().getOneObjectAt(
                this.getGridX(), this.getGridY(), Bee.class));
        }
        this.getWorld().remove(this);

    }
}
