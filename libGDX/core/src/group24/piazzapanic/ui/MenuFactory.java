package group24.piazzapanic.ui;

import java.util.ArrayList;

import org.w3c.dom.Text;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture; // This isn't needed yet, but will be when implementing images.

import group24.piazzapanic.ui.Menu;
import group24.piazzapanic.ui.TextBox;
import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.ui.Align;

/** This class is just a repository of all the methods to generate menu screens. */
public class MenuFactory {
    /** Creates a main menu and adds text and buttons to it. */
    public static Menu createMainMenu() {
        Menu mainMenu = new Menu("mainMenu");

        TextBox title = new TextBox("txt_title", new Vector2(0.5f, 0.5f), "=Piazza Panic=", Color.WHITE,
                FontHandler.titleFormat, Align.CENTRE);
        
        TextBox title2 = new TextBox("txt_title2", new Vector2(0.5f, 0.5f), "ciazzi PanaP", Color.WHITE,
                FontHandler.titleFormat, Align.TOPLEFT);

        mainMenu.addWigit(title);
        mainMenu.addWigit(title2);

        return mainMenu;
    }

    public static Menu createOptionsMenu() {
        Menu optionsMenu = new Menu("optionsMenu");

        TextBox title = new TextBox("txt_optionstitle", new Vector2(0.5f, 0.8f), "Options", Color.WHITE,
                FontHandler.subtitleFormat);

        optionsMenu.addWigit(title);

        return optionsMenu;
    }
}
