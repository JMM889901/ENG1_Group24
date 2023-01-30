package group24.piazzapanic.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import group24.piazzapanic.maths.Vector2;

/**
 * Stores an animation, in the form of frames placed around in a texture.
 */
public class StageAnimation extends Actor {
    /** An object taking frames from animationSheet then animating them (render from this).*/
    Animation<TextureRegion> animation;
    /** The picture with frames layed out from a PNG. */
    Texture animationSheet;
    /** Time progress through the animation, in seconds. */
    Float stateTime;
    /** How wide (in pixels) the animation renders onto the screen. */
    public int width;
    /** How tall (in pixels) the animation renders onto the screen. */
    public int height;
    /** The frame/area of the animation sheet that this animation is currently on. */
    TextureRegion currentFrame;

    /**
     * Creates an animation from a texture (referred to by path) and libGDX shapes it to the
     * dimensions given.
     * @param path Path to picture containing all the frames.
     * @param frameCount Number of frames in the animation.
     * @param framesPerLine Number of frames in a row.
     * @param numberOfLines Number of frames in a column (ie the number of rows).
     * @param x Pixel x of the animation.
     * @param y Pixel y of the animation.
     * @param width Width of the animation in pixels.
     * @param height Height of the animation in pixels.
     */
    public StageAnimation(String path, int frameCount, int framesPerLine, int numberOfLines, int x, int y, int width, int height) {
        super();
        this.setY(y);
        this.setX(x);
        this.width = width;
        this.height = height;
        setAnimation(path, frameCount, numberOfLines, framesPerLine);
        currentFrame = this.animation.getKeyFrame(stateTime, true);
    }

    /**
     * Creates an animation from an existing animation and libGDX shapes it to the dimensions given.
     * @param anim The animation to take frames from.
     * @param frameCount Number of frames in the animation.
     * @param framesPerLine Number of frames in a row.
     * @param numberOfLines Number of frames in a column (ie the number of rows).
     * @param x Pixel x to set the animation to.
     * @param y Pixel y to set the animation to.
     * @param width Width of the animation in pixels.
     * @param height Width of the animation in pixels.
     */
    public StageAnimation(Animation<TextureRegion> anim, int frameCount, int framesPerLine, int numberOfLines, int x, int y, int width, int height) {
        super();
        this.setY(y);
        this.setX(x);
        this.width = width;
        this.height = height;
        setAnimation(anim);
        currentFrame = this.animation.getKeyFrame(stateTime, true);
    }

    /**
     * Creates an animation from a texture (referred to by path) and libGDX shapes it to the
     * dimensions given.
     * @param path Path to picture containing all the frames.
     * @param frameCount Number of frames in the animation.
     * @param framesPerLine Number of frames in a row.
     * @param numberOfLines Number of frames in a column (ie the number of rows).
     * @param pos Position of the animation.
     * @param width Width of the animation in pixels.
     * @param height Height of the animation in pixels.
     */
    public StageAnimation(String path, int frameCount, int framesPerLine, int numberOfLines, Vector2 pos, int width, int height) {
        this(path, frameCount, framesPerLine, numberOfLines, pos.getAbsoluteX(),
                pos.getAbsoluteY(),
                width,
                height);
    }

    /**
     * Creates an animation from an existing animation and libGDX shapes it to the dimensions given.
     * @param anim The animation to take frames from.
     * @param frameCount Number of frames in the animation.
     * @param framesPerLine Number of frames in a row.
     * @param numberOfLines Number of frames in a column (ie the number of rows).
     * @param pos Position of the animation.
     * @param width Width of the animation in pixels.
     * @param height Height of the animation in pixels.
     */
    public StageAnimation(Animation<TextureRegion> anim, int frameCount, int framesPerLine, int numberOfLines, Vector2 pos, int width, int height) {
        this(anim, frameCount, framesPerLine, numberOfLines, pos.getAbsoluteX(),
                pos.getAbsoluteY(),
                width,
                height);
    }

    /**
     * Draw the animation onto the screen.
     * @param batch What libGDX draws with.
     * @param parentAlpha The transparency of the animation (unused, but required by libGDX).
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(currentFrame, getX(), getY(), width, height);
    }

    /**
     * Progress the animation.
     * @param delta Time elapsed since last call (unused, but required by libGDX).
     */
    @Override
    public void act(float delta) {
        this.stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        currentFrame = this.animation.getKeyFrame(stateTime, true);

    }

    /**
     * Give the object a new animation.
     * @param path Path to new picture containing the animation.
     * @param frameCount Number of frames in the new animation.
     * @param numberOfLines Number of lines in the new animation.
     * @param framesPerLine Number of frames in a line of the new animation.
     */
    public void setAnimation(String path, int frameCount, int numberOfLines, int framesPerLine) {
        animationSheet = new Texture(path);
        TextureRegion[][] tmp = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / framesPerLine,
                animationSheet.getHeight() / numberOfLines);

        int framesAdded = 0;
        TextureRegion[] frames = new TextureRegion[frameCount];
        int index = 0;
        for (int i = 0; i < numberOfLines; i++) {
            for (int j = 0; j < framesPerLine; j++) {
                if (framesAdded < frameCount) {
                    frames[index++] = tmp[i][j];
                    framesAdded++;
                }
            }
        }
        this.animation = new Animation<TextureRegion>(1 / 9f, frames);
        stateTime = 0f;
    }

    /**
     * Give the object a new animation that has the same dimensions, frames etc as the old one.
     * @param animation The new animation.
     */
    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        stateTime = 0f;
    }

    /**
     * Create and return a new animation
     * @param path Path to picture containing all the frames.
     * @param frameCount Number of frames in the animation.
     * @param numberOfLines Number of frames in a column (ie the number of rows).
     * @param framesPerLine Number of frames in a row.
     * @return The new animation.
     */
    public static Animation<TextureRegion> makeAnimation(String path, int frameCount, int numberOfLines,
            int framesPerLine) {
        Texture animationSheet = new Texture(path);
        TextureRegion[][] tmp = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / framesPerLine,
                animationSheet.getHeight() / numberOfLines);

        int framesAdded = 0;
        TextureRegion[] frames = new TextureRegion[frameCount];
        int index = 0;
        for (int i = 0; i < numberOfLines; i++) {
            for (int j = 0; j < framesPerLine; j++) {
                if (framesAdded < frameCount) {
                    frames[index++] = tmp[i][j];
                    framesAdded++;
                }
            }
        }
        return new Animation<TextureRegion>(1 / 9f, frames);
    }

    /**
     * Get the current frame of the animation.
     * @return The texture region containing the frame.
     */
    public TextureRegion getCurrentFrame() {
        return this.currentFrame;
    }
}
