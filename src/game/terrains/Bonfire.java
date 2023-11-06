package game.terrains;

import edu.monash.fit2099.engine.*;
import game.manager.BonfireManager;
import game.actions.ActivateBonfireAction;
import game.actions.BonfireTeleportAction;
import game.actions.RestAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.ActorSpawnLocation;

/**
 * Class for the bonfire terrain.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Ground
 */
public class Bonfire extends Ground {
    /**
     * Name of the Bonfire instance.
     */
    private String bonfireName;

    /**
     * Location where the Bonfire instance is located in the map.
     */
    private Location bonfireLocation;

    /**
     * BonfireManager instance.
     */
    private BonfireManager bonfireManager;

    /**
     * Constructor references the constructor of the abstract parent class Ground and initialises the displayChar
     * attribute and capabilities of Bonfire instance.
     */
    public Bonfire(Location bonfireLocation, String bonfireName, BonfireManager bonfireManager) {
        super('B');
        this.addCapability(Abilities.IS_BONFIRE);
        this.bonfireLocation = bonfireLocation;
        this.bonfireName = bonfireName;
        this.bonfireManager = bonfireManager;
    }

    /**
     * Checks if the actor is able to enter Bonfire.
     * @param actor the Actor who wants to enter the Bonfire.
     * @return true if actor has ENTER_BONFIRE capability, else false.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Abilities.ENTER_BONFIRE)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Implements the allowable actions the actor is able to perform in Bonfire, such as RestAction,
     * BonfireTeleportAction and ActivateBonfireAction.
     * @param actor the Actor interacting with Bonfire.
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return new actions required
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        if (this.hasCapability(Status.ACTIVATED_BONFIRE)) {
            if (actor.hasCapability(Abilities.IS_PLAYER)) {
                actions.add(new RestAction((ActorSpawnLocation) actor, this));

                for (Bonfire bonfire : bonfireManager.getAllowableBonfire()) {
                    if (bonfire != this) {
                        actions.add(new BonfireTeleportAction(bonfire));
                    }
                }
            }
        } else {
            if (actor.hasCapability(Abilities.IS_PLAYER)) {
                actions.add(new ActivateBonfireAction((ActorSpawnLocation) actor, this));
            }
        }

        return actions;
    }

    /**
     * Getter for the bonfireLocation attribute.
     * @return location where the Bonfire instance is located in the map.
     */
    public Location getBonfireLocation() {
        return bonfireLocation;
    }

    /**
     * Getter for the bonfireName attribute.
     * @return name of the Bonfire instance.
     */
    public String getBonfireName() {
        return bonfireName;
    }
}
