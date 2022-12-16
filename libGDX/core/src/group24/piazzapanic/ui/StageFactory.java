package group24.piazzapanic.ui;

import java.util.ArrayList;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture; // This isn't needed yet, but will be when implementing images.
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

/**
 * Class responsible for creating stages, may get quite long so we may need a better way of doing this
 * */
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

    //Main menu
    public static Stage createMainMenuStage() {
        //Title
        Stage stage = new Stage();
        CharSequence TitleText = "Piazza Panic!";
        Label Title = new Label(TitleText, new LabelStyle(FontHandler.titleFormat, Color.WHITE));
        Vector2 coords = new Vector2(0.5, 0.7);
        Title.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        //Title.setAlignment(Align.CENTRE);
        stage.addActor(Title);
        //Play game button
        TextButton button = WidgetFactory.createTextButton(FontHandler.subtitleFormat, Color.WHITE,
                new Vector2(0.5, 0.5), "Play game", Align.center);
        button.getStyle().overFontColor = Color.BLUE;
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
        TextButton button2 = WidgetFactory.createTextButton(FontHandler.subtitleFormat, Color.WHITE,
                new Vector2(0.5, 0.4), "Options", Align.center);
        button2.getStyle().overFontColor = Color.BLUE;
        //Create onclick function
        button2.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.print("Open Options");
                StageManager.setActiveStage("Options");
            }

        });
        stage.addActor(button2);
        StageAnimation ChefAnimation = new StageAnimation("chef-idle/chef_idle.png", 6, 6, 1, 20, 20, 154, 307);
        stage.addActor(ChefAnimation);
        return stage;
    }

    public static Stage createOptionsMenuStage() {
        //Title
        Stage stage = new Stage();
        CharSequence TitleText = "Options";
        Label Title = new Label(TitleText, new LabelStyle(FontHandler.subtitleFormat, Color.WHITE));
        Vector2 coords = new Vector2(0.5, 0.7);
        Title.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        stage.addActor(Title);

        //Volume bar label
        CharSequence sliderTextString = "Sound";
        Label sliderText = new Label(sliderTextString, new LabelStyle(FontHandler.textButtonFormat, Color.WHITE));
        coords = new Vector2(0.5, 0.55);
        sliderText.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        stage.addActor(sliderText);

        //Volume bar
        Skin skin = new Skin(Gdx.files.internal("testSkin/uiskin.json"));
        Slider scrollPane = new Slider(0, 100, 5, false, skin);
        coords = new Vector2(0.5, 0.5);
        scrollPane.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        stage.addActor(scrollPane);
        //Add return to main button
        TextButton button2 = WidgetFactory.createTextButton(FontHandler.textButtonFormat, Color.WHITE,
                new Vector2(0.2, 0.7), "Back", Align.right);
        button2.getStyle().overFontColor = Color.BLUE;
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

        return stage;
    }
}
