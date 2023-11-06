package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.interfaces.Behaviour;

/**
 * Class representing the Wander behaviour for the actor
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action,Behaviour
 */
public class WanderBehaviour extends Action implements Behaviour {
	/**
	 * random generator
	 */
	private final Random random = new Random();

	/**
     * Return the priority of this behaviour inside the behaviour list
     * @return the priority of actions
     */
	@Override
	public int getPriority() {
		return 5;
	}

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}
	}

	/**
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return the menuDescription method
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 *
	 * @param actor The actor performing the action.
	 * @return keywords of behaviour "Raagrh..."
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
