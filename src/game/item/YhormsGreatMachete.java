package game.item;

import edu.monash.fit2099.engine.*;
import game.actions.BurningAction;
import game.enums.Status;

/**
 * Class representing Yhorm The Giant's weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Axe,GameWeaponItem,WeaponItem,Item
 */
public class YhormsGreatMachete extends Axe {

    /**
     * Constant representing the damage caused by the weapon.
     */
    static final int WEAPON_DAMAGE = 95;

    /**
     * Constant representing the success hit rate of the weapon.
     */
    static final int WEAPON_HIT_RATE = 60;

    /**
     * Zero-parameter constructor.
     * Initialises the name, display character, weapon damage, weapon verb and weapon hit rate of the YhormsGreatMachete
     * weapon. The weapon has burning action.
     */
    public YhormsGreatMachete() {
        super("Yhorm's Great Machete", 'y', WEAPON_DAMAGE, "chops", WEAPON_HIT_RATE);
        addAction(new BurningAction());
    }

    /**
     * Inform a carried Item of the passage of time. It will activate the rage mode passive skill of the weapon when the
     * holder is on rage but the rage mode passive skill of the weapon is not activated.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // If the holder is on rage but the rage mode passive skill of the weapon is not activated:
        if (actor.hasCapability(Status.ON_RAGE) && !this.hasCapability(Status.RAGE_MODE)){

            // Print out a descriptive message that the Yhorm is on rage now.
            Display display = new Display();
            display.println("Yhorm goes beserk! Rghhhhh!!!");

            // Activate the rage mode passive skill of the weapon.
            this.addCapability(Status.RAGE_MODE);
        }
    }

    /**
     * Increases the hit rate of the weapon by 30 when the rage mode passive skill of the weapon is activated.
     * @return the final hit rate of the enemy
     */
    public int chanceToHit(){
        // If the rage mode passive skill of the weapon is activated, we will increase its success hit rate by 30.
        int finalHitRate = hitRate;
        if(this.hasCapability(Status.RAGE_MODE)){
            finalHitRate = hitRate + 30;
        }
        return finalHitRate;
    }
}