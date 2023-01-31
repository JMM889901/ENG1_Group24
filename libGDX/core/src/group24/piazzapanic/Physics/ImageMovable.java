package group24.piazzapanic.Physics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import group24.piazzapanic.Base;
import group24.piazzapanic.levelElements.Ingredient;

/**
 * A class that extends the Image class to allow for images that can be stored in inventory.
 * Implements the {@link Movable} interface.
 * Extends the {@link Image} class.
 */
public class ImageMovable extends Image implements Movable {

    /** The ingredient that this image represents. */
    public Ingredient ingredient;
    /** The texture of this image. */
    public Texture texture;

    /**
     * Create a new image that can be stored in inventory.
     * @param texture The {@link Texture} of this image.
     */
    public ImageMovable(Texture texture) {
        super(texture);
        this.texture = texture;
    }

    /**
     * A method stub for the act method.
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
    }


    /** Not implemented. */
    @Override
    public void actItem(float delta) {

    }

    /** Draw the image. */
    @Override
    public void drawItem(int x, int y) {
        Base.batch.draw(texture, x, y);

    }

    /** Return the {@link Ingredient} that this image represents. */
    @Override
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Draw the image at the given position and size.
     * @param x The x position of the image.
     * @param y The y position of the image.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    @Override
    public void drawItem(int x, int y, int width, int height) {
        Base.batch.draw(texture, x, y, width, height);
    }

    @Override
    public void drawItemInventory(int x, int y, int width, int height) {
        drawItem(x, y, 50, 50);

    }
}