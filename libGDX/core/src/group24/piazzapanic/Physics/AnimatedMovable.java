package group24.piazzapanic.Physics;

import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.ui.StageAnimation;

public class AnimatedMovable extends StageAnimation implements Movable {

    public Ingredient ingredient;

    public AnimatedMovable(String path, int frameCount, int framesPerLine, int numberOfLines, int x, int y, int width,
            int height) {
        super(path, frameCount, framesPerLine, numberOfLines, x, y, width, height);
    }

    public AnimatedMovable(String path, int frameCount, int framesPerLine, int numberOfLines, Vector2 pos, int width,
            int height) {
        super(path, frameCount, framesPerLine, numberOfLines, pos, width, height);
    }

    @Override
    public void actItem(float delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawItem(int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public Ingredient getIngredient() {
        // TODO Auto-generated method stub
        return ingredient;
    }

    @Override
    public void drawItem(int x, int y, int width, int height) {
        // TODO Auto-generated method stub

    }
}
