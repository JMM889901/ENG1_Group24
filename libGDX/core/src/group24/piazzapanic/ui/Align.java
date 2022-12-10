package group24.piazzapanic.ui;

/** This class has some constants and helper functions for aligning text in the UI.
 * 
 * While it looks
 * like it should use an enum, the integer arithmetic means the code is simpler without the enum
 * and doesn't mislead a reader into thinking it doesn't matter what value is behind the constants.
 */
public class Align {
    public static final int TOPLEFT = 11;
    public static final int TOP = 12;
    public static final int TOPRIGHT = 13;
    public static final int LEFT = 21;
    public static final int CENTRE = 22;
    public static final int RIGHT = 23;
    public static final int BOTTOMLEFT = 31;
    public static final int BOTTOM = 32;
    public static final int BOTTOMRIGHT = 33;

    public static final int COLUMN_LEFT = 1;
    public static final int COLUMN_CENTRE = 2;
    public static final int COLUMN_RIGHT = 3;
    public static final int ROW_TOP = 10;
    public static final int ROW_CENTRE = 20;
    public static final int ROW_BOTTOM = 30;

    /** Returns the column of the alignment constant.
     * 
     * While it may be tempting to reuse LEFT, CENTRE, RIGHT... instead of COLUMN_LEFT etc., it's
     * more clear when debugging that 10 means you've accidentally set a text alignment to a
     * category of alignments rather than 12, which can mean either and get easily overlooked.
     * 
     * @param align Which specific alignment to get the column of.
     * 
     * @return Which column the alignment fits in.
     */
    public static int getColumn(int align) {
        return align % 10;
    }

    /** Returns the row of the alignment constant.
     * 
     * While it may be tempting to reuse TOP, CENTRE, BOTTOM... instead of ROW_TOP etc., it's
     * more clear when debugging that 10 means you've accidentally set a text alignment to a
     * category of alignments rather than 12, which can mean either and get easily overlooked.
     * 
     * @param align Which specific alignment to get the row of.
     * 
     * @return Which row the alignment fits in.
     */
    public static int getRow(int align) {
        // This makes use of division between 2 ints, 11 / 10 = 1, 12 / 10 = 1, 13 / 10 = 1, etc.
        return (align / 10) * 10;
    }
}