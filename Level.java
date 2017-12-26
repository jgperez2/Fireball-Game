import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Level class is responsible for managing all of the objects in your game.
 * The GameEngine creates a new Level object for each level, and then calls that
 * Level object's update() method repeatedly until it returns either "ADVANCE"
 * (to go to the next level), or "QUIT" (to end the entire game).
 * <br/><br/>
 * This class should contain and use at least the following private fields:
 * <tt><ul>
 * <li>private Random randGen;</li>
 * <li>private Hero hero;</li>
 * <li>private Water[] water;</li>
 * <li>private ArrayList&lt;Pant&gt; pants;</li>
 * <li>private ArrayList&lt;Fireball&gt; fireballs;</li>
 * <li>private ArrayList&lt;Fire&gt; fires;</li>
 * </ul></tt>
 */
public class Level
{
	
	private Hero1 hero1;
	private Hero2 hero2;
	private ArrayList<Fireball1> fireballs1 = new ArrayList<Fireball1>();
	private ArrayList<Fireball2> fireballs2 = new ArrayList<Fireball2>();
	private ArrayList<Pant> pants = new ArrayList<Pant>();
	private ArrayList<Enemy> enemys = new ArrayList<Enemy>();
	private ArrayList<Graphic> enemyG = new ArrayList<Graphic>();
	private Water[] water = new Water[8];
	private ArrayList<Fire1> fires1 = new ArrayList<Fire1>();
	private ArrayList<Fire2> fires2 = new ArrayList<Fire2>();;
	private int i = 0;
	private Random randGen;
	
	/**
	 * This constructor initializes a new Level object, so that the GameEngine
	 * can begin calling its update() method to advance the game's play.  In
	 * the process of this initialization, all of the objects in the current
	 * level should be instantiated and initialized to their beginning states.
	 * @param randGen is the only Random number generator that should be used
	 * throughout this level, by the Level itself and all of the Objects within.
	 * @param level is a string that either contains the word "RANDOM", or the 
	 * contents of a level file that should be loaded and played. 
	 */
	public Level(Random randGen, String level) 
	{ 
		this.randGen = randGen;
		hero1 = new Hero1((GameEngine.getWidth() / 4), (GameEngine.getHeight() / 2));
//		hero2 = new Hero2((3 * GameEngine.getWidth() / 4), (GameEngine.getHeight() / 2));
		hero1.HP += Intg.HPadd;
		for (i = 0; i < 30; ++i){
			pants.add(new Pant(randGen.nextInt(GameEngine.getWidth()+1), randGen.nextInt(GameEngine.getHeight()+1), randGen));
		}
		for (i = 0; i < Intg.numEnemys; ++i){
			enemys.add(new Enemy(randGen.nextInt(GameEngine.getWidth()+1), randGen.nextInt(GameEngine.getHeight()+1), randGen));
			
		}
	}
	
	
	
