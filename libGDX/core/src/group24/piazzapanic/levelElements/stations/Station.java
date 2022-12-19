package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class Station {

    protected Vector2 location;
    protected Texture sprite;
    protected Movable item; 

    public boolean placeItem(Movable item){
        if (canPlaceItem()){
            this.item = item;
            return true;
        }
        else return false;
    }
    
    private boolean canPlaceItem() {
        if (hasItem()) return false;
        else return true;
    }

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
