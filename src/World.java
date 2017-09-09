import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class World {
	private static final String LEVEL_SRC = "res/levels/0.lvl";
	
	private int initialPlayerX;
	private int initialPlayerY;
	
	// number of tiles in level not including player
	private int numTiles;
	
	private int levelWidth;
	private int levelHeight;
	
	// offset values used to render level in the center of the screen
	private float xOffset;
	private float yOffset;
	
	// a map of items that the player cannot pass through
	private boolean[][] collisionMap;

	private Sprite[] level;
	private Player player;
	
	public World()
	throws SlickException
	{
		// loads raw level data
		level = Loader.loadSprites(LEVEL_SRC);
		
		// loads level dimensions
		levelWidth = Loader.loadDimensions(LEVEL_SRC)[0];
		levelHeight = Loader.loadDimensions(LEVEL_SRC)[1];

		// minus one since the last sprite is player
		numTiles = level.length - 1;
		
		// calculates offset values required for rendering
		xOffset = (App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE)) / 2;
		yOffset = (App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE)) / 2;
		
		// creates and populates collision map
		collisionMap = populateCollisionMap(level);
		
		/** creates player and initialises position
		 * assumes player is the last sprite in the level file
		 * might need adjusting in future
		 */
		initialPlayerX = level[numTiles].x;
		initialPlayerY = level[numTiles].y;
		player = new Player(initialPlayerX, initialPlayerY);
	}

	public void update(Input input, int delta) 
	throws SlickException
	{
		player.update(input, delta, collisionMap);
	}
	
	public void render(Graphics g)
	throws SlickException 
	{
		// renders the level
		for (int i = 0; i < this.numTiles; i++) {
			level[i].render(g, xOffset, yOffset);
		}
		// renders the player
		player.render(g, xOffset, yOffset);
	}
	
	/** populates collision map array
	 * only true for wall tiles at the moment
	 * @param the array of tiles that make up the level
	 * @return an array of tiles which can/can't be passed through by the player
	 */
	private boolean[][] populateCollisionMap(Sprite[] level) {
		boolean[][] collisionMap = new boolean[levelWidth][levelHeight];
		
		for (int i = 0; i < this.numTiles; i++) {
			if (level[i].collider) {
				collisionMap[level[i].x][level[i].y] = true;
			}
		}
		
		return collisionMap;
	}
}