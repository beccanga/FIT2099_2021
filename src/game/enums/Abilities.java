package game.enums;

import game.interfaces.Behaviour;

/**
 * Enum that represents an ability of Actor, Item, or Ground.
 * @author FIT2099S221 MA_Lab2Team4
 */
public enum Abilities {
    REST, ENTER_VALLEY, // Use this capability to check if an actor can enter the valley
    WEAK_TO_STORM_RULER, // Use this capability to check if an actor is weak to STORM RULER
    CANNOT_BE_ATTACKED, // Use this capability to check if an actor cannot be killed
    CAN_BE_BURNT, // Use this capability to check if the ground can be burned
    ENTER_BONFIRE, // Use this capability to check if the actor can enter Bonfire
    HAS_NO_WEAPON, // Use this capability to check if the actor has no weapon
    IS_LORD_OF_CINDER, // Use this capability to check if the actor is Lord Of Cinder
    YHORMS_GREAT_MACHETE_HOLDER, // Use this capability to check if the actor is Yhorms Great Machete Holder
    IS_PLAYER, // Use this capability to check if the actor is Player
    CAN_RESURRECT, // Use this capability to check if the actor is Skeleton
    IS_BONFIRE, // Use this capability to check if the ground is Bonfire
    IS_ENEMY, // Use this capability to check if the actor is Enemy
    CAN_BE_TRADED, // Use this capability to check if the item can be traded with the vendor
    ENTER_FOG_DOOR, // Use this capability to check if an actor can enter the fog door
    DYING_TOKEN, // Use this capability to check if item is dying token
    IS_RANGE_WEAPON, // Use this capability to check if it is range weapon
    IS_VENDOR, // Use this capability to check if the actor is Vendor
    IS_MIMIC // Use this capability to check if the actor is Mimic
}
