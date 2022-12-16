package group24.piazzapanic.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**
 * Deprecated, use stages instead, see StageFactory.java
 */
public class Menu extends InputAdapter {
    private String stageID;
    private ArrayList<Wigit> wigits;
    public Stage stage;

    /** Creates a new menu with a given name, Wigits should be supplied after this step.
     * @param stageID The application level name of the menu.
     */
    public Menu(String stageID) {
        this.stageID = stageID;
        this.wigits = new ArrayList<Wigit>();
        stage = new Stage();
    }

    public Menu(String stageID, Stage stage) {
        this.stageID = stageID;
        this.wigits = new ArrayList<Wigit>();
        this.stage = stage;
    }

    /** This just calls all the menu's wigits own update methods. */
    public void update() {
        for (Wigit wigit : wigits) {
            wigit.update();
        }
    }

    /** This just calls all the menu's wigits own render methods. */
    public void render() {
        for (Wigit wigit : wigits) {
            wigit.render();
        }
        if (stage != null) {
            stage.draw();
        }
    }

    public void setActive() {
        Gdx.input.setInputProcessor(this.stage);
    }

    /** 
     * @param wigit
     */
    public void addWigit(Wigit wigit) {
        this.wigits.add(wigit);
    }

    /**
     * 
     * @param wigit
     */
    public void addWigit(Button wigit) {
        this.stage.addActor(wigit);
    }

    /** This adds all the Wigits in the list supplied, can be anything that inherits from Java's
     * List interface.
     * @param wigits The list of Wigits to add.
     */
    public void addWigits(List<Wigit> wigits) {
        this.wigits.addAll(wigits);
    }

    /** 
     * @param wigit
     */
    public void removeWigit(Wigit wigit) {
        this.wigits.remove(wigit);
    }

    public String getStageID() {
        return stageID;
    }
}
