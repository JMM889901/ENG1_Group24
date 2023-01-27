package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.game.Customer;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.levelElements.Dish;

/**
 * Serving station allows users to submit completed dishes to fulfill orders.
 */
public class ServingStation extends Station {

    /**
     * Creates a new serving station where item = null, calls station with t
     * @param t The texture of the station
     */
    public ServingStation(Texture t) {
        super(t);
        this.item = null;
    }

    @Override
    public boolean placeItem(Movable item) {
        if (item instanceof Dish)
            return serveOrder(item);
        else
            return false;
    }

    /**
     * Checks if a customer has ordered the submitted dish, if yes fulfils order of that customer
     * @param dish Dish placed on the station
     * @return true if dish matches a customers order, false otherwise
     */
    private boolean serveOrder(Movable dish) {
        for (Customer c : GameData.customers) {
            if (dish == c.getOrder()) {
                c.fulfillOrder();
                return true;
            }
        }
        return false;
    }

    /**
     * Overwriting station.takeItem as items cannot be picked up from serving station
     * so always null
     */
    @Override
    public Movable takeItem() {
        this.item = null;
        return null;
    }
}
