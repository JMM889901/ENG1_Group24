package group24.piazzapanic.Physics;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import group24.piazzapanic.maths.Vector2;

public interface Movable {

    public void setVelocity(Vector2 velocity);

    public void addVelocity(Vector2 velocity);

    public void Move();

}
