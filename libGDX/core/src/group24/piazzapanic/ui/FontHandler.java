package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Everything here applies gamewide and are resources to be used by objects in
 * the game.
 */
public class FontHandler {
    //public static final String FONT_PATH = "fonts/liberation-serif/LiberationSerif-Regular.ttf";
    public static final String FONT_PATH = "fonts/ArcadeFont.ttf";
    public static BitmapFont titleFormat;
    public static BitmapFont subtitleFormat;
    public static BitmapFont testFormat;
    public static BitmapFont textButtonFormat;

    public static void create() {
        // Full disclosure, this bit of code is more or less copied from the
        // documentation.
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 72;
        titleFormat = generator.generateFont(parameter);

        parameter.size = 36;
        subtitleFormat = generator.generateFont(parameter);

        parameter.size = 24;
        textButtonFormat = generator.generateFont(parameter);

        parameter.size = 10;
        testFormat = generator.generateFont(parameter);

        generator.dispose();
    }

    public static void dispose() {
        // Make sure all fonts created above are disposed of here.
        titleFormat.dispose();
    }
}
