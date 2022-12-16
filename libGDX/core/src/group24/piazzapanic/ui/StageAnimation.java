package group24.piazzapanic.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.compression.lzma.Base;

public class StageAnimation extends Image {
    Animation<TextureRegion> animation;
    Texture animationSheet;
    Float stateTime;
    int x;
    int y;
    int width;
    int height;
    TextureRegion currentFrame;

    public StageAnimation(String path, int frameCount, int framesPerLine, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        animationSheet = new Texture(path);
        TextureRegion[][] tmp = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / framesPerLine,
                animationSheet.getHeight());

        TextureRegion[] frames = new TextureRegion[frameCount];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 6; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation<TextureRegion>(1 / 9f, frames);
        stateTime = 0f;
        currentFrame = this.animation.getKeyFrame(stateTime, true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(currentFrame, x, y, width, height); // Draw current frame at (50, 50)
    }

    @Override
    public void act(float delta) {
        this.stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        currentFrame = this.animation.getKeyFrame(stateTime, true);

    }
}
