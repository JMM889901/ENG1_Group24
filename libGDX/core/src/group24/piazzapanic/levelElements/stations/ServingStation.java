package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.graphics.Texture;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.game.Customer;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.levelElements.Dish;

/**
 * ServingStation allows users to submit completed {@link Dish}es to fulfill orders.
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

   /**
     * Overwriting station.placeItem as only dishes can be placed on the serving station
     * @param item Item to be placed on the station
     * @return true if item is a {@link Dish} which matches a {@link Customer}'s order, false otherwise
     */
    @Override
    public boolean placeItem(Movable item) {
        if (item instanceof Dish)
            return serveOrder(item);
        else
            return false;
    }

    /**
     * Checks if a customer has ordered the submitted dish, if yes fulfils order of that customer
     * @param dish Movable placed on the station
     * @return true if the item placed on the station is a {@link Dish} and matches a customers order, false otherwise
     */
    private boolean serveOrder(Movable dish) {
        for (Customer c : GameData.customers) {
            if (dish.equals(c.getOrder())) {
                c.fulfillOrder();

                return true;
            }
        }
        return false;
    }

    /**
     * Overriding station.takeItem as items cannot be picked up from serving station
     * @return null as items cannot be picked up from the serving station.
     */
    @Override
    public Movable takeItem() {
        this.item = null;
        return null;
    }
}
