package group24.piazzapanic.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.ui.StageAnimation;

public class Customer extends StageAnimation {
    public static final int entityHeight = 153;
    public static final int entityWidth = 77;
    private String name; //Probably pointless
    private int order; //Should be dish class
    private CharSequence orderText; //temporary
    private float timeLimit;
    private Label textBubble;

    public Customer() {
        super(GameData.customerSpriteSheets.get(GameData.rand.nextInt(GameData.customerSpriteSheets.size())), 6, 6, 1, 20, 20, entityWidth, entityHeight);
        timeLimit = 30f;
        this.timeLimit = 15;
        this.orderText = ":3";
        LabelStyle style = new LabelStyle();
        style.font = FontHandler.subtitleFormat;
        style.fontColor = Color.WHITE;
        this.textBubble = new Label(orderText, style);
        this.textBubble.setPosition(this.getX(), this.getY() + entityHeight);
    }

    public void fulfillOrder() {
        this.textBubble.setText("Done");
        GameData.addScore(1);
    }

    
    /** 
     * @param x
     */
    //Update text box with the customer
    @Override
    public void setX(float x) {
        super.setX(x);
        if (this.textBubble != null)
            this.textBubble.setX(x);
    }

    
    /** 
     * @param y
     */
    @Override
    public void setY(float y) {
        super.setY(y);
        if (this.textBubble != null)
            this.textBubble.setY(y + entityHeight);
    }

    
    /** 
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        textBubble.draw(batch, parentAlpha);
    }
}
