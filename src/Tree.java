import org.newdawn.slick.Input;

/**
 * @author gilbert
 * draws tree objects and avoids player going into trees.
 */
public class Tree extends Sprite {
	
	/** image directory of Tree*/
	public static final  String ADDRESS = "assets/tree.png";
	
	private static Frog player;
	
	/*basic getter, setter and constructor */

	/**lets all Tree objects to be attached to  the same player.
	 * @param p the player to be attached with.*/
	public static void setPlayer(Frog p) {
		player = p;
	}
	/** @return  the player attached. */
	public static Frog getPlayer() {
		return player;
	}
	
	/** instantiates Tree object at (x,y).
	  * @param x x coordinate of Tree on the screen
	  * @param y y coordinate of Tree on the screen*/
	public Tree(float x, float y) {
		super(ADDRESS, x, y, TILE_SIZE, TILE_SIZE);
	}
	
	@Override
	/**checks collision with player.
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
    	
    	if (this.contactSprite(getPlayer()) ) { 
    		avoidsPlayer();
    	}
	}
	
	/**prevents player going into Tree tiles
	 * @param input user keyboard input. */
	public void avoidsPlayer() {
		//put player back down. 
    	getPlayer().setY(this.getBox().getBottom() + HALF_SIZE);
	}
}
