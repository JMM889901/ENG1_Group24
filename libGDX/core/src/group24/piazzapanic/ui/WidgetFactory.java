package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import group24.piazzapanic.maths.Vector2;

public class WidgetFactory {
    //Helper function to create text buttons using relative instead of absolute positions and to reduce code bloat
    public static TextButton createTextButton(BitmapFont font, Color color, Vector2 relativePos, String text,
            int align) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = color;
        TextButton button = new TextButton(text, textButtonStyle);
        button.setPosition(relativePos.getAbsoluteX(), relativePos.getAbsoluteY(), align);
        return button;

    }
}
