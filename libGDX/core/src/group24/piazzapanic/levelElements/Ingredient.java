package group24.piazzapanic.levelElements;

import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.ImageMovable;
import group24.piazzapanic.maths.Vector2;

/**
 * A
 */
public class Ingredient extends ImageMovable {
    // Three integers with 0-1 values representing cutting/baking/frying progress
    // These are NEGATIVE if cutting/baking/frying isn't supported
    // for a given ingredient
    /**
     * The cutting progress. -1 if the item cannot be cut, 0 if the item can be cut, 1 if already cut.
     */
    protected Integer cuttingProgress;
    /**
     * The baking progress. -1 if the item cannot be baked, 0 if the item can be baked, 1 if already baked.
     */
    protected Integer bakingProgress;
    /**
     * The frying progress. -1 if the item cannot be fried, 0 if the item can be fried, 1 if already fried.
     */
    protected Integer fryingProgress;
    /**
     * The item's texture.
     */
    protected Texture sprite;
    /**
     * The ingredient's {@link IngredientType}
     */
    protected IngredientType ingredientType;

    /**
     * Constructor for the Ingredient class.
     * @param ingredientType the type of the ingredient, an {@link IngredientType}.
     * @param location the location of the ingredient, a {@link Vector2}
     */
    public Ingredient(IngredientType ingredientType, Vector2 location) {
        super(Base.errorTexture);
        this.ingredientType = ingredientType;
        this.ingredient = this;
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

    /**
     * Constructor for Ingredient without a {@link Vector2}
     * Calls {@link #Ingredient(IngredientType ingredientType, Vector2 location)} with a new Vector2
     * with x, y = 0.
     * @param ingredientType the {@link IngredientType} of the Ingredient.
     */
    public Ingredient(IngredientType ingredientType) {
        this(ingredientType, new Vector2(0, 0));
    }

    /**
     * Constructor for the Ingredient class
     * @param ingredientType An {@link IngredientType} representing the Ingredient's type
     * @param cuttingProgress An integer representing the ingredient's cutting progress.
     * @param bakingProgress An integer representing the ingredient's baking progress
     * @param fryingProgress An integer representing the ingredient's frying progress.
     */
    public Ingredient(IngredientType ingredientType, Integer cuttingProgress, Integer bakingProgress, Integer fryingProgress) {
        super(Base.errorTexture);
        this.ingredientType = ingredientType;
        this.ingredient = this;
        this.cuttingProgress = cuttingProgress;
        this.bakingProgress = bakingProgress;
        this.fryingProgress = fryingProgress;
    }

    /**
     * Cut the ingredient.
     * Sets cuttingProgress to 1 and updates the texture of the ingredient.
     * Updates the other progress variables to allow baking/frying if relevant.
     */
    public void cut() {
        this.cuttingProgress = 1;
        // Update textures and cutting progress. 
        switch (this.ingredientType.getName()) {
            case "tomato":
                this.texture = Base.cutTomatoTexture;
                break;
            case "onion":
                this.texture = Base.cutOnionTexture;
                break;
            case "lettuce":
                this.texture = Base.cutLettuceTexture;
                break;
            case "bread":
                this.texture = Base.cutBreadTexture;
                break;
            case "meat":
                this.fryingProgress = 0; // We can now fry the cut meat.
                this.texture = Base.cutMeatTexture;
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

    public boolean identicalTo(Ingredient o) {
        if (this.cuttingProgress != o.getCuttingProgress()) {
            return false;
        }
        if (this.bakingProgress != o.getBakingProgress()) {
            return false;
        }
        if (this.fryingProgress != o.getFryingProgress()) {
            return false;
        }
        return this.ingredientType.getName() == o.ingredientType.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ingredient)) {
            return false;
        } else return this.identicalTo((Ingredient) obj);
    }
}
