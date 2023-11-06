package game.item;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import game.actions.AttackAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Abstract class representing the bow weapons.
 * @author FIT2099S221 MA_Lab2Team4
 * @see GameWeaponItem,edu.monash.fit2099.engine.WeaponItem,edu.monash.fit2099.engine.Item
 */
public abstract class Bow extends GameWeaponItem{
    /**
     * Constructor references the constructor of the abstract parent class GameWeaponItem and instantiates name,
     * display character, damage, verb and hit rate of the Bow.
     * @param name        name of the Bow
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Bow(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
        addCapability(Abilities.IS_RANGE_WEAPON);
    }

    /**
     * Iterate through the allocated range from the holder and perform attack to the valid target within the range.
     * The checking of the grounds that can block thrown object is done in the called AttackAction instance.
     * @param currentLocation The current location where the actor is holding the weapon.
     * @param actor The actor who is holding the weapon.
     * @param range The range of the attack.
     */
    public void attackActorWithinRange(Location currentLocation, Actor actor, int range) {
        GameMap map = currentLocation.map();

        NumberRange xs = new NumberRange(currentLocation.x() - range, 2 * range + 1);
        NumberRange ys = new NumberRange(currentLocation.y() - range, 2 * range + 1);

        for (int x1 : xs) {
            if (x1 < 1 || x1 >= map.getXRange().max()) {
                continue;
            }

            for (int y1 : ys) {
                if (y1 < 1 || y1 > map.getYRange().max()) {
                    continue;
                }

                Location destination = currentLocation.map().at(x1, y1);
                detectValidTarget(destination, actor);
            }
        }
    }

    /**
     * Check if the target is at the input currentLocation. If yes and the actor is a valid target, we will add the
     * attack action to the target into the allowableActions of the Bow.
     * @param currentLocation The current location where the actor is holding the weapon.
     * @param actor The actor who is holding the weapon.
     */
    public void detectValidTarget(Location currentLocation, Actor actor){
        if (currentLocation.containsAnActor()) {
            Actor target = currentLocation.getActor();
            boolean conditionTarget = (target != actor) && !target.hasCapability(Abilities.CANNOT_BE_ATTACKED);
            boolean conditionActor = actor.hasCapability(Status.HOSTILE_TO_ENEMY) || (actor.hasCapability(Abilities.IS_ENEMY) && !target.hasCapability(Abilities.IS_ENEMY));
            if (conditionTarget && conditionActor) {
                target.addCapability(Status.RANGE);
                String coordinates = "(" + currentLocation.x() + ", " + currentLocation.y() + ")";
                allowableActions.add(new AttackAction(target, coordinates));
            }
        }
    }

}
