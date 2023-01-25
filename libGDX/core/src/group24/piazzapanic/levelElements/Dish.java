package group24.piazzapanic.levelElements;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;

import com.badlogic.gdx.graphics.Texture;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.ImageMovable;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.maths.Vector2;

public class Dish extends ImageMovable {
    public ArrayList<Ingredient> Ingredients;
    ArrayList<Ingredient> BurgerIngredients = new ArrayList<Ingredient>(
            Arrays.asList(Base.BURGER_BUN, Base.BURGER, Base.CHOPPED_LETTUCE));
    ArrayList<Ingredient> SaladIngredients = new ArrayList<Ingredient>(
            Arrays.asList(Base.CHOPPED_ONION, Base.CHOPPED_LETTUCE, Base.CHOPPED_LETTUCE));
    ArrayList<Ingredient> CurrentIngredients = new ArrayList<Ingredient>();

    public static void init() {

    }

    public Dish() {

        super(Base.dishTexture);
        this.texture = Base.dishTexture;
        this.Ingredients = new ArrayList<Ingredient>();
        //super.setWidth(Base.tile_pixel_width);
        //super.setHeight(Base.tile_pixel_height);
    }

    public boolean addIngredient(Ingredient item) {
        this.Ingredients.add(item);
        return true;
    }

    public void act(float delta) {
        ArrayList<String> CurrentIngredients = new ArrayList<String>();
        // Get arraylist of all ingredient names, sort it, compare it, return it otherwise. 
        for (Ingredient item : Ingredients) {
            CurrentIngredients.add(item.getName());
        }
        Collections.sort(CurrentIngredients); // Sort the ArrayList of Strings of ingredients
        if (CurrentIngredients.equals(BurgerIngredients)) {
            this.Ingredients = null; // IT'S BURGR TIME, BABYE.  
            //TODO - SET ME TO **BORGER**
        } else if (CurrentIngredients.equals(SaladIngredients)) {
            this.Ingredients = null; // SALAD MOMENt
            //TODO - set me as a salad. 
        }
        CurrentIngredients = null; // To avoid a memory leak. 
    }
}
