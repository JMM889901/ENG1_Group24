package group24.piazzapanic.levelElements;

import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.maths.Vector2;

import java.util.concurrent.TimeUnit;

// Needs to extend Movable when this is implemented
public class Ingredient {
    // Can we replaced cookedState with:
    protected Integer cookedState;


    // Three floats with 0-1 values representing cutting/baking/frying progress
    // These are NEGATIVE if cutting/baking/frying isn't supported
    // for a given ingredient
    protected Float cuttingProgress;
    protected Float bakingProgress;
    protected Float fryingProgress;
    protected Texture sprite;
    protected String ingredientType;

    public Ingredient(String ingredientType, Vector2 location){
        this.ingredientType = ingredientType;
        // TODO - add in sprite: this.sprite = sprite;


        // Set constraints for ingredients
        // assuming they are all raw when initialised
        switch (this.ingredientType){
            case "tomato":
                this.cuttingProgress = 0f;
                this.bakingProgress = -1f;
                this.fryingProgress = -1f;
            case "lettuce":
                this.cuttingProgress = 0f;
                this.bakingProgress = -1f;
                this.fryingProgress = -1f;
            case "onions":
                this.cuttingProgress = 0f;
                this.bakingProgress = -1f;
                this.fryingProgress = -1f;
        }

    }
    // TODO - add constraints on operations on Ingredients


    /*
    This function increments the cutting value by (1/[cutting time])
    It returns the cutting progress of the ingredient
    which is then handled by the station
     */
    public float incrementalCut() throws InterruptedException {
        // For now, every ingredient takes max 20 seconds to cut
        // TODO - implement interaction checks in CuttingStation.
        TimeUnit.SECONDS.sleep(1);
        this.cuttingProgress = this.cuttingProgress + 0.05f;
        return this.cuttingProgress;
    }
    public float incrementalBake() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        this.bakingProgress = this.bakingProgress + 0.05f;
        return this.bakingProgress;
    }
    public float incrementalFry() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        this.fryingProgress = this.fryingProgress += 1;
        return this.fryingProgress;
    }
    public void cuttingComplete(){
        // this.sprite = cut sprite
        // Update what we can do with each ingredient now that it's cut
        switch (this.ingredientType){
            case "tomato":
                this.bakingProgress = -1f;
                this.fryingProgress = 0f;
            // TODO - this is a method stub, as the salad recipe doesn't require anything beyond just
            // cutting and combining all the items.
        }
    }
    public void fryingComplete(){
        // this.sprite = fried sprite
        // update constraints
    }

    public void bakingComplete(){
        // this.sprite = baked sprite
        // update constraints
    }


    // Getters
    public float getCuttingProgress() {return this.cuttingProgress;}
    public float getBakingProgress() {return this.bakingProgress;}
    public float getFryingProgress() {return this.fryingProgress;}
}
