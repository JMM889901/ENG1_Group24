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

    // I (Joss) have decided having a click method would be inappropriate for anything other than a button.
    // Please remove these comments once buttons have been implemented/someone disagrees with me.

    public void update() {

    }

    public void render() {

    }
}
