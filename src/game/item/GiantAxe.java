package game.item;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.SpinAttackAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Class representing the Giant Axe weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Axe,GameWeaponItem,WeaponItem,Item
 */
public class GiantAxe extends Axe {
    /**
     * class constant for the weapon damage
     */
    static final int WEAPON_DAMAGE = 50;
    /**
     * class constant for the weapon hit rate
     */
    static final int WEAPON_HIT_RATE = 80;
    /**
     * class constant for the souls price
     */
    static final int SOULS_PRICE = 1000;

    /**
     * Constructor.
     * references the constructor from the parent class GameWeaponItem
     * initialises the name, display character, damage, verb, hit rate, souls and capabilities of the GiantAxe.
     */
    public GiantAxe() {
        super("Giant Axe", 'g', WEAPON_DAMAGE, "chops", WEAPON_HIT_RATE);
        setSouls(SOULS_PRICE);
        addAction(new SpinAttackAction(this));
    }

    /**
     * Gets the active skill of this weapon, which is SpinAttackAction if the target is hostile to enemy.
     * @param target the actor to be attacked by the weapon.
     * @param direction the direction of target, e.g. "north"
     * @return the active skill, SpinAttackAction instance.
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if(target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            return new SpinAttackAction(this);
        }
        return null;
    }

    /**
     * Reduce the final damage caused by the GiantAxe by half if it executes the SpinAttackAction. Else, return normal
     * damage.
     * @return the final damage that ca be caused by the GiantAxe after checking if the active skill, SpinAttackAction
     * is executed.
     */
    @Override
    public int damage() {
        int finalDamage = damage;
        if (this.hasCapability(Status.SPIN_ATTACK_EXECUTED)){
            finalDamage /= 2;
        }
        return finalDamage;
    }
}
