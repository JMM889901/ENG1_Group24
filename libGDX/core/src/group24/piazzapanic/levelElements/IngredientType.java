package group24.piazzapanic.levelElements;


/**
 * IngredientType is pretty much just a stand-in for String, but it makes the code more readable.
 */
public class IngredientType{
    public static final IngredientType TOMATO = new IngredientType("tomato");
    public static final IngredientType MEAT = new IngredientType("meat");
    public static final IngredientType LETTUCE = new IngredientType("lettuce");
    public static final IngredientType ONION = new IngredientType("onion");
    public static final IngredientType BREAD = new IngredientType("bread");

    private String name;

    public IngredientType(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IngredientType) {
            return ((IngredientType) obj).getName().equals(name);
        } else if (obj instanceof String) {
            return ((String) obj).equals(name);
        } else {
            return false;
        }
    }
}
