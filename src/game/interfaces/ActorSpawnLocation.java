package game.interfaces;

import edu.monash.fit2099.engine.*;

/**
 * Interface for Actor's spawned location
 * @author FIT2099S221 MA_Lab2Team4
 */
public interface ActorSpawnLocation {

    /**
     * Get Actor latest spawn location
     */
    Location getLatestSpawnPoint();

    /**
     * Set Actor latest spawn location
     * @param location Actor new spawn location
     */
    void setLatestSpawnPoint(Location location);
}
