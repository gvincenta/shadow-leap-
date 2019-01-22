import org.newdawn.slick.Input;
/**
 * @author gilbert
 * controls RaceCar behaviour and interaction with player.
 */
public class RaceCar extends MovingObject {
	
	/* the additional attributes */
	
	/**RaceCar image directory*/
	public static final  String ADDRESS =  "assets/racecar.png"; 

	/**RaceCar movement rate */
	public static final float RATE = 0.5f; 

	/** instantiates RaceCar object at (x,y) with specific direction.
	  * @param x x-coordinate of RaceCar on the screen
	  * @param y y-coordinate of RaceCar on the screen
	  * @param direction specifies RaceCar's direction */	
	public RaceCar(float x, float y,boolean direction) {
		super(ADDRESS, x, y, direction);	
	}

	@Override
	/** moves to the right/left, and reduces player's life when making contact.
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
	    move(input, delta, RATE);
	    
	    //collision detection with player 
	    if (this.contactSprite(getPlayer())) {
	    	affectsPlayer();
	    }
	}
}

