import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class World {
	private static final String LEVEL_SRC = "res/levels/0.lvl";
	
	int initialPlayerX;
	int initialPlayerY;
	
	int levelWidth;
	int levelHeight;
	int numTiles;
	int[] dimensions;
	
	float xOffset;
	float yOffset;
	
	// a map of items that the player cannot pass through
	boolean[][] collisionMap;
	// stores raw level data
	private Sprite[] level;
	// stores player
	private Player player;
	

	
	public World()
	throws SlickException
	{
		// loads raw level data
		level = Loader.loadSprites(LEVEL_SRC);
		
		dimensions = Loader.loadDimensions(LEVEL_SRC);
		levelWidth = dimensions[0];
		levelHeight = dimensions[1];
		// minus one since the last sprite is player
		numTiles = level.length - 1;
		
		// calculates offset values required for rendering
		xOffset = (App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE)) / 2;
		yOffset = (App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE)) / 2;
		
		// creates and populates collision map
		collisionMap = populateCollisionMap(level);
		
		// creates player and initialises position
		// assumes player is the last sprite in the level file, might need adjusting in future
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
		for (int i = 0; i < this.numTiles; i++) {
			level[i].render(g, xOffset, yOffset);
		}
		player.render(g, xOffset, yOffset);
	}
	
	// populates collision map array. true for wall tiles.
	private boolean[][] populateCollisionMap(Sprite[] map) {
		boolean[][] collisionMap = new boolean[levelWidth][levelHeight];
		
		for (int i = 0; i < this.numTiles; i++) {
			if (map[i].collider) {
				collisionMap[map[i].x][map[i].y] = true;
			}
		}
		
		return collisionMap;
	}
}