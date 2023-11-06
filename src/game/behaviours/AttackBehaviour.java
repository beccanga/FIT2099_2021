package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.*;
import java.util.Random;

/**
 * Class that contains the attributes that lets the actor attack
 * @author FIT2099S221 MA_Lab2Team4
 * @see Behaviour
 */
public class AttackBehaviour implements Behaviour {

    /**
     * If the weapon held by the actor is a ranged weapon, the actor will perform an attack to the other actors that are
     * hostile to it and is within the attack range. Else, the actor can only perform attack to the other actors that
     * are within its adjacent squares.
     * @param actor the Actor acting.
     * @param map the GameMap containing the Actor.
     * @return the action to attack other actors, else returns null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Actions actions = new Actions();

        if(actor.hasCapability(Status.USING_RANGED_WEAPON) && actor.hasCapability(Status.ENGAGED)){
            actions = rangeAttack(actor);
        }
        else{
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor target = destination.getActor();
                    if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        actions.add(new AttackAction(target, exit.getName()));
                        actions.add(actor.getWeapon().getActiveSkill(target, exit.getName()));
                    }
                }
            }
        }
        Random random = new Random();
        if(actions.size() >= 1){
            return actions.get(random.nextInt(actions.size()));
        }
        return null;
    }

    /**
     * Return an Actions instance that contains all the attack actions that can be performed by the ranged weapon held
     * by actor.
     * @param actor the actor that has the range weapon and perform the range attack.
     * @return an Actions instance that contains all the attack actions that can be performed by the ranged weapon.
     */
    public Actions rangeAttack(Actor actor){
        Actions actions = new Actions();
        for(Item item: actor.getInventory()){
            if(item.hasCapability(Abilities.IS_RANGE_WEAPON)) {
                actions.add(item.getAllowableActions());
            }
        }
        return actions;
    }

    /**
     * Return the priority of the behaviour inside the behaviour list.
     * @return the priority of actions.
     */
    @Override
    public int getPriority() {
        return 1;
    }
}
