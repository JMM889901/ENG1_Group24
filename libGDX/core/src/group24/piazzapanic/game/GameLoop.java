package group24.piazzapanic.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.ui.FontHandler;
import group24.piazzapanic.ui.StageAnimation;
import group24.piazzapanic.ui.StageManager;
import group24.piazzapanic.ui.WidgetFactory;
import group24.piazzapanic.Base;
import group24.piazzapanic.levelElements.stations.*;

/**
 * The GameLoop class is the main game loop. It handles setting up the game,
 * spawning customers and the level, spawning players, the game score, and so on. It extends {@link Stage}.
 */
public class GameLoop extends Stage {
    /** The game score */
    private final Label scoreCounter;
    /** Var for storing positions in per frame calculations, making a new vector causes the funni (memory leak)*/
    private Vector2 curPosition;

    /** The rows of stations. Done this way to avoid Z-level drawing issues */
    private final ArrayList<Group> rows;

    /** The stations in the game level */
    private ArrayList<Station> stations;

    public int maxCustomers = 5;
    public int totalCustomers;

    /**
     * GameLoop constructor, adds a score counter and sets up level data.
     */
    public GameLoop() {
        GameData.gameTime = 0f;
        GameData.sinceLastSpawn = 0f;
        GameData.customers = new ArrayList<Customer>();
        totalCustomers = 0;
        this.rows = new ArrayList<Group>();
        this.stations = new ArrayList<Station>();
        //Read level data from file, create stations and add them to the level
        GameData.level = new Level("levels/Level 3");
        for (int y = GameData.level.getHeight() - 1; y >= 0; y--) {
            Group group = new Group();
            for (int x = 0; x < GameData.level.getWidth(); x++) {
                if (GameData.level.grid[x][y] != null) {

                    group.addActor(GameData.level.grid[x][y]);
                    this.stations.add(GameData.level.grid[x][y]);
                }
            }
            this.addActor(group);
            this.rows.add(group);
        }
        // //Add pause button
        // TextButton pauseButton = WidgetFactory.createTextButton(FontHandler.textButtonFormat, Color.WHITE,
        //         new Vector2(0.15, 0.95), "||", Align.right);
        // pauseButton.getStyle().overFontColor = Color.BLUE;
        // //Create onclick function
        // pauseButton.addListener(new ChangeListener() {

        //     @Override
        //     public void changed(ChangeEvent event, Actor actor) {
        //         System.out.print("Open Main");
        //         StageManager.setActiveStage("Pause");
        //     }

        // });
        // stage.addActor(backButton);

        //Player creation

        GameData.player = new Player(GameData.level.startX + 0.5, GameData.level.startY + 0.5,
                GameData.initialChef1Animation, GameData.chef1Animations);
        GameData.player1 = GameData.player;
        GameData.player2 = new Player(GameData.level.startX + 0.5, GameData.level.startY + 0.5,
                GameData.initialChef2Animation, GameData.chef2Animations);

        this.addActor(GameData.player1);
        this.addActor(GameData.player2);

        this.curPosition = new Vector2(0, 0);
        //Create score counter
        LabelStyle style = new LabelStyle();
        style.font = FontHandler.subtitleFormat;
        style.fontColor = Color.WHITE;
        CharSequence count = Integer.toString(GameData.score);
        scoreCounter = new Label(count, style);
        Vector2 pos = new Vector2(0.95, 0.9); // Score counter position.
        scoreCounter.setPosition(pos.getAbsoluteX(), pos.getAbsoluteY(), Align.bottomLeft);
        this.addActor(scoreCounter);

        //Create Inventory Panel
        StageAnimation ChefAnimation = new StageAnimation(GameData.chef1Animations.get("IdleFrontSelected"), 6, 6, 1,
                new Vector2(0.85, 0.85), 50, 100);
        StageAnimation ChefAnimation1 = new StageAnimation(GameData.chef2Animations.get("IdleFrontSelected"), 6, 6, 1,
                new Vector2(0.8, 0.85), 50, 100);

        this.addActor(ChefAnimation);
        this.addActor(ChefAnimation1);

        //Create customers
        GameData.customerSpriteSheets = new ArrayList<String>(Arrays.asList("customers/customer_1_idle.png",
                "customers/customer_2_idle.png", "customers/customer_3_idle.png"));
        GameData.rand = new Random();

    }

    /**
     * Adds a score to the score counter.
     * @param score The score to add (Integer)
     */
    public void addScore(int score) {
        CharSequence count = Integer.toString(score);
        this.scoreCounter.setText(count);
    }

