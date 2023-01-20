package group24.piazzapanic.levelElements.stations;

//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.Physics;
import group24.piazzapanic.Physics.Movable;;

public class BakingStation extends Station {

    float timeKeyHeld;

    public BakingStation(/*Texture sprite, Vector2 location*/) {
        super(Base.bakingStationTexture);
        //this.sprite = sprite;
        //this.location = location;

        // I don't think each instance of any station should store its own texture, that just eats
        // up memory. I've put the actual drawing of stations into GameLoop.
        // Similarly, each object doesn't need to know its own location, it should only know, on a
        // temporary basis, how far away a player interacting with it is.
        // If it turns out that each object really needs to know its own location, we can add that
        // back in, but I don't think we should prematurely add in the redundant data - Joss.
        this.item = null;
    }

    public BakingStation(/*Texture sprite, Vector2 location,*/ Movable item) {
        super(Base.bakingStationTexture);
        /*this.sprite = sprite;
        this.location = location;*/
        this.item = item;
    }

    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Base.ACT_KEY) && Physics.isNear(this, GameData.player)) {
            System.out.println("Baking..." + timeKeyHeld + " " + delta);
            timeKeyHeld += delta;
            if (timeKeyHeld > 10 && super.item.getIngredient().getBakingProgress() == 0) {
                // Cutting is done! poggers

                super.item.getIngredient().bake();
            } else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }

    }

}
