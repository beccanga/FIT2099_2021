package game.actors.enemies;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.item.CindersOfALord;

/**
 * The boss of Design o' Souls
 * FIXME: This boss is Boring. It does nothing. You need to implement features here.
 * TODO: Could it be an abstract class? If so, why and how?
 */

/**
 * An abstract class representing the boss level enemies of the game.
 * This is an abstract class as there may be more bosses introduced in this game, and we can inherit attributes through
 * this abstract LordOfCinder class.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Enemies,Actor
 */
public abstract class LordOfCinder extends Enemies {
    /**
     * Constructor references the constructor from the parent class Enemies and initialises the name, display char,
     * hit points, initial location and capabilities of the Lord Of Cinder.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints, Location initialLocation) {
        super(name, displayChar, hitPoints, initialLocation);
        addCapability(Abilities.IS_LORD_OF_CINDER);
    }
}