package game.interfaces;

import edu.monash.fit2099.engine.*;

/**
 * Interface for TokenCollector. It contains methods that should be implemented in child classes to set and get the
 * location where the token should be spawned.
 * @author FIT2099S221 MA_Lab2Team4
 */
public interface TokenCollector {

    /**
     * Get TokenOfSoul spawn location
     * @return Location of TokenOfSoul
     */
    Location getTokenSpawnLocation();

    /**
     * Set TokenOfSoul spawn location
     * @param location New location of the TokenOfSoul
     */
    void setTokenSpawnLocation(Location location);
}
