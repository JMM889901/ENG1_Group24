package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.levelElements.Dish;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class CounterTop extends Station {

    ArrayList item = new ArrayList<>();
    public CounterTop(Texture t) {
        super(t);
        this.item = null;
    }

    public CounterTop(Movable item) {
        super(Base.counterTopTexture);
        this.item.add(item);
    }

    public void act(float delta){
        
    }
}
