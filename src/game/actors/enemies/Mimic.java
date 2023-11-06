package game.actors.enemies;

import edu.monash.fit2099.engine.*;
import game.actors.Chest;
import game.actors.enemies.Enemies;
import game.actors.enemies.LordOfCinder;
import game.enums.Abilities;
import game.interfaces.Resettable;

/**
 * Class representing Mimic enemy.
 * 
 * @author FIT2099S221 MA_Lab2Team4
 * @see Enemies,Actor
 */
public class Mimic extends Enemies implements Resettable {

    /**
     * Initial location where the Mimic is spawned from the Chest.
     */
    private Location initialLocation;

    /**
     * Class constant of souls for the Mimic.
     */
    static final int SOULS = 200;

    /**
     * Constructor references constructor from parent abstract Enemies class. It
     * initialises name, displayChar, hitPoints, initial location, souls and
     * capabilities attribute of the Mimic.
     * 
     * @param initialLocation initial location of enemy
     */
    public Mimic(Location initialLocation) {
        super("Mimic", 'M', 100, initialLocation);
        registerInstance();
        setSouls(SOULS);
        addCapability(Abilities.HAS_NO_WEAPON);
        addCapability(Abilities.IS_MIMIC);
        this.initialLocation = initialLocation;
    }

    /**
     * Mimic holds no weapons. The method initialises the damage and verb attribute
     * of the intrinsic weapon of the Mimic.
     * 
     * @return An IntrinsicWeapon instance containing the base damage and verb when
     *         attacking actor as its parameter.
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    /**
     * Allows any classes that use this interface to reset abilities, attributes,
     * and items. TODO: Use this method in a reset manager to run the soft-reset.
     *
     * @param map position in map
     */
    @Override
    public void resetInstance(GameMap map) {
        this.hurt(Integer.MAX_VALUE);
        map.removeActor(this);
        Chest chest = new Chest();
        if (!map.isAnActorAt(initialLocation)) {
            map.at(initialLocation.x(), initialLocation.y()).addActor(chest);
        }
    }
}
