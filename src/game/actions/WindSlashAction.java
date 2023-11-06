package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * Class representing the Wind Slash action required by Yhorm's Great Machete weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class WindSlashAction extends WeaponAction {
    /**
     * Direction where the attack is directed to.
     */
    private String direction;
    /**
     * Target to be attacked by wind slash action.
     */
    private Actor target;

    /**
     * Constructor references the constructor in the abstract parent class WeaponAction and initialises the target and
     * direction attribute of the WindSlashAction instance.
     * @param weaponItem the weapon item that has capabilities
     */
    public WindSlashAction(WeaponItem weaponItem, Actor target, String direction) {
        super(weaponItem);
        this.target = target;
        this.direction = direction;
    }

    /**
     * Performs the action(active skill) from the weapon
     * @param actor the actor holding the weapon
     * @param map the location
     * @return the result of the attack performed
     */
    public String execute(Actor actor, GameMap map) {
        weapon.addCapability(Status.WIND_SLASH_EXECUTED);
        AttackAction attackAction = new AttackAction(target, direction);
        String result = attackAction.execute(actor, map);

        target.addCapability(Status.STUN);
        actor.addCapability(Status.AFTER_WIND_SLASH);
        weapon.removeCapability(Status.WIND_SLASH_EXECUTED);
        result += "\nWind Slash Attack Executed!!!";

        return result;
    }

    /**
     * Describe that actor stuns the target after executing the wind slash action.
     * @param actor the actor involved
     * @return a string describing the action that actor stuns target.
     */
    @Override
    public String menuDescription(Actor actor) {return actor + " stuns " + target;}
}
