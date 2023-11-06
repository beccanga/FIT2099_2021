package game.terrains;

import edu.monash.fit2099.engine.*;
import static game.enums.Abilities.ENTER_FOG_DOOR;
import game.enums.Abilities;

/**
 * Class representing fog door.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Ground
 */
public class FogDoor extends Ground {

    /**
     * Location where the fog door leads to.
     */
    private Location location;

    /**
     * Description of the direction where the fog door leads to.
     */
    private String direction;

    /**
     * Constructor references the constructor of the abstract parent class Ground and initialises the displayChar,
     * location and direction attribute of the FogDoor.
     *
     * @param location location of fog door
     * @param direction direction of fog door
     */
    public FogDoor(Location location, String direction) {
        super('=');
        this.location = location;
        this.direction = direction;
    }

    /**
     * If the location contains a Player, the method will add the MoveActorAction instance into the actions list and
     * return it so that the Player can be moved to the next map.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the actions allowed
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (location.containsAnActor() && actor.hasCapability(ENTER_FOG_DOOR)) {
            actions.add(new MoveActorAction(this.location, this.direction));
        }
        return actions;
    }

    /**
     * Allows actor who has the capability to enter the Fog door to enter the Fog door.
     * @param actor the Actor who wants to enter the fog door.
     * @return true if the actor has the capability to enter the Fog door. Else, return false.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        boolean canEnter = false;
        if (actor.hasCapability(ENTER_FOG_DOOR)) {
            canEnter = true;
        }
        return canEnter;
    }
}
