package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import group24.piazzapanic.ui.Wigit;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.Base;
import group24.piazzapanic.maths.Vector2;

public class TextBox extends Wigit {
    // Im definitely forgetting the better way to do this
    // Used by constructor to alter coords based on text size
    public static final int pin_left = 0;
    public static final int pin_topLeft = 1;
    public static final int pin_bottomLeft = 2;
    public static final int pin_bottom = 3;
    public static final int pin_centre = 4;
    public static final int pin_top = 5;
    public static final int pin_topRight = 6;
    public static final int pin_bottomRight = 7;
    public static final int pin_right = 8;

    private String text;
    private Color colour;
    private BitmapFont textFormat;

    // fontX = (gameWidth - font.getBounds(fontText).width) / 2
    public TextBox(String wigitID, Vector2 relativeLocation, String text, Color backGroundColour,
            BitmapFont textFormat) {
        super(wigitID, relativeLocation);
        this.text = text;
        this.colour = colour;
        this.textFormat = textFormat;
    }

    public TextBox(String wigitID, Vector2 relativeLocation, String text, Color backGroundColour,
            BitmapFont textFormat, int attachPoint) {
        super(wigitID, relativeLocation);
        this.text = text;
        this.colour = colour;
        this.textFormat = textFormat;
        pin(attachPoint);
    }

    @Override
    public void pin(int pin) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(this.textFormat, this.text);
        // Handling X axis manipulation
        switch (pin) {
            case pin_right:
            case pin_bottomRight:
            case pin_topRight:
                location.x = (location.x - Math.toIntExact(Math.round(layout.width)));
                break;
            case pin_centre:
            case pin_bottom:
            case pin_top:
                location.x = (location.x - (Math.toIntExact(Math.round(layout.width / 2))));
                break;
        }
        // Handle Y axis manipulation
        switch (pin) {
            case pin_bottomLeft:
            case pin_bottomRight:
            case pin_bottom:
                location.y = (location.y + Math.toIntExact(Math.round(layout.height)));
                break;
            case pin_left:
            case pin_right:
            case pin_centre:
                location.y = (location.y + (Math.toIntExact(Math.round(layout.height / 2))));
                break;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        // FontHandler.typeface.setColor(textColour);
        textFormat.draw(Base.batch, text, location.x, location.y);
    }
}