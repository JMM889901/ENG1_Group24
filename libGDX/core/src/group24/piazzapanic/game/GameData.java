package group24.piazzapanic.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

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
    public static List<String> customerSpriteSheets;
    public static Random rand;
    

    
    /** 
     * @param score
     */
    public static void addScore(int score) {
        GameData.score += score;
        gameLoop.addScore(score);
    }
}
