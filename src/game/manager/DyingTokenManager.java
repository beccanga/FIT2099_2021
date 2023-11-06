package game.manager;

import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;
import game.enums.Status;
import game.item.TokenOfSoul;

import java.util.ArrayList;

/**
 * Class representing the Token manager.
 * @author FIT2099S221 MA_Lab2Team4
 */
public class DyingTokenManager {
    /**
     * Array List for token of souls
     */
    public TokenOfSoul token;

    /**
     * initialises new tokenList
     */
    public DyingTokenManager() {
        token = null;
    }

    /**
     * Method to register Token
     * @param tokenOfSoul token of soul to be added into the list of tokens
     */
    public void setDyingToken(TokenOfSoul tokenOfSoul) {
        token = tokenOfSoul;
    }

    /**
     * Method getDyingToken to get the dying token
     * @return token of soul
     */
    public TokenOfSoul getDyingToken() {
        return token;
    }

    /**
     * Method to remove dying token
     */
    public void removeDyingToken() {
        token = null;
    }
}