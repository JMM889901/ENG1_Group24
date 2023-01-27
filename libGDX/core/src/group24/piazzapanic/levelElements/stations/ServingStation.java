package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.game.Customer;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.levelElements.Dish;

public class ServingStation extends Station {

    public ServingStation(Texture t) {
        super(t);
        this.item = null;
    }

    @Override
    public boolean placeItem(Movable item) {
        if (item instanceof Dish) return serveOrder(item);
        else return false;
    }

    private boolean serveOrder(Movable dish) {
        for (Customer c : GameData.customers) {
            if (dish == c.getOrder()) {
                c.fulfillOrder();
                return true;
            }
        }
        return false;
    }

    @Override
    public Movable takeItem() {
        this.item = null;
        return null;
    }
}
