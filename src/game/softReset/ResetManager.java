package game.softReset;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.GameMap;
import game.item.Bow;
import game.item.GameWeaponItem;

/**
 * A global Singleton manager that does soft-reset on the instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 * A3: Think about how will you improve this implementation in the future assessment.
 * What could be the drawbacks of this implementation?
 *
 * @author FIT2099S221 MA_Lab2Team4
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor which instantiates the resettableList.
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     * FIXME: it does nothing, you need to implement it :)
     */
    public void run(GameMap map){
        cleanUp();
        for (Resettable resettable: resettableList) {
            resettable.resetInstance(map);
        }
    }

    /**
     * Add the Resettable instance to the list
     * FIXME: it does nothing, you need to implement it :)
     * @param resettable the interface instance
     */
    public void appendResetInstance(Resettable resettable){
        resettableList.add(resettable);
    }

    /**
     * clean up instances (actor, item, or ground) that doesn't exist anymore in the map
     * FIXME: it does nothing, you need to implement it :)
     */
    private void cleanUp(){
        // TODO Clean up instance
        List<Resettable> toRemove = new ArrayList<>();
        for (Resettable resettable: resettableList) {
            if (!resettable.isExist()) {
                toRemove.add(resettable);
            }
        }
        resettableList.removeAll(toRemove);
    }
}