	/**
	 * The GameEngine calls this method repeatedly to update all of the objects
	 * within your game, and to enforce all of the rules of your game.
	 * @param time is the time in milliseconds that have elapsed since the last
	 * time this method was called.  This can be used to control the speed that
	 * objects are moving within your game.
	 * @return When this method returns "QUIT" the game will end after a short
	 * 3 second pause and a message indicating that the player has lost.  When
	 * this method returns "ADVANCE", a short pause and win message will be 
	 * followed by the creation of a new level which replaces this one.  When
	 * this method returns anything else (including "CONTINUE"), the GameEngine
	 * will simply continue to call this update() method as usual. 
	 */
	public String update(int time) 
	{
		
		hero1.update(time);
//		hero2.update(time);

//		for(i = 0; i < water.length; ++i){
//			if (water[i] != null){
//				water[i] = water[i].update(time);
//			}
//		}
		
		for(i = 0; i < enemys.size(); ++i){
			Fireball2 ball = enemys.get(i).update(time);
			if (ball != null){
				fireballs2.add(ball);
			}
		}
		
		for (int i = 0; i < fireballs2.size(); ++i){
			if (fireballs2.get(i).shouldRemove()){
				fireballs2.remove(i);
			}
			else
				fireballs2.get(i).update(time);
		}
		
		for (int i = 0; i < fireballs1.size(); ++i){
			if (fireballs1.get(i).shouldRemove()){
				fireballs1.get(i).destroy();;
			}
			else
				fireballs1.get(i).update(time);
		}
		
		for(i = 0; i < fires1.size(); ++i){
			Fireball1 ball = fires1.get(i).update(time);
			if (ball != null){
				fireballs1.add(ball);
			}
		}
//		for(i = 0; i < fires2.size(); ++i){
//			Fireball2 ball = fires2.get(i).update(time);
//			if (ball != null){
//				fireballs2.add(ball);
//			}
//		}
		for(i = 0; i < pants.size(); ++i){
			pants.get(i).update(time);
		}
		
		fireballs1 = Hero1.giveFireball();
//		fireballs2 = Hero2.giveFireball();
		
		for(i = 0; i < pants.size(); ++i){
			Fire1 fire1  = pants.get(i).handleFireballCollisions1(fireballs1);
//			Fire2 fire2  = pants.get(i).handleFireballCollisions2(fireballs2);
			Enemy enemy  = pants.get(i).handleFireballCollisions3(fireballs2);
			if (fire1 != null){
				fires1.add(fire1);
				if (pants.get(i).shouldRemove()){
					pants.remove(i);
				}
			}
//			if (fire2 != null){
//				fires2.add(fire2);
//				if (pants.get(i).shouldRemove()){
//					pants.remove(i);
//				}
//			}
			if (enemy != null){
				enemys.add(enemy);
				if (pants.get(i).shouldRemove()){
					pants.remove(i);
				}
			}
		}
//		for(i = 0; i < fires.size(); ++i){
//			fires.get(i).handleWaterCollisions(water);
//			if (fires.get(i).shouldRemove()){
//				fires.remove(i);
//			}
//		}
		for (int i = 0; i < fireballs2.size(); ++i) {
			if(hero1.handleFireballCollisions(fireballs2.get(i))){
				fireballs2.remove(i);
			}
		}
		if (hero1.HP < 1){
			return "QUIT";
		}
		for (int i = 0; i < enemys.size(); ++i) {
			if(enemys.get(i).handleFireballCollisions(fireballs1)){
				enemys.remove(i);
			}
		}
		if (enemys.size() < 1){
			Intg.nextRound();
			return "ADVANCE";
		}
		getHUDMessage();
		return "CONTINUE"; 
	}

	/**
	 * This method returns a string of text that will be displayed in the
	 * upper left hand corner of the game window.  Ultimately this text should 
	 * convey the number of unburned pants and fires remaining in the level.  
	 * However, this may also be useful for temporarily displaying messages that 
	 * help you to debug your game.
	 * @return a string of text to be displayed in the upper-left hand corner
	 * of the screen by the GameEngine.
	 */
	public String getHUDMessage() 
	{
		String hud = "HP: " + hero1.HP + "\n";
		hud += "Pants Left: " + pants.size() + "\n";
		hud += "Fires1 Left: " + fires1.size() + "\n";
		hud += "FEnemys Left: " + enemys.size() + "\n";
		hud += "Rounds won: " + Intg.round;
		return hud; 
	}

	/**
	 * This method creates a random level consisting of a single Hero centered
	 * in the middle of the screen, along with 6 randomly positioned Fires,
	 * and 20 randomly positioned Pants.
	 */
	public void createRandomLevel() 
	{ 
		// TODO: Implement this method.		
	}

	/**
	 * This method initializes the current game according to the Object location
	 * descriptions within the level parameter.
	 * @param level is a string containing the contents of a custom level file 
	 * that is read in by the GameEngine.  The contents of this file are then 
	 * passed to Level through its Constructor, and then passed from there to 
	 * here when a custom level is loaded.  You can see the text within these 
	 * level files by dragging them onto the code editing view in Eclipse, or 
	 * by printing out the contents of this level parameter.  Try looking 
	 * through a few of the provided level files to see how they are formatted.
	 * The first line is always the "ControlType: #" where # is either 1, 2, or
	 * 3.  Subsequent lines describe an object TYPE, along with an X and Y 
	 * position, formatted as: "TYPE @ X, Y".  This method should instantiate 
	 * and initialize a new object of the correct type and at the correct 
	 * position for each such line in the level String.
	 */
	public void loadLevel(String level) 
	{ 
		// TODO: Implement this method.		
	}

	/**
	 * This method creates and runs a new GameEngine with its first Level.  Any
	 * command line arguments passed into this program are treated as a list of
	 * custom level filenames that should be played in a particular order.
	 * @param args is the sequence of custom level files to play through.
	 */
	public static void main(String[] args)
	{
		GameEngine.start(null,args);
	}
}
