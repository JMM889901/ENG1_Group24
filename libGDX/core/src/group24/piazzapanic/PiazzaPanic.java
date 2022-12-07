package group24.piazzapanic;

//import java.util.HashMap;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

import group24.piazzapanic.ui.Menu;
import group24.piazzapanic.ui.MenuFactory;
import group24.piazzapanic.Base;
import group24.piazzapanic.ui.FontHandler;

public class PiazzaPanic extends ApplicationAdapter {
	Texture img;

	private String state;
	private ArrayList<Menu> menus = new ArrayList<Menu>();

	SpriteBatch batch;
	PerspectiveCamera camera;
	Viewport viewport;

	@Override
	public void create() {
		// In desktop/build/src/group24/piazzapanic/DesktopLauncher.java:
		// `config.setResizable(false)` has been added, so the user can't drag to
		// resize.
		// This line sets the size of the window, which shall not change during the
		// game.
		// Gdx.graphics.setWindowedMode(Base.WINDOW_WIDTH, Base.WINDOW_HEIGHT);

		viewport = new ExtendViewport(1280, 720);
		viewport.getCamera().position.set(640, 360, 0);

		FontHandler.create();

		Base.batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		// menus.add(MenuFactory.createMainMenu());
		menus.add(MenuFactory.createCoordGrid());
		// menus.add(MenuFactory.createPinTest());

	}

	// @Override
	// public void resize(int width, int height) {
	// viewport.update(width, height, true);
	// camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	// }

	@Override
	public void render() {
		ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
		viewport.apply();
		Base.batch.setProjectionMatrix(viewport.getCamera().combined);
		Base.batch.begin();

		Base.batch.draw(img, 0, 0);
		menus.get(0).render();
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
