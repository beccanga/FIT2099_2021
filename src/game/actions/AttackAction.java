package game.actions;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.actors.enemies.Enemies;
import game.actors.enemies.LordOfCinder;
import game.enums.Abilities;
import game.enums.Status;
import game.item.TokenOfSoul;

/**
 * Special Action for attacking other Actors.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked.
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator.
	 */
	protected Random rand = new Random();

	/**
	 * Constructor instantiates the actor target and the direction attribute of this action instance.
	 * 
	 * @param target    the Actor to be attacked
	 * @param direction the direction for Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Performs the attack action based on the damage and hit rate of the weapon used by the actor. This method takes
	 * into account if the weapon used is a range weapon or normal weapon.
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return result of string type to execute the attack action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		int damage = weapon.damage();

		if(target.hasCapability(Status.RANGE) && rangedAttackBlocked(actor, map)){
			target.removeCapability(Status.RANGE);
			return "The range attack from " + actor + "is blocked!";
		}

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		String result = "";
		result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		if (!target.isConscious()) {
			result = notConscious(actor, map);
		}

		return result;
	}

	/**
	 * Method notConscious to perform the checking and actions of the current actor is alive and what happens if the actor is not
	 * alive and returns the status to notify the death of actor.
	 * @param actor the actor
	 * @param map the map of the actor
	 * @return String showing status of the actor.
	 */
	public String notConscious(Actor actor, GameMap map) {
		String result = "";
		Actions dropActions = new Actions();

		if (target.hasCapability(Abilities.CAN_RESURRECT) && !target.hasCapability(Status.IS_RESURRECTED)) {
			Random rand = new Random();
			if (rand.nextInt(100) < 50) {
				target.addCapability(Status.PERCENTAGE_HEAL);
				target.heal(100);
				target.removeCapability(Status.PERCENTAGE_HEAL);
				target.addCapability(Status.IS_RESURRECTED);
				return "Skeleton Resurrected!";
			}
		}

		// drop all items
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction(actor));
		for (Action drop : dropActions)
			drop.execute(target, map);

		if (!target.hasCapability(Abilities.IS_PLAYER)) {
			if (actor.asSoul() != null && target.asSoul() != null) {
				target.asSoul().transferSouls(actor.asSoul());
			}
		}

		Location targetLocation = map.locationOf(target);

		if (target.hasCapability(Abilities.IS_PLAYER)) {
			result = "";
		} else {
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}

		if (target.hasCapability(Abilities.IS_MIMIC)) {
			int numOfToken = rand.nextInt(3) + 1;

			for (int i = 0; i < numOfToken; i++) {
				TokenOfSoul tokenOfSoul = new TokenOfSoul(100);
				// Didn't register token to DyingTokenManager, need to revise.
				tokenOfSoul.setTokenLocation(targetLocation);
				targetLocation.addItem(tokenOfSoul);
			}
			return "Dropped " + numOfToken + " Token Of Souls";
		}

		if (target.hasCapability(Abilities.IS_LORD_OF_CINDER)) {
			result = " _____       ___   _______     ______       ___   ________  \n"
					+ "|_   _|    .'   `.|_   __ \\   |_   _ `.   .'   `.|_   __  | \n"
					+ "  | |     /  .-.  \\ | |__) |    | | `. \\ /  .-.  \\ | |_ \\_| \n"
					+ "  | |   _ | |   | | |  __ /     | |  | | | |   | | |  _|    \n"
					+ " _| |__/ |\\  `-'  /_| |  \\ \\_  _| |_.' / \\  `-'  /_| |_     \n"
					+ "|________| `.___.'|____| |___||______.'   `.___.'|_____|    \n"
					+ "                                                            \n"
					+ "   ______  _____  ____  _____  ______   ________  _______     \n"
					+ " .' ___  ||_   _||_   \\|_   _||_   _ `.|_   __  ||_   __ \\    \n"
					+ "/ .'   \\_|  | |    |   \\ | |    | | `. \\ | |_ \\_|  | |__) |   \n"
					+ "| |         | |    | |\\ \\| |    | |  | | |  _| _   |  __ /    \n"
					+ "\\ `.___.'\\ _| |_  _| |_\\   |_  _| |_.' /_| |__/ | _| |  \\ \\_  \n"
					+ " `.____ .'|_____||_____|\\____||______.'|________||____| |___| \n"
					+ "                                                              \n"
					+ " ________    _       _____     _____     ________  ____  _____  \n"
					+ "|_   __  |  / \\     |_   _|   |_   _|   |_   __  ||_   \\|_   _| \n"
					+ "  | |_ \\_| / _ \\      | |       | |       | |_ \\_|  |   \\ | |   \n"
					+ "  |  _|   / ___ \\     | |   _   | |   _   |  _| _   | |\\ \\| |   \n"
					+ " _| |_  _/ /   \\ \\_  _| |__/ | _| |__/ | _| |__/ | _| |_\\   |_  \n"
					+ "|_____||____| |____||________||________||________||_____|\\____| \n"
					+ "                                                                \n";
		}
		return result;
	}

	/**
	 * Check if the range attack from the actor who is using the range weapon is blocked by Ground instances that have
	 * the block thrown object ability, such as Wall.
	 * @param actor The actor that performs the attack.
	 * @param map The map where the current actor is on.
	 * @return boolean value of the attack
	 */
	public Boolean rangedAttackBlocked(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		NumberRange xs, ys;
		xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
		ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

		for (int x : xs) {
			if (x < 1 || x >= map.getXRange().max()) {
				continue;
			}
			for (int y : ys) {
				if (y < 1 || y > map.getXRange().max()) {
					continue;
				}
				if (map.at(x, y).getGround().blocksThrownObjects()){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Describe the attack action performed by actor to the target at a specific direction.
	 * @param actor The actor performing the action.
	 * @return the description of the attack action caused by actor to the target at a specific direction.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
