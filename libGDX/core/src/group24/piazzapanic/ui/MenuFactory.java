package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;  // This isn't needed yet, but will be when implementing images.

import group24.piazzapanic.ui.Menu;
import group24.piazzapanic.ui.TextBox;
import group24.piazzapanic.maths.Vector2;

/** This class is just a repository of all the methods to generate menu screens. */
public class MenuFactory {
    /** Creates a main menu and adds text and buttons to it. */
    public static Menu createMainMenu() {
        Menu mainMenu = new Menu("mainMenu");

        TextBox title = new TextBox("txt_title", new Vector2(0.175f, 0.5f), "Piazza Panic", Color.WHITE, FontHandler.titleFormat);

        mainMenu.addWigit(title);

        return mainMenu;
    }
}
