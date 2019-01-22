import org.newdawn.slick.Input;

/**
 * @author gilbert
 * Controls rideable objects behaviour. 
 */
public class Transportation extends MovingObject{
	/**Rate at which Transportation-derived objects move */
	public static final float[] RATE = new float[] {0.1f, 0.085f,  0.07f};
	
	/**Width of each Transportation-derived objects */
	public static final int[] WIDTH = new int[] {132, 144, 228};
	
	/**Height of each Transportation-derived objects */
	public static final int HEIGHT = 48;
	
	/**index for Log */
	public static final int LOG_INDEX = 0;
	
	/**index for Turtle */
	public static final int TURTLE_INDEX = 1;
	
	/**index for LongLog */
	public static final int LONG_LOG_INDEX = 2;
	
	/** instantiates Transportation object 
	 * at (x,y) with specific direction.
	  * @param x x coordinate of Transportation object on the screen
	  * @param y y coordinate of Transportation object on the screen
	  * @param direction specifies Transportation object's direction
	  * @param imgSrc specifies Transportation object's image directory 
	 * @param boxWidth the width of the BoundingBox.
	 * @param boxWidth the height of the BoundingBox.*/
	public Transportation(String imageSrc, float x, float y,  int boxWidth, 
			int boxHeight, boolean direction) {
		super(imageSrc, x, y, direction, boxWidth, boxHeight);
	}
	
	/**sets the frog free */
	public void leavePlayer() {
		getPlayer().activate();
		getPlayer().setOther(null);
	}
	
	/**connects this to the frog,
	 * protects frog from water. */
	public void carryPlayer() {
		getPlayer().deactivate();
		getPlayer().setOther(this);
	}
	
	
	/**carries frog along when this is attached with the player
 	 *  @param delta the state of the screen.
	 *  @param rate the rate the Transportation is moving. */
	public void drivePlayer(int delta, float rate) {
		
		if (this == getPlayer().getOther()) {
			
			//move player to the left
			if (!this.getDirection()) {
				getPlayer().setX(getPlayer().getX() - delta * rate);
				
				/*keeps the player in bound with this*/
				if ( Math.abs(this.getBox().getRight() - 
						getPlayer().getBox().getLeft()) < 0.1) {
					getPlayer().setX(this.getBox().getRight() - HALF_SIZE);
				}
				
			    /*check if player is on screen*/
				getPlayer().checkBound();
			}
			
			//move player to the right
			else {
				getPlayer().setX(getPlayer().getX() + delta * rate);
				
				/*keeps the player in bound with this*/
			    if ( Math.abs(this.getBox().getLeft() - 
			    		getPlayer().getBox().getRight()) < 0.1) {
			    	getPlayer().setX(this.getBox().getLeft() + HALF_SIZE);
			    }
			    
			    /*check if player is on screen*/
			    getPlayer().checkBound();
			    
			}
		}
	}
	
	/**when visible on screen, check the connection to player */
	public void checkPlayerWhenVisible(int delta, float rate) {
		
		//if connected to player, carry player
		if ( this.contactSprite(getPlayer()) ) {
			carryPlayer();
			drivePlayer(delta, rate);
		}
		
		//leave player when no longer connected. 
		else if  ( !this.contactSprite(getPlayer()) && 
				getPlayer().getOther() == this ) {
			leavePlayer();
		}
	}
	
	
	/**affectsPlayer() won't function with Log, LongLog, and Turtles. */
	@Override
	public void affectsPlayer() {
	
	}
	/**default implementation of move for all Transportations
	 * @param input user keyboard input.
	 * @param delta the state of the screen. 
	 * @param rate the rate at which the object moves.
	 * @param offset the half-width of the Transportation object. */
	public void move(Input input, int delta, float rate, int offset) {

		/* updates vehicles going to left as well as their bounding box 
		 * accordingly */
	    if (! (this.getDirection()) ) {			
			this.setX(this.getX() - delta * rate);
			
			/*teleports to the other side of the screen */
			
			if (this.getBox().getRight() <= 0) {
			    this.setX(App.SCREEN_WIDTH + offset);
				
			}
			
		}
	    
		/* updates vehicles going to right as well as their bounding box 
		 * accordingly */
		else  {
		    this.setX(this.getX() + delta * rate);
			
			
			/*teleports to the other side of the screen */
			if (this.getBox().getLeft() >= App.SCREEN_WIDTH ) {
				
			    this.setX(-offset);
			}
			
		}
	}
			    
		    
			
		
		

}
