import org.newdawn.slick.Input;
/**
 * Handles Water tiles init, update and render. 
 * Handles Water colliding with player.
 */
public class Water extends Sprite{
	/** image directory for water */
	public final static String ADDRESS = "assets/water.png"; 
	
	private static Frog player;

	/**sets player to all Water objects. 
	 * @param p the player object to be attached. */
	public static void setPlayer(Frog p) {
		player = p;
	}
	
	/** instantiates Water object at (x,y).
	  * @param x x-coordinate of Water on the screen
	  * @param y y-coordinate of Water on the screen*/
	public Water(float x, float y) {
		super(ADDRESS, x,y, TILE_SIZE, TILE_SIZE);
	}
	
	/**checks if player hits water when it's not carried by anything else */
	public boolean hitWhenPlayerActive() {
		// hits only when player is 'detectable'
		if (player.getStatus() && this.contactSprite(player)) {
	    	return true;
		}
		
		return false;
	}
	
	@Override
	/**checks collision with player.
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
		if(hitWhenPlayerActive()) {
			affectsPlayer();
		}
	}
	
	/**makes player lose life*/
	public void affectsPlayer() {
		player.loseLife();
	}
}
