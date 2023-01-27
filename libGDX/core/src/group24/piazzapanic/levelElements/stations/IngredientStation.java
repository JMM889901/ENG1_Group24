package group24.piazzapanic.levelElements.stations;


import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.levelElements.Dish;
import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.levelElements.IngredientType;

/**
 * IngredientStations provide the player with a supply of ingredients.
 * Their Item is always the Ingredient specified in IngredientType
 * allowing players to pick up the Ingredient.
 */
public class IngredientStation extends Station {
    private final IngredientType ingredientType; // The type of ingredient the IngredientStation serves.

    /**
     * Find and return the correct texture for the given ingredient type
     * @param type The IngredientType we want the texture for.
     * @return a Texture for the ingredientStation
     */
    public static Texture getIngredientStationAsset(IngredientType type) {
        Texture texture = Base.ingredientStationTexture;
        switch (type.getName()) {
            case "tomato":
                return Base.tomatoStationTexture;
            case "onion":
                return Base.onionStationTexture;
            case "lettuce":
                return Base.lettuceStationTexture;
            case "bread":
                return Base.breadStationTexture;
            case "meat":
                return Base.meatStationTexture;
            case "dish":
                return Base.dishStationTexture;
        }
        return texture;
    }

    /**
     * wait oops.
     * @param gridX
     * @param gridY
     * @param ingredientType
     */
    public IngredientStation(int gridX, int gridY, IngredientType ingredientType) {
        super(getIngredientStationAsset(ingredientType));
        this.ingredientType = ingredientType;
    }

    @Override
    public Movable takeItem() {
        if (hasItem()) {
            Movable tmp = this.item;
            this.item = null;
            return tmp;
        } else {
            if (ingredientType.getName() == "dish") {
                return new Dish();
            }
            return new Ingredient(ingredientType);
        }

    }

}
