package game.actors.enemies;

import edu.monash.fit2099.engine.*;
import game.item.CindersOfALord;
import game.item.YhormsGreatMachete;
import game.behaviours.EmberFormBehaviour;
import game.enums.Abilities;
import game.enums.Status;
/**
 * Class that represents a Yhorm The Giant boss, which is one type of the Lord Of Cinder.
 * @author FIT2099S221 MA_Lab2Team4
 * @see LordOfCinder,Enemies,Actor
 */
public class YhormTheGiant extends LordOfCinder {
    /**
     * Class constant for maximum hit points of Yhorm The Giant
     */
    static final int MAX_HIT_POINTS = 500;

    /**
     * Class constant for Yhorm The Giant's souls
     */
    static final int YHORM_SOULS = 5000;

    /**
     * EmberFormBehaviour instance to check if the Yhorm The Giant has executed the Ember Form Behaviour.
     */
    private EmberFormBehaviour emberFormBehaviour;

    /**
     * Constructor references the constructor of the abstract parent class LordOfCinder and initialises the name,
     * display char, hit points, initial location, capabilities, weapons/items and souls of the attributes of
     * Yhorm The Giant.
     * @param initialLocation the initial location of the actor
     */
    public YhormTheGiant(Location initialLocation) {
        super("Yhorm The Giant", 'Y', MAX_HIT_POINTS, initialLocation);
        maxHitPoints = MAX_HIT_POINTS;
        registerInstance();
        addCapability(Abilities.WEAK_TO_STORM_RULER);
        addCapability(Abilities.YHORMS_GREAT_MACHETE_HOLDER);
        addItemToInventory(new YhormsGreatMachete());
        addItemToInventory(new CindersOfALord(name, new YhormsGreatMachete()));
        setSouls(YHORM_SOULS);
    }

    /**
     * Check if the Ember Form behaviour can be executed and check if Yhorm The Giant is stunned.
     * @param actions    collection of possible Actions for Yhorm The Giant.
     * @param lastAction The Action this Yhorm The Giant took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Yhorm The Giant.
     * @param display    the I/O object to which messages may be written.
     * @return DoNothingAction instance if Yhorm The Giant is stunned. Else, select and return an action that is
     * returned from one of the behaviour instance of the Yhorm The Giant.
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // Check the health points to decide if the Ember Form should be executed
        if (this.hitPoints < this.maxHitPoints / 2 && !this.hasCapability(Status.EMBER_FORM_EXECUTED)){
            if (emberFormBehaviour == null){ // Make sure add only once.
                emberFormBehaviour = new EmberFormBehaviour();
                behaviours[emberFormBehaviour.getPriority()] = emberFormBehaviour;
            }
        }

        // Check if the Yhorm The Giant has the Stun Status.
        if (this.hasCapability(Status.STUN)) {
            display.println("Yhorm The Giant is stunned!");

            // Remove the Stun status from capabilities list, because the effect of the stun can only last for one round.
            this.removeCapability(Status.STUN);

            // Yhorm The Giant does nothing for one round when it is stunned.
            return new DoNothingAction();
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /** Reset the attributes and conditions of Yhorm The Giant after reset. If the Yhorm The Giant is still conscious
     * after the reset, heal the hitPoints of the Yhorm The Giant to the maximum. Next, remove the capabilities of the
     * EmberFormBehaviour that was possessed by the Yhorm The Giant.
     * @param map location of the actor
     */
    @Override
    public void resetInstance(GameMap map) {
        if (this.isConscious()) {
            this.heal(Math.abs(getCurrHitPoint()) + getMaxHitPoint());

            if(this.hasCapability(Status.EMBER_FORM_EXECUTED)){
                this.removeCapability(Status.EMBER_FORM_EXECUTED);
            }

            if(this.hasCapability(Status.ON_RAGE)){
                this.removeCapability(Status.ON_RAGE);
            }
        }
        super.restInstance(map);
    }
}
