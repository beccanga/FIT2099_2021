package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.terrains.Bonfire;

/**
 * Class representing the Bonfire teleport action.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class BonfireTeleportAction extends Action {

    /**
     * target bonfire instance.
     */
    private Bonfire targetBonfire;

    /**
     * Constructor that initialises the Bonfire instance.
     * @param bonfire target bonfire
     */
    public BonfireTeleportAction(Bonfire bonfire) {
        this.targetBonfire = bonfire;
    }

    /**
     * Move the actor to the target Bonfire instance.
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description that the actor is moved to the target Bonfire.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, targetBonfire.getBonfireLocation());
        return actor + "teleported to " + targetBonfire.toString();
    }

    /**
     * Describe the condition that the actor is moved to the target Bonfire.
     * @param actor The actor performing the action.
     * @return Returns a description that the actor is moved to the target Bonfire.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves to " + targetBonfire.getBonfireName() + "'s Bonfire";
    }
}
