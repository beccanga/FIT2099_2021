package game.actors.enemies;

import edu.monash.fit2099.engine.*;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.item.BroadSword;
import game.item.GiantAxe;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for the Skeleton enemy
 * @author FIT2099S221 MA_Lab2Team4
 * @see Enemies,Actor
 */

public class Skeleton extends Enemies {

    /**
     * Class constant for Skeleton souls.
     */
    static final int SOULS = 250;

    /**
     * Constructor references the constructor of the abstract parent class Enemies and instantiates the name,
     * displayChar, hitPoints, initial location, souls, behaviours, capability a and weapon of the Skeleton.
     * @param initialLocation The location where the Skeleton is allocated initially in the map.
     */
    public Skeleton(Location initialLocation) {
        super("Skeleton", 'S', 100, initialLocation);
        registerInstance();
        setSouls(SOULS);
        selectWeapon();
        WanderBehaviour wanderBehaviour = new WanderBehaviour();
        behaviours[wanderBehaviour.getPriority()] = wanderBehaviour;
        this.addCapability(Abilities.CAN_RESURRECT);
    }

    /**
     * Add a random weapon into the inventory list attribute of the Skeleton.
     * Skeleton can either hold BroadSword or Giant Axe
     */
    public void selectWeapon(){
        ArrayList<Item> weaponAL = new ArrayList<Item>();
        weaponAL.add(new BroadSword());
        weaponAL.add(new GiantAxe());

        Random rand = new Random();
        int index = rand.nextInt(weaponAL.size());
        this.addItemToInventory(weaponAL.get(index));
    }

    /** Heal the Skeleton with maximum hit points after reset.
     * @param map The current map where the Skeleton is in.
     */
    @Override
    public void resetInstance(GameMap map) {
        this.heal(Math.abs(getCurrHitPoint()) + getMaxHitPoint());
        super.restInstance(map);
    }

    /**
	 * Heal the Skeleton with the input points by adding the points into the current skeleton's hitPoint attribute.
	 * If skeleton has Status.PERCENTAGE_HEAL capability, increase the hitPoints of the skeleton by percentage. Else,
     * increase the hitPoints of the skeleton by absolute amount.
	 * @param points number of hit points to be added into the skeleton hitPoints attribute.
	 */
    @Override
    public void heal(int points) {
        if (this.hasCapability(Status.PERCENTAGE_HEAL)) {
            if (hitPoints < 0) {
                hitPoints = 0;
            }
			double percentage = points / 100.0;
			hitPoints += ((int) (percentage * maxHitPoints));
			hitPoints = Math.min(hitPoints, maxHitPoints);
		} else {
			super.heal(points);
		}
    }
}



