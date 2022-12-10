package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import group24.piazzapanic.ui.Textable;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.Base;
import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.ui.Align;

public class TextBox extends Textable {
    private Color colour;

    public TextBox(String wigitID, Vector2 relativeLocation, String text, Color backGroundColour,
            BitmapFont textFormat, int textAlignment) {
        super(wigitID, relativeLocation);
        this.text = text;
        this.colour = colour;
        this.textFormat = textFormat;
        this.textAlignment = textAlignment;
        alignText();
    }

    public TextBox(String wigitID, Vector2 relativeLocation, String text, Color backGroundColour,
            BitmapFont textFormat) {
        // Just make use of the other constructor, but textAlign is CENTRE by default.
        this(wigitID, relativeLocation, text, backGroundColour, textFormat, Align.CENTRE);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        textFormat.draw(Base.batch, text, location.x, location.y);
    }
}