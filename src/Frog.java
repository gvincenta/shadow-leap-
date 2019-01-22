import java.util.ArrayList;

import org.newdawn.slick.Input;


/**
 * @author gilbert
 * Frog class to create player object and handles player's behaviour. 
 */
public class Frog extends Sprite{

	/**player's initial x-coordinate */
	 public static final  int XSTART = 512;
	 
	 /**player's initial y-coordinate */
	 public final static int YSTART = 720;
	 
	 /**player's Image directory */
	 public final static String ADDRESS =  "assets/frog.png";
	 
	 //player has a few lives. 
	 private ArrayList<Life> life;
	 private int lifeCounter = 0;
	 
	 // player can be protected by a Transportation.
	 private boolean active = true; 
	 private Transportation other= null; 
	 
	 /** player starts with 3 lives*/
	 public static final int INIT_LIVES = 3;
	 
	 /**instantiates a new Frog.
	  * @param x: x coordinate of frog on the screen
	  * @param y: y coordinate of frog on the screen*/
	 public Frog(float x, float y) {
	     super(ADDRESS, XSTART, YSTART, TILE_SIZE, TILE_SIZE);
	     initLife();
	 }
	 
	 /** attaches frog to the "Transportation" objects (Log/LongLog/Turtle)
	  * @param other the rideable object the player will be attached to. 
	  */
	public void attach(Transportation other) {
			this.other = other;
	}
	
	/** detaches frog from the rideable objects */
	public void detach() {
		this.other = null;
	}
	
	/**moves the player along the screen accordingly.
	 * @param input the keyboard input from user.
	 * @param delta the state of the screen.
	 * @param rate the rate at which the frog moves. */
	public void move(Input input, int delta, float rate) {

		/* go up 48 pixels if up is pressed */
		if (input.isKeyPressed(Input.KEY_UP)) {
			this.setY(this.getY() - rate);	
		}
		
		/*go down 48 pixels if down is pressed */
		else if (input.isKeyPressed(Input.KEY_DOWN)) {	
	        this.setY(this.getY() + rate);					
		}
		
		/*go left 48 pixels if left is pressed */
		else if (input.isKeyPressed(Input.KEY_LEFT)) {
			this.setX(this.getX() -rate);			
		}
		
		/*go right 48 pixels if right is pressed */
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			this.setX(this.getX() + rate);			
		}

		this.checkBound();

	}
	
	/**make sure frog doesn't go away from the screen. */
	public void checkBound() {
		/* avoids going off the screen */
		
		if (App.SCREEN_WIDTH <= this.getBox().getRight() ) {
			this.setX(App.SCREEN_WIDTH - HALF_SIZE);
		}
		
	
		if (this.getBox().getLeft()  <= 0) {
			this.setX(HALF_SIZE);
		}
		if (this.getBox().getBottom() >= (App.SCREEN_HEIGHT)) {
	        this.setY(App.SCREEN_HEIGHT - HALF_SIZE);
		}
		if (this.getBox().getTop()  < 0) {
			this.setY(HALF_SIZE);
		}	
	}
	
	/**moves frog object accordingly. 
	 * @param input the keyboard input from user.
	 * @param delta the state of the screen.*/
	public void update(Input input, int delta) {
		move(input, delta, TILE_SIZE);
	}
	
	/**adds more lives to frog object. */
	public void addLife() {
		this.life.add(new Life (lifeCounter++));
	}
	
	/**reduces frog's life by 1. 
	 * game ends when  frog's remaining lives is below 0.
	 * then put back frog to its initial position.  */
	public void loseLife() {
		lifeCounter--;
		
		/*checks remaining lives*/
		if (lifeCounter < 0) {
			System.exit(0);
		}
		
		/* reduces Life object */
		this.life.remove(lifeCounter);
		
		/* then put back frog to its initial position. */
		this.reset();
		
	}
	
	/**resets frog's position to (x,y): (512,720) */
	public void reset() {		
		this.setX(XSTART);
		this.setY(YSTART);
	}
	
	
	@Override
	/**renders frog's image and its remaining lives */
	public void render() { 
		// draw frog
		this.getImage().drawCentered(
				this.getPosition().getX(),this.getPosition().getY());
		
		//draw remaining lives 
		for (Life item: life) {
			item.render();
		}
	}
	
	/** instantiates life attribute, and adds 3 lives to frog object. */
	public void initLife() {
		life = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
	    	 addLife();
	     }
	}
	/** makes frog 'detectable' by water */
	public void activate() {
		this.active = true;
	}
	
	/** makes frog 'undetectable' by water */
	public void deactivate() {
		this.active = false;
	}
	
	/** gets frog's status (active or not)
	 * @return boolean: frog's status */
	public boolean getStatus() {
		return this.active;
	}
	
	/** sets  frog's status accordingly
	 * @param active: intended status for frog object */
	public void setStatus(boolean active) {
		this.active = active;
	}
	
	/**gets the Transportation object the frog is currently attached to. 
	 */
	public Transportation getOther() {
		return this.other;
	}
	
	/**attaches frog to the intended Transportation object.
	 * @param other the Transportation object to be attached. */
	public void setOther(Transportation other) {
		this.other = other;
	}

	
	
}
