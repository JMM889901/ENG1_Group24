package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.Physics;

public class FryingStation extends Station {
    public FryingStation() {
        super(Base.fryingStationTexture);
    }

    float timeKeyHeld; // Time key is held for

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Base.ACT_KEY) && Physics.isNear(this, GameData.player)) {

            timeKeyHeld += delta;
            if (timeKeyHeld > 10 && super.item.ingredient.getCuttingProgress() == 0) {
                // Cutting is done! poggers
                super.item.ingredient.cut();
            } else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }

    }
}
