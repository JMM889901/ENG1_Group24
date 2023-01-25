package group24.piazzapanic.game;

import group24.piazzapanic.Base;
import group24.piazzapanic.Physics.AnimatedMovable;
import group24.piazzapanic.Physics.Movable;
import group24.piazzapanic.ui.StageAnimation;
import group24.piazzapanic.levelElements.stations.*;
import group24.piazzapanic.maths.Vector2;

import java.util.HashMap;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The Player class encapsulates player data (position, veolocity etc.), but
 * does nothing itself.
 */
public class Player extends Actor {
    public static final int TEXTURE_WIDTH = 48;
    public static final int TEXTURE_HEIGHT = 96;

    // How wide the player is in grid units. Applies to both "width" and
    // "length"/"height".
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
                                                 // collidable objects.
    Vector2 playerPosition; //Meant to prevent mem leaks, otherwise this would be in the draw func
    public Movable holding; // The player's one-item inventory.

    public StageAnimation animation;
    public HashMap<String, Animation<TextureRegion>> AnimMap;
    ProgressBar bar;
    boolean DrawBar;

    public static enum facing {
        UP, DOWN, LEFT, RIGHT
    }

    public facing direction;
    private String currentKey;
    private String key;

    public Player(double x, double y, StageAnimation animation) {
        this(x, y, animation, Base.chef1Animations);
    }

    public Player(double x, double y, StageAnimation animation, HashMap<String, Animation<TextureRegion>> AnimMap) {
        this.x = x;
        this.y = y;
        direction = facing.DOWN;
        this.animation = animation;
        this.AnimMap = AnimMap;

        this.bar = new ProgressBar(0, 3, 0.1f, false, new Skin(Gdx.files.internal("testSkin/uiskin.json")));

        this.playerPosition = new Vector2(0, 0);
        bar.setSize(Player.TEXTURE_WIDTH, bar.getPrefHeight());
        //bar.setAnimateDuration(2);
        //bar.draw(null, 0);
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        playerPosition.gridUnitTranslateInplace(
                this.x - Player.GRID_WIDTH * Player.TEXTURE_SCALE / 2,
                this.y - Player.GRID_WIDTH / 2);
        Base.batch.draw(this.animation.getCurrentFrame(),
                playerPosition.getAbsoluteX() + GameData.offsetX,
                playerPosition.getAbsoluteY() + GameData.offsetY,
                (float) Player.GRID_WIDTH * Player.TEXTURE_SCALE * Base.tile_pixel_width,
                (float) Player.GRID_WIDTH * Player.TEXTURE_SCALE * Base.tile_pixel_width
                        * Player.TEXTURE_HEIGHT / Player.TEXTURE_WIDTH);

        //if (this.holding != null) {
        //    System.out.println(this.holding);
        //    this.holding.drawItem(
        //            (int) playerPosition.getAbsoluteX() + GameData.offsetX
        //                    + ((int) (Player.GRID_WIDTH * Player.TEXTURE_SCALE * Base.tile_pixel_width)) / 2,
        //            (int) (playerPosition.getAbsoluteY() + GameData.offsetY
        //                    + (Player.GRID_WIDTH * Player.TEXTURE_SCALE * Base.tile_pixel_width) / 2));
        //}
        if (DrawBar) {
            bar.draw(batch, parentAlpha);
        }

    }

    public Station getFacingStation() {
        Station activeStation;
        int roundedX = (int) (Math.floor(this.x));
        int roundedY = (int) (Math.floor(this.y));
        switch (this.direction) {
            case UP:
                activeStation = GameData.level.getStation(roundedX, roundedY + 1);
                break;
            case DOWN:
                activeStation = GameData.level.getStation(roundedX, roundedY - 1);
                break;
            case LEFT:
                activeStation = GameData.level.getStation(roundedX - 1, roundedY);
                break;
            case RIGHT:
                activeStation = GameData.level.getStation(roundedX + 1, roundedY);
                break;
            default:
                System.out.println("Bruh.");
                return null;
        }
        return activeStation;
    }

    public boolean pickUp() {
        // Find the direction the player is facing
        // Then find the nearest object.
        Station activeStation;
        activeStation = getFacingStation();
        if (activeStation != null) { // Check that we do have a station! 
            this.holding = activeStation.takeItem();
        }
        return true;
    }

    public boolean putDown() {
        // Find the direction the player is facing
        // Then find the nearest object.
        Station activeStation;

        activeStation = getFacingStation();

        if (activeStation != null && Physics.isNear(activeStation, GameData.player))

        {
            boolean Result = activeStation.placeItem(this.holding);
            if (Result == true) {
                this.holding = null;
                return Result;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void act(float delta) {

        switch (this.direction) {
            case DOWN:
                key = "Front";
                break;
            case LEFT:
                key = "Left";
                break;
            case RIGHT:
                key = "Right";
                break;
            case UP:
                key = "Back";
                break;
            default:
                key = "Front";
                break;

        }
        if (this != GameData.player) {
            key = "Idle" + key;
        } else if (this.x_vel == 0 && this.y_vel == 0) {
            key = "Idle" + key + "Selected";
        }
        if (key != this.currentKey) {
            currentKey = key;
            this.animation.setAnimation(this.AnimMap.get(key));
        }

        this.animation.act(delta);
        if (GameData.player != this) {
            DrawBar = false;
            return;
        }
        if (Gdx.input.isKeyJustPressed(Base.PICKUP_KEY)) {
            if (this.holding == null) {
                System.out.println("Inventory is empty.");
                this.pickUp();
            } else {
                System.out.println("Currently holding: " + this.holding);
                this.putDown();
            }
        }
        Station station = this.getFacingStation();
        if (Gdx.input.isKeyPressed(Base.ACT_KEY) && station != null) {
            float progress = station.progress;
            if (progress > 0.1) {
                playerPosition.gridUnitTranslateInplace(
                        this.x - Player.GRID_WIDTH * Player.TEXTURE_SCALE / 2,
                        this.y - Player.GRID_WIDTH / 2);
                bar.setPosition(playerPosition.getAbsoluteX() + GameData.offsetX,
                        playerPosition.getAbsoluteY() + GameData.offsetY + Player.TEXTURE_HEIGHT + 5);
                bar.setValue(progress);
                DrawBar = true;
            } else {
                DrawBar = false;
            }
        } else {
            DrawBar = false;
        }
    }
}
