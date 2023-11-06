package game.actors.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.item.CindersOfALord;
import game.item.DarkmoonLongbow;

/**
 * Class representing Aldrich The Devourer, a new type of Lord Of Cinder (Boss).
 * @author FIT2099S221 MA_Lab2Team4
 * @see LordOfCinder,Enemies,Actor
 */
public class AldrichTheDevourer extends LordOfCinder{

    /**
     * Class constant for initial hit points of Aldrich The Devourer.
     */
    static final int INITIAL_HIT_POINTS = 350;

    /**
     * Class constant for enemy souls of Aldrich The Devourer.
     */
    static final int ENEMY_SOULS = 5000;

    /**
     * One-parameter constructor references the constructor from the parent class LordOfCinder and
     * initialises the name, display char, hit points and initial location attributes of Aldrich The Devourer.
     * @param initialLocation Initial location where the Aldrich The Devourer is allocated in the map.
     */
    public AldrichTheDevourer(Location initialLocation) {
        super("Aldrich The Devourer", 'A', INITIAL_HIT_POINTS, initialLocation);
        setSouls(ENEMY_SOULS);
        addItemToInventory(new DarkmoonLongbow());
        addItemToInventory(new CindersOfALord(name, new DarkmoonLongbow()));
        addCapability(Status.USING_RANGED_WEAPON);
        registerInstance();
    }

    /**
     * Add the following behaviour into the behaviours list of the current enemy if actor who is hostile to them is
     * within 3 adjacent squares from Aldrich. Next, loop through the behaviour list and return an action to be
     * performed by Aldrich The Devourer on the current turn.
     * @param actions    collection of possible Actions that can be performed by for this enemy, Aldrich The Devourer.
     * @param lastAction The Action this enemy took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the enemy
     * @param display    the I/O object to which messages may be written
     * @return the action that will be performed by Aldrich The Devourer on the current turn.
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        if (!this.hasCapability(Status.FOLLOWING_PLAYER) && this.hasCapability(Status.ENGAGED)) {
            Location currentLocation = map.locationOf(this);
            NumberRange xs = new NumberRange(currentLocation.x() - 3, 7);
            NumberRange ys = new NumberRange(currentLocation.y() - 3, 7);

            for (int x1 : xs) {
                if (x1 < 1 || x1 >= map.getXRange().max()) {
                    continue;
                }

                for (int y1 : ys) {
                    if (y1 < 1 || y1 > map.getYRange().max()) {
                        continue;
                    }

                    Location destination = map.at(x1, y1);
                    if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        display.println(name + " is following Player");
                        FollowBehaviour followBehaviour = new FollowBehaviour(destination.getActor());
                        behaviours[followBehaviour.getPriority()] = followBehaviour;
                        addCapability(Status.FOLLOWING_PLAYER);
                    }
                }
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /** Reset the attributes and conditions of Aldrich The Devourer after reset. Heal the Aldrich The Devourer to the
     *  maximum hit points after reset.
     * @param map The current map where the Aldrich The Devourer is allocated.
     */
    @Override
    public void resetInstance(GameMap map) {;
        // Heal to maximum health
        if (this.isConscious()) {
            this.heal(Math.abs(getCurrHitPoint()) + getMaxHitPoint());
        }
        super.restInstance(map);
    }
}
