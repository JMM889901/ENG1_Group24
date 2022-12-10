package group24.piazzapanic.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class stageManager {
    private static HashMap<String, Stage> stages;
    private static Stage activeStage;

    public stageManager() {
        stages = new HashMap<String, Stage>();
        stages.put("MainMenu", MenuFactory.createMainMenuStage());
        setActiveStage("MainMenu");
        stages.put("Options", MenuFactory.createOptionsMenuStage());
    }

    public static void setActiveStage(Stage stage) {
        activeStage = stage;
        Gdx.input.setInputProcessor(activeStage);
    }

    public static void setActiveStage(String stage) {
        activeStage = getStage(stage);
        Gdx.input.setInputProcessor(activeStage);
    }

    public static Stage getActiveStage() {
        return activeStage;
    }

    public static Stage getStage(String Name) {
        return stages.get(Name);
    }
}
