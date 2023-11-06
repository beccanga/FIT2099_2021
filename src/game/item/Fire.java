package game.item;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.softReset.ResetManager;
import game.enums.Abilities;

/**
 * Class representing fire item.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Item
 */
public class Fire extends Item {
    /**
     * class constant representing burning damage of the Fire item
     */
    static final int BURNING_DAMAGE = 25;
    /**
     * class constant representing the max burning period
     */
    static final int MAX_BURNING_PERIOD = 3;
    /**
     * burning period of the fire item
     */
    private int burningPeriod;

    /**
     * Constructor
     * references the abstract parent class Item
     * and intialises the name, displayChar, portable and burning period attributes for Fire Item
     */
    public Fire() {
        super("Burning Area", 'V', false);
        burningPeriod = MAX_BURNING_PERIOD;
    }

    /**
     * Causes burning damage to the actor standing on the current location.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {

        if (burningPeriod > 0) {
            if (currentLocation.containsAnActor() && !currentLocation.getActor().hasCapability(Abilities.YHORMS_GREAT_MACHETE_HOLDER)) {
                Actor actor = currentLocation.getActor();
                actor.hurt(BURNING_DAMAGE);

                Display display = new Display();
                display.println(actor + " is burnt by fire and lost " + BURNING_DAMAGE + " HP.");

                AttackAction attackAction = new AttackAction(actor, "surroundings");
                attackAction.notConscious(actor, currentLocation.map());
            }
            burningPeriod -= 1;
        }
        else{
            currentLocation.removeItem(this);
        }
    }
}

