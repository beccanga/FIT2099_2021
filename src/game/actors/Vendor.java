package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.BuyWeaponsAction;
import game.actions.IncreaseMaxHPAction;
import game.actions.TradeCindersAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.ActorSpawnLocation;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.interfaces.TokenCollector;
import game.item.BroadSword;
import game.item.GiantAxe;

/**
 * Class representing the vendor.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Actor
 */
public class Vendor extends Actor {
    /**
     * Constructor references the constructor in the abstract parent class Actor and instantiates the name,
     * displayCharm, hitPoints, and capabilities.
     */
    public Vendor() {
        super("FireKeeper", 'F', Integer.MAX_VALUE);
        addCapability(Abilities.CANNOT_BE_ATTACKED);
        addCapability(Abilities.IS_VENDOR);
    }

    /**
     * Action that Vendor performs during each play turn of the game.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Contains the actions that can be done by actor to Vendor. This includes purchasing weapons or increasing actor
     * maximum HP.
     * @param otherActor the Actor that is interacting with the Vendor.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return The actions for the actor to buy weapon
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        actions.add(new IncreaseMaxHPAction());

        // Weapons that can be sold
        actions.add(new BuyWeaponsAction(new BroadSword()));
        actions.add(new BuyWeaponsAction(new GiantAxe()));

        return actions;
    }
}
