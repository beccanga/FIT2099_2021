package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * Class representing the charge action required by the Storm Ruler weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see WeaponAction
 */
public class ChargeAction extends WeaponAction {
    /**
     * Class constant for the max charges of the weapon.
     */
    static final int MAX_CHARGE = 3;

    /**
     * Remaining charge value of the weapon.
     */
    private int charge;

    /**
     * Constructor
     * references the abstract parent class WeaponItem
     * instantiates the weaponItem and charge attribute of the ChargeAction instance.
     * @param weaponItem the weapon item that has capabilities
     */
    public ChargeAction(WeaponItem weaponItem) {
        super(weaponItem);
        charge = MAX_CHARGE;
    }

    /**
     * Performs the charge action
     * @param actor the actor needs to attack
     * @param map the location
     * @return the result/description of the weapon's charge
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = "";

        if (charge > 0) {
            // Check if it is first charge
            if (charge == MAX_CHARGE){
                weapon.addCapability(Status.IS_EXECUTED);
                weapon.addCapability(Status.CHARGING);
                actor.addCapability(Status.DISARMED);
            }
            result += actor + " is charging " + weapon + ". Charges remaining: " + charge;
            charge -= 1;
        }
        else {
            weapon.removeCapability(Status.CHARGING);
            weapon.addCapability(Status.FULLY_CHARGED);
            actor.removeCapability(Status.DISARMED);
            result += "The Storm Ruler is fully charged !!!";
        }
        return result;
    }

    /**
     * Descriptive string of the actor involved and the action
     * @param actor the actor using the active skill
     * @return a string describing the action performed by actor
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " charges Storm Ruler.";
    }
}
