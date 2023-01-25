package group24.piazzapanic.levelElements;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;

import com.badlogic.gdx.graphics.Texture;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.ImageMovable;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.maths.Vector2;

public class Dish extends ImageMovable{
    static final ArrayList<Ingredient> BURGER_RECIPE = new ArrayList<Ingredient>(Arrays.asList(Base.BURGER_BUN, Base.BURGER, Base.CHOPPED_LETTUCE));
    static final ArrayList<Ingredient> SALAD_RECIPE = new ArrayList<Ingredient>(Arrays.asList(Base.CHOPPED_ONION, Base.CHOPPED_LETTUCE, Base.CHOPPED_LETTUCE));
    ArrayList<Ingredient> Ingredients = new ArrayList<Ingredient>();
    ArrayList<Ingredient> recipe;
    boolean complete;

    public static void init() {

    }

    public Dish() {
        super(Base.dishTexture);
        super.setWidth(Base.tile_pixel_width);
        super.setHeight(Base.tile_pixel_height);
        this.complete = false;
        this.recipe = new ArrayList<Ingredient>();
    }


    public boolean addIngredient(Ingredient item){
        if(recipe.size() == 0){
            ArrayList<Ingredient> tmp = this.Ingredients;
            tmp.add(item);
            if(getRecipe(tmp)){ //checks if there is a recipe with this combination of ingredients
                this.Ingredients.add(item);
            }
        }
        else{
            if(this.recipe.contains(item) && !this.Ingredients.contains(item)){
                this.Ingredients.add(item);
            }
        }
        this.complete = checkComplete();
        return true;
    }

    //moved recipe checks to separate method so it doesnt check when it doesnt need to
    public void act(float delta){
        /*
        ArrayList<String> CurrentIngredients = new ArrayList<String>();
       // Get arraylist of all ingredient names, sort it, compare it, return it otherwise. 
        for (Ingredient item : Ingredients){
            CurrentIngredients.add(item.getName());
        }
        Collections.sort(CurrentIngredients); // Sort the ArrayList of Strings of ingredients
        if (CurrentIngredients.equals(BURGER_RECIPE)){
            this.Ingredients = null; // IT'S BURGR TIME, BABYE.  
            //TODO - SET ME TO **BORGER**
        }
        else if (CurrentIngredients.equals(SALAD_RECIPE)){
            this.Ingredients = null; // SALAD MOMENt
            //TODO - set me as a salad. 
        }
        CurrentIngredients = null; // To avoid a memory leak. 
        */
    }

    private boolean getRecipe( ArrayList<Ingredient> currentIngredients){
        boolean matchSalad = true;
        boolean matchBurger = true;
        for (Ingredient i : currentIngredients) {
            if(!BURGER_RECIPE.contains(i)){
                matchBurger = false;
            }
            if(!SALAD_RECIPE.contains(i)){
                matchSalad = false;
            }
        }
        if(matchSalad && !matchBurger) recipe = SALAD_RECIPE; //if it only matches salad set recipe to salad
        else if(matchBurger && !matchSalad) recipe = BURGER_RECIPE; //if it only matches burger set recipe to burger
        else if(!matchBurger && !matchSalad) return false; //returns flase if it matches none of the recipes
        return true; //returns true if there is a recipe that matches

    }

    private boolean checkComplete(){
        if(this.Ingredients.size() != 0 && this.recipe.size() != 0){
            for (Ingredient i : recipe) {
                if(!this.Ingredients.contains(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean isComplete(){
        return complete;
    }


}
