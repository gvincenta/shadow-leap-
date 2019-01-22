/**
 * @author gilbert
 * defines each object's position on screen.
 */
public class Position {
	
	private float x, y;
	
	/*basic getter, setter and constructor */
	
	/** sets x-coordinate
	 * @param x x-coordinate of the object */
	public void setX (float x){
		this.x = x;
	}
	/** sets y-coordinate
	 * @param y y-coordinate of the object */
	public void setY (float y){
		this.y = y;
	}
	/** @return x-coordinate*/
	public float getX (){
		return this.x;
	}
	/** @return y-coordinate*/
	public float getY (){
		return this.y;
	}
	/**Instantiates a position at (x,y) 
	  * @param x x coordinate of MovingObject object on the screen.
	  * @param y y coordinate of MovingObject object on the screen.*/
	public Position(float x, float y) {
		setX(x);
		setY(y);
	}
}

