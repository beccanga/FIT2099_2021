package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class that increase the max HP of a player.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class IncreaseMaxHPAction extends Action {
    /**
     * class constant for hp price
     */
    static final int HP_PRICE = 200;
    /**
     * class constant to represent the HP increment per transaction.
     */
    static final int HP_INCREASE = 25;

    /**
     * Allows the actor to increase HP in return for souls
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return transaction description of the actor and attributes updates
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor.asSoul() == null){
            return "Transaction fails.";
        }
        String result = "";
        boolean isSubtracted = actor.asSoul().subtractSouls(HP_PRICE);

        if (isSubtracted){
            actor.increaseMaxHp(HP_INCREASE);
            result += "Transaction is successful. Buyer HP points have increased by " + HP_INCREASE;
        } else {
            result += "Transaction fails. Insufficient souls to buy HP.";
        }
        return result;
    }

    /**
     * Describe the actor buys certain amount of souls to increase his maximum hit points with certain amount.
     * @param actor The actor performing the action.
     * @return description of increase maximum HP action by trading souls action performed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys Max HP modifier (+"+ HP_INCREASE + "HP): " + HP_PRICE + " souls" ;
    }

}
