package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * Class representing the wall.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Ground
 */
public class Wall extends Ground {
	/**
	 * Constructor
	 * references the constructor of the abstract parent class Ground
	 * initialise the displayChar attribute of the Wall.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Checks if the actor can enter the terrain(Wall).
	 * @param actor the Actor who wants to enter the Wall.
	 * @return false. No actor can enter Wall.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Check if the Wall can block the thrown objects.
	 * @return true. Wall can block thrown objects.
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
