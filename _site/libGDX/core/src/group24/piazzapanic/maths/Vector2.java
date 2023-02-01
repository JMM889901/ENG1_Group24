package group24.piazzapanic.maths;

import java.lang.Math;

import group24.piazzapanic.Base;
import group24.piazzapanic.maths.Vector2i;

/** This class is a simple 2D vector class. It uses doubles so can store non-integers. */
public class Vector2 {
    // To be honest I just got sick of pissing around with libGDX's way of doing things and Java
    // just mucking things up.
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Takes decimal values between 0 and 1 (fractions of the whole screen) and translates them
     * into absolute coordinates.
     * @param relativeLocation The location of the object with each value a proportion of the
     *                         respective dimension.
     * @return The absolute location of the object.
     */
    public Vector2i translateToAbsoluteLocation() {
        return new Vector2i(
                Math.toIntExact(Math.round(x * Base.WINDOW_WIDTH)),
                Math.toIntExact(Math.round(y * Base.WINDOW_HEIGHT)));
    }

    public int getAbsoluteX() {
        return Math.toIntExact(Math.round(x * Base.WINDOW_WIDTH));
    }

    public int getAbsoluteY() {
        return Math.toIntExact(Math.round(y * Base.WINDOW_HEIGHT));
    }
}
