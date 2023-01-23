package group24.piazzapanic.levelElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.ImageMovable;
import group24.piazzapanic.levelElements.IngredientType;

//import java.util.concurrent.TimeUnit;

// Needs to extend Movable when this is implemented
public class Ingredient extends ImageMovable {
    // Three integers with 0-1 values representing cutting/baking/frying progress
    // These are NEGATIVE if cutting/baking/frying isn't supported
    // for a given ingredient
    protected Integer cuttingProgress;
    protected Integer bakingProgress;
    protected Integer fryingProgress;
    protected Texture sprite;
    protected IngredientType ingredientType;

    public Ingredient(IngredientType ingredientType, Vector2 location) {
        super(Base.errorTexture);
        this.ingredientType = ingredientType;
        // TODO - add in sprite: this.sprite = sprite;

        // Set constraints for ingredients
        // assuming they are all raw when initialised
        switch (this.ingredientType.getName()) {
            case "tomato":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = Base.rawTomatoTexture;
                break;
            case "lettuce":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = Base.rawLettuceTexture;
                break;
            case "onion":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = Base.rawOnionTexture;
                break;
            case "bread":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = Base.rawBreadTexture;
                break;
            case "meat":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = Base.rawMeatTexture;
                break;
        }

    }

    public Ingredient(IngredientType ingredientType) {
        this(ingredientType, new Vector2(0, 0));
    }

    /**
     * Cut the ingredient.
     */
    public void cut() {
        this.cuttingProgress = 1;
        // this.sprite = cut sprite
        // Update what we can do with each ingredient now that it's cut
        switch (this.ingredientType.getName()) {
            case "tomato":
                this.bakingProgress = -1;
                this.fryingProgress = 0;
                this.texture = Base.cutTomatoTexture;
                break;
            case "onion":
                this.bakingProgress = 0;
                this.fryingProgress = 0;
                this.texture = Base.cutOnionTexture;
                break;
            case "lettuce": // You can't cut or fry lettuce!
                this.texture = Base.cutLettuceTexture;
                break;
            case "bread": // Why would you bake bread?
                this.fryingProgress = 0;
                this.texture = Base.cutOnionTexture;
                break;
            case "meat":
                this.bakingProgress = 0;
                this.fryingProgress = 0;
                this.texture = Base.cutMeatTexture;
                // TODO - this is a method stub, as the salad recipe doesn't require anything beyond just
                // cutting and combining all the items.
        }
    }

    /**
     * Fry the ingredient
     */
    public void fry() {
        this.fryingProgress = 1;
        switch (this.ingredientType.getName()) {
            case "tomato":
                this.bakingProgress = -1;
                this.texture = Base.cutTomatoTexture;
                break;
            case "onion":
                this.bakingProgress = 0;
                this.texture = Base.cutOnionTexture;
                break;
            case "lettuce":
                this.texture = Base.cutLettuceTexture;
                break;
            case "bread":
                this.bakingProgress = 0;
                this.texture = Base.cutOnionTexture;
                break;
            case "meat":
                this.bakingProgress = -1;
                this.texture = Base.friedMeatTexture;
        }
    }

    /**
     * Bake the ingredient
     */
    public void bake() {
        this.bakingProgress = 1;
        // this.sprite = baked sprite
        // update constraints
    }

    // Getters
    public float getCuttingProgress() {
        return this.cuttingProgress;
    }

    public float getBakingProgress() {
        return this.bakingProgress;
    }

    public float getFryingProgress() {
        return this.fryingProgress;
    }
}
