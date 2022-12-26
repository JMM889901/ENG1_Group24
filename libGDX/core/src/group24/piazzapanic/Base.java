package group24.piazzapanic;

import java.util.Scanner;
import java.io.File;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** Core values like screen size, that will need to access, but nothing will need to modify.
 * THIS CLASS *MUST* HAVE NO DEPENDENCIES ON OTHER CLASSES IN THIS PROJECT.
 */
public class Base {
    public static String CONFIG_PATH = "config.txt";

    public static int WINDOW_WIDTH = 100;
    public static int WINDOW_HEIGHT = 100;

    public static SpriteBatch batch;

    /** Read the config file and set the values of the variables in this class.
     * This method should be called before any other code runs.
     */
    public static void init() {
        // This has just been spat out by Copilot, so we definitely need to refactor it for plagiarism reasons lol.
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
    }
}
