package group24.piazzapanic.Physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import group24.piazzapanic.Base;
import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.maths.Vector2;

public class ImageMovable extends Image implements Movable {

    public Ingredient ingredient;
    public Texture texture;

    public ImageMovable(Texture texture) {
        super(texture);
        this.texture = texture;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void actItem(float delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void drawItem(int x, int y) {
        Base.batch.draw(texture, x, y);

    }

    @Override
    public Ingredient getIngredient() {
        // TODO Auto-generated method stub
        return ingredient;
    }

    @Override
    public void drawItem(int x, int y, int width, int height) {
        Base.batch.draw(texture, x, y, width, height);
    }

    @Override
    public void drawItemInventory(int x, int y, int width, int height) {
        drawItem(x, y, 50, 50);

    }
}