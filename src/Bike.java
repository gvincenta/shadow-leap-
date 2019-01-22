import org.newdawn.slick.Input;

/**
 * @author gilbert
 * Bike class to handle Bike's object movements and collisions with Player.
 */
public class Bike extends MovingObject{

	/**Bike's image directory */
	public static final  String ADDRESS =  "assets/bike.png";
	/**Bike's movement rate*/
	public static final float RATE = 0.2f; 
	/**Bike's reverse points*/
	public static final float[] X_REVERSE_POINT = new float[] {24, 1000};
	

	
	/** instantiates Bike object at (x,y) with specific direction.
	  * @param x x coordinate of Bike on the screen
	  * @param y y coordinate of Bike on the screen
	  * @param direction specifies Bike's direction */	
	public Bike(float x, float y,boolean direction) {
		super(ADDRESS, x, y, direction);
	}
	
	/** moves to the right/left, and reduces player's life when making contact.
	 * reverse at required x coordinates. 
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	@Override
	public void update(Input input, int delta) {
		/*reverses accordingly */
		if (this.getX() <= X_REVERSE_POINT[0]) {
			reverse();
			this.setX(X_REVERSE_POINT[0]);
		}
		
		else if (this.getX() >= X_REVERSE_POINT[1]) {
			reverse();
			this.setX(X_REVERSE_POINT[1]);
		}
		
		move(input, delta, RATE);
	    
		//handles collisions with player
	    if (this.contactSprite(getPlayer())) {
	    	affectsPlayer();
	    }   
	}
	
	/** reverses Bike's direction*/
	public void reverse() {
		setDirection( !getDirection() );
	}
}
