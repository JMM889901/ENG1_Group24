package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.levelElements.Dish;
import group24.piazzapanic.levelElements.Ingredient;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class CounterTop extends Station {

    ArrayList<Movable> items = new ArrayList<Movable>();

    public CounterTop(Texture t) {
        super(t);
        this.items = null;
    }

    public CounterTop(Movable item) {
        super(Base.counterTopTexture);
        this.items.add(item);
    }

    @Override
    public boolean placeItem(Movable item) {
        boolean hasDish = false;
        //if (this.items != null){
            if (this.items.size() > 0) { // If it currently has items on it.
                for (Movable currentItem : this.items) { // If one of these is a dish.
                    if (currentItem instanceof Dish) {
                        hasDish = true;
                    }
                }
            }
        //}
        if (hasDish){
            this.items.add(item);
            return true;
        } else {
            if (this.items.size() == 0) { // If the list is either empty or NULL.
                this.items.add(item);
                return true;
            }
           // if (this.items == null){
            //    this.items = new ArrayList<Movable>();
            //    this.items.add(item);
            //}
        }
        return false;
    }

    @Override
    public Movable takeItem() {
        if (this.items.size() == 1) { // Nice, simple easy. just one item
            return this.items.get(0);
        }
        if (this.items.size() > 1) {
            return this.items.get(this.items.size() - 1); // Return the last item
        }
        return null; // countertop, her eyes enormous: you STEAL ITEM FROM countertop? you STEAL her
                     // ITEM like the criminal? oh! oh! jail for mother! jail for mother for One
                     // Thousand Years!!!!
    }

}
