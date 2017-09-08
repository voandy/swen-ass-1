import java.io.FileReader;
import java.util.Scanner;

import org.newdawn.slick.SlickException;

public class Loader {	
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return level[]
	 */
	public static Sprite[] loadSprites(String filename)
	throws SlickException
	{
		Sprite[] level = new Sprite[App.MAX_TILES];
        try (Scanner scanner = new Scanner(new FileReader(filename))) {
        	
        	// The first sprite in the array contains level dimensions
        	level[0] = textToDimensions(scanner.nextLine());
        	
        	int i = 1;
            while (scanner.hasNextLine()) {
                level[i] = textToSprite(scanner.nextLine());
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return level;
	}
	
	// parses a line from levels CSV into a Sprite 
	private static Sprite textToSprite(String line) 
	throws SlickException
	{
		Sprite sprite;
		String[] properties = line.split(",");
		sprite = new Sprite(properties[0], Integer.parseInt(properties[1]), Integer.parseInt(properties[2]));
		
		return sprite;
	}
	
	// parses first line from CSV into Sprite containing map dimensions
	private static Sprite textToDimensions(String line) 
	throws SlickException
	{
		Sprite sprite;
		String[] properties = line.split(",");
		sprite = new Sprite("player", Integer.parseInt(properties[0]), Integer.parseInt(properties[1]));
		
		return sprite;
	}

}