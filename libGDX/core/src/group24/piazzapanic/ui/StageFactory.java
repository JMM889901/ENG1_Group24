package group24.piazzapanic.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.GameLoop;
import group24.piazzapanic.maths.Vector2;
import com.badlogic.gdx.utils.Align;

/**
 * Class responsible for creating stages.
 */
public class StageFactory {

    /**
     * Create the main menu stage. 
     * @return The new stage created.
     */
    public static Stage createMainMenuStage() {
        // Title
        Stage stage = new Stage();
        GameData.music = Gdx.audio.newMusic(Gdx.files.internal("TITLE-MUSIC.mp3"));
        GameData.music.setLooping(true);
        // GameData.music.play();
        CharSequence TitleText = "Piazza Panic!";
        Label Title = new Label(TitleText, new LabelStyle(FontHandler.titleFormat, Color.WHITE));
        Vector2 coords = new Vector2(0.5, 0.7);
        Title.setPosition(coords.getAbsoluteX(), coords.getAbsoluteY(), Align.center);
        stage.addActor(Title);

        // Play button
        TextButton button = WidgetFactory.createTextButton(FontHandler.subtitleFormat, Color.WHITE,
                new Vector2(0.5, 0.5), "Play game", Align.center);
        button.getStyle().overFontColor = Color.BLUE;
        //Create onclick function
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.print("Open Game");
                GameData.music.dispose();
                GameData.music = Gdx.audio.newMusic(Gdx.files.internal("MAIN-MUSIC.mp3"));
                GameData.music.setLooping(true);
                //  GameData.music.play();
                StageManager.setActiveStage("Game");
            }

        });
        stage.addActor(button);

        // Options button
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

        // Temporary (?) dancing chef.
        StageAnimation ChefAnimation = new StageAnimation("chef/chef_idle_front_selected.png", 6, 6, 1, 20, 20, 154,
                307);
        StageAnimation ChefAnimation1 = new StageAnimation("chef/chef_1_idle_front_selected.png", 6, 6, 1,
                Base.WINDOW_WIDTH - 180, 20, 154, 307);

        stage.addActor(ChefAnimation);
        stage.addActor(ChefAnimation1);
        return stage;
    }

    /**
     * Create the options menu stage.
     * @return The new stage created.
     */
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
                System.out.print("Open Main");
                StageManager.setActiveStage("MainMenu");
            }

        });
        stage.addActor(button2);

        return stage;
    }

    /**
     * Create the pause menu stage.
     * @return The new stage created.
     */
    public static Stage createPauseMenuStage() {
        Stage stage = new Stage() {
            @Override
            public void act() {
                if (Gdx.input.isKeyJustPressed(Base.PAUSE_KEY)) {
                    StageManager.setActiveStage("Game");
                }
                super.act();
            }
        };
        TextButton button = WidgetFactory.createTextButton(FontHandler.subtitleFormat, Color.WHITE,
                new Vector2(0.5, 0.5), "Return to game", Align.center);
        button.getStyle().overFontColor = Color.BLUE;
        //Create onclick function
        button.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.print("Open Game");
                //  GameData.music.play();
                StageManager.setActiveStage("Game");
            }

        });
        stage.addActor(button);

        TextButton restart = WidgetFactory.createTextButton(FontHandler.subtitleFormat, Color.WHITE,
                new Vector2(0.5, 0.4), "Restart", Align.center);
        restart.getStyle().overFontColor = Color.BLUE;
        //Create onclick function
        restart.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.print("Open Game");
                GameData.gameLoop = new GameLoop();
                StageManager.addStage("Game", GameData.gameLoop);
                StageManager.setActiveStage("Game");
            }

        });
        stage.addActor(restart);
        return stage;
    }
}
