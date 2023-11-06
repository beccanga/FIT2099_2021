package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.manager.DyingTokenManager;
import game.actors.Player;
import game.enums.Abilities;
import game.interfaces.*;
import game.softReset.ResetManager;
import game.item.TokenOfSoul;

/**
 * Class that represents the dying behaviour
 * @author FIT2099S221 MA_Lab2Team4
 * @see Behaviour
 */
public class DyingBehaviour implements Behaviour {
    /**
     * Token Collector instance from the tokenCollectorInstance
     */
    private TokenCollector tokenCollectorInstance;
    /**
     * Actor spawn location instance from the ActorSpawnLocation interface
     */
    private ActorSpawnLocation actorSpawnLocation;
    /**
     * Player Dying Token Manager
     */
    private DyingTokenManager dyingTokenManager;

    /**
     * Constructor
     * instantiates token collector instance and actor spawn location
     * @param playerInstance instance of player for token collector instance and actor spawn location
     */
    public DyingBehaviour(Player playerInstance, DyingTokenManager dyingTokenManager) {
        this.tokenCollectorInstance = playerInstance;
        this.actorSpawnLocation = playerInstance;
        this.dyingTokenManager = dyingTokenManager;
    }

    /**
     * Actions for a soft reset when actor dies
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return the actor is resetted back to the latest spawning point
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location tokenSpawnLocation = tokenCollectorInstance.getTokenSpawnLocation();

        // checks if the actor is conscious
        if (!actor.isConscious()) {
            ResetManager resetManager = ResetManager.getInstance();
            resetManager.run(map);

            // Spawn TokenOfSoul
            TokenOfSoul prevToken = dyingTokenManager.getDyingToken();
            if (prevToken != null) {
                map.at(prevToken.getTokenLocation().x(), prevToken.getTokenLocation().y())
                        .removeItem(prevToken);
                dyingTokenManager.removeDyingToken();
            }

            TokenOfSoul tokenInstance = new TokenOfSoul(0);
            tokenInstance.addCapability(Abilities.DYING_TOKEN);
            dyingTokenManager.setDyingToken(tokenInstance);

            tokenInstance.setTokenLocation(map.at(tokenSpawnLocation.x(), tokenSpawnLocation.y()));
            map.at(tokenSpawnLocation.x(), tokenSpawnLocation.y()).addItem(tokenInstance);

            // Transfer the souls to the TokenOfSoul
            tokenInstance.resetSouls();
            if(actor.asSoul() != null && tokenInstance.asSoul() != null){
                actor.asSoul().transferSouls(tokenInstance);
            }

            return new MoveActorAction(actorSpawnLocation.getLatestSpawnPoint(), "back to the latest spawn point");
        }

        return null;
    }

    /**
     * Return the priority of this behaviour inside the behaviour list
     * @return the priority of actions
     */
    @Override
    public int getPriority() {
        return 0;
    }
}