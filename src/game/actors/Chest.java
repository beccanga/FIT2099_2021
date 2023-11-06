package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.OpenChestAction;
import game.enums.Abilities;

/**
 * Class representing the Chest.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Actor
 */
public class Chest extends Actor  {

    /**
     * Constructor references the constructor of the abstract Actor class and it initialises the name, displayChar and
     * hitPoints attribute of the Chest object.
     */
    public Chest() {
        super("Chest", '?', Integer.MAX_VALUE);
        this.addCapability(Abilities.CANNOT_BE_ATTACKED);
    }

    /**
     * Select and return an action to perform on the current turn. The chest will do nothing for every turn.
     * @param actions    collection of possible Actions for the Chest.
     * @param lastAction The Action this Chest took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Chest
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns the OpenChestAction instance which represents the opening chest action that can be done by the otherActor
     * to the current Chest.
     *
     * @param otherActor the Actor that might be interacting with the current Chest.
     * @param direction  String representing the direction of the other Actor.
     * @param map        the map containing the Chest
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = super.getAllowableActions(otherActor, direction, map);

        actions.add(new OpenChestAction(this));

        return actions;
    }

}
