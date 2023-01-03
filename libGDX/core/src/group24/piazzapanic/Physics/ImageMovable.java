package group24.piazzapanic.Physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import group24.piazzapanic.maths.Vector2;

public class ImageMovable extends Image implements Movable {
    Vector2 velocity = new Vector2(0, 0);

    public ImageMovable(Texture texture) {
        super(texture);
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void addVelocity(Vector2 velocity) {
        this.velocity.x += velocity.x;
        this.velocity.y += velocity.y;
    }

    public void Move() {
        float x = this.getX();
        float y = this.getY();
        x += this.velocity.x;
        y += this.velocity.y;
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Move();
    }

}