package group24.piazzapanic.ui;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import group24.piazzapanic.maths.Vector2;


public abstract class Textable extends Wigit {
    protected String text;
    protected BitmapFont textFormat;
    protected int textAlignment;
    
    public Textable(String wigitID, Vector2 relativeLocation) {
        super(wigitID, relativeLocation);
    }

    /** Aligns the text to the correct location based on the textAlignment variable. */
    protected void alignText() {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(this.textFormat, this.text);
        
        // Handling X axis manipulation.
        if (Align.getColumn(textAlignment) == Align.COLUMN_RIGHT) {
            // Shift text to the right.
            location.x = (location.x - Math.toIntExact(Math.round(layout.width)));
        } else if (Align.getColumn(textAlignment) == Align.COLUMN_CENTRE) {
            // Shift text half way to the right.
            location.x = (location.x - (Math.toIntExact(Math.round(layout.width / 2))));
        }
        // Else, if left, text is already on the left, so leave as is.

        // Handle Y axis manipulation.
        if (Align.getRow(textAlignment) == Align.ROW_BOTTOM) {
            // Shift text to the bottom.
            location.y = (location.y + Math.toIntExact(Math.round(layout.height)));
        } else if (Align.getRow(textAlignment) == Align.ROW_CENTRE) {
            // Shift text half way to the bottom.
            location.y = (location.y + (Math.toIntExact(Math.round(layout.height / 2))));
        }
        // Else, if top, text is already at the top, so leave as is.
    }
}
