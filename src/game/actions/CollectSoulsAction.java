package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;
import game.item.TokenOfSoul;

/**
 * Class that represents the collect souls action.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class CollectSoulsAction extends Action {
    /**
     * Token of soul instance
     */
    private TokenOfSoul tokenInstance;

    /**
     * Constructor
     * instantiates the token instance
     * @param tokenInstance contains token
     */
    public CollectSoulsAction(TokenOfSoul tokenInstance) {
        this.tokenInstance = tokenInstance;
    }

    /**
     * Allow the actor to collect/transfer souls
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The amount of souls collected from the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int souls = tokenInstance.getSouls();
        this.tokenInstance.transferSouls(actor.asSoul());
        tokenInstance.addCapability(Status.TOKEN_TO_REMOVE);
        map.at(tokenInstance.getTokenLocation().x(), tokenInstance.getTokenLocation().y()).removeItem(tokenInstance);
        return String.format("Collected %d souls from TokenOfSouls.", souls);
    }

    /**
     * Describe the actor collecting the souls action.
     * @param actor The actor performing the action.
     * @return Description of actor collecting souls
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " collect souls from TokenOfSouls";
    }
}
