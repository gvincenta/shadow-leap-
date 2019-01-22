import java.util.ArrayList;

import org.newdawn.slick.Input;

/**
 * @author gilbert
 * Handles when player reaches goal. 
 */
public class Goal extends Sprite{
	/*added  basic attributes */
	
	/** frog Image directory */
	public static final String ADDRESS = "assets/frog.png";
	/** width size */
	public static final int WIDTH= 144;
	/** height size */
	public static final int HEIGHT = 48;
	/** maximum goal */
	public static final int MAX = 5;
	
	//Goals can be filled
	private static int filledGoalCounter; 
	private boolean isFilled = false;
	
	/** x-coordinates */
	public static final float[] X_COORDINATES  = new float[] {120, 312, 504, 
			696, 888};
	
	/** y-coordinate */
	public static final float Y_COORDINATES = 48; 
	
	/*connects to frog*/
	private static  Frog player;
	
	/** instantiates Goal object at (x,y)
	 * @param x x-coordinate of Goal on the screen
	 * @param y y-coordinate of Goal on the screen */
	public Goal(float x, float y) {
		super(ADDRESS, x, y, WIDTH, HEIGHT);	
	}
	
	/** resets player or reduces player's life accordingly*/
	public void affectsPlayer() {
		
		/*filling the same hole again reduces player's life*/
		if (this.isFilled) {
			player.loseLife();
		}
		
		/*filling for the first time resets player to its initial position*/
		else {
			this.fill();
			player.reset();
			filledGoalCounter++;
			System.out.println(filledGoalCounter);
		}
	}
	
	/** checks if all goals are full. 
	 * @return true if full, false if not full yet. */
	public static boolean isFull() {
		if (filledGoalCounter == MAX) {
			return true;
		}
		return false;
	}
	
	/** make Goals on screen and connections to player.
	 * @param p the player to be connected to.
	 * @param sprites goal objects are added into the sprites arraylist.
	 */
	public static void makeGoals(ArrayList<Sprite> sprites, Frog p) {
		for (int i = 0; i < MAX; i++) {
			sprites.add( new Goal(X_COORDINATES[i],Y_COORDINATES));
		}
		player = p;
		filledGoalCounter = 0;
	}
	
	/** fills the goal. */
	public void fill() {
		this.isFilled = true;
	}
	
	/** renders frog image only when the goal is filled. */
	public void render() {
		if (this.isFilled) {
			getImage().drawCentered(this.getPosition().getX(),
					this.getPosition().getY());
		}
	}
	
	/** checks collision with player.  
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
		if (this.contactSprite(player)) {
			affectsPlayer();
		}
	}
	
}
