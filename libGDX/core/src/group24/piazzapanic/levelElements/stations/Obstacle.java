package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;

/**
 * Just something to stand in the way of the player, block off areas, etc.
 */
public class Obstacle extends Station {
    public Obstacle() {
        super(Base.obstacleTexture);
    }

    @Override
    public boolean hasItem() {
        return true;
    }

    @Override
    public Movable takeItem() {
        return null;
    }
}
