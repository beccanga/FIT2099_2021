package game.enums;

import game.interfaces.Behaviour;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 * @author FIT2099S221 MA_Lab2Team4
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    CHARGING, // use this capability for charging
    FULLY_CHARGED, // use this capability for fully charged
    IS_EXECUTED, // use this capability when actor is executed
    WIND_SLASH_EXECUTED, // use this capability when actor is executed by wind slash action
    EMBER_FORM_EXECUTED, // use this capability when actor is executed by ember form
    ON_RAGE, // use this capability when actor on rage mode
    PERCENTAGE_HEAL, // use this capability to get percentage healed
    STUN, // use this capability for stun passive skill
    DULLNESS, // use this capability for dullness passive skill
    FOLLOWING_PLAYER, // use this capability to be following player
    UNDER_ATTACK, // use this capability when under attack
    RAGE_MODE, // use this capability when actor in range mode
    AFTER_WIND_SLASH, // use this capability after wind slash
    DISARMED, // use this capability when actor is disarmed
    SPIN_ATTACK_EXECUTED, // use this capability when actor is executed by spin attack action
    ACTIVATED_BONFIRE, // use this capability when bonfire activated
    TOKEN_TO_REMOVE, // use this capability when token removed
    USING_RANGED_WEAPON, // use this capability when using ranged weapon
    RANGE, //use this capability when range
    ENGAGED, //use this capability for engaged
    IS_RESURRECTED, // Use this capability to check if the actor can be resurrected
}
