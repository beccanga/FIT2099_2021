package game.terrains;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actors.enemies.Undead;

import java.util.Random;

/**
 * Class for the cemetery terrain.
 * @author FIT2099S221 MA_Lab2Team4
 * @see Ground
 */
public class Cemetery extends Ground {
    /**
     * Random generator.
     */
    private Random rand = new Random();

    /**
     * Constructor references the constructor of the abstract parent class Ground and initialises the displayChar
     * attribute of the Cemetery instance.
     */
    public Cemetery() {
        super('C');
    }

    /**
     * Spawns Undead from the current location.
     * @param location The location of the Cemetery
     */
    @Override
    public void tick(Location location) {
        if (!location.containsAnActor()){
            //  25% success rate to spawn undead
            if (rand.nextInt(100) < 25) {
                location.addActor(new Undead(location));
                Display display = new Display();
                display.println("Undead is spawn from the cemetery!");
            }
        }
    }
}
