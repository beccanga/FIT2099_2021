package game.actions;

import edu.monash.fit2099.engine.*;
import game.item.GameWeaponItem;


/**
 * Class representing action that trades cinder with corresponding enemy's weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class TradeCindersAction extends Action {

    /**
     * Cinder that can be traded.
     */
    private Item cinderItem;

    /**
     * Weapon that is associated with the cinder and can be traded with the cinder.
     */
    private GameWeaponItem weapon;

    /**
     * Constructor that references the constructor in the abstract parent class Action and initialises the item
     * weapon attribute of the TradeCindersAction instance.
     * @param cinderItem Item that will be traded with weapon.
     * @param weapon Weapon that can be traded with the item.
     */
    public TradeCindersAction(Item cinderItem, GameWeaponItem weapon){
        this.cinderItem = cinderItem;
        this.weapon = weapon;
    }

    /**
     * Remove the tradableItem from the inventory list of the actor and swap the old weapon held by actor with the
     * weapon item attribute.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string describing that the actor trades cinder with the weapon.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        SwapWeaponAction swapWeaponAction = new SwapWeaponAction(weapon);
        actor.removeItemFromInventory(this.cinderItem);
        String result = swapWeaponAction.execute(actor, map);
        return menuDescription(actor) + " with " + weapon + "\n" + result;
    }

    /**
     * Describe the trading cinderItem with weapon action.
     * @param actor The actor performing the action.
     * @return string describing the trading cinder with weapon action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades " + cinderItem;
    }
}
