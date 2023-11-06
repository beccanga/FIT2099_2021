package game.terrains;

import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents bare dirt.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Ground
 */
public class Dirt extends Ground {
	/**
	 * Constructor references the constructor of the abstract parent class Ground and initialise the displayChar
	 * attribute and CAN_BE_BURNT capabilities of Dirt ground.
	 */
	public Dirt() {
		super('.');
		addCapability(Abilities.CAN_BE_BURNT);
	}
}
