package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import group24.piazzapanic.maths.Vector2;

/**
 * A collection of convenience functions for creating widgets (all static methods).
 */
public class WidgetFactory {
    
    /**
     * Create a text button with set location, dimensions and text style.
     * @param font A font object for the text.
     * @param color The colour of the text.
     * @param relativePos The position of the button as a proportion of the screen size.
     * @param text The button's text.
     * @param align Where the text anchors/aligns to on the button.
     * @return A filled out text button.
     */
    public static TextButton createTextButton(BitmapFont font, Color color, Vector2 relativePos, String text, int align) {
        // This function really just combines lines of code into a single function call.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = color;
        TextButton button = new TextButton(text, textButtonStyle);
        button.setPosition(relativePos.getAbsoluteX(), relativePos.getAbsoluteY(), align);
        return button;
    }
}
