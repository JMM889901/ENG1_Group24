package group24.piazzapanic.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import group24.piazzapanic.game.GameLoop;

public class StageManager {
    private static HashMap<String, Stage> stages;
    private static Stage activeStage;

    /**
     * Stage manager manages active screens and buttons, currently lacks multi stage input support so this may need to be looked at
     * Currently will only draw and recieve inputs from the active stage, no multi-stage support yet
     */
    public static void init() {
        stages = new HashMap<String, Stage>();
        stages.put("MainMenu", StageFactory.createMainMenuStage());
        stages.put("Options", StageFactory.createOptionsMenuStage());
        stages.put("Game", new GameLoop());

        setActiveStage("MainMenu");
    }

    /**
     * Set the active stage by passing the stage itself, 
     * does not add it to stages so should only be used for temporary menus that are destroyed after use
     * sets the specified stage as the input processor and the drawn stage
     * @param stage
     */
    public static void setActiveStage(Stage stage) {
        activeStage = stage;
        Gdx.input.setInputProcessor(activeStage);
    }

    /**
     * Set the active stage by passing the name
     * sets the specified stage as the input processor and the drawn stage
     * for stage names check the stage manager constructor
     * @param stage
     */
    public static void setActiveStage(String stage) {
        activeStage = getStage(stage);
        Gdx.input.setInputProcessor(activeStage);
    }

    public static Stage getActiveStage() {
        return activeStage;
    }

    /**
     * Get stage by name
     * @param Name
     * @return the stage with that name
     */
    public static Stage getStage(String Name) {
        return stages.get(Name);
    }
}
