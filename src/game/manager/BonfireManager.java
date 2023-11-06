package game.manager;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;
import game.softReset.ResetManager;
import game.terrains.Bonfire;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class representing the bonfire manager.
 * @author FIT2099S221 MA_Lab2Team4
 */
public class BonfireManager {

    /**
     * Array List of bonfire
     */
    private ArrayList<Bonfire> bonfireList;

    /**
     * Constructor
     * initialises the bonfireList
     */
    public BonfireManager(){
        bonfireList = new ArrayList<>();
    }

    /**
     * Method to register bonfire instance in the bonfireList
     * @param bonfire the bonfire
     */
    public void registerBonfireInstance(Bonfire bonfire) {
        bonfireList.add(bonfire);
    }

    /**
     * Method to add bonfire to the map
     */
    public void addBonfireToMap() {
        for (Bonfire bonfire: bonfireList) {
            bonfire.getBonfireLocation().setGround(bonfire);
        }
    }

    /**
     * Method to get allowable bonfire
     * @return new array list for bonfire
     */
    public ArrayList<Bonfire> getAllowableBonfire() {
        ArrayList<Bonfire> bonfireArrayList = new ArrayList<>();
        for (Bonfire bonfire: bonfireList) {
            if (bonfire.hasCapability(Status.ACTIVATED_BONFIRE)) {
                bonfireArrayList.add(bonfire);
            }
        }
        return bonfireArrayList;
    }


}
