package group24.piazzapanic;

//import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.ui.StageManager;

/**
 * The main class of the game.
 */
public class PiazzaPanic extends ApplicationAdapter {

    /** The viewport of the game. */
    Viewport viewport;

    /**
     * Called when the Application is first created.
     */
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


        StageManager.init();
    }

    /**
     * Render the game.
     */
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
     * Called when the Application is resized.
     * @param width the new width in pixels
     * @param height the new height in pixels
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /** Destroy the game. */
    @Override
    public void dispose() {
        Base.dispose();
        FontHandler.dispose();
        //idleChefSheet.dispose();
    }
}
