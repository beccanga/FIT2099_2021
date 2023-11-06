package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.item.Fire;

/**
 * Class representing the burning action.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class BurningAction extends Action {

    /**
     * Check if the ground in adjacent squares has the CAN_BE_BURNT capability. If yes, create and add Fire instance
     * onto the ground.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of burning action performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Check the surroundings
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            // If the surrounding is dirt, add Fire Item onto them.
            if (destination.getGround().hasCapability(Abilities.CAN_BE_BURNT)) {
                destination.addItem(new Fire());
            }
        }
        return menuDescription(actor);
    }

    /**
     * Describe the conditions when the burning action is executed.
     * @param actor The actor performing the action.
     * @return description of burning action performed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " burns the surroundings.";
    }
}
