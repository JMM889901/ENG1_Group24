package group24.piazzapanic.levelElements.stations;

//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.Physics;
import group24.piazzapanic.Physics.Movable;

public class BakingStation extends Station {

    public BakingStation(/* Texture sprite, Vector2 location */) {
        super(GameData.bakingStationTexture);
        // this.sprite = sprite;
        // this.location = location;

        // I don't think each instance of any station should store its own texture, that
        // just eats
        // up memory. I've put the actual drawing of stations into GameLoop.
        // Similarly, each object doesn't need to know its own location, it should only
        // know, on a
        // temporary basis, how far away a player interacting with it is.
        // If it turns out that each object really needs to know its own location, we
        // can add that
        // back in, but I don't think we should prematurely add in the redundant data -
        // Joss.
        this.item = null;
    }

    public BakingStation(/* Texture sprite, Vector2 location, */ Movable item) {
        super(GameData.bakingStationTexture);
        /*
         * this.sprite = sprite;
         * this.location = location;
         */
        this.item = item;
    }

    /**
     * Bakes the item.
     * act(float delta) is called by the stage.
     * float progress
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {

    }

    @Override
    public void interact(float delta) {
        // TODO Auto-generated method stub
        if (super.item == null) {
            return;
        }
        if (super.item.getIngredient().getBakingProgress() == 1) { // Item already baked, so can't bake further.
            System.out.println("Already baked......");
            return;
        }
        if (super.item.getIngredient().getBakingProgress() == -1) { // Item cannot be baked
            //                    timeKeyHeld = 0;
            return;
        }
        timeKeyHeld += delta; // Increase the
        if (timeKeyHeld > 3 && super.item.getIngredient().getBakingProgress() == 0) {
            super.item.getIngredient().bake(); // Button held for 3 seconds, so item is baked.
            timeKeyHeld = 0;
        }
    }
}