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
import com.badlogic.gdx.utils.Align;

public class StageFactory {
    /*
    This class uses libgdx align not ours
    
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
        //Title
        Stage stage = new Stage();
        CharSequence TitleText = "Piazza Panic";
        Label Title = new Label(TitleText, new LabelStyle(FontHandler.textButtonFormat, Color.BLACK));
        Vector2 coords = new Vector2(0.5, 0.7);
        Title.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        //Title.setAlignment(Align.CENTRE);
        stage.addActor(Title);

        //Play game button
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = FontHandler.textButtonFormat;
        textButtonStyle.fontColor = Color.BLACK;
        textButtonStyle.overFontColor = Color.BLUE;
        coords = new Vector2(0.5, 0.5);
        TextButton button = new TextButton("Play Game", textButtonStyle);
        button.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        //Create onclick function
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO Auto-generated method stub
                System.out.print("Play game");
            }

        });
        stage.addActor(button);

        //Open options button
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = FontHandler.textButtonFormat;
        textButtonStyle.fontColor = Color.BLACK;
        textButtonStyle.overFontColor = Color.BLUE;
        coords = new Vector2(0.5, 0.4);
        TextButton button2 = new TextButton("Options", textButtonStyle);
        button2.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        //Create onclick function
        button2.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO Auto-generated method stub
                System.out.print("Open Options");
                StageManager.setActiveStage("Options");
            }

        });
        stage.addActor(button2);
        return stage;
    }

    public static Stage createOptionsMenuStage() {
        //Title
        Stage stage = new Stage();
        CharSequence TitleText = "Options";
        Label Title = new Label(TitleText, new LabelStyle(FontHandler.textButtonFormat, Color.BLACK));
        Vector2 coords = new Vector2(0.5, 0.7);
        Title.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        stage.addActor(Title);

        //Volume bar label
        CharSequence sliderTextString = "Sound";
        Label sliderText = new Label(sliderTextString, new LabelStyle(FontHandler.subtitleFormat, Color.BLACK));
        coords = new Vector2(0.5, 0.55);
        sliderText.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        stage.addActor(sliderText);

        //Volume bar
        Skin skin = new Skin(Gdx.files.internal("testSkin/uiskin.json"));
        Slider scrollPane = new Slider(0, 100, 5, false, skin);
        coords = new Vector2(0.5, 0.5);
        scrollPane.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);

        //Add return to main button
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = FontHandler.textButtonFormat;
        textButtonStyle.fontColor = Color.BLACK;
        textButtonStyle.overFontColor = Color.BLUE;
        coords = new Vector2(0.3, 0.7);
        TextButton button2 = new TextButton("Back to menu", textButtonStyle);
        button2.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        //Create onclick function
        button2.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO Auto-generated method stub
                System.out.print("Open Main");
                StageManager.setActiveStage("MainMenu");
            }

        });
        stage.addActor(button2);
        stage.addActor(scrollPane);
        return stage;
    }
}
