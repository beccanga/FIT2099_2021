package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.BurningAction;
import game.item.Fire;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;

/**
 * Class representing the Ember Form behaviour
 * @author FIT2099S221 MA_Lab2Team4
 * @see Behaviour
 */
public class EmberFormBehaviour implements Behaviour {

    /**
     * Return the priority of this behaviour inside the behaviour list
     * @return the priority of actions
     */
    @Override
    public int getPriority() {
        return 0;
    }

    /**
     * performs the actions
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the ember form behaviour
     */
    public Action getAction(Actor actor, GameMap map) {
        if(!actor.hasCapability(Status.EMBER_FORM_EXECUTED)){
            actor.addCapability(Status.ON_RAGE);
            actor.addCapability(Status.EMBER_FORM_EXECUTED);
            return new BurningAction();
        }
        return null;
    }
}
