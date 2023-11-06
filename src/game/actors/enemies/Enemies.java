package game.actors.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Abstract class represents Enemies in the game.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Actor,Soul,Resettable
 */
abstract public class Enemies extends Actor implements Soul, Resettable{

    /**
     * Souls for the enemies
     */
    private int souls;

    /**
     * Location where the enemy is spawned or allocated initially in the map.
     */
    private Location initialLocation;

    /**
     * Array of behaviours that will contain the behaviours that can be performed by the enemies.
     */
    Behaviour[] behaviours = new Behaviour[10];

    /**
     * Constructor references the constructor from the abstract parent class Actor and initialises the name,
     * display char, hit points and initial location attribute of the Enemy.
     * @param name        the name of the enemy.
     * @param displayChar the character that will represent the enemy in the display.
     * @param hitPoints   the enemy's current hit points.
     */
    public Enemies(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints);
        this.initialLocation = initialLocation;
        AttackBehaviour attackBehaviour = new AttackBehaviour();
        behaviours[attackBehaviour.getPriority()] = attackBehaviour;
        addCapability(Abilities.IS_ENEMY);
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor. The method implements the
     * requirements that if the otherActor is hostile to enemy and otherActor is not disarmed, the otherActor can attack
     * the current Actor using normal attack or active action of the weapon held by the otherActor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap the actor is on.
     * @return A collection of Actions that the otherActor can do to the current Actor.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);
        // Enemies can attack actors that are hostile to them, but enemies will not attack each other
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if(!otherActor.hasCapability(Status.DISARMED)) {
                actions.add(new AttackAction(this, direction));
                actions.add(otherActor.getWeapon().getActiveSkill(this, direction));
            }
        }
        return actions;
    }

    /**
     * Add the following behaviour into the behaviours list of the current enemy if actor who is hostile to them is
     * within its adjacent squares. Next, loop through the behaviour list and return an action to perform on the current
     * turn.
     * @param actions    collection of possible Actions for this enemy.
     * @param lastAction The Action this enemy took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the enemy
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed.
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        if (!this.hasCapability(Status.FOLLOWING_PLAYER)) {
            for (Exit exit: map.locationOf(this).getExits()) {
                if (exit.getDestination().getActor() != null && exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {

                    if(this.hasCapability(Status.USING_RANGED_WEAPON)){
                        this.addCapability(Status.ENGAGED);
                    }
                    else{
                        display.println(name + " is following Player");
                        FollowBehaviour followBehaviour = new FollowBehaviour(exit.getDestination().getActor());
                        behaviours[followBehaviour.getPriority()] = followBehaviour;
                        addCapability(Status.FOLLOWING_PLAYER);
                    }
                }
            }
        }

        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            if (Behaviour != null) {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * setter for the souls attribute
     * @param souls souls of the enemies
     */
    public void setSouls(int souls){
        this.souls = souls;
    }

    /**
     * Increase the souls attribute of the enemy by the valid number of input souls.
     * @param souls number of souls to be incremented.
     * @return true if the souls are added successfully. Else, return false.
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
     * Subtract the souls attribute of the enemy by the valid number of input souls.
     * @param souls number souls to be deducted
     * @return boolean of whether the souls is subtracted
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
     * Getter for souls
     * @return souls
     */
    public int getSouls() {
        return this.souls;
    }

    /** Respawn the enemy in its initial location and remove the following and attack behaviour of the enemy.
     * @param map The current mao where the enemy is allocated.
     */
    public void restInstance(GameMap map) {
        map.moveActor(this, this.initialLocation);

        for (Behaviour behaviour: behaviours) {
            if (behaviour instanceof FollowBehaviour) {
                behaviours[behaviour.getPriority()] = null;
            }
        }
        
        this.removeCapability(Status.FOLLOWING_PLAYER);
        this.removeCapability(Status.UNDER_ATTACK);
	}

    /**
     * Check if the enemy is still conscious to determine if the enemy should be removed from the resettable list.
     * @return true if the enemy is still conscious. Else, return false.
     */
    public boolean isExist() {
        if (!this.isConscious()) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Transfer souls from the current enemy to the input soulObject.
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(this.souls);
        this.subtractSouls(this.souls);
    }

    /**
     * Describe the conditions and attributes such as name, hitPoints, maxHitPoints and weapon that is currently held by
     * the enemy.
     * @return string describing the conditions and attributes of the enemies.
     */
    public String toString(){
        String result = "";
        if (this.hasCapability(Abilities.HAS_NO_WEAPON)){
            result = String.format("%s(%d/%d)(no weapon)", this.name, this.hitPoints, this.maxHitPoints);
        }
        else{
            result = String.format("%s(%d/%d)(%s)", this.name, this.hitPoints, this.maxHitPoints, this.getWeapon());
        }
        return result;
    }

    /**
	 * Getter for the enemy's maxHitPoint attribute.
	 * @return Enemy's max hit point
	 */
	public int getMaxHitPoint() {
		return this.maxHitPoints;
	}

	/**
	 * Getter fot the enemy's current hitPoint attribute.
	 * @return Enemy's current hit point
	 */
	public int getCurrHitPoint() {
		return this.hitPoints;
	}
}
