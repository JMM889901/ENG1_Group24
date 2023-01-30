package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.game.GameData;

import java.util.ArrayList;

public class CounterTop extends Station {

    /**
     * An ArrayList of {@link Movable}s on the counter top
     */
    ArrayList<Movable> items = new ArrayList<Movable>();

    /**
     * Creates a new counter top without an Item.
     * @param t The texture of the counter top
     */
    public CounterTop(Texture t) {
        super(t);
        this.items = null;
    }

    /**
     * Creates a new counter top with an Item.
     * @param item A {@link Movable} to be placed on the counter top
     */
    public CounterTop(Movable item) {
        super(GameData.counterTopTexture);
        this.items.add(item);
    }

}
