package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class CuttingStation extends Station {

    float timeKeyHeld; // Time key is held for
    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Keys.F)) { // I've chosen F as a way to interact with the station but this is a
                                              // placeholder.
            timeKeyHeld += delta;
            if (timeKeyHeld > 10 && super.item.cuttingProgress == 0) { 
                // Cutting is done! poggers
                super.item.cut(); 
            } else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }

    }
}
