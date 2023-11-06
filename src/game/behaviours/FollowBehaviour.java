package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.interfaces.Behaviour;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Behaviour
 */
public class FollowBehaviour implements Behaviour {
	/**
	 * target actor from the Actor class
	 */
	private Actor target;

	/**
	 * Constructor
	 * instantiates the target actor
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
     * Return the priority of the this behaviour inside the behaviour list
     * @return the priority of actions
     */
	@Override
	public int getPriority() {
		return 2;
	}

	/**
	 * Follows the target actor
	 * @param actor the Actor acting
	 * @param map the GameMap containing the Actor
	 * @return the new location of the actor
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}
		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}