package game.item;

import java.util.Random;

/**
 * Class that represents the weapon BroadSword.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Sword,GameWeaponItem,edu.monash.fit2099.engine.WeaponItem,edu.monash.fit2099.engine.Item
 */
public class BroadSword extends Sword {

    /**
     * Class constant for the weapon damage.
     */
    static final int WEAPON_DAMAGE = 30;

    /**
     * Class constant for the weapon hit rate.
     */
    static final int WEAPON_HIT_RATE = 80;

    /**
     * Class constant for the souls price of the weapon.
     */
    static final int SOULS_PRICE = 500;

    /**
     * Constructor references the constructor from the parent class Sword and instantiates name, display character,
     * damage, verb, hit rate and souls of the BroadSword.
     */
    public BroadSword() {
        super("Broadsword", 'b', WEAPON_DAMAGE, "slashes", WEAPON_HIT_RATE);
        setSouls(SOULS_PRICE);
    }

    /**
     * Implement the critical strike passive skill of the BroadSword to double the damage caused by this weapon.
     * @return The final damage that will be caused by the broadsword.
     */
    public int damage() {
        int finalDamage = damage;
        Random rand = new Random();
        if (rand.nextInt(100) < 20) {
            finalDamage = damage * 2;
        }
        return finalDamage;
    }

}
