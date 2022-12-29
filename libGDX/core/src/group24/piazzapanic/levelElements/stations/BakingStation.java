package group24.piazzapanic.levelElements.stations;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import group24.piazzapanic.levelElements.Movable;

public class BakingStation extends Station{

    public BakingStation(Texture sprite, Vector2 location){
        this.sprite = sprite;
        this.location = location;
        this.item = null;
    }
    
    public BakingStation(Texture sprite, Vector2 location, Movable item){
        this.sprite = sprite;
        this.location = location;
        this.item = item;
    }

    public boolean act(float delta) {
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
