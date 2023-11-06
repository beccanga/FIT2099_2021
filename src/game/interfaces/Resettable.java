package game.interfaces;

import edu.monash.fit2099.engine.GameMap;
import game.softReset.ResetManager;

/**
 * Interface that contains methods that should be implemented in child classes to reset abilities, attributes, and
 * items.
 * @author FIT2099S221 MA_Lab2Team4
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and items.
     * TODO: Use this method in a reset manager to run the soft-reset.
     */
    void resetInstance(GameMap map);

    /**
     * Allows any classes that use this interface to reset abilities, attributes, and items.
     * TODO: Use this method in a reset manager to rest.
     */
    // void restInstance(GameMap map);

    /**
     * A useful method to clean up the list of instances in the ResetManager class
     * @return the existence of the instance in the game.
     * for example, true to keep it permanent, or false if instance needs to be removed from the reset list.
     */
    boolean isExist();

    /**
     * a default interface method that register current instance to the Singleton manager.
     * TODO: Use this method at the constructor of `this` instance.
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
