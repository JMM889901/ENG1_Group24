package group24.piazzapanic.game;
import group24.piazzapanic.Physics.AnimatedMovable;
import group24.piazzapanic.levelElements.Movable;
import group24.piazzapanic.ui.StageAnimation;
import group24.piazzapanic.levelElements.stations.*;


/**
 * The Player class encapsulates player data (position, veolocity etc.), but does nothing itself.
 */
public class Player {
    public static final int TEXTURE_WIDTH = 48;
    public static final int TEXTURE_HEIGHT = 96;

    // How wide the player is in grid units. Applies to both "width" and "length"/"height".
    public static final double GRID_WIDTH = 0.6; // Don't set to more than 1.
    public static final float TEXTURE_SCALE = 1.3f; // Texture is scaled with this and GRID_WIDTH.

    public double x;
    public double y;
    public double x_vel = 0;
    public double y_vel = 0;
    public static double acceleration = 25; // Grid units per second squared.
    public static double deacceleration = 20;
    public static double maxSpeed = 3; // Grid units per second.
    public static double minSpeed = 0.4; // The player is deemed still if they are below this.
    public static double movementEpsilon = 0.01; // Just a small number to offset the player from
                                                  //collidable objects.
    public Movable holding; // The player's one-item inventory.

    public StageAnimation animation;

    public enum facing{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public facing direction;

    public Player(double x, double y, StageAnimation animation) {
        this.x = x;
        this.y = y;
        direction = facing.DOWN;
        this.animation = animation;
    }

    /**
     * Returns the y position of the top of the player's hitbox.
     */
    public double top() {
        return y + GRID_WIDTH / 2;
    }

    /**
     * Returns the y position of the bottom of the player's hitbox.
     */
    public double bottom() {
        return y - GRID_WIDTH / 2;
    }

    /**
     * Returns the x position of the right of the player's hitbox.
     */
    public double right() {
        return x + GRID_WIDTH / 2;
    }

    /**
     * Returns the x position of the left of the player's hitbox.
     */
    public double left() {
        return x - GRID_WIDTH / 2;
    }

    public boolean pickUp(){
        // Find the direction the player is facing
        // Then find the nearest object.
        Station activeStation;

        switch(this.direction){
            case UP:
                activeStation = GameData.level.getStation(Math.toIntExact(Math.round(Math.floor(this.x))), Math.toIntExact(Math.round(Math.floor(this.y + 1))));
                break;
            case DOWN:
                activeStation = GameData.level.getStation(Math.toIntExact(Math.round(Math.floor(this.x))), Math.toIntExact(Math.round(Math.floor(this.y - 1))));
                break;
            case LEFT:
                activeStation = GameData.level.getStation(Math.toIntExact(Math.round(Math.floor(this.x - 1))), Math.toIntExact(Math.round(Math.floor(this.y))));
                break;
            case RIGHT: 
                activeStation = GameData.level.getStation(Math.toIntExact(Math.round(Math.floor(this.x + 1))), Math.toIntExact(Math.round(Math.floor(this.y))));
                break;
            default:
                System.out.println("Bruh.");
                return false;
        }
        this.holding = activeStation.takeItem();
        return true;
    }

    public boolean putDown(){
        // Find the direction the player is facing
        // Then find the nearest object.
        Station activeStation;

        switch(this.direction){
            case UP:
                activeStation = GameData.level.getStation(Math.toIntExact(Math.round(Math.floor(this.x))), Math.toIntExact(Math.round(Math.floor(this.y + 1))));
                break;
            case DOWN:
                activeStation = GameData.level.getStation(Math.toIntExact(Math.round(Math.floor(this.x))), Math.toIntExact(Math.round(Math.floor(this.y - 1))));
                break;
            case LEFT:
                activeStation = GameData.level.getStation(Math.toIntExact(Math.round(Math.floor(this.x - 1))), Math.toIntExact(Math.round(Math.floor(this.y))));
                break;
            case RIGHT: 
                activeStation = GameData.level.getStation(Math.toIntExact(Math.round(Math.floor(this.x + 1))), Math.toIntExact(Math.round(Math.floor(this.y))));
                break;
            default:
                System.out.println("Bruh.");
                return false;
        }
        return activeStation.placeItem(this.holding);
    }
}
