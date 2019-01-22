/**
 * @author gilbert
 * remaining life of player.
 */
public class Life extends Tile{
	/**Life image directory*/
	public static final String ADDRESS = "assets/lives.png" ;
	/**Life's y-coordinate*/
	public static final int YSTART = 744;
	/**Life's x-coordinate*/
	public static final int XSTART = 24;
	/**Life's image width*/
	public static final int WIDTH = 24;
	/**Life's image height*/
	public static final int HEIGHT = 24;
	/**each life is 32 pixels apart.*/
	public static final int STEP = 32;

	/** instantiates Life object	 
	 * @param num the life number to calculate the x coordinate. 
	 */
	public   Life(int num) {
		
		super(ADDRESS, (XSTART + (STEP * num)), YSTART );
	}
}
