package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Class for the spin attack action required by the Giant Axe weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see WeaponAction
 */
public class SpinAttackAction extends WeaponAction {
    /**
     * Constructor references the constructor in the abstract parent class weaponItem and instantiates the weapon item.
     * @param weaponItem represents the weapon item that can use the spin attack action.
     */
    public SpinAttackAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Performs the spin attack action to the other actors at adjacent squares of the actor.
     * @param actor the actor who performs the spin attack action.
     * @param map the map
     * @return result of the action performed
     */
    public String execute(Actor actor, GameMap map) {
        weapon.addCapability(Status.SPIN_ATTACK_EXECUTED);
        String result = "";
        String prefix = "";
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor() && !destination.getActor().hasCapability(Abilities.CANNOT_BE_ATTACKED)){
                Actor target = destination.getActor();
                if(actor.hasCapability(Abilities.IS_PLAYER) || (actor.hasCapability(Abilities.IS_ENEMY) && !target.hasCapability(Abilities.IS_ENEMY))){
                    AttackAction attackAction = new AttackAction(target, exit.getName());
                    result += prefix + attackAction.execute(actor, map);
                    prefix = System.lineSeparator();
                }
            }
        }

        if (result == ""){
            result = "There are no enemies around!";
        }
        weapon.removeCapability(Status.SPIN_ATTACK_EXECUTED);
        return result;
    }

    /**
     * Describes actor performs the spin attack action and attacks the surrounding enemies.
     * @param actor the actor who performs the spin attack action.
     * @return a string describing the spin attack action(active skill) performed
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks the surrounding enemies using Giant Axe! (Spin Attack Action)";
    }
}
