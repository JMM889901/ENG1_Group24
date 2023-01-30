package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * A convenience class to create fonts that will be used in titles, buttons etc.
 */
public class FontHandler {
    /** The path to the font used for rendered text. */
    public static final String FONT_PATH = "fonts/ArcadeFont.ttf";
    /** The font used by large text/menu titles. */
    public static BitmapFont titleFormat;  // Have static objects for the fonts used in the game.
    /** The font used by subtitles. */
    public static BitmapFont subtitleFormat;
    /** The font used on smaller buttons. */
    public static BitmapFont textButtonFormat;
    /** The font used for content. */
    public static BitmapFont contentFormat;

    /**
     * Create the fonts and store them as the statics.
     */
    public static void create() {
        // Full disclosure, a bunch of the lines below have just been taken from the docs.
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 72;
        titleFormat = generator.generateFont(parameter);

        parameter.size = 36;
        subtitleFormat = generator.generateFont(parameter);

        parameter.size = 24;
        textButtonFormat = generator.generateFont(parameter);

        parameter.size = 24;
        contentFormat = generator.generateFont(parameter);

        generator.dispose();
    }

    public static void dispose() {
        // Make sure all fonts created above are disposed of here.
        titleFormat.dispose();
        subtitleFormat.dispose();
        textButtonFormat.dispose();
    }
}
