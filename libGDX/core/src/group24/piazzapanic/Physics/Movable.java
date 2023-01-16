package group24.piazzapanic.Physics;

import group24.piazzapanic.maths.Vector2;

public interface Movable {

    public void setVelocity(Vector2 velocity);

    public void addVelocity(Vector2 velocity);

    public void Move();

}
