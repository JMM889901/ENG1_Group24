package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.game.GameData;

public class Bin extends Station {

    public Bin() {
        super(GameData.binTexture);
        this.item = null;
    }

    /**
     * @param Item to try place
     * @returns true, always. 
     * Returns true without assigning the item, causes there to be no references to the item therby "deleting" it
     */
    @Override
    public boolean placeItem(Movable item) {
        this.item = null;
        return true;
    }

    @Override
    public Movable takeItem() {
        this.item = null;
        return null;
    }
}
