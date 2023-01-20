package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.Movable;;

public class Station extends Image {

    public Station(Texture Texture) {
        super(Texture);
        super.setWidth(Base.tile_pixel_width);
        super.setHeight(Base.tile_pixel_height);
    }

    //protected Vector2 location;
    //protected Texture sprite;
    protected Movable item;

    protected int gridX; // Once these are set, don't change them.
    protected int gridY;

    /**
     * Creates a new station, which knows it's own location to be as specified.
     * @param gridX The x coordinate of the station in the grid.
     * @param gridY The y coordinate of the station in the grid.
     */
    public Station(int gridX, int gridY) {
        super();
        this.gridX = gridX;
        this.gridY = gridY;
    }

    /**
     * Creates a new station without specifiying it's location. Deprecated.
     */
    public Station() {
        super();
        System.out.println("Deprecated constructor called for Station.");
    }

    /**
     * Puts `item` on the station if there is no item already.
     * @param item
     * @return true if the item was placed, false if there was already an item on the station.
     */
    public boolean placeItem(Movable item) {
        if (canPlaceItem()) {
            this.item = item;
            return true;
        } else
            return false;
    }

    /**
     * A private convenience method to check if an item can be placed on the station.
     * @return Whether an item can be placed on the station.
     */
    private boolean canPlaceItem() {
        return !hasItem();
    }

    /**
     * Checks if the station has an item on it.
     * @return Whether the station has an item/items on it.
     */
    public boolean hasItem() {
        return this.item != null;
    }

    public Movable takeItem() {
        if (hasItem()) {
            Movable tmp = this.item;
            this.item = null;
            return tmp;
        } else
            return null;
    }



    @Override
    public void draw(Batch arg0, float arg1) {
        super.draw(arg0, arg1);
        if (this.item != null) {
            this.item.drawItem((int) (this.getX() + ((Base.tile_pixel_width / 2))),
                    (int) (this.getY() + (Base.tile_pixel_height / 2)));
        }
    }

}
