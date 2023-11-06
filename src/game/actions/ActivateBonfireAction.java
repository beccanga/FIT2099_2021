package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.enemies.Enemies;
import game.actors.enemies.LordOfCinder;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.ActorSpawnLocation;
import game.terrains.Bonfire;

/**
 * Class representing the activate bonfire action.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class ActivateBonfireAction extends Action {
    /**
     * actor instance of actor spawn location
     */
    private ActorSpawnLocation actorInstance;
    /**
     * target Bonfire instance
     */
    private Bonfire targetBonfire;

    /**
     * Constructor initialises the actorInstance and bonfire of the ActivateBonfireAction object.
     * @param actorInstance instance of an actor
     * @param bonfire bonfire (firelink shrine)
     */
    public ActivateBonfireAction(ActorSpawnLocation actorInstance, Bonfire bonfire) {
        this.actorInstance = actorInstance;
        this.targetBonfire = bonfire;
    }

    /**
     * Check if the input actor is a Player. Is yes, activates the current Bonfire instance where the Player is on.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Abilities.IS_PLAYER)) {
            actorInstance.setLatestSpawnPoint(targetBonfire.getBonfireLocation());
        }
        this.targetBonfire.addCapability(Status.ACTIVATED_BONFIRE);
        return "Bonfire Lit";
    }

    /**
     * Returns a string describing the activating Bonfire.
     * @param actor The actor performing the action.
     * @return string describing the activating Bonfire.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " lights the Bonfire";
    }
}
