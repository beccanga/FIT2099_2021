package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actors.Chest;
import game.actors.enemies.Mimic;
import game.item.TokenOfSoul;
import java.util.Random;

/**
 * Class representing the opening chest action.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Action
 */
public class OpenChestAction extends Action {

    /**
     * The chest to be opened.
     */
    private Chest targetChest;

    /**
     * Constructor initialises the targetChest attribute of the OpenChestAction.
     * @param targetChest The current chest to be opened.
     */
    public OpenChestAction(Chest targetChest) {
        this.targetChest = targetChest;
    }

    /**
     * Implements that there is 50% chance chest becoming an enemy and 50% chance
     * dropping the token of souls after opening the chest.
     * @param actor The actor performing the opening chest action.
     * @param map   The map the actor is on.
     * @return a string describing if the chest becomes a Mimic instance or a
     *         TokenOfSoul after the chest is opened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location chestLocation = map.locationOf(targetChest);
        map.removeActor(targetChest);

        Random rand = new Random();
        if (rand.nextBoolean()) {
            int numOfToken = rand.nextInt(3) + 1;

            for (int i = 0; i < numOfToken; i++) {
                TokenOfSoul tokenOfSoul = new TokenOfSoul(100);
                tokenOfSoul.setTokenLocation(chestLocation);
                chestLocation.addItem(tokenOfSoul);
            }
            return "Dropped " + numOfToken + " Token Of Souls";
        }
        else {
            Mimic mimic = new Mimic(chestLocation);
            chestLocation.addActor(mimic);
            return "Mimic is spawned";
        }
    }

    /**
     * Returns a string describing that the chest is opened.
     * @param actor The actor performing the action.
     * @return string describing the opening chest action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the Chest";
    }
}
