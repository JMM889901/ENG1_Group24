package group24.piazzapanic;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.Input.Keys;

import group24.piazzapanic.Physics.AnimatedMovable;
import group24.piazzapanic.game.Player;
import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.levelElements.IngredientType;
import group24.piazzapanic.ui.StageAnimation;

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

    public static StageAnimation initialChef1Animation;
    public static StageAnimation initialChef2Animation;
    public static HashMap<String, Animation<TextureRegion>> chef1Animations;
    public static HashMap<String, Animation<TextureRegion>> chef2Animations;

    public static Texture debugSquareTexture;

    public static Texture floorTexture;
    public static Texture bakingStationTexture;
    public static Texture counterTopTexture;
    public static Texture counterRightCornerTexture;
    public static Texture counterRightTexture;
    public static Texture counterEndTexture;
    public static Texture cuttingStationTexture;
    public static Texture fryingStationTexture;
    public static Texture ingredientStationTexture;
    public static Texture tomatoStationTexture;
    public static Texture onionStationTexture;
    public static Texture lettuceStationTexture;
    public static Texture breadStationTexture;
    public static Texture meatStationTexture;
    public static Texture dishStationTexture;
    public static Texture dishTexture;

    public static Texture obstacleTexture;

    public static Texture errorTexture;
    // Ingredient textures
    public static Texture rawTomatoTexture;
    public static Texture cutTomatoTexture;
    public static Texture rawOnionTexture;
    public static Texture cutOnionTexture;
    public static Texture friedOnionTexture;
    public static Texture rawLettuceTexture;
    public static Texture cutLettuceTexture;
    public static Texture rawBreadTexture;
    public static Texture cutBreadTexture;
    public static Texture friedBreadTexture; // Do we want to fry/toast the bread?
    public static Texture rawMeatTexture;
    public static Texture cutMeatTexture;
    public static Texture friedMeatTexture; // Do we want to have a Burger texture too?

    // Don't declare any unused textures otherwise gradle just inexplicably dies permanently.
    public static final int UP_KEY = Keys.W;
    public static final int DOWN_KEY = Keys.S;
    public static final int LEFT_KEY = Keys.A;
    public static final int RIGHT_KEY = Keys.D;
    public static final int ACT_KEY = Keys.F; // Interact with a station
    public static final int PICKUP_KEY = Keys.E; // Pickup/putdown items 
    public static final int SELECT_KEY = Keys.ENTER;
    public static final int SWAP_KEY = Keys.Q;

    //ingredients with states to use in recipes
    public static final Ingredient CHOPPED_TOMATO = new Ingredient(IngredientType.TOMATO, 1, -1, -1);
    public static final Ingredient CHOPPED_LETTUCE = new Ingredient(IngredientType.LETTUCE, 1, -1, -1);
    public static final Ingredient CHOPPED_ONION = new Ingredient(IngredientType.ONION, 1, -1, -1);
    public static final Ingredient BURGER_BUN = new Ingredient(IngredientType.BREAD, 1, -1, -1);
    public static final Ingredient BURGER = new Ingredient(IngredientType.MEAT, 1, -1, 1);
    
    // SELECT_KEY just clicks the "first" option in a given menu. Useful to ignore UI bugs when
    // developing non-UI features.

    /**
     * Read the config file and set the values of the variables in this class.
     * This method should be called before any other code runs.
     */
    public static void init() {
        // Read config file.
        try {
            Scanner scanner = new Scanner(new File(CONFIG_PATH));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.strip().equals("")) {
                    continue;
                }

                String[] split = line.split("=");
                if (split.length != 2) {
                    System.out.println("Invalid line syntax: '" + line + "'.");
                    if (split.length == 1) {
                        throw new Exception("Invalid config file - no argument supplied.");
                    } else if (split.length > 2) {
                        throw new Exception("Invalid config file - too many arguments.");
                    } else {
                        throw new Exception("Invalid config file - bad length array (" + split.length + ").");
                    }
                }
                String left = split[0];
                String right = split[1];

                if (left.equals("resolution")) {
                    String[] resolutionStr = right.split("x");
                    if (resolutionStr.length != 2) {
                        System.out.println("Bad resolution: '" + right + "'.");
                        if (resolutionStr.length == 1) {
                            throw new Exception("Invalid config file - only one dimension supplied.");
                        } else if (resolutionStr.length > 2) {
                            throw new Exception("Invalid config file - too many dimensions supplied.");
                        } else {
                            throw new Exception("Invalid config file - bad dimensions (" + resolutionStr.length
                                    + " parameters given).");
                        }
                    }

                    // Check if the strings are numeric.
                    for (int i = 0; i < resolutionStr[0].length(); i++) {
                        if (!Character.isDigit(resolutionStr[0].charAt(i))) {
                            System.out.println("Bad Dimension: '" + resolutionStr[0] + "'.");
                            throw new Exception("Invalid config file - non-numeric width.");
                        }
                    }
                    for (int i = 0; i < resolutionStr[1].length(); i++) {
                        if (!Character.isDigit(resolutionStr[1].charAt(i))) {
                            System.out.println("Bad Dimension: '" + resolutionStr[1] + "'.");
                            throw new Exception("Invalid config file - non-numeric height.");
                        }
                    }

                    WINDOW_WIDTH = Integer.parseInt(resolutionStr[0]);
                    WINDOW_HEIGHT = Integer.parseInt(resolutionStr[1]);
                } else {
                    System.out.println("Unknown key: '" + left + "=...' .");
                    throw new Exception("Invalid config file - unknown key");
                }
            }
            scanner.close();
        } catch (Exception exception) {
            System.out.println("Error reading config file, Using default settings");
            WINDOW_WIDTH = 1280;
            WINDOW_HEIGHT = 720;
            exception.printStackTrace();
        }

        tile_pixel_width = (int) Math.floor(TILE_GRID_UNIT * WINDOW_WIDTH);
        tile_pixel_height = (int) Math
                .round(tile_pixel_width * ((double) Base.TILE_TEXTURE_HEIGHT) / Base.TILE_TEXTURE_WIDTH);

        batch = new SpriteBatch();
        //Chef animations
        chef1Animations = new HashMap<String, Animation<TextureRegion>>();
        chef1Animations.put("IdleFrontSelected",
                StageAnimation.makeAnimation("chef/chef_idle_front_selected.png", 6, 1, 6));
        chef1Animations.put("IdleBackSelected",
                StageAnimation.makeAnimation("chef/chef_idle_back_selected.png", 6, 1, 6));
        chef1Animations.put("IdleLeftSelected",
                StageAnimation.makeAnimation("chef/chef_idle_left_selected.png", 6, 1, 6));
        chef1Animations.put("IdleRightSelected",
                StageAnimation.makeAnimation("chef/chef_idle_right_selected.png", 6, 1, 6));
        chef1Animations.put("IdleFront", StageAnimation.makeAnimation("chef/chef_idle_front.png", 6, 1, 6));
        chef1Animations.put("IdleBack", StageAnimation.makeAnimation("chef/chef_idle_back.png", 6, 1, 6));
        chef1Animations.put("IdleLeft", StageAnimation.makeAnimation("chef/chef_idle_left.png", 6, 1, 6));
        chef1Animations.put("IdleRight", StageAnimation.makeAnimation("chef/chef_idle_right.png", 6, 1, 6));
        chef1Animations.put("Front", StageAnimation.makeAnimation("chef/chef_walk_front.png", 6, 1, 6));
        chef1Animations.put("Back", StageAnimation.makeAnimation("chef/chef_walk_back.png", 6, 1, 6));
        chef1Animations.put("Left", StageAnimation.makeAnimation("chef/chef_walk_left.png", 6, 1, 6));
        chef1Animations.put("Right", StageAnimation.makeAnimation("chef/chef_walk_right.png", 6, 1, 6));

        chef2Animations = new HashMap<String, Animation<TextureRegion>>();
        chef2Animations.put("IdleFrontSelected",
                StageAnimation.makeAnimation("chef/chef_1_idle_front_selected.png", 6, 1, 6));
        chef2Animations.put("IdleBackSelected",
                StageAnimation.makeAnimation("chef/chef_1_idle_back_selected.png", 6, 1, 6));
        chef2Animations.put("IdleLeftSelected",
                StageAnimation.makeAnimation("chef/chef_1_idle_left_selected.png", 6, 1, 6));
        chef2Animations.put("IdleRightSelected",
                StageAnimation.makeAnimation("chef/chef_1_idle_right_selected.png", 6, 1, 6));
        chef2Animations.put("IdleFront", StageAnimation.makeAnimation("chef/chef_1_idle_front.png", 6, 1, 6));
        chef2Animations.put("IdleBack", StageAnimation.makeAnimation("chef/chef_1_idle_back.png", 6, 1, 6));
        chef2Animations.put("IdleLeft", StageAnimation.makeAnimation("chef/chef_1_idle_left.png", 6, 1, 6));
        chef2Animations.put("IdleRight", StageAnimation.makeAnimation("chef/chef_1_idle_right.png", 6, 1, 6));
        chef2Animations.put("Front", StageAnimation.makeAnimation("chef/chef_1_walk_front.png", 6, 1, 6));
        chef2Animations.put("Back", StageAnimation.makeAnimation("chef/chef_1_walk_back.png", 6, 1, 6));
        chef2Animations.put("Left", StageAnimation.makeAnimation("chef/chef_1_walk_left.png", 6, 1, 6));
        chef2Animations.put("Right", StageAnimation.makeAnimation("chef/chef_1_walk_right.png", 6, 1, 6));

        initialChef1Animation = new StageAnimation(chef1Animations.get("IdleFrontSelected"), 6, 6, 1, 0, 0,
                Player.TEXTURE_WIDTH, Player.TEXTURE_HEIGHT);
        initialChef2Animation = new StageAnimation(chef2Animations.get("IdleFrontSelected"), 6, 6, 1, 0, 0,
                Player.TEXTURE_WIDTH, Player.TEXTURE_HEIGHT);
        debugSquareTexture = new Texture("debugsquare.png");

        // Load station textures.
        floorTexture = new Texture("stations/kitchen_floor_2.png");
        bakingStationTexture = new Texture("stations/baking_station_closed.png");
        counterTopTexture = new Texture("stations/countertop.png");
        counterRightCornerTexture = new Texture("stations/countertop_rightcorner.png");
        counterRightTexture = new Texture("stations/counterside.png");
        counterEndTexture = new Texture("stations/counterend.png");
        cuttingStationTexture = new Texture("stations/cutting_station.png");
        fryingStationTexture = new Texture("stations/frying_station_off.png");
        ingredientStationTexture = new Texture("stations/ingredientstation.png");
        tomatoStationTexture = new Texture("stations/BLOOD-FOR-THE-MEAT-GOD.png");
        onionStationTexture = new Texture("stations/ingredientstation.png");
        lettuceStationTexture = new Texture("stations/lettuce_sack.png");
        breadStationTexture = new Texture("stations/ingredientstation.png");
        meatStationTexture = new Texture("stations/ingredient_station_meat.png");
        dishStationTexture = new Texture("stations/ingredient_station_plate.png");

        obstacleTexture = new Texture("stations/wall.png");

        // Ingredient Textures
        rawTomatoTexture = new Texture("ingredients/tomato.png");
        cutTomatoTexture = new Texture("ingredients/chopped_tomato.png");
        rawOnionTexture = new Texture("stations/sourceerr.png");
        cutOnionTexture = new Texture("stations/sourceerr.png");
        friedOnionTexture = new Texture("stations/sourceerr.png");
        rawLettuceTexture = new Texture("ingredients/lettuce.png");
        cutLettuceTexture = new Texture("ingredients/chopped_lettuce.png");
        rawBreadTexture = new Texture("stations/sourceerr.png");
        cutBreadTexture = new Texture("stations/sourceerr.png");
        friedBreadTexture = new Texture("stations/sourceerr.png");
        rawMeatTexture = new Texture("ingredients/raw_meat.png");
        cutMeatTexture = new Texture("ingredients/raw_burger.png");
        friedMeatTexture = new Texture("ingredients/burger.png");
        dishTexture = new Texture("ingredients/plate.png");

        errorTexture = new Texture("stations/sourceerr.png");
    }

    /**
     * Basically the opposite of init (without the config file reading), this method should be the
     * last thing called before the program exits.
     */
    public static void dispose() {
        batch.dispose();

        debugSquareTexture.dispose();

        floorTexture.dispose();
        bakingStationTexture.dispose();
        counterTopTexture.dispose();
        cuttingStationTexture.dispose();
        fryingStationTexture.dispose();
        ingredientStationTexture.dispose();
        obstacleTexture.dispose();
        dishStationTexture.dispose();

        errorTexture.dispose();
    }
}
