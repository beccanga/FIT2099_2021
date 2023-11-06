package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.item.GameWeaponItem;

/**
 * Class that allows actors perform the action to buy weapons.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class BuyWeaponsAction extends Action {
    /**
     * Weapon to be bought.
     */
    private GameWeaponItem weapon;

    /**
     * Constructor instantiates the weapon attribute of the BuyWeaponsAction class.
     * @param newWeapon new weapon to be bought.
     */
    public BuyWeaponsAction(GameWeaponItem newWeapon) {
        weapon = newWeapon;
    }

    /**
     * Performs the transaction of purchase through the SwapWeaponAction instance if the souls of the actor is higher
     * than the souls price of the weapon.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The transaction status
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int weaponSouls = weapon.getSouls();

        if (actor.asSoul() == null){
            return "Transaction failed.";
        }

        boolean isSubtracted = actor.asSoul().subtractSouls(weaponSouls);

        if (isSubtracted){
            SwapWeaponAction swapWeaponAction = new SwapWeaponAction(weapon);
            String result = swapWeaponAction.execute(actor, map);
            return "Transaction is successful." + result;
        } else{
            return "Transaction failed. Insufficient souls to buy new weapon";
        }
    }

    /**
     * Returns a string describing the purchase weapon action.
     * @param actor The actor performing the action.
     * @return String describing the purchase weapon action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + weapon  + " (" + weapon.getSouls() + " souls)";
    }
}
