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
     *
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
     * Construct the IngredientStation class
     *
     * @param gridX          the X grid coordinates of the station
     * @param gridY          the Y grid coordinates of the station
     * @param ingredientType the IngredientType that the station serves.
     */
    public IngredientStation(int gridX, int gridY, IngredientType ingredientType) {
        super(getIngredientStationAsset(ingredientType));
        this.ingredientType = ingredientType;
    }

    /**
     * A stub to stop players putting items down on the IngredientStation
     *
     * @param item the Movable the player is trying to put down (but this is pointless!)
     * @return false. always.
     */
    @Override
    public boolean placeItem(Movable item) {
        return false;
    }

    /**
     * Pick up the Ingredient or Dish from the station.
     *
     * @return An Ingredient or Dish. The type returned is specified in the constructor {@link #IngredientStation}
     */
    @Override
    public Movable takeItem() {
        if (ingredientType.getName() == "dish") {
            return new Dish();
        }
        return new Ingredient(ingredientType);
    }

}
