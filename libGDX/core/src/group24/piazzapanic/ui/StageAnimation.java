package group24.piazzapanic.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.badlogic.gdx.utils.reflect.Constructor;

import group24.piazzapanic.maths.Vector2;

public class StageAnimation extends Image {
    Animation<TextureRegion> animation;
    Texture animationSheet;
    Float stateTime;
    public int width;
    public int height;
    TextureRegion currentFrame;

    public StageAnimation(String path, int frameCount, int framesPerLine, int numberOfLines, int x, int y, int width,
            int height) {
        super();
        this.setY(y);
        this.setX(x);
        this.width = width;
        this.height = height;
        setAnimation(path, frameCount, numberOfLines, framesPerLine);
        currentFrame = this.animation.getKeyFrame(stateTime, true);
    }

    public StageAnimation(Animation<TextureRegion> anim, int frameCount, int framesPerLine, int numberOfLines, int x,
            int y, int width,
            int height) {
        super();
        this.setY(y);
        this.setX(x);
        this.width = width;
        this.height = height;
        setAnimation(anim);
        currentFrame = this.animation.getKeyFrame(stateTime, true);
    }

    public StageAnimation(String path, int frameCount, int framesPerLine, int numberOfLines, Vector2 pos, int width,
            int height) {
        this(path, frameCount, framesPerLine, numberOfLines, pos.getAbsoluteX(),
                pos.getAbsoluteY(),
                width,
                height);
    }

    public StageAnimation(Animation<TextureRegion> anim, int frameCount, int framesPerLine, int numberOfLines,
            Vector2 pos, int width,
            int height) {
        this(anim, frameCount, framesPerLine, numberOfLines, pos.getAbsoluteX(),
                pos.getAbsoluteY(),
                width,
                height);
    }

    /** 
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(currentFrame, getX(), getY(), width, height); // Draw current frame at (50, 50)
    }

    /** 
     * @param delta
     */
    @Override
    public void act(float delta) {
        this.stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        currentFrame = this.animation.getKeyFrame(stateTime, true);

    }

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

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        stateTime = 0f;
    }

    //Should really not be in this class but oh well
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

    public TextureRegion getCurrentFrame() {
        return this.currentFrame;
    }
}
