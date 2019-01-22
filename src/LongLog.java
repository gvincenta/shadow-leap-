import org.newdawn.slick.Input;
/**
 * @author gilbert
 * controls LongLog behaviour and interaction with player and ExtraLife.
 */
public class LongLog extends Log{
	/**LongLog image directory*/
	public static final String ADDRESS  = "assets/longlog.png";
	
	/** instantiates LongLog object at (x,y) with specific direction.
	  * @param x x coordinate of LongLog on the screen
	  * @param y y coordinate of LongLog on the screen
	  * @param direction specifies LongLog's direction */
	public LongLog(  float x, float y, boolean direction) {
		super(ADDRESS, x, y, direction);
	}
	
	@Override
	/** moves itself  
	 * checks collision with player,
	 * drives the crate, if there is one. 
	 * @param input user keyboard input.
	 * @param delta the state of the screen.  */
	public void update(Input input, int delta) {
		move(input,delta, RATE[LONG_LOG_INDEX], WIDTH[LONG_LOG_INDEX] /2 );
		
		//checks player
		checkPlayerWhenVisible(delta, RATE[LONG_LOG_INDEX]);
		
		/*move crate*/
		if (getCrate() != null) {
			driveExtraLife(delta, RATE[LONG_LOG_INDEX]);
		}
	}
	
}
