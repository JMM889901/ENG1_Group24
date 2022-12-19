package group24.piazzapanic.levelElements.stations;

public class IngredientStation extends Station{

    private Ingredient ingredientType;

    public IngredientStation(Texture sprite, Vector2 location, Ingredient ingredientType){
        this.sprite = sprite;
        this.location = location;
        this.item = null;
        this.ingredientType = ingredientType;
    }
    
    public IngredientStation(Texture sprite, Vector2 location, Movable item, Ingredient ingredientType){
        this.sprite = sprite;
        this.location = location;
        this.item = item;
        this.ingredientType = ingredientType;
    }

    @Override
    public Movable takeItem(){
        if(hasItem()) {
            Movable tmp = this.item;
            this.item = null;
            return tmp;
        }
        else return ingredientType;
    }
    
}
