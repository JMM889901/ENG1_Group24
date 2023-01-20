package group24.piazzapanic.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.Base;
import group24.piazzapanic.levelElements.stations.*;

public class GameLoop extends Stage {
    private Label scoreCounter;

    private int offsetX = 100; //offsets for the camera, in pixels.
    private int offsetY = 50;

    /**
     * GameLoop constructor, adds a score counter and sets up level data.
     */
    public GameLoop() {
        GameData.gameTime = 0f;
        GameData.sinceLastSpawn = 0f;
        GameData.customers = new ArrayList<Customer>();

        //Create score counter
        LabelStyle style = new LabelStyle();
        style.font = FontHandler.subtitleFormat;
        style.fontColor = Color.WHITE;
        CharSequence count = Integer.toString(GameData.score);
        scoreCounter = new Label(count, style);
        Vector2 pos = new Vector2(0.95, 0.9);  // Score counter position.
        scoreCounter.setPosition(pos.getAbsoluteX(), pos.getAbsoluteY(), Align.bottomLeft);
        this.addActor(scoreCounter);

        GameData.level = new Level("levels/Level 1");
        GameData.player = new Player(GameData.level.startX + 0.5, GameData.level.startY + 0.5,
                Base.initialChefAnimation);

        GameData.customerSpriteSheets = new ArrayList<String>(Arrays.asList("customers/customer_1_idle.png",
                "customers/customer_2_idle.png", "customers/customer_3_idle.png"));
        GameData.rand = new Random();
        for (int y = GameData.level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < GameData.level.getWidth(); x++) {
                if (GameData.level.grid[x][y] != null) {
                    this.addActor(GameData.level.grid[x][y]);
                }
            }
        }
    }

    /** 
     * @param score
     */
    public void addScore(int score) {
        CharSequence count = Integer.toString(score);
        this.scoreCounter.setText(count);
    }

    /** 
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        GameData.gameTime += delta;
        GameData.sinceLastSpawn += delta;

        if (GameData.sinceLastSpawn >= 5 && GameData.customers.size() < 5) {
            //Create new customer offset location.
            Customer customer = new Customer();
            customer.setX(GameData.customers.size() * (Customer.entityWidth + 30));
            GameData.customers.add(customer);
            this.addActor(customer);
            GameData.sinceLastSpawn = 0;
        }
        GameData.player.animation.act(1);
        // Run player movement and physics, it's quite long so I put it in a separate function.
        GameData.player.act(delta);
        Physics.playerMovement(GameData.player, delta);
    }

    /**
     * Draws all level elements, floor tiles, players and (yet to be implemented) customers.
     */
    @Override
    public void draw() {

        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);

        // Iterate through level array and draw tiles.
        Vector2 curPosition;
        Texture curTexture = Base.errorTexture;
        Station curStation;
        // Make sure the tiles are drawn first higher up the screen.
        for (int y = GameData.level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < GameData.level.getWidth(); x++) {
                curPosition = Vector2.gridUnitTranslate(x, y);
                Base.batch.draw(Base.floorTexture, curPosition.getAbsoluteX() + GameData.offsetX,
                        curPosition.getAbsoluteY() + GameData.offsetY, Base.tile_pixel_width, Base.tile_pixel_height);

            }

        }

        if (Base.DEBUG) {
            // Set to true to see the chef's hitbox.
            Vector2 bottomLeft = Vector2.gridUnitTranslate(GameData.player.x - Player.GRID_WIDTH / 2,
                    GameData.player.y - Player.GRID_WIDTH / 2);
            Vector2 topRight = Vector2.gridUnitTranslate(GameData.player.x + Player.GRID_WIDTH / 2,
                    GameData.player.y + Player.GRID_WIDTH / 2);
            Base.batch.draw(Base.debugSquareTexture, bottomLeft.getAbsoluteX() + GameData.offsetX,
                    bottomLeft.getAbsoluteY() + GameData.offsetY, topRight.getAbsoluteX() - bottomLeft.getAbsoluteX(),
                    topRight.getAbsoluteY() - bottomLeft.getAbsoluteY());
        }

        super.draw();
        Vector2 playerPosition = Vector2.gridUnitTranslate(
                GameData.player.x - Player.GRID_WIDTH * Player.TEXTURE_SCALE / 2,
                GameData.player.y - Player.GRID_WIDTH / 2);
        Base.batch.draw(Base.initialChefAnimation.getCurrentFrame(),
                playerPosition.getAbsoluteX() + GameData.offsetX,
                playerPosition.getAbsoluteY() + GameData.offsetY,
                (float) Player.GRID_WIDTH * Player.TEXTURE_SCALE * Base.tile_pixel_width,
                (float) Player.GRID_WIDTH * Player.TEXTURE_SCALE * Base.tile_pixel_width
                        * Player.TEXTURE_HEIGHT / Player.TEXTURE_WIDTH);
        // Todo: draw the player at the right z level depending on its y position.
    }
}
