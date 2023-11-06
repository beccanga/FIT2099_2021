package game.interfaces;

/**
 * Interface representing the consumable
 * @author FIT2099S221 MA_Lab2Team4
 */
public interface Consumable {
    /**
     * getter for number of current charge
     * @return current charge
     */
    int getCurrCharge();

    /**
     * setter for number of current charge
     * @param charges number of charge
     */
    void setCurrCharge(int charges);
}
