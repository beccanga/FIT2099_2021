package game.item;

import edu.monash.fit2099.engine.*;
import game.actions.DrinkingAction;
import game.interfaces.Consumable;
import game.interfaces.Resettable;

/**
 * Class for the Estus Flask Item.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Item,Consumable,Resettable
 */
public class EstusFlask extends Item implements Consumable, Resettable {

    static final int MAX_CHARGES = 3;
    private int curr_charge;

    /**
     * Constructor references the constructor of the abstract parent class Item and initialises the name, displayChar,
     * portable and current charge attribute of the Estus Flask.
     */
    public EstusFlask() {
        super("Estus Flask", 'E', false);
        curr_charge = MAX_CHARGES;
        this.allowableActions.add(new DrinkingAction(this));
        registerInstance();
    }

    /**
     * Getter for the current charge.
     * @return the remaining number of charges for the estus flask.
     */
    public int getCurrCharge() {
        return this.curr_charge;
    }

    /**
     * Setter for the current charge.
     * @param charge contains the number of charges for the estus flask
     */
    public void setCurrCharge(int charge) {
        this.curr_charge = charge;
    }

    /**
     * method for refilling the Item
     * @param charge number of chargers the estus flask contains
     */
    public void refillCharge(int charge) {
        this.curr_charge = Math.max(charge, MAX_CHARGES);
    }

    /** Refill the Estus Flask during the reset.
     * @param map the map where the Estus Flask is at.
     */
    @Override
	public void resetInstance(GameMap map) {
        this.refillCharge(MAX_CHARGES);
    }

    /**
     * Checks if the item exists in the Resettables list attribute in ResetManager.
     * @return true
     */
	public boolean isExist() {
		return true;
	}

}
