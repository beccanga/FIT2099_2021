package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.actors.Chest;
import game.actors.Player;
import game.actors.Vendor;
import game.actors.enemies.AldrichTheDevourer;
import game.actors.enemies.Skeleton;
import game.actors.enemies.YhormTheGiant;
import game.enums.Abilities;
import game.manager.BonfireManager;
import game.terrains.*;
import game.item.StormRuler;

/**
 * The main class for the game.
 * @author FIT2099S221 MA_Lab2Team4
 */
public class Application {
	public Application() {
	}

	/**
	 * The main method to run the game in Profane Capital and Anor Londo that the player can be transported through a fog door.
	 * As well as calls various Actors such as Player, Yhorm The Giant,
	 * Skeleton calls Cemetery class to spawn Undead enemy, Aldrich the Devourer and Chest.
	 *
	 * @param args
	 */

	public static void main(String[] args) {

		World world = new World(new Display());
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley());

		List<String> profaneCapitalMap = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"........................................................................++......",
				".........................................................................+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				".............................++.......+......................++++...............",
				".............................................................+++++++............",
				"..................................###___###...................+++...............",
				"..................................#_______#......................+++............",
				"...........++.....................#_______#.......................+.............",
				".........+++......................#_______#........................++...........",
				"............+++...................####_####..........................+..........",
				"..............+......................................................++.........",
				"..............++.................................................++++++.........",
				"............+++...................................................++++..........",
				"+..................................................................++...........",
				"++...+++.........................................................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				"#####___#####++++......................+...............................+..+.....",
				"_..._....._._#.++......................+...................................+....",
				"...+.__..+...#+++...........................................................+...",
				"...+.....+._.#.+.....+++++...++..............................................++.",
				"___.......___#.++++++++++++++.+++.............................................++");

		// first game map
		GameMap profaneCapital = new GameMap(groundFactory, profaneCapitalMap);
		world.addGameMap(profaneCapital);

		// second game map
		List<String> anorLondoMap = Arrays.asList(
				"..........+++.........................................................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"........................................................................++......",
				".........................................................................+++....",
				"............................+.............................................+++...",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				"................................................................................",
				".............................++.......+......................++++...............",
				".............................................................+++++++............",
				"..........................#__#####################...........+++................",
				"..........................#......................#..............................",
				"..........................#.......#........+.....#...............+++............",
				"...........++.............#...............+......#................+.............",
				".........+++..............#....#.........__......#..................++..........",
				"..........................#......................#..............................",
				"............+++...........####___#####__##########...................+..........",
				"..............+......................................................++.........",
				"..............++.................................................++++++.........",
				"............+++...................................................++++..........",
				"+..................................................................++...........");

		GameMap anorLondo = new GameMap(groundFactory, anorLondoMap);
		world.addGameMap(anorLondo);

		// First Map: Profane Capital
		Location playerInitialPosition = profaneCapital.at(38, 12);
//		Location playerInitialPosition = profaneCapital.at(3, 12); // For testing purpose
		Actor player = new Player("Unkindled (Player)", '@', 0, profaneCapital.at(38, 11), playerInitialPosition);
		world.addPlayer(player, playerInitialPosition);

		// Place the Vendor in the map
		profaneCapital.at(37, 11).addActor(new Vendor());

		// Place the Cemetery in the map
		Location[] cemeteryLocations = { profaneCapital.at(12, 10), profaneCapital.at(30, 22), profaneCapital.at(15, 7),
				profaneCapital.at(38, 4), profaneCapital.at(60, 3), profaneCapital.at(50, 15), };

		for (Location location : cemeteryLocations) {
			location.setGround(new Cemetery());
		}

		// Spawning Skeletons in Map
		Location[] skeletonLocations = { profaneCapital.at(5, 10), profaneCapital.at(10, 5), profaneCapital.at(15, 7),
				profaneCapital.at(20, 3), profaneCapital.at(29, 3), profaneCapital.at(66, 5), profaneCapital.at(32, 14),
				profaneCapital.at(26, 9), profaneCapital.at(50, 20), profaneCapital.at(57, 5) };

		for (Location location : skeletonLocations) {
			location.addActor(new Skeleton(location));
		}

		// Place the Storm Ruler in the map
		Item stormRuler = new StormRuler();
		profaneCapital.at(7, 25).addItem(stormRuler);

		// Place Yhorm the Giant/boss in the map
		 Location YhormLocation = profaneCapital.at(6, 25);
		YhormLocation.addActor(new YhormTheGiant(YhormLocation));

		 Location AldrichLocation = anorLondo.at(40, 14);
		 AldrichLocation.addActor(new AldrichTheDevourer(AldrichLocation)); // Testing Purpose

		// Place the Cemetery in the map
		// Cemetery locations
		Location[] cemeteryLocations2 = { anorLondo.at(12, 10), anorLondo.at(25, 12), anorLondo.at(15, 7),
				anorLondo.at(33, 4), anorLondo.at(50, 15) };

		for (Location location : cemeteryLocations2) {
			location.setGround(new Cemetery());
		}
		// Spawning Skeletons in Map
		Location[] skeletonLocations2 = { anorLondo.at(5, 10), anorLondo.at(10, 5), anorLondo.at(15, 7),
				anorLondo.at(20, 3), anorLondo.at(29, 3), anorLondo.at(26, 9), anorLondo.at(52, 15),
				anorLondo.at(57, 5) };

		for (Location location : skeletonLocations2) {
			location.addActor(new Skeleton(location));
		}

		// Place fogDoor in map //fogDoorLocation1
		Location fogDoorLocation1 = profaneCapital.at(38, 25);
		// Place fogDoor in Anor Londo // fogDoorLocation2
		Location fogDoorLocation2 = anorLondo.at(38, 0);

		fogDoorLocation1.setGround(new FogDoor(fogDoorLocation2, "to Anor Londo"));
		fogDoorLocation2.setGround(new FogDoor(fogDoorLocation1, "to Profane Capital"));

		// Place a Bonfire at the middle
		BonfireManager bonfireManager = new BonfireManager();

		bonfireManager.registerBonfireInstance(new Bonfire(profaneCapital.at(38, 11), "Firelink Shrine", bonfireManager));
		bonfireManager.registerBonfireInstance(new Bonfire(anorLondo.at(38, 14), "Anor Londo", bonfireManager));
		bonfireManager.registerBonfireInstance(new Bonfire(anorLondo.at(38, 9), "Anor Londo Outside Chamber", bonfireManager));

		bonfireManager.addBonfireToMap();

		// Add Chest to Profane Capital Map
		for(int i = 1; i <= 8; i ++){
			Random random = new Random();
			int x = random.nextInt(80);
			int y = random.nextInt(26);
			Location destination = profaneCapital.at(x, y);
			if(!destination.containsAnActor() && destination.getGround().hasCapability(Abilities.CAN_BE_BURNT)){
				destination.addActor(new Chest());
			}
		}

		// Add Chest to Anor Londo Map
		for(int i = 1; i <= 8; i ++){
			Random random = new Random();
			int x = random.nextInt(80);
			int y = random.nextInt(21);
			Location destination = anorLondo.at(x, y);
			if(!destination.containsAnActor() && destination.getGround().hasCapability(Abilities.CAN_BE_BURNT)){
				destination.addActor(new Chest());
			}
		}

		world.run();
	}
}
