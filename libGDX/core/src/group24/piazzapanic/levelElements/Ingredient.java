package group24.piazzapanic.levelElements;

import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.levelElements.IngredientType;

//import java.util.concurrent.TimeUnit;

// Needs to extend Movable when this is implemented
public class Ingredient {
    // Three integers with 0-1 values representing cutting/baking/frying progress
    // These are NEGATIVE if cutting/baking/frying isn't supported
    // for a given ingredient
    protected Integer cuttingProgress;
    protected Integer bakingProgress;
    protected Integer fryingProgress;
    protected Texture sprite;
    protected IngredientType ingredientType;

    public Ingredient(IngredientType ingredientType, Vector2 location){
        this.ingredientType = ingredientType;
        // TODO - add in sprite: this.sprite = sprite;


        // Set constraints for ingredients
        // assuming they are all raw when initialised
        switch (this.ingredientType.getName()){
            case "tomato":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
            case "lettuce":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
            case "onions":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
        }

    }
    // TODO - add constraints on operations on Ingredients
    // i.e. "You can't cut this because it's already been cut"


    /**
     * Cut the ingredient.
     */
    public void cut(){
        this.cuttingProgress = 1;
        // this.sprite = cut sprite
        // Update what we can do with each ingredient now that it's cut
        switch (this.ingredientType.getName()){
            case "tomato":
                this.bakingProgress = -1;
                this.fryingProgress = 0;
            // TODO - this is a method stub, as the salad recipe doesn't require anything beyond just
            // cutting and combining all the items.
        }
    }

    /**
     * Fry the ingredient
     */
    public void fry(){
        this.fryingProgress = 1;
        // this.sprite = fried sprite
        // update constraints
    }

    /**
     * Bake the ingredient
     */
    public void bake(){
        this.bakingProgress = 1;
        // this.sprite = baked sprite
        // update constraints
    }


    // Getters
    public float getCuttingProgress() {return this.cuttingProgress;}
    public float getBakingProgress() {return this.bakingProgress;}
    public float getFryingProgress() {return this.fryingProgress;}
}