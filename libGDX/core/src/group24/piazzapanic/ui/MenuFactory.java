package group24.piazzapanic.ui;

import java.util.ArrayList;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture; // This isn't needed yet, but will be when implementing images.
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

    /*
        Libgdx allign nums are different to ours
    	static public final int center = 1 << 0;
        static public final int top = 1 << 1;
        static public final int bottom = 1 << 2;
        static public final int left = 1 << 3;
        static public final int right = 1 << 4;
        
        static public final int topLeft = top | left;
        static public final int topRight = top | right;
        static public final int bottomLeft = bottom | left;
        static public final int bottomRight = bottom | right;
    */
    public static Stage createMainMenuStage() {

        Stage stage = new Stage();
        CharSequence TitleText = "Piazza Panic";
        Label Title = new Label(TitleText, new LabelStyle(FontHandler.titleFormat, Color.BLACK));
        Vector2 coords = new Vector2(0.5, 0.7);
        Title.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), 1);
        //Title.setAlignment(Align.CENTRE);
        stage.addActor(Title);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = FontHandler.subtitleFormat;
        textButtonStyle.fontColor = Color.BLACK;
        coords = new Vector2(0.5, 0.5);
        TextButton button = new TextButton("Play Game", textButtonStyle);
        button.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), 1);
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO Auto-generated method stub
                System.out.print("Play game");
            }

        });
        stage.addActor(button);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = FontHandler.textButtonFormat;
        textButtonStyle.fontColor = Color.BLACK;
        textButtonStyle.overFontColor = Color.BLUE;
        coords = new Vector2(0.5, 0.4);
        TextButton button2 = new TextButton("Options", textButtonStyle);
        button2.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), 1);
        button2.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO Auto-generated method stub
                System.out.print("Open Options");
                stageManager.setActiveStage("Options");
            }

        });
        stage.addActor(button2);
        return stage;
    }

    public static Stage createOptionsMenuStage() {
        Stage stage = new Stage();
        CharSequence TitleText = "Options";
        Label Title = new Label(TitleText, new LabelStyle(FontHandler.titleFormat, Color.BLACK));
        Vector2 coords = new Vector2(0.5, 0.7);
        Title.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), 1);
        //Title.setAlignment(Align.CENTRE);
        stage.addActor(Title);

        CharSequence sliderTextString = "Sound";
        Label sliderText = new Label(sliderTextString,
                new LabelStyle(FontHandler.subtitleFormat, Color.BLACK));
        coords = new Vector2(0.5, 0.55);
        sliderText.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), 1);
        //Title.setAlignment(Align.CENTRE);
        stage.addActor(sliderText);

        Skin skin = new Skin(Gdx.files.internal("testSkin/uiskin.json"));
        Slider scrollPane = new Slider(0, 100, 5, false, skin);
        coords = new Vector2(0.5, 0.5);
        scrollPane.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), 1);

        stage.addActor(scrollPane);
        return stage;
    }
}
