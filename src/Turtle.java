import java.util.concurrent.TimeUnit;

import org.newdawn.slick.Input;

/**
 * @author gilbert
 * controls all turtle behaviour and collision with player.
 */
public class Turtle extends Transportation{
	
	/** image directory for turtle */
	public static final String ADDRESS  = "assets/turtles.png";
	
	
	/** vanish after 7 seconds*/
	public static final long VANISH = 7;
	
	/** resurface after 2 seconds*/
	public static final long RESURFACE = 2;
	
	private static long time = System.currentTimeMillis();
	private static boolean on = true; 
	
	/** instantiates Turtle object at (x,y) with specific direction.
	  * @param x x coordinate of Turtle on the screen
	  * @param y y coordinate of Turtle on the screen
	  * @param direction specifies Turtle's direction */
	public Turtle(float x, float y, boolean direction) {
		super(ADDRESS, x,y, WIDTH[TURTLE_INDEX], HALF_SIZE,direction);
	}
	
	
	/**disappearing all Turtles*/
	public static void disappear() { 
		on = false;
	}
	
	/**resurfacing all Turtles */
	public static void reappear() {
		on = true;
	}
	
	
	/** go under water and resurface at given interval */
	public static void changeState() {
	
		/*disappear after 7 seconds */

		if (on && (  TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis()
				- time))  == VANISH) ) {
			disappear();
			
			//make sure keeps counting
			time = System.currentTimeMillis();
			
		}
		
		/*resurface after 2 seconds */
		else if (!on && 
				(TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() 
				- time))  == RESURFACE ) ) {
			
			reappear();
			
			//make sure keeps counting
			time = System.currentTimeMillis();

		}
		
	}
	@Override
	/** moves itself.
	 * checks collision with player when visible / invisible.
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
		
		
		move(input,delta, RATE[TURTLE_INDEX], WIDTH[TURTLE_INDEX]/2);
		
		/*checks connection with player when on screen */
		if (on) {
			checkPlayerWhenVisible(delta, RATE[TURTLE_INDEX]);	
		}
		
		/*checks connection with player when not on screen */
		else {
			checkPlayerWhenInvisible();
		}
	}
	
	/** not carrying player when underwater */
	public void checkPlayerWhenInvisible() {
		if  (getPlayer().getOther() == this ) {
			leavePlayer();
		}
	}

	@Override
	/** render only when not underwater */
	public void render() { 
		if(on) {
			this.getImage().drawCentered(this.getX(),this.getY());
		}
	}
}
