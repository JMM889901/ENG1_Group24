package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.Gdx;

import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.Physics;

public class FryingStation extends Station {
    public FryingStation() {
        super(Base.fryingStationTexture);
    }

    public void act(float delta) {
        if (super.item != null) {
            if (Gdx.input.isKeyPressed(Base.ACT_KEY) && GameData.player.getFacingStation() == this
                    && Physics.isNear(this, GameData.player)) {
                if (super.item.getIngredient().getFryingProgress() == 1) {
                    System.out.println("Already fried..........");
                    return; // The item is already fried, don't go any further.
                }
                if (super.item.getIngredient().getFryingProgress() == -1) {
                    timeKeyHeld = 0;
                    return;
                }
                timeKeyHeld += delta;
                if (timeKeyHeld > 3 && super.item.getIngredient().getFryingProgress() == 0) {
                    // Cutting is done! poggers
                    System.out.println("Hm.");
                    super.item.getIngredient().fry();
                    System.out.println(" get fried idiot...");
                    timeKeyHeld = 0;
                }
            } else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }
    }
}
