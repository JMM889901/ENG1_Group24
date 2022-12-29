package group24.piazzapanic.maths;

import java.lang.Math;

import group24.piazzapanic.Base;

/**
 * This class is a simple 2D vector class. It uses doubles so can store non-integers. Do not use
 * with level elements, UI elements only.
 */
public class Vector2 extends com.badlogic.gdx.math.Vector2 {
    // These should only be from 0 to 1, -1 means unset and anything else is a worrying mistake.
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(int x, int y) {
        // This will lead to slight innacuracies, but they get rounded away anyway so it won't
        // change the integer values. (eg 500 / 1280, * 1280 = 500.0000000000001)
        this.x = x / (double) Base.WINDOW_WIDTH;
        this.y = y / (double) Base.WINDOW_HEIGHT;
    }

    public int getAbsoluteX() {
        return Math.toIntExact(Math.round(x * Base.WINDOW_WIDTH));
    }

    public int getAbsoluteY() {
        return Math.toIntExact(Math.round(y * Base.WINDOW_HEIGHT));
    }

    public static Vector2 gridUnitTranslate(float gridX, float gridY) {
        return new Vector2(gridX * Base.TILE_GRID_UNIT, gridY * Base.TILE_GRID_UNIT * (double) Base.WINDOW_WIDTH / Base.WINDOW_HEIGHT);
    }
}
