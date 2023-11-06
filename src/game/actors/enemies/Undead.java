package game.actors.enemies;


import edu.monash.fit2099.engine.*;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.Random;

/**
 * Class representing an undead minion.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Enemies,Actor
 */
public class Undead extends Enemies {
	// Will need to change this to a collection if Undeads gets additional Behaviours.
	static final int SOULS = 50;

	/**
	 * Constructor references the constructor from the abstract parent class Enemies and initialises the name,
	 * displayChar, hit points, register instance, behaviours and souls of the Undead.
	 */
	public Undead(Location initialLocation) {
		super("Undead", 'u', 50, initialLocation);
		registerInstance();
		WanderBehaviour wanderBehaviour = new WanderBehaviour();
		behaviours[wanderBehaviour.getPriority()] = wanderBehaviour;
		addCapability(Abilities.HAS_NO_WEAPON);
		setSouls(SOULS);
	}

	/**
	 * Undead holds no weapons. The method initialises the damage and verb attribute of the intrinsic weapon of the
	 * Undead.
	 * @return An IntrinsicWeapon instance containing the base damage and verb when attacking actor as its parameter.
	 */
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "punches");
	}

	/**
	 * Implement the requirement that if the Undead is not following the player and not under attack, it has 10% chance
	 * of being removed from the map.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (!(this.hasCapability(Status.FOLLOWING_PLAYER) || this.hasCapability(Status.UNDER_ATTACK))) {
			Random rand = new Random();
			if (rand.nextInt(100) < 10) {
				map.removeActor(this);
			}
		}

		if (map.contains(this)) {
			return super.playTurn(actions, lastAction, map, display);
		} else {
			return new DoNothingAction();
		}
	}

	/**
	 * Remove the Undead from the current map after reset.
	 * @param map the current map where the Undead is in.
	 */
	@Override
	public void resetInstance(GameMap map) {
		map.removeActor(this);
	}
}
