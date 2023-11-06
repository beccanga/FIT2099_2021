package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import static game.enums.Abilities.ENTER_VALLEY;

/**
 * Class representing the valley terrain.
 * The gorge or endless gap that is dangerous for the Player.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Ground
 */
public class Valley extends Ground {
	/**
	 * Constructor references the constructor in the abstract parent class Ground and initialises the displayChar of
	 * valley.
	 */
	public Valley() {
		super('+');
	}

	/**
	 * Apply very large damage to the actor if actor steps on valley/the current location
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		if (location.containsAnActor()) {
			location.getActor().hurt(Integer.MAX_VALUE);
		}
	}

	/**
	 * Checks if the actor is able to enter the location(valley).
	 * @param actor the Actor who wants to enter valley.
	 * @return true if the actor has the capability to enter valley. Else, return false.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		boolean canEnter = false;
		if (actor.hasCapability(ENTER_VALLEY)){
			canEnter = true;
		}
		return canEnter;
	}
}
