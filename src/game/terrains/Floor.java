package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents the floor inside a building.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Ground
 */
public class Floor extends Ground {
	/**
	 * Constructor references the constructor of the abstract parent class Ground and initialise the displayChar
	 * attribute of the floor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Checks if the actor is able to enter the floor.
	 * @param actor the Actor who enters the floor
	 * @return the true if actor has the enter bonfire capability. Else, return false.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor.hasCapability(Abilities.ENTER_BONFIRE)){
			return true;
		}
		return false;
	}


}
