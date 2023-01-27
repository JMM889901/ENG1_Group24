package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.Gdx;

import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.Physics;
import group24.piazzapanic.levelElements.Ingredient;

/**
 * FryingStations allow users to fry Ingredients.
 * They interact with {@link Ingredient}'s {@link Ingredient#fry()}  and {@link Ingredient#getFryingProgress()} methods.
 * This class extends {@link Station}.
 */
public class FryingStation extends Station {
    /**
     * Initialise the FryingStation and set its texture
     */
    public FryingStation() {
        super(GameData.fryingStationTexture);
    }

    /**
     * Allows the player to interact with the station by frying the item.
     * Checks that the player is near the station, facing the station, and is pressing the ACT_KEY.
     * Checks if the item has already been fried or can't be fried.
     * If it can be fried increments timeKeyHeld by act until timeKeyHeld > 3 seconds. Then fries the item.
     * n.b. act(float delta) is called every frame.
     *
     * @param delta Time in seconds since the last frame.
     */
    public void act(float delta) {

    }

    @Override
    public void interact(float delta) {
        if (super.item == null) {
            return;
        }
        if (super.item.getIngredient().getFryingProgress() == 1) { // The item is already fried.
            System.out.println("Already fried..........");
            return; // The item is already fried, don't go any further.
        }
        if (super.item.getIngredient().getFryingProgress() == -1) { // The item cannot be fried.
            timeKeyHeld = 0;
            return;
        }
        timeKeyHeld += delta;
        if (timeKeyHeld > 3 && super.item.getIngredient().getFryingProgress() == 0) {
            // Cutting is done! poggers
            System.out.println("Hm.");
            super.item.getIngredient().fry();
            System.out.println(" get fried idiot...");
            timeKeyHeld = 0; // Reset to avoid horrible loop!
        }
    }
}
