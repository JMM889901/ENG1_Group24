package group24.piazzapanic.levelElements;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.ImageMovable;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.levelElements.stations.Station;

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
    public static final ArrayList<Ingredient> BURGER_RECIPE = new ArrayList<Ingredient>(
            Arrays.asList(GameData.BURGER_BUN, GameData.BURGER, GameData.CHOPPED_LETTUCE));
    /** The Salad recipe. */
    public static final ArrayList<Ingredient> SALAD_RECIPE = new ArrayList<Ingredient>(
            Arrays.asList(GameData.CHOPPED_ONION, GameData.CHOPPED_LETTUCE, GameData.CHOPPED_TOMATO));
    /** The list of all dishes. */
    public static Dish BURGER = new Dish(BURGER_RECIPE);
    public static Dish SALAD = new Dish(SALAD_RECIPE);
    public static ArrayList<Dish> Dishes = new ArrayList<Dish>(
            Arrays.asList(BURGER, SALAD));
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

    /**
     * Initialise the Dish. Set its texture, size, completeness, and recipe.
     * @param recipe The recipe for the dish, an ArrayList of {@link Ingredient}s.
     */
    public Dish(ArrayList<Ingredient> recipe) {
        super(GameData.dishTexture);
        super.setWidth(Base.tile_pixel_width);
        super.setHeight(Base.tile_pixel_height);
        this.complete = true;
        this.recipe = recipe;
        this.Ingredients = recipe;
    }

    /**
     * Adds an ingredient to the dish.
     * Will only add ingredients if the ingredient is part of a recipe.
     * @param item The {@link Ingredient} to add to the dish.
     * @return True if the ingredient was added, false otherwise.
     */
    public boolean addIngredient(Ingredient item) {
        if (recipe.size() == 0) {
            ArrayList<Ingredient> tmp = new ArrayList<Ingredient>(this.Ingredients);
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
        } else if (this.recipe.contains(item) && !this.Ingredients.contains(item)) { //Prevents adding duplicate ingredients to the dish.
            this.Ingredients.add(item);
            this.complete = checkComplete();
            System.out.println("added to dish\ncurrent:");
            System.out.println("recipe:");
            return true;
        }
        return false;
    }

    /**
     * Not implemented.
     */
    public void act(float delta) {
    }

    /**
     * Sets the Dish's recipe when an ingredient is first added, as it is initialised without a recipe.
     * @param currentIngredients an ArrayList of {@link Ingredient}s.
     * @return True if there is a recipe that matches the ingredients, false otherwise.
     */
    private boolean setRecipe(ArrayList<Ingredient> currentIngredients) {
        boolean matchSalad = true;
        boolean matchBurger = true;
        for (Ingredient i : currentIngredients) {
            System.out.println(i.getCuttingProgress());
            System.out.println(i.getBakingProgress());
            System.out.println(i.getFryingProgress());
            System.out.println(i.ingredientType.getName());
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
            return matchBurger || matchSalad; //returns false if it matches none of the recipes
        return true; //returns true if there is a recipe that matches

    }

    /**
     * Checks if the recipe is complete (that is, all the ingredients for the recipe have been added to the dish).
     * If it is, set the dish's texture to the appropriate dish texture.
     * @return True if the recipe is complete, false otherwise.
     */
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
     * Gets the dish's {@link #complete} value.
     * @return true if the dish is complete, false otherwise.
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Checks if the dish is equal to another dish.
     * A dish is equal to another dish if they have the same recipe and completeness value.
     * @param obj The {@link Dish} to compare to.
     * @return True if the dishes are equal, false otherwise.
     */
    @Override
    public void drawItemInventory(int x, int y, int width, int height) {
        super.drawItemInventory(x, y, width, height);

        for (Ingredient ingredient : Ingredients) {
            System.out.println(ingredient);
            y -= 50;
            ingredient.drawItemInventory(x, y, Base.tile_pixel_width / 2,
                    Base.tile_pixel_width / 2);
        }
        System.out.println("IntelliJ? more like IntelliCringe");
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
