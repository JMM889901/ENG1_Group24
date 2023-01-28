package group24.piazzapanic.levelElements;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.ImageMovable;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.maths.Vector2;

/**
 * An ingredient is an item that can be used in a recipe.
 * Ingredients can be cut, baked, and fried.
 * This is represented by an integer value for each of these actions.
 * The value is -1 if the item cannot be cut/baked/fried, 0 if the item can be cut/baked/fried, and 1 if the item has already been cut/baked/fried.
 * This class extends {@link ImageMovable}.
*/
public class Ingredient extends ImageMovable {

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
        super(GameData.errorTexture);
        this.ingredientType = ingredientType;
        this.ingredient = this;
        // Set constraints for ingredients
        // assuming they are all raw when initialised
        switch (this.ingredientType.getName()) {
            case "tomato":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = GameData.rawTomatoTexture;
                break;
            case "lettuce":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = GameData.rawLettuceTexture;
                break;
            case "onion":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = GameData.rawOnionTexture;
                break;
            case "bread":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = GameData.rawBreadTexture;
                break;
            case "meat":
                this.cuttingProgress = 0;
                this.bakingProgress = -1;
                this.fryingProgress = -1;
                this.texture = GameData.rawMeatTexture;
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
    public Ingredient(IngredientType ingredientType, Integer cuttingProgress, Integer bakingProgress,
            Integer fryingProgress) {
        super(GameData.errorTexture);
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
                this.texture = GameData.cutTomatoTexture;
                break;
            case "onion":
                this.texture = GameData.cutOnionTexture;
                break;
            case "lettuce":
                this.texture = GameData.cutLettuceTexture;
                break;
            case "bread":
                this.texture = GameData.cutBreadTexture;
                break;
            case "meat":
                this.fryingProgress = 0; // We can now fry the cut meat.
                this.texture = GameData.cutMeatTexture;
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
                this.texture = GameData.cutTomatoTexture;
                break;
            case "onion":
                this.bakingProgress = 0;
                this.texture = GameData.cutOnionTexture;
                break;
            case "lettuce":
                this.texture = GameData.cutLettuceTexture;
                break;
            case "bread":
                this.bakingProgress = 0;
                this.texture = GameData.cutOnionTexture;
                break;
            case "meat":
                this.bakingProgress = -1;
                this.texture = GameData.friedMeatTexture;
        }
    }

    /**
     * Bake the ingredient
     * NOTE - not required for assessment one, think of this
     * as a fun little bonus for assessment two. :)
     */
    public void bake() {
        this.bakingProgress = 1;
        // this.sprite = baked sprite
        // update constraints
    }

    // Getters

    /**
     * Get the ingredient's cutting progress.
     * @return an Integer of the cutting progress
     */
    public Integer getCuttingProgress() {
        return this.cuttingProgress;
    }

    /**
     * Get the ingredient's baking progress.
     * @return an Integer of the baking progress
     */

    public Integer getBakingProgress() {
        return this.bakingProgress;
    }

    /**
     * Get the ingredient's frying progress.
     * @return an Integer of the frying progress
     */
    public Integer getFryingProgress() {
        return this.fryingProgress;
    }

    /**
     * Check if Ingredients have identical types and cutting/baking/frying progresses
     * @param o The ingredient to be compared
     * @return true if the ingredients are identical, false otherwise
     */
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

    /**
     * Check if Ingredients have identical types and cutting/baking/frying progresses
     * @param obj The ingredient to be compared
     * @return true if the ingredients are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ingredient)) {
            return false;
        } else
            return this.identicalTo((Ingredient) obj);
    }
}
