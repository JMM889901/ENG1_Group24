package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.Gdx;

import group24.piazzapanic.Base;

public class CuttingStation extends Station {

    public CuttingStation() {
        super(Base.cuttingStationTexture);
    }

    float timeKeyHeld; // Time key is held for

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Base.ACT_KEY)) {

            timeKeyHeld += delta;
            if (timeKeyHeld > 10 && super.item.getIngredient().getCuttingProgress() == 0) {
                // Cutting is done! poggers
                super.item.getIngredient().cut();
            } else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }

    }
}
