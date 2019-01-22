import org.newdawn.slick.Input;

/**
 * @author gilbert
 *  handle bus's object movements and  detect collision with player.
 */
public class Bus extends MovingObject {

	/**Bus image directory */
	public static final  String ADDRESS =  "assets/bus.png"; 
	/**Bus movement rate */
	public static final float RATE = 0.15f; 
	
	/** instantiates Bus object at (x,y) with specific direction.
	  * @param x x coordinate of Bus on the screen
	  * @param y y coordinate of Bus on the screen
	  * @param direction specifies Bus's direction */	
	public Bus(float x, float y,boolean direction) {
		super(ADDRESS, x, y, direction);
	}

	@Override
	/** moves to the right/left, and reduces player's life when making contact.
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
	    move(input, delta, RATE);
	    if (this.contactSprite(getPlayer())) {
	    	affectsPlayer();
	    }
	}
}
