package group24.piazzapanic;

import java.util.Scanner;
import java.io.File;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Keys;


/** Core values like screen size, that will need to access, but nothing will need to modify.
 * THIS CLASS *MUST* HAVE NO DEPENDENCIES ON OTHER CLASSES IN THIS PROJECT.
 */
public class Base {
    public static final boolean DEBUG = false;
    public static String CONFIG_PATH = "config.txt";

    public static int WINDOW_WIDTH = 100;
    public static int WINDOW_HEIGHT = 100;

    // These 2 numbers below work best if they make a nice ratio (eg 1:2).
    public static final int TILE_TEXTURE_WIDTH = 100;
    public static final int TILE_TEXTURE_HEIGHT = 200;
    public static final float TILE_GRID_UNIT = 0.05f; // 20 tiles fit within one screen width.
    public static int tile_pixel_width;
    public static int tile_pixel_height;

    public static SpriteBatch batch;

    public static Texture tempChefTexture;
    public static Texture debugSquareTexture;

    public static Texture floorTexture;
    public static Texture bakingStationTexture;
    public static Texture counterTopTexture;
    public static Texture cuttingStationTexture;
    public static Texture fryingStationTexture;
    public static Texture ingredientStationTexture;
    public static Texture obstacleTexture;

    public static Texture errorTexture;
    // Don't declare any unused textures otherwise gradle just inexplicably dies permanently.
    
    // This is a placeholder but can be changed just once if we change our mind since it's global.
    public static final int UP_KEY = Keys.W;
    public static final int DOWN_KEY = Keys.S;
    public static final int LEFT_KEY = Keys.A;
    public static final int RIGHT_KEY = Keys.D;
    public static final int ACT_KEY = Keys.F;


    /** Read the config file and set the values of the variables in this class.
     * This method should be called before any other code runs.
     */
    public static void init() {
        // Read config file.
        try {
            File configFile = new File(CONFIG_PATH);
            Scanner configScanner = new Scanner(configFile);
            while (configScanner.hasNextLine()) {
                String line = configScanner.nextLine();
                if (line.strip().equals("")) {
                    continue;
                }
                String[] lineSplit = line.split("=");
                if (lineSplit.length != 2) {
                    System.out.println("Invalid syntax: " + line);
                    throw new Exception("Invalid config file - key without value");
                }
                String key = lineSplit[0];
                String value = lineSplit[1];

                if (key.equals("resolution")) {
                    String[] resolutionSplit = value.split("x");
                    if (resolutionSplit.length != 2) {
                        throw new Exception("Invalid config file - too many arguments for resolution");
                    }
                    WINDOW_WIDTH = Integer.parseInt(resolutionSplit[0]);
                    WINDOW_HEIGHT = Integer.parseInt(resolutionSplit[1]);
                } else {
                    System.out.println("Unknown key: " + key);
                    throw new Exception("Invalid config file - unknown key");
                }
            }
            configScanner.close();
        } catch (Exception exception) {
            System.out.println("Error reading config file, Using default settings");
            WINDOW_WIDTH = 1280;
            WINDOW_HEIGHT = 720;
            exception.printStackTrace();
        }
        
        tile_pixel_width = (int) Math.floor(TILE_GRID_UNIT * WINDOW_WIDTH);
        tile_pixel_height = (int) Math.round(tile_pixel_width * ((double) Base.TILE_TEXTURE_HEIGHT) / Base.TILE_TEXTURE_WIDTH);


        batch = new SpriteBatch();

        tempChefTexture = new Texture("chef-idle/chef_idle_1.png");
        debugSquareTexture = new Texture("debugsquare.png");

        // Load station textures.
        floorTexture = new Texture("stations/floor.png");
        bakingStationTexture = new Texture("stations/bakingstation.png");
        counterTopTexture = new Texture("stations/countertop.png");
        cuttingStationTexture = new Texture("stations/notimplemented.png");
        fryingStationTexture = new Texture("stations/notimplemented.png");
        ingredientStationTexture = new Texture("stations/notimplemented.png");
        obstacleTexture = new Texture("stations/wall.png");

        errorTexture = new Texture("stations/err.png");
    }

    public static void dispose() {
        batch.dispose();

        tempChefTexture.dispose();
        debugSquareTexture.dispose();

        floorTexture.dispose();
        bakingStationTexture.dispose();
        counterTopTexture.dispose();
        cuttingStationTexture.dispose();
        fryingStationTexture.dispose();
        ingredientStationTexture.dispose();
        obstacleTexture.dispose();
        
        errorTexture.dispose();
    }
}
