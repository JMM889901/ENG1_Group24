package group24.piazzapanic.Physics;

import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.ui.StageAnimation;

public class AnimatedMovable extends StageAnimation implements Movable {
    Vector2 velocity;

    public AnimatedMovable(String path, int frameCount, int framesPerLine, int numberOfLines, int x, int y, int width,
            int height) {
        super(path, frameCount, framesPerLine, numberOfLines, x, y, width, height);
        velocity = new Vector2(0, 0);
    }

    public AnimatedMovable(String path, int frameCount, int framesPerLine, int numberOfLines, Vector2 pos, int width,
            int height) {
        super(path, frameCount, framesPerLine, numberOfLines, pos, width, height);
        velocity = new Vector2(0, 0);
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
}
