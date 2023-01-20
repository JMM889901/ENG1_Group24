package group24.piazzapanic.levelElements.stations;

//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;

import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.Base;
import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.levelElements.IngredientType;

public class IngredientStation extends Station {

    private final IngredientType ingredientType;

    public IngredientStation(int gridX, int gridY, IngredientType ingredientType) {
        super(Base.ingredientStationTexture);
        this.item = null;
        this.ingredientType = ingredientType;
    }

    public IngredientStation(int gridX, int gridY, Movable item, IngredientType ingredientType) {
        super(gridX, gridY);
        System.out.println("Deprecated constructor called for IngredientStation.");
        this.item = item;
        this.ingredientType = ingredientType;
    }

    @Override
    public Movable takeItem() {
        if (hasItem()) {
            Movable tmp = this.item;
            this.item = null;
            return tmp;
        } else {
            return new Ingredient(ingredientType);
        }

    }

}
