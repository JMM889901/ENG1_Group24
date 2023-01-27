package group24.piazzapanic.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.levelElements.IngredientType;
import group24.piazzapanic.ui.StageAnimation;

import com.badlogic.gdx.audio.Music;

/*
 * Globally accessible functions and variables should be stored here.
 */
public class GameData {
    public static GameLoop gameLoop;
    public static Integer score = 0;
    public static ArrayList<Customer> customers;
    public static float gameTime;
    public static float sinceLastSpawn;
    public static Level level;
    public static Player player;
    public static Player player1;
    public static Player player2;
    public static Music music;
    public static List<String> customerSpriteSheets;
    public static Random rand;
    public static int offsetX = 400; //offsets for the camera, in pixels.
    public static int offsetY = 200;

    //ingredients with states to use in recipes
    public static Ingredient CHOPPED_TOMATO;
    public static Ingredient CHOPPED_LETTUCE;
    public static Ingredient CHOPPED_ONION;
    public static Ingredient BURGER_BUN;
    public static Ingredient BURGER;

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
    public static Texture binTexture;
    public static Texture servingStationTexture;

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

    //completed dish textures
    public static Texture burgerDishTexture;
    public static Texture saladDishTexture;

    public static void init() {
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
        tomatoStationTexture = new Texture("stations/tomato_sack.png");
        onionStationTexture = new Texture("stations/onion_sack.png");
        lettuceStationTexture = new Texture("stations/lettuce_sack.png");
        breadStationTexture = new Texture("stations/bread_sack.png");
        meatStationTexture = new Texture("stations/ingredient_station_meat.png");
        dishStationTexture = new Texture("stations/ingredient_station_plate.png");
        binTexture = new Texture("stations/bin.png");
        servingStationTexture = new Texture("stations/customer_station.png"); // get new texture
        obstacleTexture = new Texture("stations/wall.png");

        // Ingredient Textures
        rawTomatoTexture = new Texture("ingredients/tomato.png");
        cutTomatoTexture = new Texture("ingredients/chopped_tomato.png");
        rawOnionTexture = new Texture("ingredients/onion.png");
        cutOnionTexture = new Texture("ingredients/chopped_onion.png");
        friedOnionTexture = new Texture("stations/sourceerr.png");//remove?
        rawLettuceTexture = new Texture("ingredients/lettuce.png");
        cutLettuceTexture = new Texture("ingredients/chopped_lettuce.png");
        rawBreadTexture = new Texture("ingredients/bread.png");
        cutBreadTexture = new Texture("ingredients/sliced_bread.png");
        friedBreadTexture = new Texture("stations/sourceerr.png"); //remove?
        rawMeatTexture = new Texture("ingredients/raw_meat.png");
        cutMeatTexture = new Texture("ingredients/raw_burger.png");
        friedMeatTexture = new Texture("ingredients/cooked_burger.png");
        dishTexture = new Texture("ingredients/plate.png");

        //dish textures
        burgerDishTexture = new Texture("ingredients/assembled_burger.png");
        saladDishTexture = new Texture("ingredients/salad.png");

        errorTexture = new Texture("stations/sourceerr.png");

        CHOPPED_TOMATO = new Ingredient(IngredientType.TOMATO, 1, -1, -1);
        CHOPPED_LETTUCE = new Ingredient(IngredientType.LETTUCE, 1, -1, -1);
        CHOPPED_ONION = new Ingredient(IngredientType.ONION, 1, -1, -1);
        BURGER_BUN = new Ingredient(IngredientType.BREAD, 1, -1, -1);
        BURGER = new Ingredient(IngredientType.MEAT, 1, -1, 1);
    }

    /** 
     * @param score
     */
    public static void addScore(int score) {
        GameData.score += score;
        gameLoop.addScore(GameData.score);
    }

    public static void dispose() {
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
