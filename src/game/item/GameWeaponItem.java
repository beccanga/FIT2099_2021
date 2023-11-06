package game.item;

import edu.monash.fit2099.engine.*;
import game.actions.SwapWeaponAction;

/**
 * Class for the game's weapon items.
 * @author FIT2099S221 MA_Lab2Team4
 * @see WeaponItem,Item
 */
public abstract class GameWeaponItem extends WeaponItem {

    /**
     * Souls for GameWeaponItem
     */
    private int souls;

    /**
     * Constructor.
     * References the constructor of the abstract parent class WeaponItem in the engine package
     * instantiates name, display character, damage, verb, hit rate and souls
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public GameWeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
        souls = 0;
    }

    /** Actor cannot drop the weapon.
     * @param actor an actor that will interact with this item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Pick up the item if the item is portable. Then, swap and replace the old weapon in current inventory with the new
     * weapon that is picked up.
     * @param actor an actor that will interact with this item
     * @return swaps the weapon if the item is portable, else null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (portable) {
            return new SwapWeaponAction(this);
        }
        return null;
    }

    /** Add an Action instance into the allowableActions attribute of the GameWeaponItem instance.
     * @param action actions that are allowed
     */
    public void addAction(Action action){
        this.allowableActions.add(action);
    }

    /**
     * Setter for souls attribute.
     * @param newSouls value of the items
     */
    public void setSouls(int newSouls) {
        souls = newSouls;
    }

    /**
     * Getter for souls attribute.
     * @return the amount of souls
     */
    public int getSouls() {
        return souls;
    }
}
