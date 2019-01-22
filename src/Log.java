import org.newdawn.slick.Input;

/**
 * @author gilbert
 * controls Log behaviour and interaction with player and ExtraLife.
 */
public class Log extends Transportation{
	/**Log image directory*/
	public static final String ADDRESS  = "assets/log.png";
	

	/*attachable to ExtraLife object*/
	private ExtraLife crate;
	
	/**attaches self to crate
	 * @param crate the ExtraLife object to be attached to. */
	public void setExtraLife(ExtraLife crate) {
		this.crate  = crate;
	}
	
	/** instantiates Log object at (x,y) with specific direction.
	  * @param x x coordinate of Log on the screen
	  * @param y y coordinate of Log on the screen
	  * @param direction specifies Log's direction */
	public Log(  float x, float y, boolean direction) {
		super(ADDRESS, x,y, WIDTH[LOG_INDEX], TILE_SIZE, direction);
	}
	
	/** instantiates Log-derived object (i.e. LongLog) 
	 * at (x,y) with specific direction.
	  * @param x x coordinate of Log object on the screen
	  * @param y y coordinate of Log object on the screen
	  * @param direction specifies Log object's direction
	  * @param imgSrc specifies Log object's image directory */
	public Log(String imgSrc, float x, float y, boolean direction) {
		super(imgSrc, x,y, WIDTH[LONG_LOG_INDEX], TILE_SIZE, direction);
	}
	
	/** moves itself  
	 * checks collision with player,
	 * drives the crate, if there is one. 
	 * @param input user keyboard input.
	 * @param delta the state of the screen.  */
	public void update(Input input, int delta) {
		move(input,delta, RATE[LOG_INDEX], WIDTH[LOG_INDEX] /2 );
		
		//handles collision with player,  
		checkPlayerWhenVisible(delta, RATE[LOG_INDEX]);
		
		//drives crate when exists.
		if (crate != null) {
			driveExtraLife(delta, RATE[LOG_INDEX]);
		}
	}
	@Override
	/**moves itself
	 * teleports to the other side of the screen when going out of the screen.	 
	 * drags the crate along with it. 
	 * @param input user keyboard input.
	 * @param delta the state of the screen.  */
	public void move(Input input, int delta, float rate, int offset) {

		/* updates vehicles going to left as well as their bounding box 
		 * accordingly */
	    if (! (this.getDirection()) ) {			
			this.setX(this.getX() - delta * rate);
			
			/*teleports to the other side of the screen */
			
			//when there is a crate attached, carries the crate along!
			if (crate!= null && this.getBox().getRight() <= 0) {
				float tmp = crate.getX() - this.getX();
			    this.setX(App.SCREEN_WIDTH + offset);
				crate.setX(this.getX() + tmp);
				
			}
			
			// else just teleports 
			else if  (this.getBox().getRight() <= 0) {
			    this.setX(App.SCREEN_WIDTH + offset);
			}
			
			
		}
	     
		/* updates vehicles going to right as well as their bounding box 
		 * accordingly */
		else   {
		    this.setX(this.getX() + delta * rate);
			
			
			/*teleports to the other side of the screen */
		    
			//when there is a crate attached, carries the crate along!
			if (crate!= null && this.getBox().getLeft() >= App.SCREEN_WIDTH ) {
				float tmp = crate.getX() - this.getX();
			    this.setX(-offset);
				crate.setX(this.getX() + tmp);
			}
			
			// else just teleports 
			else if  (this.getBox().getLeft() >= App.SCREEN_WIDTH ) {
			    this.setX(-offset);
			}
			
		}
	}
	
	
	
	/** drives the crate 
	 * @param rate the rate the ExtraLife will move at. 
	 * @param delta the state of the screen.  */
	public void driveExtraLife(int delta, float rate) {
		
		//drive to the right
		if (this.getDirection()) {
			crate.setX(crate.getX() + delta * rate);
			
		}
		
		//drive to the left
		else if (!this.getDirection()) {
			crate.setX(crate.getX() - delta * rate);
		}
		
	}
	
	
	/** carries ExtraLife (crate) 
	 *  @param crate the ExtraLife object to be carried. */
	public void carry(ExtraLife crate) {
		crate.attach(this);
		this.setExtraLife(crate);
	}
	
	/**detaches this from crate*/
	public void detach() {
		this.crate = null;	
	}
	
	/** gets the crate attached to this Log */
	public ExtraLife getCrate() {
		return this.crate;
	}
}
