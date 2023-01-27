package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.Gdx;
import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.Physics;
import group24.piazzapanic.levelElements.Ingredient;

/**
 * CuttingStations allow users to cut Ingredients.
 * They interact with {@link Ingredient}'s {@link Ingredient#cut()}  and {@link Ingredient#getCuttingProgress()} methods.
 * This class extends {@link Station}.
 */
public class CuttingStation extends Station {
    /**
     * Initialise the CuttingStation and set its texture
     */
    public CuttingStation() {
        super(GameData.cuttingStationTexture);
    }
    @Override
    
    /**
     * Allows the player to cut the item.
     * Checks that the player is near the station, facing the station, and is pressing the ACT_KEY.
     * Checks if the item has already been cut or can't be cut.
     * If it can be cut increases timeKeyHeld by act until timeKeyHeld > 3 seconds. Then cuts the item.
     * n.b. act(float delta) is called every frame.
     *
     * @param delta Time in seconds since the last frame.
     */
    public void act(float delta) {
        if (super.item != null) { // Avoid null pointer errors.
            if (super.item.getIngredient() == null) { // Avoid null pointer errors.
                return;
            }
            // Checks the user is pressing the interaction key, is facing the station, and is near the station.
            if (Gdx.input.isKeyPressed(Base.ACT_KEY) && GameData.player.getFacingStation() == this
                    && Physics.isNear(this, GameData.player)) {
                if (super.item.getIngredient().getCuttingProgress() == 1) {
                    return; // The item is already cut, don't go any further.
                }
                if (super.item.getIngredient().getCuttingProgress() == -1) {
                    timeKeyHeld = 0;
                    return;
                }
                timeKeyHeld += delta;
                if (timeKeyHeld > 3 && super.item.getIngredient().getCuttingProgress() == 0) {
                    super.item.getIngredient().cut();
                    timeKeyHeld = 0;
                }
            } else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }
    }
}
