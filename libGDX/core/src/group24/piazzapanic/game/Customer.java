package group24.piazzapanic.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import group24.piazzapanic.levelElements.Dish;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.ui.StageAnimation;

public class Customer extends StageAnimation {
    /**
     * The height of the customer
     */
    public static final int entityHeight = 153;
    /**
     * The width of the customer
     */
    public static final int entityWidth = 77;
    /**
     * The name for the customer
     */
//    private String name; //Probably pointless
    /**
     * The order for the customer, a {@link Dish}
     */
    private Dish order;
    /**
     * The text for the customer's order
     */
    private CharSequence orderText; //temporary

    /**
     * The time limit for the customer's order to be filled.
     */
    private float timeLimit;
    /**
     * The text bubble for the customer
     */
    private final Label textBubble;

    /**
     * Constructor for the Customer class
     */
    public Customer() {
        super(GameData.customerSpriteSheets.get(GameData.rand.nextInt(GameData.customerSpriteSheets.size())), 6, 6, 1,
                20, 20, entityWidth, entityHeight);
//        timeLimit = 30f;
        this.timeLimit = 15;
        this.orderText = ":3";
        LabelStyle style = new LabelStyle();
        style.font = FontHandler.subtitleFormat;
        style.fontColor = Color.WHITE;
        this.textBubble = new Label(orderText, style);
        this.textBubble.setPosition(this.getX(), this.getY() + entityHeight);
        this.order = Dish.Dishes.get(GameData.rand.nextInt(Dish.Dishes.size()));
    }

    /** Fulfil the customer's order i.e. they have had their dish served and are happy now :) */
    public void fulfillOrder() {
        this.textBubble.setText("Done");
        GameData.addScore(1);
    }

    /** Update the customer's order text box location with the customer's location */
    @Override
    public void setX(float x) {
        super.setX(x);
        if (this.textBubble != null)
            this.textBubble.setX(x);
    }

    /**
     * Set the Y position of the customer and their text bubble
     * @param y The new Y position
     */
    @Override
    public void setY(float y) {
        super.setY(y);
        if (this.textBubble != null)
            this.textBubble.setY(y + entityHeight);
    }

    /**
     * Draw the customer and their text bubble
     * @param batch The batch it's being drawn as part of
     * @param parentAlpha The alpha value of the parent
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        textBubble.draw(batch, parentAlpha);
    }

    /**
     * Get the customer's order
     * @return A {@link Dish} representing the customer's order
     */
    public Dish getOrder() {
        return this.order;
    }
}
