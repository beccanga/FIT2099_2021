package game.actors;

import edu.monash.fit2099.engine.*;
import game.manager.DyingTokenManager;
import game.behaviours.DyingBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.ActorSpawnLocation;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.interfaces.TokenCollector;
import game.item.*;

import java.util.ArrayList;

/**
 * Class representing the Player.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Actor,Soul,Resettable,TokenCollector,ActorSpawnLocation
 */
public class Player extends Actor implements Soul, Resettable, TokenCollector, ActorSpawnLocation {
	/**
	 * class menu
	 */
	private final Menu menu = new Menu();
	/**
	 * souls for the player
	 */
	private int souls;
	/**
	 * location of the lastest spawn point
	 */
	private Location latestSpawnPoint;
	/**
	 * location of the token spawn
	 */
	private Location tokenSpawnLocation;
	/**
	 * constant class of initial hit points
	 */
	private static final int INITIAL_HIT_POINTS = 100;

	/**
	 * ArrayList storing the behaviours instance
	 */
	private ArrayList<Behaviour> behaviours = new ArrayList<>();

	/**
	 * Constructor
	 * references the constructor of the abstract parent class Actor in the engine package
	 * instantiates the name, displayChar, hitPoints, capabilities, souls, weapons, items,location, TokenOfSoul location, and registerInstance
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 */
	public Player(String name, char displayChar, int presetSouls, Location initialSpawnPoint,
		Location initialTokenSpawnLocation) {
		super(name, displayChar, INITIAL_HIT_POINTS);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.ENTER_VALLEY);
		this.addCapability(Abilities.ENTER_BONFIRE);
		this.addCapability(Abilities.IS_PLAYER);
		this.souls = presetSouls;
		this.addItemToInventory(new EstusFlask());
		this.addItemToInventory(new BroadSword());
		behaviours.add(new DyingBehaviour(this, new DyingTokenManager()));
		latestSpawnPoint = initialSpawnPoint;
		tokenSpawnLocation = initialTokenSpawnLocation;
		registerInstance();
		//assignment 3
		this.addCapability(Abilities.ENTER_FOG_DOOR);

	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting
	 *                   things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the actor status and console menu
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if (!isConscious()) {
			for (Behaviour behaviour : behaviours) {
				if (behaviour instanceof DyingBehaviour) {
					Action action = behaviour.getAction(this, map);
					if (action != null) {
						display.println(" ____  ____   ___   _____  _____   ______   _____  ________  ______    \n"
                    				  + "|_  _||_  _|.'   `.|_   _||_   _| |_   _ `.|_   _||_   __  ||_   _ `.  \n"
                    				  + "  \\ \\  / / /  .-.  \\ | |    | |     | | `. \\ | |    | |_ \\_|  | | `. \\ \n"
                    			  	  + "   \\ \\/ /  | |   | | | '    ' |     | |  | | | |    |  _| _   | |  | | \n"
                    			  	  + "   _|  |_  \\  `-'  /  \\ \\__/ /     _| |_.' /_| |_  _| |__/ | _| |_.' / \n"
                    				  + "  |______|  `.___.'    `.__.'     |______.'|_____||________||______.'  \n"
                    				  + "                                                                       \n");
						return action;
					}
				}
			}
		} else {
			setTokenSpawnLocation(map.locationOf(this));
		}

		// Print actor status
		System.out.printf("%s (%d/%d), holding %s, %d souls\n", this.name, this.hitPoints, this.maxHitPoints,
				this.getWeapon(), this.getSouls());

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Add points to the current Actor's hitpoints total.
	 * @param points number of hitpoints to add.
	 */
	@Override
	public void heal(int points) {
		if (this.hasCapability(Status.PERCENTAGE_HEAL)) {
			double percentage = points / 100.0;
			hitPoints += ((int) (percentage * maxHitPoints));
			hitPoints = Math.min(hitPoints, maxHitPoints);
		} else {
			System.out.println(points);
			super.heal(points);
		}
	}

	/**
	 * Transfer the current instance's souls to another Soul instance.
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(getSouls());
		subtractSouls(getSouls());
	}

	/**
	 * Check if there are added souls to the player
	 * @param souls number of souls to be incremented.
	 * @return boolean of whether the souls are added to the player
	 */
	@Override
	public boolean addSouls(int souls) {
		boolean isAdded = false;
		if (souls >= 0) {
			this.souls += souls;
			isAdded = true;
		}
		return isAdded;
	}

	/**
	 * Check if souls are subtracted from the player
	 * @param souls number souls to be deducted
	 * @return boolean of whether the souls are added to the player
	 */
	@Override
	public boolean subtractSouls(int souls) {
		boolean isSubtracted = false;
		if (souls >= 0 && this.souls - souls >= 0) {
			this.souls -= souls;
			isSubtracted = true;
		}
		return isSubtracted;
	}

	/**
	 * getter for souls
	 * @return souls
	 */
	@Override
	public int getSouls() {
		return this.souls;
	}

	/**
	 * sets the latest spawning point
	 * 
	 * @param newLocation the new location for the spawning point
	 */
	public void setLatestSpawnPoint(Location newLocation) {
		this.latestSpawnPoint = newLocation;
	}

	/**
	 * getter for the latest spawning point
	 */
	public Location getLatestSpawnPoint() {
		return this.latestSpawnPoint;
	}

	/**
	 * Reset instance to move actor to the latest spawn point
	 * @param map location on the map
	 */
	@Override
	public void resetInstance(GameMap map) {
		if (!isConscious()) {
			map.moveActor(this, getLatestSpawnPoint());
		}
		this.heal(Math.abs(getCurrHitPoint()) + getMaxHitPoint());
	}

	/**
	 * Checks if the Player exists
	 * @return boolean of whether actor exists
	 */
	public boolean isExist() {
		return true;
	}

	/**
	 * Sets the latest token spawn location
	 * @param newTokenSpawnLocation new token spawning location
	 */
	public void setTokenSpawnLocation(Location newTokenSpawnLocation) {
		this.tokenSpawnLocation = newTokenSpawnLocation;
	}

	/**
	 * Gets the latest token spawm location
	 * @return the token spawn location
	 */
	public Location getTokenSpawnLocation() {
		return this.tokenSpawnLocation;
	}

	/**
	 * Get the player's max hit point
	 * @return Player's max hit point
	 */
	public int getMaxHitPoint() {
		return this.maxHitPoints;
	}

	/**
	 * Get the player's current hit point
	 * @return Player's current hit point
	 */
	public int getCurrHitPoint() {
		return this.hitPoints;
	}
}
