import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.newdawn.slick.Input;

/**
 * @author gilbert
 * controls Extralife behaviour on screen and collision with player.
 */
public class ExtraLife extends MovingObject{
	/*attachable to log*/
	private Log other;
	
	/**ExtraLife Image directory*/
	public static final  String ADDRESS =  "assets/extralife.png"; 
	
	/**ExtraLife moves every 2 seconds*/
	public static final int RATE = 2;
	
	/*required time and duration of appearance*/
	
	/**ExtraLife delays appearance for at least 25 seconds*/
	public static final int MINIMUM = 25;
	/**ExtraLife appears for maximum of 14 seconds*/
	public static final int APPEAR = 14;
	/**ExtraLife delays appearance 25-35 seconds */
	public static final int GAP = 11;

	/*keeps track of the time */
	
	//time to move
	private  long time =System.currentTimeMillis();
	
	//time off the screen 
	private long offboard = System.currentTimeMillis(); 
	
	//time off the screen
	private long onboard = System.currentTimeMillis();
	
	/*indicates to render or not*/
	private boolean on = false;
	
	/*flags to indicate what to do next*/
	
	/**shut down flag*/
	public static final int SHUT = 1;
	/**move flag*/
	public static final int MOVE = 2;
	/**wake up flag*/
	public static final int TURN_ON = 3;
	/**do nothing flag*/
	public static final int NONE = 4;

	/**init delay time.*/
	public  final long INTERVAL = randomiseTime();
	
	/** instantiates Extralife object. */	
	public ExtraLife() {
		super(ADDRESS, 0, 0, true);	
		
		/*starts from off the screen*/
		this.deactivate();
	}
	
	
	/** picks a delay time to appear on the screen. 
	 * @return the randomised time */
	public  long randomiseTime(){
		Random rand = new Random();
		return (rand.nextInt(GAP) + MINIMUM) ;
	}

	/**disappears from the screen,
	 * recalculates time to be on the screen again. */
	public  void disappear() {
		/* set this self unrender-able*/
		this.deactivate();
		
		/*removes this connection to the Log object */
		other.detach();
		this.other = null;
		
		/*resets timer to get back on screen again later.*/
		offboard = System.currentTimeMillis();
	}
	
	/**goes back to  the screen,
	 * resets time for movement and appearance duration. */
	public  void reappear() {
		this.activate();
		this.setDirection(true);
		onboard = time = System.currentTimeMillis() ;
	}
	
	
	/** decides to disappear/reappear/move/none.
	 * @return APPEAR  to reappear on the screen,
	 * MOVE to move on the screen,
	 * SHUT to disappear from the screen,
	 * NONE to do nothing.
	 */
	public int changeState() {
		
		if (this.getStatus() ) {
			// too long stayed. bye.
			if ( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() 
					- onboard) == APPEAR ) {
				disappear();
				return SHUT;
			}
			
			// move when 2 seconds have passed. 
			else if (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() 
					- time) == RATE ) {
				time =  System.currentTimeMillis();
				return MOVE;
			}
			
			
		}

		else if (!this.getStatus()){
			// time to go back to the screen. 
			if ( TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() 
					- offboard))  == INTERVAL ) {
				reappear();
				return TURN_ON;
			}
		}

		/*if nothing else goes on*/
		return NONE;
		
	}
	
	/**picks a Log object to hop on.
	 *@param transport the Logs and LongLogs  that can be hopped on. */
	public void pickLog(ArrayList<Log> transport) {
		//checks possible indices
		int size = transport.size();
		
		//randomise index
		Random rand = new Random();
		int index = rand.nextInt(size) ;
		
		//choose Log and initalise position. 
		transport.get(index).carry(this);
		this.setPosition(other.getX(), other.getY());
		
	}
	
	/** moves to the left or right accordingly.
	 * @param input user keyboard input.
	 * @param delta the state of the screen. 
	 * @param rate the rate at which the ExtraLife moves.*/
	public void move(Input input, int delta, float rate) {
		
		/*  going to left */

		if (! (this.getDirection()) ) {	
			
			float tmp = this.getX() - other.getX();
			this.setX(this.getX()- rate);
			
			/*make sure still inbound with the log, 
			 * reverse direction at the edge of the log */
			if (!this.contactSprite(other)) {
				this.setX(other.getX() + tmp );
				this.reverse();
			    this.setX(this.getX() + rate);
			}
			
		}
		    
		/*  going to right */
		else  {
			float tmp = this.getX() - other.getX();
		    this.setX(this.getX() + rate);	
		    
			/*make sure still inbound with the log, 
			 * reverse direction at the edge of the log */
		    if (!this.contactSprite(other)) {
		    	
				this.setX(other.getX() + tmp );
				this.reverse();
			    this.setX(this.getX() - rate);
			}
		}	
    }
	
	/** moves to the right/left, and detects collision with player.
	 * @param input user keyboard input.
	 * @param delta the state of the screen.
	 * @param transport Log objects to be chosen upon reappearing. */
	public void update(Input input, int delta, ArrayList<Log> transport ) {
	
		//get status
		int status = changeState();


		//calls functions accordingly to status returned. 
		
		if (status ==MOVE ) {
			move(input, delta, TILE_SIZE);
		}
		
		else if (status ==SHUT ) {
			return;
		}
		
		else if (status ==TURN_ON) {			
			this.pickLog(transport);
			return;
		}
		
		// when on the screen, handles collisions with player.
		if( this.contactSprite(getPlayer()) && ( status == MOVE || 
				status == NONE) && this.getStatus() ) {
			affectsPlayer();
		}
		
	}
	
	
	/** attaches this self to a Log.
	 * @param other the chosen Log to be attached */
	public void attach(Log other) {
		this.other =  other;
		this.setX(other.getX());
		this.setY(other.getY());
	}
	
	/** detaches this self to a Log. */
	public void detach() {
		this.other = null;

	}
	
	/** gets the Log  attached
	 * @return the Log  attached
	 */
	public Sprite getOther() {
		return this.other;
	}
	
	/** reverses the direction
	 */
	public void reverse() {
		setDirection( !getDirection() );
	}
	
	@Override
	/**draws ExtraLife object only when 'on the screen'. */
	public void render() { 
		// draw center when 'on the screen'
		if(this.getStatus()) {
			this.getImage().drawCentered(this.getX(),this.getY());
		}
		
	} 
	
	@Override
	/**adds life to player, then disappear from screen. */
	public void affectsPlayer() {
		getPlayer().addLife();
		disappear();
	}
	
	/**allows to be rendered */
	public void activate() {
		this.on = true;
	}
	
	/**makes ExtraLife object not render-able*/
	public void deactivate() {
		this.on = false;
	}
	
	/**checks Extralife's state 
	 * @return ExtraLife's status (on the screen / not) */
	public boolean getStatus() {
		return this.on;
	}
	
	


	 
	
	
}
