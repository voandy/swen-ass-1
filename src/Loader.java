import java.io.FileReader;
import java.util.Scanner;

import org.newdawn.slick.SlickException;

public class Loader {	
	private static final int NO_DIMENSIONS = 2;
	
	// loads the map dimensions from the level file
	public static int[] loadDimensions(String filename) {
		int[] dimensions = new int[NO_DIMENSIONS];
		String[] line = new String[NO_DIMENSIONS];
		
		try (Scanner scanner = new Scanner(new FileReader(filename))) {
			line = scanner.nextLine().split(",");
		} 
		catch (Exception e) {
            e.printStackTrace();
        }
		
		dimensions[0] = Integer.parseInt(line[0]);
		dimensions[1] = Integer.parseInt(line[1]);
		
		return dimensions;
	}
	
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return level[]
	 */
	public static Sprite[] loadSprites(String filename)
	throws SlickException
	{
		Sprite[] data = new Sprite[App.MAX_TILES];
		Sprite[] level;
		int count = 0;
		
        try (Scanner scanner = new Scanner(new FileReader(filename))) {
        	
        	// skips the first line which only contains dimensions
        	scanner.nextLine();
        	
        	int i = 0;
            while (scanner.hasNextLine()) {
                data[i] = textToSprite(scanner.nextLine());
                i++;
            }
            count = i;
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Copies level data into a smaller array of known size
        level = new Sprite[count];
        for (int i = 0; i < count; i++) {
        	level[i] = data[i];
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
}