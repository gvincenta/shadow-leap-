import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;


/**
 * @author gilbert
 * Controls all sprites.
 */
public class SpriteController {
	
	/*constants and atttributes */
	/** total number of levels contained */
	public static final int NUM_OF_LEVELS = 6;
	
	private int current_level = 0;
	
	/** file directory of each level */
	public static final String[] LEVELS = new String[] {"assets/levels/0.lvl", 
			"assets/levels/1.lvl", "assets/levels/2.lvl","assets/levels/3.lvl",
			"assets/levels/4.lvl","assets/levels/5.lvl"};
	
	/*indices when reading files*/
	/**object type at index 0 */
	public static final int TYPE = 0;
	
	/**object x-coordinate at index 1 */
	public static final int X = 1;
	
	/**object y-coordinate at index 2 */
	public static final int Y = 2;
	
	/**object direction at index 3 */
	public static final int DIRECTION = 3;

	/*all game objects */
	private ArrayList<Sprite> sprites;
	private ArrayList<Water> waters;
	private ArrayList<Log> transport;
	private ArrayList<Turtle> turtle;
	private ArrayList<Tile> tiles;

	private ExtraLife crate;
	private Frog player;
	

	
	/**opens level 0 as well as initialize all game objects on screen. */
	public SpriteController() {
		init();
		readCSV(LEVELS[0]);
	}
	
	/**make new references to objects and connect them to player*/
	public void init () {
		makeEmptyArrayList();
		player =  new Frog(Frog.XSTART, Frog.YSTART);
		crate = new ExtraLife();
		MovingObject.setPlayer(player);
		Transportation.setPlayer(player);
		Water.setPlayer(player);
		Tree.setPlayer(player);
	}
	
	/**reads the next csv file available */
	public void goNextLevel() {
		// if there's a next level, go next level.
		if (current_level != (NUM_OF_LEVELS - 1)) {
			init();
			current_level++;
			this.readCSV(LEVELS[current_level]);
		}
		// if reached the last level, finish the game. 
		else {
			finishGame();
		}
	}
	
	/**quits the game*/
	public void finishGame() {
		System.exit(0);
	}

	/**reads the whole csv in a '.lvl' file 
	 * and makes references to the objects.
	 * @param lvl the file directory of the level. */
	public void readCSV(String lvl) {
		
		/*try-catch exception */
		try ( Scanner sc = new Scanner(new FileReader (lvl)) ){
			
			String[] text;
			/*creating all objects while there's next line to be read*/
			while (sc.hasNextLine()) {
				/*splits each line into distinct, readable attributes */
				text = sc.nextLine().toLowerCase().split(",");
				
				/*creates objects according to its type */
				
				 if ( text[TYPE].equals("bulldozer" ) ) {
					
					this.sprites.add(new Bulldozer(Float.parseFloat(text[X]),
							Float.parseFloat(text[Y]), 
							Boolean.parseBoolean(text[DIRECTION]) ) );
				}
				
				
				else if (text[TYPE].equals("water") ) {
					this.waters.add(new Water( Float.parseFloat(text[X]),
							Float.parseFloat(text[Y]) ));
				}
				 
				else if (text[TYPE].equals("grass") ) {
					this.tiles.add(new Grass( Float.parseFloat(text[X]),
							Float.parseFloat(text[Y])));
				}
				 
				else if ( text[TYPE].equals("bus" ) ) {
					
					this.sprites.add(new Bus(Float.parseFloat(text[X]),
							Float.parseFloat(text[Y]), 
							Boolean.parseBoolean(text[DIRECTION]) ) );
					
				}
				else if (text[TYPE].equals("racecar") ) {
					this.sprites.add(new RaceCar( Float.parseFloat(text[X]), 
							Float.parseFloat(text[Y]),
							Boolean.parseBoolean(text[DIRECTION]) ) );
				}
				else if (text[TYPE].equals("bike") ) {
					this.sprites.add(new Bike( Float.parseFloat(text[X]),
							Float.parseFloat(text[Y]),
							Boolean.parseBoolean(text[DIRECTION])  ));
				}
				
				
				
				else if (text[TYPE].equals("turtle") ) {
					this.turtle.add(new Turtle( Float.parseFloat(text[X]),
					Float.parseFloat(text[Y]), 
					Boolean.parseBoolean(text[DIRECTION]) ) );
				}
				 
				else if (text[TYPE].equals("log") ) {
					this.transport.add(new Log(  Float.parseFloat(text[X]),
							Float.parseFloat(text[Y]),
							Boolean.parseBoolean(text[DIRECTION]) ) );
				}
				else if (text[TYPE].equals("longlog") ) {
					this.transport.add(new LongLog(  Float.parseFloat(text[X]),
							Float.parseFloat(text[Y]), 
							Boolean.parseBoolean(text[DIRECTION]) ) );
				}
				else if (text[TYPE].equals("tree") ) {
					this.sprites.add(new Tree( Float.parseFloat(text[X]),
							Float.parseFloat(text[Y]) ));
				}
			}
		}
		
		//in case it does not work.
		catch (IOException e){
			e.printStackTrace();
		}
		
		/*instatiates the 5 goals, connects Goal to player. */
		Goal.makeGoals(sprites, player);

	}
	
	
	


	/** Updates all of the sprites in the game
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
		player.update(input, delta);

		
		
		//controls all turtles. 
		Turtle.changeState();

		/*updates all objects */
		
		for (Turtle item: turtle) {
			item.update(input,delta);
		}

		
		for (Sprite item: sprites) { 
			item.update(input,delta);
			
		}
		for (Log item: transport) {
			item.update(input,delta);
		}
		
		crate.update(input, delta, transport);
				
		for (Water item: waters) {
			item.update(input,delta);	
		}
		
		/* check all goals. If full, go to the next stage. */
		if (Goal.isFull()) {
			goNextLevel();
		}
	}
	
	/**Draw all of the sprites in the game.
	 * @param g graphics for drawing. */
	public void render(Graphics g) {
		//added feature:  only draw when current time is even (blinking screen)
		
	   //if (System.currentTimeMillis() % 2 ==0 ) { 
		//uncomment to see added feature
			for (Water item: waters) {
				item.render();
			}
			
			for (Turtle item: turtle) {
				item.render();
	
			}
			
			for (Sprite item: sprites) {
				item.render();
			}
		
	
			
			for (Log item: transport) {
				item.render();
	
			}
			crate.render();
	
			for (Tile item: tiles) {
				item.render();
	
			} 
		
		//} //uncomment to see added feature
	    
	    //draws player and its remaining lives all the time. 
		player.render();

	}
	
	/**make new references in arraylists */
	public void makeEmptyArrayList() {
		 sprites = new ArrayList<>();
		waters = new ArrayList<>();
		 transport = new ArrayList<>();
		 turtle = new ArrayList<>();
		 tiles = new ArrayList<>();
	}
	
}
