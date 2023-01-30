package group24.piazzapanic.ui;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.GameLoop;

/**
 * Manages which stage is "active" (ie is displayed and is responding to user input).
 */
public class StageManager {
    /** A dictionary privataly noting a name and a corresponding stage. */
    private static HashMap<String, Stage> stages;
    /** A "global" value indicating the current active stage. */
    private static Stage activeStage;
    /** A "global" value indicating string whose corresponding stage is currently active. */
    private static String activeStageName;

    /**
     * Create the various stages now, using `StageFactory.java`.
     */
    public static void init() {
        stages = new HashMap<String, Stage>();
        stages.put("MainMenu", StageFactory.createMainMenuStage());
        stages.put("Options", StageFactory.createOptionsMenuStage());
        GameData.gameLoop = new GameLoop();
        stages.put("Game", GameData.gameLoop);
        stages.put("Pause", StageFactory.createPauseMenuStage());
        stages.put("Instructions", StageFactory.createInstructionsStage());
        setActiveStage("MainMenu");
    }

    /**
     * Add a stage to the record of stages, supplying a string name for it.
     * @param key The name to refer to the stage by.
     * @param stage The stage itself.
     */
    public static void addStage(String key, Stage stage) {
        stages.put(key, stage);
    }

    /**
     * Set the active stage without supplying a string. Used for "anonymous" stages that aren't
     * stored permanently.
     * @param stage The stage object to use as the active stage.
     */
    public static void setActiveStage(Stage stage) {
        activeStage = stage;
        Gdx.input.setInputProcessor(activeStage);
    }

    /**
     * Set the active stage by name (names of stages are as shown in the StageManager constructor).
     * @param stage  The name of an already existing stage.
     */
    public static void setActiveStage(String stage) {
        activeStage = getStage(stage);
        activeStageName = stage;
        Gdx.input.setInputProcessor(activeStage);
    }

    /** 
     * Get the stage object of whichever stage is currently active.
     * @return The stage object itself.
     */
    public static Stage getActiveStage() {
        return activeStage;
    }

    /**
     * Get the name of the active stage.
     * @return The string/key referring to the active stage.
     */
    public static String getActiveStageName() {
        return activeStageName;
    }

    /**
     * Get a stage by name.
     * @param name The name of the desired stage.
     * @return The stage referred to by 
     */
    public static Stage getStage(String name) {
        return stages.get(name);
    }
}
