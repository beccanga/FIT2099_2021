package game.item;

import edu.monash.fit2099.engine.*;
import game.actions.ChargeAction;
import game.actions.WindSlashAction;
import game.enums.Abilities;
import game.enums.Status;

import java.util.Random;

/**
 * Class for Storm Ruler weapon.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Sword,GameWeaponItem,WeaponItem,Item
 */
public class StormRuler extends Sword {
    /**
     * class constant for the weapon damage
     */
    static final int WEAPON_DAMAGE = 70;
    /**
     * class constant for the weapon hit rate
     */
    static final int WEAPON_HIT_RATE = 60;
    /**
     * class constant for the price of souls
     */
    static final int SOULS_PRICE = 2000;
    /**
     * charge action from the ChargeAction class
     */
    private ChargeAction chargeAction;

    /**
     * Constructor initialises the name, displayChar, damage, verb, hitRate and souls attribute of the Storm Ruler
     * instance.
     *
     */
    public StormRuler() {
        super("StormRuler", '7', WEAPON_DAMAGE, "strikes", WEAPON_HIT_RATE);
        setSouls(SOULS_PRICE);
    }

    /**
     * Changes the magnitude of the damage that can be caused by the StormRuler when different passive skills are
     * activated.
     * @return final damageafter considering the passive adn active action of the weapon used.
     */
    public int damage(){
        Random rand = new Random();
        Display display = new Display();
        int finalDamage = damage;

        if(this.hasCapability((Status.WIND_SLASH_EXECUTED))){
            finalDamage = damage * 2;
        }
        else{
            // Check if the Dullness passive skill is activated.
            if(this.hasCapability(Status.DULLNESS)){
                finalDamage = finalDamage / 2;
                display.println("The Dullness passive skill is executed. Damage is halved.");
            }

            if (rand.nextInt(100) < 20){
                // If the Dullness passive skill is not activated, activate the Critical Strike passive skill.
                finalDamage = finalDamage * 2;
                display.println("The Critical strike passive skill is executed. Damage is doubled.");
            }
        }
        return finalDamage;
    }

    /**
     * Changes the magnitude of the hit rate that can be caused by the StormRuler when Wind Slash Action is executed.
     * @return Hit rate when the weapon is used.
     */
    @Override
    public int chanceToHit() {
        int finalHitRate = hitRate;
        if(this.hasCapability((Status.WIND_SLASH_EXECUTED))){
            finalHitRate = 100;
        }
        return finalHitRate;
    }

    /**
     * Check the conditions in order to activate the DULLNESS passive skill and WindSlash actions.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        Display display = new Display();

        // When the StormRuler is charging, the StormRuler cannot be used to attack.
        if(this.hasCapability(Status.CHARGING)){
            allowableActions.clear(); // Empty the allowable actions list.
        }

        // Check if the Wind Slash active skill has been invoked in the StormRuler or
        // Check if this is the first time charging.
        if(chargeAction == null || actor.hasCapability(Status.AFTER_WIND_SLASH) ){
            actor.removeCapability(Status.AFTER_WIND_SLASH);
            this.removeCapability(Status.FULLY_CHARGED);

            allowableActions.clear();

            // Add the Charge Action to the allowable actions in the StormRuler so that Player can select and invoke it
            // in the console later.
            chargeAction = new ChargeAction(this);
            this.addAction(chargeAction);
        }
        else {
            if (this.hasCapability(Status.CHARGING) && !this.hasCapability(Status.IS_EXECUTED)){
                display.println(chargeAction.execute(actor, currentLocation.map()));
            }
            else {
                this.removeCapability(Status.IS_EXECUTED);
            }
        }
    }

    /**
     * Implement the Dullness ability and wind slash active skill of the StormRuler after checking the conditions of the
     * target and if the StormRuler weapon is charged.
     * @param target the target actor that is attacked by the weapon.
     * @param direction the direction of target, e.g. "north"/
     * @return WindSlashAction instance if the StormRuler is fully charged
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        // If target is weak to StormRuler, deactivate the Dullness passive skill.
        if (target.hasCapability(Abilities.WEAK_TO_STORM_RULER)){
            this.removeCapability(Status.DULLNESS);
        }

        // If target is not weak to StormRuler, Activate the Dullness passive skill.
        else{
            this.addCapability(Status.DULLNESS);
        }

        // Check if the target can be attacked with the Wind Slash active skill and check if the StormRuler
        // is fully charged.
        if(target.hasCapability(Abilities.WEAK_TO_STORM_RULER) && this.hasCapability(Status.FULLY_CHARGED)){
            return new WindSlashAction(this, target, direction);
        }
        return null;
    }

    /**
     * Return a descriptive string of the weapon's status tto determine if the StormRuler is fully charging
     * @return the String of the Storm Ruler's status
     */
    public String toString(){
        String result = name;
        if (this.hasCapability(Status.CHARGING)){
            result += "(CHARGING)";
        }
        else if (this.hasCapability(Status.FULLY_CHARGED))
        {
            result += "(FULLY CHARGED)";
        }
        return result;
    }
}
