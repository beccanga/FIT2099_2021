package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;
import game.interfaces.Consumable;

/**
 * Class that represents the drinking action.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class DrinkingAction extends Action {
    /**
     * class constant for max charges of the item
     */
    static final int MAX_CHARGES = 3;
    /**
     * class constant for the hp percentage per charge
     */
    static final int HP_PERCENTAGE_PER_CHARGE = 40;
    /**
     * current estus flask from the consumable interface
     */
    private Consumable currEstusFlask;

    /**
     * Constructor instantiates the current estus flask instance.
     * @param currEstusFlask contains the current Estus flask
     */
    public DrinkingAction(Consumable currEstusFlask) {
        this.currEstusFlask = currEstusFlask;
    }

    /**
     * Performs the drinking action by reducing the charges of Estus Flask and healing the player with appropriate
     * amount of hit points until the remaining charges of the Estus Flask is 0.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The action and amount of hit points added to the actor.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (currEstusFlask.getCurrCharge() > 0) {
            currEstusFlask.setCurrCharge(currEstusFlask.getCurrCharge() - 1);
            actor.addCapability(Status.PERCENTAGE_HEAL);
            actor.heal(HP_PERCENTAGE_PER_CHARGE);
            actor.removeCapability(Status.PERCENTAGE_HEAL);

            result = actor + " drinks Estus Flask and heals " + HP_PERCENTAGE_PER_CHARGE
                    + "% of his maximum hit points.";
        } else {
            result = "The Estus Flask is empty and has no more charges";
        }
        return result;
    }

    /**
     * Display the description of the selection for drinking action in the console.
     * @param actor The actor performing the action.
     * @return The description of the actor and the amount of chargers left in the Estus Flask (max = 3).
     */
    @Override
    public String menuDescription(Actor actor) {
        // display selection in the Estus Flask
        return actor + " drinks Estus Flask " + "(" + currEstusFlask.getCurrCharge() + "/" + MAX_CHARGES + ")";
    }

}