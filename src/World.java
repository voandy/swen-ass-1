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
	
	float xOffset;
	float yOffset;
	
	// a map of items that the player cannot pass through
	boolean[][] collisionMap;
	
	// stores raw level data
	private Sprite[] level;
	// strors map spites only
	private Sprite[] map;
	// stores player
	private Player player;
	

	
	public World()
	throws SlickException
	{
		// loads raw level data
		level = Loader.loadSprites(LEVEL_SRC);
		
		// extracts map properties from level data
		levelWidth = (int) level[0].x;
		levelHeight = (int) level[0].y;
		numTiles = getNumTiles(level);
		
		// calculates offset values required for rendering
		xOffset = (App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE)) / 2;
		yOffset = (App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE)) / 2;
		
		// extracts map data from level data
		map = levelToMap(level, App.TILE_SIZE, levelWidth, levelHeight);
		
		// creates and populates collision map
		collisionMap = populateCollisionMap(map);
		
		// creates player and initialises position
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
			map[i].render(g, xOffset, yOffset);
		}
		player.render(g, xOffset, yOffset);
	}
	
	// creates a map array from raw level data and extracts initial player position
	private Sprite[] levelToMap(Sprite[] level, int tileSize, int levelWidth, int levelHeight) {
		map = new Sprite[this.numTiles];
		
		int i;
		for (i = 0; i < this.numTiles; i++) {
			map[i] = level[i + 1];
		}
		
		// assumes player position is the last line of level file
		initialPlayerX = level[i + 2].x;
		initialPlayerY = level[i + 2].y;
		
		return map;
	}
	
	// returns the number of tiles in loaded level
	private int getNumTiles(Sprite[] level) {
		int i = 0;
		while(level[i] != null && i < App.MAX_TILES) {
			i++;
			if (i == App.MAX_TILES) {
				throw new RuntimeException("Tile limit reached.");
			}
		}
		// minus 3 since the first sprite is dimensions and the last is player
		return i - 3;
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