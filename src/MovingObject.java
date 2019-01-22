import org.newdawn.slick.Input;
/**
 * @author gilbert
 * controls general Moveable objects behaviour, 
 * and  interaction with player.
 */
public class MovingObject extends Sprite {

	private static Frog player;
	
	// moves to the left if false, or right if true. 
	private boolean direction;
	
	/*constructors, getters and setters*/
	
	/** instantiates MovingObject object
	 * at (x,y) with specific direction. bounding box is fixated at 48x48.
	  * @param x x coordinate of MovingObject object on the screen
	  * @param y y coordinate of MovingObject object on the screen
	  * @param direction specifies MovingObject object's direction
	  * @param imgSrc specifies MovingObject object's image directory */
	public MovingObject(String imageSrc,float x, float y,boolean direction) {
		super(imageSrc, x, y, TILE_SIZE, TILE_SIZE);
		setDirection(direction);
	}
	
	/** instantiates MovingObject object. 
	 * at (x,y) with specific direction. 
	 * with a boundingbox sized at boxWidth x boxHeight.
	 * @param x x coordinate of MovingObject object on the screen.
	 * @param y y coordinate of MovingObject object on the screen.
	 * @param direction specifies MovingObject object's direction.
	 * @param boxWidth the width of the BoundingBox.
	 * @param boxWidth the height of the BoundingBox.
	 * @param imgSrc specifies MovingObject object's image directory */
	public MovingObject(String imageSrc,float x, float y,boolean direction,  
			int boxWidth, int boxHeight) {
		super(imageSrc, x, y, boxWidth, boxHeight);
		setDirection(direction);
	}
	
	/**lets all  MovingObject  to be attached to player.
	 * @param p the player to be attached with.*/
	public static void setPlayer(Frog p) {
		player = p;
	}
	
	/** @return  the player attached. */
	public static Frog getPlayer() {
		return player;
	}
	
	/**sets the MovingObject's direction (left/ right).
	 * @param direction direction of the object. */
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	/**gets the MovingObject's current direction (left/ right).
	 * @return current direction. */
	public boolean getDirection() {
		return this.direction ;
	}
	
	/**default implementation of move for all MovingObjects
	 * @param input user keyboard input.
	 * @param delta the state of the screen. 
	 * @param rate the rate at which the object moves.*/
	public void move(Input input, int delta, float rate) {

		/* updates vehicles going to left as well as their bounding box 
		 * accordingly */
	    if (! (this.getDirection()) ) {			
			this.setX(this.getX() - delta * rate);
			
			/*teleports to the other side of the screen */
			
			if (this.getBox().getRight() <= 0) {
			    this.setX(App.SCREEN_WIDTH + HALF_SIZE);
				
			}
			
		}
	    
		/* updates vehicles going to right as well as their bounding box 
		 * accordingly */
		else  {
		    this.setX(this.getX() + delta * rate);
			
			
			/*teleports to the other side of the screen */
			if (this.getBox().getLeft() >= App.SCREEN_WIDTH ) {
			    this.setX(-HALF_SIZE);
			}
		}
	}
	
	
	
	/**at default, makes player lose life. */
	public void affectsPlayer() {
		player.loseLife();
	}

}
