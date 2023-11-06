package game.item;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Abstract class representing the sword weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see GameWeaponItem,WeaponItem,Item
 */
public abstract class Sword extends GameWeaponItem{

    /**
     * Constructor references the constructor of the abstract parent class GameWeaponItem. It initialises name,
     * display character, damage, verb, and hit rate attribute of the sword weapon.
     * @param name        name of the sword weapon
     * @param displayChar character to use for display when sword weapon is on the ground
     * @param damage      amount of damage this sword weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Sword(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

}
