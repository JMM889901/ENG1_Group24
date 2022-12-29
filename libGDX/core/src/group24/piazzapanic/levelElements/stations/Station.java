package group24.piazzapanic.levelElements.stations;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;;
public class Station extends Image{

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
        return !hasItem();
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
