package group24.piazzapanic.levelElements.stations;

//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;

import group24.piazzapanic.Physics.Movable;

import javax.swing.Spring;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import group24.piazzapanic.Base;
import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.levelElements.IngredientType;

public class IngredientStation extends Station {

    private final IngredientType ingredientType;

    public IngredientStation(int gridX, int gridY, IngredientType ingredientType) {
        super(Base.ingredientStationTexture);
        Texture newTexture = Base.ingredientStationTexture;
        switch (ingredientType.getName()) {
            case "tomato":
                newTexture = Base.tomatoStationTexture;
                newTexture = Base.onionStationTexture;
            case "lettuce":
                newTexture = Base.lettuceStationTexture;
            case "bread":
                newTexture = Base.breadStationTexture;
            case "meat":
                newTexture = Base.meatStationTexture;
        }
        this.setDrawable(new SpriteDrawable(new Sprite(newTexture)));
        // TODO - you've got your texture.
        // now we just have to update the texture?
        // which I do not know how to do at all.
        // pls help me lol         
        this.item = new Ingredient(ingredientType);
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
