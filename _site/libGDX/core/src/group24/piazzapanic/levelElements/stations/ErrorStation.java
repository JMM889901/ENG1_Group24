package group24.piazzapanic.levelElements.stations;

import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;

/**
 * This class is just used to make level file errors more conspicuous.
 */
public class ErrorStation extends Station {
    /**
     * Constructor for the ErrorStation
     * which has a conspicuous texture
     * to show any level file errors.
     */
    public ErrorStation() {
        super(GameData.errorTexture);
    }
}
