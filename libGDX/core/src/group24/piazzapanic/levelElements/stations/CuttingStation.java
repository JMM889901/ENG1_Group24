package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.Gdx;

import group24.piazzapanic.Base;

public class CuttingStation extends Station {

    float timeKeyHeld; // Time key is held for
    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Base.ACT_KEY)) { // I've chosen F as a way to interact with the station but this is a
                                              // placeholder.                          
            // I've moved `Key.F` into Base so that every station uses the same key - Joss.

            timeKeyHeld += delta;
            if (timeKeyHeld > 10 && super.item.ingredient.getCuttingProgress() == 0) { 
                // Cutting is done! poggers
                super.item.ingredient.cuttingComplete(); 
            } else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }

    }
}
