package group24.piazzapanic;

//import java.util.HashMap;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.Gdx;

import group24.piazzapanic.ui.Menu;
import group24.piazzapanic.ui.MenuFactory;
import group24.piazzapanic.Base;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.ui.StageManager;;

public class PiazzaPanic extends ApplicationAdapter {
    Texture img;

    private String state;
    private ArrayList<Menu> menus = new ArrayList<Menu>();

    SpriteBatch batch;
    PerspectiveCamera camera;
    Viewport viewport;
    Stage stage;
    TextButton button;
    StageManager stageManager;

    @Override
    public void create() {
        Base.init();
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

        Base.batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");

        stageManager = new StageManager();
        //menus.add(MenuFactory.createMainMenu());
        //menus.add(MenuFactory.createOptionsMenu());
        // menus.add(MenuFactory.createCoordGrid());
        // menus.add(MenuFactory.createPinTest());
        //Menu buttonTest = MenuFactory.createButtonTest();
        //menus.add(buttonTest);
        //Gdx.input.setInputProcessor(stage);
        //buttonTest.setActive();
        //TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        //textButtonStyle.font = FontHandler.subtitleFormat;
        //textButtonStyle.fontColor = Color.BLACK;
        //button = new TextButton("Custom Btn ", textButtonStyle);

    }

    @Override
    public void render() {
        ScreenUtils.clear(1f, 0.8f, 0.5f, 1);
        viewport.apply();
        Base.batch.setProjectionMatrix(viewport.getCamera().combined);
        Base.batch.begin();
        // Base.batch.draw(img, 0, 0);
        //menus.get(0).render();
        stageManager.getActiveStage().draw();
        //button.draw(Base.batch, 100);
        Base.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        Base.batch.dispose();
        FontHandler.dispose();
        img.dispose();
    }
}
