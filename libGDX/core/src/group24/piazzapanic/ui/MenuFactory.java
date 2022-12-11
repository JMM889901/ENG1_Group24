package group24.piazzapanic.ui;

import java.util.ArrayList;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture; // This isn't needed yet, but will be when implementing images.
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import group24.piazzapanic.ui.Menu;
import group24.piazzapanic.ui.TextBox;
import group24.piazzapanic.maths.Vector2;

/** This class is just a repository of all the methods to generate menu screens. */
/** Currently also contains methods to produce menu elements since we are switching to stages
 * Stages are created in StageFactory
 */
public class MenuFactory {
    /** Creates a main menu and adds text and buttons to it. */
    public static Menu createMainMenu() {
        Menu mainMenu = new Menu("mainMenu");

        TextBox title = new TextBox("txt_title", new Vector2(0.5f, 0.5f), "=Piazza Panic=",
                Color.WHITE, FontHandler.titleFormat, Align.CENTRE);

        TextBox title2 = new TextBox("txt_title2", new Vector2(0.5f, 0.5f), "ciazzi PanaP",
                Color.WHITE, FontHandler.titleFormat, Align.TOPLEFT);

        mainMenu.addWigit(title);
        mainMenu.addWigit(title2);

        return mainMenu;
    }

    public static TextButton createTextButton(BitmapFont font, Color color, Vector2 relativePos, String text,
            int align) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = color;
        TextButton button = new TextButton(text, textButtonStyle);
        button.setPosition(relativePos.getAbsoluteX(), relativePos.getAbsoluteY(), align);
        return button;

    }

    public static Menu createOptionsMenu() {
        Menu optionsMenu = new Menu("optionsMenu");

        TextBox title = new TextBox("txt_optionstitle", new Vector2(0.5f, 0.8f), "Options",
                Color.WHITE, FontHandler.subtitleFormat);

        optionsMenu.addWigit(title);

        return optionsMenu;
    }

    public static Menu createButtonTest() {
        //Menu ButtonMenu = new Menu("ButtonTest");
        Stage ButtonStage = new Stage();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = FontHandler.subtitleFormat;
        textButtonStyle.fontColor = Color.BLACK;
        TextButton button = new TextButton("Custom Btn ", textButtonStyle);
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO Auto-generated method stub
                System.out.print("bruh");
            }

        });

        ButtonStage.addActor(button);
        Menu ButtonMenu = new Menu("ButtonTest", ButtonStage);
        return ButtonMenu;
    }

}
