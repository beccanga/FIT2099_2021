package game.actions;

import edu.monash.fit2099.engine.*;
import game.terrains.Bonfire;
import game.softReset.ResetManager;
import game.enums.Abilities;
import game.interfaces.ActorSpawnLocation;

/**
 * Class representing the rest action.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class RestAction extends Action {
    /**
     * actor instance from the ActorSpawnLocation interface
     */
    private ActorSpawnLocation actorInstance;

    private Bonfire targetBonfire;

    /**
     * Constructor instantiates actor's spawn location and targetBonfire instance attribute of the RestAction.
     * @param actorInstance Actor Object
     */
    public RestAction(ActorSpawnLocation actorInstance, Bonfire targetBonfire) {
        this.actorInstance = actorInstance;
        this.targetBonfire = targetBonfire;
    }

    /**
     * Performs the rest actions by invoking the run method on the ResetManager instance.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The resting actions
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map);

        if (actor.hasCapability(Abilities.IS_PLAYER)) {
            actorInstance.setLatestSpawnPoint(targetBonfire.getBonfireLocation());
        }
        return "Player rested";
    }

    /**
     * descriptive string of the rest action done by actor in Bonfire.
     * @param actor The actor performing the action.
     * @return a descriptive string of the actor performing the rest action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + targetBonfire.getBonfireName() + "'s Bonfire";
    }
}
