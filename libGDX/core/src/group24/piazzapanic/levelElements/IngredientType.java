package group24.piazzapanic.levelElements;


/**
 * Represents the various types of Ingredients that the game supports.
 */
public class IngredientType{
    /**
     * Represents tomatoes
     */
    public static final IngredientType TOMATO = new IngredientType("tomato");
    /**
     * Represents meat
     */
    public static final IngredientType MEAT = new IngredientType("meat");
    /**
     * Represents lettuce
     */
    public static final IngredientType LETTUCE = new IngredientType("lettuce");
    /**
     * Represents onion
     */
    public static final IngredientType ONION = new IngredientType("onion");
    /**
     * Represents bread
     */
    public static final IngredientType BREAD = new IngredientType("bread");

    /**
     * The name of the Ingredient
     */
    private final String name;

    /**
     * Constructor for the IngredientType class
     * @param s the name of the IngredientType.
     */
    public IngredientType(String s) {
        name = s;
    }

    /**
     * Gets the name of the IngredientType
     * @return a String containing the IngredientType's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the IngredientType
     * @return a String containing the IngredientType's name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Checks if two IngredientTypes are equal (have the same name)
     * @param obj The Object to check for equality.
     * @return True if equal, False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IngredientType) {
            return ((IngredientType) obj).getName().equals(name);
        } else if (obj instanceof String) {
            return obj.equals(name);
        } else {
            return false;
        }
    }
}
