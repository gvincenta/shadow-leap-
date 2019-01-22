import org.newdawn.slick.Input;

/**
 * @author gilbert
 * Bulldozer class to handle Bulldozer's movements and collision with player.
 */
public class Bulldozer extends MovingObject {
	/* the additional attributes */
	/**Bulldozer's image directory */
	public static final  String ADDRESS =  "assets/bulldozer.png"; 
	/**Bulldozer's movement rate */
	public static final float RATE = 0.05f; 
	

	
	/** instantiates Bulldozer object at (x,y) with specific direction.
	  * @param x x coordinate of Bulldozer on the screen
	  * @param y y coordinate of Bulldozer on the screen
	  * @param direction specifies Bulldozer's direction */	
	public Bulldozer(float x, float y,boolean direction) {
		super(ADDRESS, x, y, direction);		
	}
	
	
	@Override
	/** pushes player accordingly, makes player cannot go to Bulldozer's tile.
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
    	
		move(input, delta, RATE);
		
		
    	if (this.contactSprite(getPlayer()) ) {   
    		//push player when next to Bulldozer.
    		if ( (this.getDirection() && getPlayer().getBox().getLeft() 
    			<= this.getBox().getRight() ) || ( !this.getDirection() 
    			&& getPlayer().getBox().getRight() >= this.getBox().getLeft()) ) 
    		{
            	push(delta);
        	}
    		//prevents player going into Bulldozer.
    		avoidsPlayer(input);
        	
    	}
	}
	
	/**prevents player from going on to bulldozer's tile
	 * @param input user keyboard input. */
	public void avoidsPlayer(Input input) {
		
    	//sets back player to where it came from 

    	if (input.isKeyDown(Input.KEY_UP)) {
    		getPlayer().setY(this.getBox().getBottom() + HALF_SIZE);
    	}
    		
    	if (input.isKeyDown(Input.KEY_DOWN)) {
    		getPlayer().setY(this.getBox().getTop() - HALF_SIZE);
    	}
    	if (input.isKeyDown(Input.KEY_RIGHT)) {
    		getPlayer().setX(this.getBox().getLeft() - HALF_SIZE);
    	}
    	if (input.isKeyDown(Input.KEY_LEFT)) {
    		getPlayer().setX(this.getBox().getRight() + HALF_SIZE);
    	}
	}
	
	/** push player when player is next to bulldozer
	 * @param delta the state of the screen. */
    public void push(int delta ) {
    	
    	/*pushes player to the right along with the bulldozer.*/	
    	if (this.getDirection()) {
    		getPlayer().setX(getPlayer().getX() + (delta * RATE)) ;
    		
    		/*reduces player's life when it's going off the screen.*/
        	if (getPlayer().getBox().getRight() >= App.SCREEN_WIDTH) {
       			affectsPlayer();
       		}
   		}
    	
    	/*pushes player to the left along with the bulldozer.*/	
    	else {
    		getPlayer().setX(getPlayer().getX() - (delta * RATE)) ;
    		
    		/*reduces player's life when it's going off the screen.*/
        	if (getPlayer().getBox().getLeft() <= 0) {
        		affectsPlayer();
        	} 	
    	}
    	
    }
}
