package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.game.GameData;

/**
 * Obstacles stop the player walking through a specific area
 */
public class Obstacle extends Station {
    /**
     * Class constructor.
     */
    public Obstacle() {
        super(GameData.obstacleTexture);
    }

    /**
     * obstacles cannot hold items.
     * this is here to stop players putting items on walls.
     * @return false
     */
    @Override
    public boolean hasItem() {
        return false;
    }

    /**
     * obstacles do not have items.
     * you cannot pick up things that don't exist...
     * or can you?
     * oooOOOOOoooOOOoOOOOooo!
     * @return null
     */
    @Override
    public Movable takeItem() {
        return null;
    }
}
