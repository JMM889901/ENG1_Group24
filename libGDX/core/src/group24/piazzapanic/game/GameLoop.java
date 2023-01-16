package group24.piazzapanic.game;

import java.util.ArrayList;

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

    private int offsetX = 300; //offsets for the camera, in pixels.
    private int offsetY = 100;

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
        Vector2 pos = new Vector2(0.95, 0.9);
        scoreCounter.setPosition(pos.getAbsoluteX(), pos.getAbsoluteY(), Align.bottomLeft);
        this.addActor(scoreCounter);

        GameData.level = new Level("levels/Level 1");
        GameData.player = new Player(GameData.level.startX + 0.5, GameData.level.startY + 0.5);
    }

    public void addScore(int score) {
        CharSequence count = Integer.toString(score);
        this.scoreCounter.setText(count);
    }

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

        // Run player movement and physics, it's quite long so I put it in a separate function.
        Physics.playerMovement(GameData.player, delta);
    }

    /**
     * Draws all level elements, floor tiles, players and (yet to be implemented) customers.
     */
    @Override
    public void draw() {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
        super.draw();

        // Iterate through level array and draw tiles.
        Vector2 curPosition;
        Texture curTexture = Base.errorTexture;
        Station curStation;
        // Make sure the tiles are drawn first higher up the screen.
        for (int y = GameData.level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < GameData.level.getWidth(); x++) {
                curPosition = Vector2.gridUnitTranslate(x, y);
                Base.batch.draw(Base.floorTexture, curPosition.getAbsoluteX() + offsetX,
                        curPosition.getAbsoluteY() + offsetY, Base.tile_pixel_width, Base.tile_pixel_height);

                if (y == (int) Math.floor(GameData.player.bottom())) {
                    Vector2 playerPosition = Vector2.gridUnitTranslate(
                            GameData.player.x - Player.GRID_WIDTH * Player.TEXTURE_SCALE / 2,
                            GameData.player.y - Player.GRID_WIDTH / 2);
                    Base.batch.draw(Base.tempChefTexture, playerPosition.getAbsoluteX() + offsetX,
                            playerPosition.getAbsoluteY() + offsetY,
                            (float) Player.GRID_WIDTH * Player.TEXTURE_SCALE * Base.tile_pixel_width,
                            (float) Player.GRID_WIDTH * Player.TEXTURE_SCALE * Base.tile_pixel_width
                                    * Player.TEXTURE_HEIGHT / Player.TEXTURE_WIDTH);
                }

                curStation = GameData.level.getStation(x, y);

                if (curStation == null) {
                    // In this case you only need to draw the floor, which has already been done.
                    continue;
                } else if (curStation instanceof BakingStation) {
                    curTexture = Base.bakingStationTexture;
                } else if (curStation instanceof CounterTop) {
                    curTexture = Base.counterTopTexture;
                } else if (curStation instanceof CuttingStation) {
                    curTexture = Base.cuttingStationTexture;
                } else if (curStation instanceof FryingStation) {
                    curTexture = Base.fryingStationTexture;
                } else if (curStation instanceof IngredientStation) {
                    curTexture = Base.ingredientStationTexture;
                } else if (curStation instanceof Obstacle) {
                    curTexture = Base.obstacleTexture;
                } else {
                    System.out.println("Unknown station type: " + curStation + ", defaulting to floor.");
                    curTexture = Base.errorTexture;
                }

                Base.batch.draw(curTexture, curPosition.getAbsoluteX() + offsetX, curPosition.getAbsoluteY() + offsetY,
                        Base.tile_pixel_width, Base.tile_pixel_height);
            }
        }

        if (Base.DEBUG) {
            // Set to true to see the chef's hitbox.
            Vector2 bottomLeft = Vector2.gridUnitTranslate(GameData.player.x - Player.GRID_WIDTH / 2,
                    GameData.player.y - Player.GRID_WIDTH / 2);
            Vector2 topRight = Vector2.gridUnitTranslate(GameData.player.x + Player.GRID_WIDTH / 2,
                    GameData.player.y + Player.GRID_WIDTH / 2);
            Base.batch.draw(Base.debugSquareTexture, bottomLeft.getAbsoluteX() + offsetX,
                    bottomLeft.getAbsoluteY() + offsetY, topRight.getAbsoluteX() - bottomLeft.getAbsoluteX(),
                    topRight.getAbsoluteY() - bottomLeft.getAbsoluteY());
        }

        // Todo: draw the player at the right z level depending on its y position.
    }
}
