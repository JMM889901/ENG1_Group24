package group24.piazzapanic.levelElements;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.ImageMovable;
import group24.piazzapanic.game.GameData;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Dishes are used to serve Ingredients.
 * They interact with {@link Ingredient}'s {@link Ingredient#fry()}  and {@link Ingredient#getFryingProgress()} methods.
 * This class extends {@link Station}.
 * Recipes are stored as an ArrayList of Ingredients.
 */
public class Dish extends ImageMovable {
    /** The burger recipe. */
    static final ArrayList<Ingredient> BURGER_RECIPE = new ArrayList<Ingredient>(
            Arrays.asList(GameData.BURGER_BUN, GameData.BURGER, GameData.CHOPPED_LETTUCE));
    /** The Salad recipe. */
    static final ArrayList<Ingredient> SALAD_RECIPE = new ArrayList<Ingredient>(
            Arrays.asList(GameData.CHOPPED_ONION, GameData.CHOPPED_LETTUCE, GameData.CHOPPED_TOMATO));
    /** The list of all dishes. */
    public static ArrayList<Dish> Dishes = new ArrayList<Dish>(
            Arrays.asList(new Dish(BURGER_RECIPE), new Dish(SALAD_RECIPE)));
    ArrayList<Ingredient> Ingredients = new ArrayList<Ingredient>();
    /** Stores the dish's recipe. */
    ArrayList<Ingredient> recipe;
    /** Stores the dish's current progress towards completion. */
    boolean complete;

    /**
     * Initialise the Dish. Set its texture, size, completeness, and recipe.
     */
    public Dish() {

        super(GameData.dishTexture);
        super.setWidth(Base.tile_pixel_width);
        super.setHeight(Base.tile_pixel_height);
        this.complete = false;
        this.recipe = new ArrayList<Ingredient>();
    }

    public Dish(ArrayList<Ingredient> recipe) {
        super(GameData.dishTexture);
        super.setWidth(Base.tile_pixel_width);
        super.setHeight(Base.tile_pixel_height);
        this.complete = true;
        this.recipe = recipe;
        this.Ingredients = recipe;
    }

    public boolean addIngredient(Ingredient item) {
        if (recipe.size() == 0) {
            ArrayList<Ingredient> tmp = this.Ingredients;
            tmp.add(item);
            if (setRecipe(tmp)) { //checks if there is a recipe with this combination of ingredients
                this.Ingredients.add(item);
                this.complete = checkComplete();
                System.out.println("added to dish\ncurrent:");
                for (Ingredient i : Ingredients) {
                    System.out.println(i.ingredientType.getName());
                }
                System.out.println("recipe:");
                for (Ingredient i : recipe) {
                    System.out.println(i.ingredientType.getName());
                }
                return true;
            }
        } else if (this.recipe.contains(item) && !this.Ingredients.contains(item)) {
            this.Ingredients.add(item);
            this.complete = checkComplete();
            System.out.println("added to dish\ncurrent:");
            for (Ingredient i : Ingredients) {
                System.out.println(i.ingredientType.getName());
            }
            System.out.println("recipe:");
            for (Ingredient i : recipe) {
                System.out.println(i.ingredientType.getName());
            }
            return true;
        }
        return false;
    }


    //moved recipe checks to separate method so it doesnt check when it doesnt need to
    public void act(float delta) {
        /*
        ArrayList<String> CurrentIngredients = new ArrayList<String>();
        // Get arraylist of all ingredient names, sort it, compare it, return it otherwise. 
        for (Ingredient item : Ingredients) {
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

    private boolean setRecipe(ArrayList<Ingredient> currentIngredients) {
        boolean matchSalad = true;
        boolean matchBurger = true;
        for (Ingredient i : currentIngredients) {
            System.out.println(i.getCuttingProgress());
            System.out.println(i.getBakingProgress());
            System.out.println(i.getFryingProgress());
            System.out.println(i.ingredientType.getName());
            // for (Dish d : Dishes) {
            //     if(d.recipe.c)
            // }
            if (!BURGER_RECIPE.contains(i)) {
                matchBurger = false;
            }
            if (!SALAD_RECIPE.contains(i)) {
                matchSalad = false;
            }
        }
        if (matchSalad && !matchBurger)
            recipe = SALAD_RECIPE; //if it only matches salad set recipe to salad
        else if (matchBurger && !matchSalad)
            recipe = BURGER_RECIPE; //if it only matches burger set recipe to burger
        else
            return matchBurger || matchSalad; //returns flase if it matches none of the recipes
        return true; //returns true if there is a recipe that matches

    }


    private boolean checkComplete() {
        if (this.Ingredients.size() != 0 && this.recipe.size() != 0) {
            for (Ingredient i : recipe) {
                if (!this.Ingredients.contains(i)) {
                    return false;
                }
            }
            if (recipe == BURGER_RECIPE)
                this.texture = GameData.burgerDishTexture;
            else if (recipe == SALAD_RECIPE)
                this.texture = GameData.saladDishTexture;
            return true;
        }
        return false;
    }

    /**
     * Gets the dish's completeness.
     * @return true if the dish is complete, false otherwise.
     */
    public boolean isComplete() {
        return complete;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dish)) {
            return false;
        }
        Dish dish = (Dish) obj;
        if (this.complete != dish.complete) {
            return false;
        }
        if (this.recipe != dish.recipe) {
            return false;
        }
        return true;
    }
}
