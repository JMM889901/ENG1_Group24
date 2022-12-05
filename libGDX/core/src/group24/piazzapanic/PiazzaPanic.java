package group24.piazzapanic;

//import java.util.HashMap;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
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

	@Override
	public void create() {
		// In desktop/build/src/group24/piazzapanic/DesktopLauncher.java:
		// `config.setResizable(false)` has been added, so the user can't drag to
		// resize.
		// This line sets the size of the window, which shall not change during the
		// game.
		Gdx.graphics.setWindowedMode(Base.WINDOW_WIDTH, Base.WINDOW_HEIGHT);

		FontHandler.create();

		Base.batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		menus.add(MenuFactory.createMainMenu());
		// menus.add(MenuFactory.createCoordGrid());
		// menus.add(MenuFactory.createPinTest());
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
		Base.batch.begin();
		// Base.batch.draw(img, 0, 0);
		menus.get(0).render();
		Base.batch.end();
	}

	@Override
	public void dispose() {
		Base.batch.dispose();
		FontHandler.dispose();
		img.dispose();
	}
}
