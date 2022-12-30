package group24.piazzapanic.levelElements.stations;


//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import group24.piazzapanic.levelElements.Movable;

public class Station extends Image{

    //protected Vector2 location;
    //protected Texture sprite;
    protected Movable item; 

    /**
     * Puts `item` on the station if there is no item already.
     * @param item
     * @return true if the item was placed, false if there was already an item on the station.
     */
    public boolean placeItem(Movable item){
        if (canPlaceItem()){
            this.item = item;
            return true;
        }
        else return false;
    }
    
    /**
     * A private convenience method to check if an item can be placed on the station.
     * @return Whether an item can be placed on the station.
     */
    private boolean canPlaceItem() {
        return !hasItem();
    }

    /**
     * Checks if the station has an item on it.
     * @return Whether the station has an item/items on it.
     */
    public boolean hasItem(){
        return this.item != null;
    }

    public Movable takeItem(){
        if(hasItem()) {
            Movable tmp = this.item;
            this.item = null;
            return tmp;
        }
        else return null;
    }

}
