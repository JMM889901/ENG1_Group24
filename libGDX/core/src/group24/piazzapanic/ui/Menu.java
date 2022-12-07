package group24.piazzapanic.ui;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String stageID;
    private ArrayList<Wigit> wigits;

    /** Creates a new menu with a given name, Wigits should be supplied after this step.
     * @param stageID The application level name of the menu.
     */
    public Menu(String stageID) {
        this.stageID = stageID;
        this.wigits = new ArrayList<Wigit>();
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
    }

    public void addWigit(Wigit wigit) {
        this.wigits.add(wigit);
    }

    /** This adds all the Wigits in the list supplied, can be anything that inherits from Java's
     * List interface.
     * @param wigits The list of Wigits to add.
     */
    public void addWigits(List<Wigit> wigits) {
        this.wigits.addAll(wigits);
    }

    public void removeWigit(Wigit wigit) {
        this.wigits.remove(wigit);
    }

    public String getStageID() {
        return stageID;
    }
}
