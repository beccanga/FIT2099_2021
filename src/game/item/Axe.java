package game.item;

/**
 * Abstract class representing the axe.
 * @author FIT2099S221 MA_Lab2Team4
 * @see GameWeaponItem,edu.monash.fit2099.engine.WeaponItem,edu.monash.fit2099.engine.Item
 */
public abstract class Axe extends GameWeaponItem{
    /**
     * Constructor references the constructor of the abstract parent class GameWeaponItem and instantiates name,
     * display character, damage, verb and hit rate attributes of the Axe weapon.
     * @param name        name of the Axe
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Axe(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }
}
