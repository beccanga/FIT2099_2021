package game.item;

import edu.monash.fit2099.engine.*;
import game.actions.TradeCindersAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Class representing the item, Cinders Of A Lord.
 * @author FIT2099S221 MA_Lab2Team4
 * @see edu.monash.fit2099.engine.Item
 */
public class CindersOfALord extends Item {

    /**
     * The weapon that is associated with the cinder.
     */
    private GameWeaponItem cinderWeapon;

    /**
     * Constructor references the constructor of the abstract parent class Item and intializes the name, displayChar and
     * portable attribute for Cinders Of The Lord Item.
     */
    public CindersOfALord(String name, GameWeaponItem weapon) {
        super(name, '%', true);
        this.name = name;
        this.cinderWeapon = weapon;
        this.addCapability(Abilities.CAN_BE_TRADED);
    }

    /**
     * Describe the cinder of the lord.
     * @return string describing the cinder of lord.
     */
    public String toString(){
        return "Cinder of " + name;
    }

    /**
     * Check if Vendor is in the surrounding of the actor that holds the cinder. If yes, create and add the
     * TradeCindersAction instance into the allowableActions attribute of the CindersOfALord instance.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        allowableActions.clear();
        for (Exit exit: currentLocation.getExits()) {
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Abilities.IS_VENDOR)) {
                allowableActions.add(new TradeCindersAction(this, cinderWeapon));
            }
        }
    }

}
