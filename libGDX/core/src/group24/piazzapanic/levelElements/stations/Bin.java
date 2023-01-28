package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.game.GameData;

/**
 * Bins allow users to dispose of items.
 * When an item is placed on a Bin station, the item is deleted.
 */
public class Bin extends Station {

    /**
     * Class constructor.
     */
    public Bin() {
        super(GameData.binTexture);
        this.item = null;
    }

    /**
     * Returns true without assigning the item, dereferencing it.
     * @param item {@link Movable} to try and place
     * @return true
     *
     */
    @Override
    public boolean placeItem(Movable item) {
        this.item = null;
        return true;
    }

    /**
     * Bins do not have items that can be picked up.
     * This is implemented to satisfy the {@link Station} parent.
     * @return null
     */
    @Override
    public Movable takeItem() {
        this.item = null;
        return null;
    }
}
