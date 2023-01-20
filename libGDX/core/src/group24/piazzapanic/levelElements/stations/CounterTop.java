package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;;

public class CounterTop extends Station {

    public CounterTop() {
        super(Base.counterTopTexture);
        this.item = null;
    }

    public CounterTop(Movable item) {
        super(Base.counterTopTexture);
        this.item = item;
    }
}
