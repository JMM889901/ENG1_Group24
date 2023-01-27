package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;

/**
 * This class is just used to make level file errors more conspicuous.
 */
public class ErrorStation extends Station {
    public ErrorStation() {
        super(Base.errorTexture);
    }
}
