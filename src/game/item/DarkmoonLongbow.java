package game.item;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import java.util.Random;

/**
 * Class representing the Darkmoon Longbow weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Bow,GameWeaponItem,WeaponItem,Item
 */
public class DarkmoonLongbow extends Bow {

    /**
     * Class constant for weapon damage.
     */
    static final int WEAPON_DAMAGE = 70;

    /**
     * Class constant for weapon hit rate.
     */
    static final int WEAPON_HIT_RATE = 80;

    /**
     * Class constant for the range of attack of the weapon.
     */
    static final int RANGE_OF_ATTACK = 3;

    /**
     * Constructor.
     * References the constructor of the abstract parent class WeaponItem in the engine package
     * instantiates name, display character, damage, verb, hit rate and souls.
     */
    public DarkmoonLongbow() {
        super("Darkmoon Longbow", 'd', WEAPON_DAMAGE, "shoot", WEAPON_HIT_RATE);
    }

    /**
     * Implement the critical hit passive skill of the weapon by doubling the return damage caused by the weapon.
     *
     * @return damage caused by the Darkmoon Longbow after considering the critical hit passive skill.
     */
    @Override
    public int damage() {
        Random rand = new Random();
        int finalDamage = damage;
        // Critical hit passive skills
        if (rand.nextInt(100) < 15) {
            finalDamage *= 2;
        }
        return finalDamage;
    }

    /**
     * Implement the ranged attack to the valid actors within the range of the actor carrying the item.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        actor.addCapability(Status.USING_RANGED_WEAPON);
        allowableActions.clear();
        attackActorWithinRange(currentLocation, actor, RANGE_OF_ATTACK);
    }
}