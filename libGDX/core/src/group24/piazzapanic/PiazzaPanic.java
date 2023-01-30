package group24.piazzapanic;

//import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.ui.StageManager;

public class PiazzaPanic extends ApplicationAdapter {
    Texture img;

    SpriteBatch batch;
    PerspectiveCamera camera;
    Viewport viewport;
    Stage stage;
    TextButton button;
    StageManager stageManager;
    Float stateTime;
    Animation<TextureRegion> chefIdle;
    //Texture idleChefSheet;

    @Override
    public void create() {
        Base.init();
        GameData.init();
        // In desktop/build/src/group24/piazzapanic/DesktopLauncher.java:
        // `config.setResizable(false)` has been added, so the user can't drag to
        // resize.
        // This line sets the size of the window, which shall not change during the
        // game.

        viewport = new ExtendViewport(Base.WINDOW_WIDTH, Base.WINDOW_HEIGHT);
        viewport.getCamera().position.set(Base.WINDOW_WIDTH / 2, Base.WINDOW_HEIGHT / 2, 0);
        // position.set() accepts floats.

        Gdx.graphics.setWindowedMode(Base.WINDOW_WIDTH, Base.WINDOW_HEIGHT);
        FontHandler.create();

        img = new Texture("badlogic.jpg");

        StageManager.init();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        viewport.apply();
        Base.batch.setProjectionMatrix(viewport.getCamera().combined);

        // Bypass some UI for sake of testing.
        if (StageManager.getActiveStageName().equals("MainMenu")) {
            if (Gdx.input.isKeyPressed(Base.SELECT_KEY)) {
                StageManager.setActiveStage("Game");
            }
        }
        StageManager.getActiveStage().act();
        Base.batch.begin();

        StageManager.getActiveStage().draw();
        Base.batch.end();
    }

    /**
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        Base.dispose();
        FontHandler.dispose();
        img.dispose();
        //idleChefSheet.dispose();
    }
}
