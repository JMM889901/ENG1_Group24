package group24.piazzapanic.levelElements.stations;

//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;

import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.game.GameData;

/**
 * BakingStation allows users to bake items.
 * They interact with {@link group24.piazzapanic.levelElements.Ingredient}.
 */
public class BakingStation extends Station {

    /**
     * Constructor for BakingStation with no item.
     */
    public BakingStation() {
        super(GameData.bakingStationTexture);
        this.item = null;
    }

    /**
     * Constructor for BakingStation that is holding an item.
     * @param item an {@link Movable} item.
     */
    public BakingStation(Movable item) {
        super(GameData.bakingStationTexture);
        this.item = item;
    }

/**
 * Not implemented
 */
    @Override
    public void act(float delta) {

    }

    
        /**
     * Bakes the item.
     * interact(float delta) is called by the stage every frame..
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void interact(float delta) {
        if (super.item == null) { // No item to bake.
            return;
        }
        if (super.item.getIngredient().getBakingProgress() == 1) { // Item already baked, so can't bake further.
            System.out.println("Already baked......");
            return;
        }
        if (super.item.getIngredient().getBakingProgress() == -1) { // Item cannot be baked

            return;
        }
        timeKeyHeld += delta; // Increase the timeKeyHeld by the time since the last frame.
        if (timeKeyHeld > 3 && super.item.getIngredient().getBakingProgress() == 0) {
            super.item.getIngredient().bake(); // Button held for 3 seconds, so item is baked.
            timeKeyHeld = 0; // Reset the timeKeyHeld.
        }
    }
}