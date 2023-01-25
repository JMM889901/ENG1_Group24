package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;

public class Bin extends Station {

    public Bin() {
        super(Base.binTexture);
        this.item = null;
    }

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
