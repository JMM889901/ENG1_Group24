package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import group24.piazzapanic.ui.Wigit;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.Base;
import group24.piazzapanic.maths.Vector2;

public class TextBox extends Wigit {
    private String text;
    private Color colour;
    private BitmapFont format;

    public TextBox(String wigitID, Vector2 relativeLocation, String text, Color backGroundColour, BitmapFont textFormat) {
        super(wigitID, relativeLocation);
        this.text = text;
        this.colour = colour;
        format = textFormat;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        //FontHandler.typeface.setColor(textColour);
        FontHandler.typeface.draw(Base.batch, text, location.x, location.y);
    }
}