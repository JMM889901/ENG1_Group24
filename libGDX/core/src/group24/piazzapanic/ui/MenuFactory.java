package group24.piazzapanic.ui;

import java.util.ArrayList;

import org.w3c.dom.Text;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture; // This isn't needed yet, but will be when implementing images.

import group24.piazzapanic.ui.Menu;
import group24.piazzapanic.ui.TextBox;
import group24.piazzapanic.maths.Vector2;

/**
 * This class is just a repository of all the methods to generate menu screens.
 */
public class MenuFactory {
    /** Creates a main menu and adds text and buttons to it. */
    public static Menu createMainMenu() {
        Menu mainMenu = new Menu("mainMenu");

        TextBox title = new TextBox("txt_title", new Vector2(0.5f, 0.5f), "Piazza Panic", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_centre);

        mainMenu.addWigit(title);

        return mainMenu;
    }

    /*
     * Debug function to create a grid of coordinates on the screen
     */
    public static Menu createCoordGrid() {
        Menu coordMenu = new Menu("coordMenu");
        for (float x = 0; x <= 1; x += 0.1) {
            for (float y = 0; y <= 1; y += 0.1) {
                TextBox coord = new TextBox("coord" + x + y, new Vector2(x, y), x + "," + y, Color.WHITE,
                        FontHandler.testFormat);
                coordMenu.addWigit(coord);
            }
        }
        return coordMenu;
    }

    // Demonstrate different pins
    public static Menu createPinTest() {
        Menu pinMenu = new Menu("pinMenu");
        TextBox centre = new TextBox("centre", new Vector2(0.5, 0.5), "centre", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_centre);

        TextBox left = new TextBox("left", new Vector2(0.3, 0.5), "left", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_left);

        TextBox right = new TextBox("right", new Vector2(0.3, 0.5), "right", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_right);

        TextBox top = new TextBox("top", new Vector2(0.3, 0.5), "top", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_top);

        TextBox bottom = new TextBox("bottom", new Vector2(0.3, 0.5), "bottom", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_bottom);

        TextBox topLeft = new TextBox("topLeft", new Vector2(0.7, 0.5), "topLeft", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_topLeft);

        TextBox topRight = new TextBox("topRight", new Vector2(0.7, 0.5), "topRight", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_topRight);

        TextBox bottomLeft = new TextBox("bottomLeft", new Vector2(0.7, 0.5), "bottomLeft", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_bottomLeft);

        TextBox bottomRight = new TextBox("bottomRight", new Vector2(0.7, 0.5), "bottomRight", Color.WHITE,
                FontHandler.titleFormat, TextBox.pin_bottomRight);

        pinMenu.addWigit(top);
        pinMenu.addWigit(topLeft);
        pinMenu.addWigit(topRight);
        pinMenu.addWigit(bottom);
        pinMenu.addWigit(bottomLeft);
        pinMenu.addWigit(bottomRight);
        pinMenu.addWigit(left);
        pinMenu.addWigit(right);
        pinMenu.addWigit(centre);
        return pinMenu;
    }
}