    /**
     * Spawns customers, handles swapping players and pausing game.
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        GameData.gameTime += delta;

        if (this.totalCustomers < this.maxCustomers) {
            GameData.sinceLastSpawn += delta;
        }
        if (GameData.sinceLastSpawn >= 5) {
            //Create new customer offset location.
            Customer customer = new Customer();
            customer.setX(GameData.customers.size() * (Customer.entityWidth + 30));
            GameData.customers.add(customer);
            this.addActor(customer);
            GameData.sinceLastSpawn = 0;
            this.totalCustomers++;
        }
        if (Gdx.input.isKeyJustPressed(Base.SWAP_KEY)) {
            if (GameData.player == GameData.player1) {
                System.out.println("Swapping player 1 to 2");
                GameData.player = GameData.player2;
            } else if (GameData.player == GameData.player2) {
                System.out.println("Swapping player 2 to 1");
                GameData.player = GameData.player1;
            }
        }
        if (Gdx.input.isKeyJustPressed(Base.PAUSE_KEY)) {
            StageManager.setActiveStage("Pause");
        }
        // Run player movement and physics, it's quite long so I put it in a separate function.
        Physics.playerMovement(GameData.player, delta);
        resortActors();
        super.act(delta);
    }

    /**
     * Resorts the actors in the game loop to avoid Z-level drawing issues.
     */
    private void resortActors() {
        int ylevel = 0;
        int offset = 0;
        for (Actor actor : this.rows) {
            if (GameData.level.getHeight() - 1 - ((int) Math.floor(GameData.player1.y)) == ylevel - offset) {
                GameData.player1.setZIndex(ylevel);
                offset++;
                ylevel++;
            }
            if (GameData.level.getHeight() - 1 - ((int) Math.floor(GameData.player2.y)) == ylevel - offset) {
                GameData.player2.setZIndex(ylevel);
                offset++;
                ylevel++;
            }
            actor.setZIndex(ylevel);
            ylevel++;
        }
    }

    /**
     * Resorts the customers in the game loop to avoid Z-level drawing issues.
     */
    public void resortCustomers() {
        int i = 0;
        for (Customer customer : GameData.customers) {
            customer.setX(i * (Customer.entityWidth + 30));
            i++;
        }
    }

    /**
     * Draws all level elements, floor tiles, players and customers.
     */
    @Override
    public void draw() {

        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);

        // Iterate through level array and draw tiles.
        // Make sure the tiles are drawn first higher up the screen.
        for (int y = GameData.level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < GameData.level.getWidth(); x++) {
                curPosition = Vector2.gridUnitTranslate(x, y);
                Base.batch.draw(GameData.floorTexture, curPosition.getAbsoluteX() + GameData.offsetX,
                        curPosition.getAbsoluteY() + GameData.offsetY, Base.tile_pixel_width, Base.tile_pixel_width);

            }
        }

        //This code causes a slight memory leak
        if (Base.DEBUG) { // Draws the player's hitbox area.
            Vector2 bottomLeft = Vector2.gridUnitTranslate(GameData.player.x - Player.GRID_WIDTH / 2,
                    GameData.player.y - Player.GRID_WIDTH / 2);
            Vector2 topRight = Vector2.gridUnitTranslate(GameData.player.x + Player.GRID_WIDTH / 2,
                    GameData.player.y + Player.GRID_WIDTH / 2);
            Base.batch.draw(GameData.debugSquareTexture, bottomLeft.getAbsoluteX() + GameData.offsetX,
                    bottomLeft.getAbsoluteY() + GameData.offsetY, topRight.getAbsoluteX() - bottomLeft.getAbsoluteX(),
                    topRight.getAbsoluteY() - bottomLeft.getAbsoluteY());
        }

        //Draw the player inventory
        if (GameData.player1.holding != null) {
            curPosition.x = 0.85;
            curPosition.y = 0.85;
            //curPosition = new Vector2(0.85, 0.85);
            GameData.player1.holding.drawItemInventory(curPosition.getAbsoluteX(), curPosition.getAbsoluteY() - 50, 50,
                    50);
        }
        if (GameData.player2.holding != null) {
            curPosition.y = 0.85;
            curPosition.x = 0.8;
            GameData.player2.holding.drawItemInventory(curPosition.getAbsoluteX(), curPosition.getAbsoluteY() - 50, 50,
                    50);
        }
        Base.batch.end();
        Base.batch.begin();
        super.draw();//Draw stations and player
        Base.batch.end();
        Base.batch.begin();
        //Draw items
        for (Station station : this.stations) {
            if (station.item != null) {
                station.item.drawItem((int) (station.getX() + ((Base.tile_pixel_width / 3))),
                        (int) (station.getY() + (Base.tile_pixel_height / 2)), Base.tile_pixel_width / 2,
                        Base.tile_pixel_width / 2);
            }
        }
    }
}
