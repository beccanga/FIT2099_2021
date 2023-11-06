package game.item;

import edu.monash.fit2099.engine.*;
import game.actions.CollectSoulsAction;
import game.interfaces.Soul;

/**
 * class representing the token of soul.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Item,Soul
 */
public class TokenOfSoul extends Item implements Soul {

    /**
     * location where the TokenOfSouls is spawned.
     */
    private Location tokenLocation;
    /**
     * souls value for the TokenOfSoul instance.
     */
    private int souls;

    /**
     * Constructor references the constructor of abstract parent class Item and initialises the name, display char,
     * portable and allowable actions attribute for the token of souls. Can be collected the Player.
     */
    public TokenOfSoul(int defaultSouls) {
        super("Token Of Soul", '$', false);
        this.addSouls(defaultSouls);
        allowableActions.add(new CollectSoulsAction(this));
    }

    /**
     *
     * @param actor an actor that will interact with this item
     * @return pickUpAction from referencing the abstract parent class Item
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return super.getPickUpAction(actor);
    }

    /**
     * Initialising the location for token
     * @param newTokenLocation new token location variable created
     */
    public void setTokenLocation(Location newTokenLocation) {
        this.tokenLocation = newTokenLocation;
    }

    /**
     * Getter for the token location
     * @return token location
     */
    public Location getTokenLocation() {
        return this.tokenLocation;
    }

    /**
     * Transfer souls between objects.
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(getSouls());
        subtractSouls(getSouls());
    }

    /**
     * Checks if the number of souls have been increased.
     * @param souls number of souls to be incremented.
     * @return boolean of whether the souls have been added
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
     * Method to subtract souls
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
     * getter for souls
     * @return souls
     */
    @Override
    public int getSouls() {
        return this.souls;
    }

    /**
     * reset souls, instantiates souls = 0
     */
    public void resetSouls() {
        this.souls = 0;
    }
}
