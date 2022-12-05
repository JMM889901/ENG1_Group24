package group24.piazzapanic.ui;

import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.maths.Vector2i;

public abstract class Wigit {
    protected String wigitID;
    protected Vector2i location;

    public Wigit(String wigitID, Vector2 relativeLocation) {
        this.wigitID = wigitID;
        this.location = relativeLocation.translateToAbsoluteLocation();
    }

    /**
     * Activated when the wigit is clicked, requires implementation by child
     * classes.
     */
    public void click() {
        System.out.println("Unimplemented Wigit click method has been called!");
    }

    public void update() {

    }

    public void render() {

    }

    public void pin(int pin) {

    }
}
