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

public class GameLoop extends Stage {
    private float gameTime;
    private float sinceLastSpawn;
    private ArrayList<Customer> customers;
    private Label scoreCounter;
    public Integer score;
    public static GameLoop activeGameLoop;
    
    private Level level;
    private float playerX; //player position in grid coordinate units.
    private float playerY;
    private int offsetX = 300; //offsets for the camera, in pixels.
    private int offsetY = 300;

    public GameLoop() {
        this.gameTime = 0f;
        this.sinceLastSpawn = 0f;
        this.score = 0;
        customers = new ArrayList<Customer>();

        //Create score counter
        LabelStyle style = new LabelStyle();
        style.font = FontHandler.subtitleFormat;
        style.fontColor = Color.WHITE;
        CharSequence count = Integer.toString(score);
        scoreCounter = new Label(count, style);
        Vector2 pos = new Vector2(0.95, 0.9);
        scoreCounter.setPosition(pos.getAbsoluteX(), pos.getAbsoluteY(), Align.bottomLeft);
        this.addActor(scoreCounter);
        
        //Gameloop should be static, however stage cannot be static
        //So this kinda works to let functions be called statically
        GameLoop.activeGameLoop = this;
        // ^ This is very hacky! Just creating a gameloop instance (and that gameloop becomes
        // active) means "this" gameloop is no longer active, which can make things very hard to
        // debug if we make a mistake anywhere else. - Joss

        level = new Level("levels/Level 1");
        playerX = level.startX + 0.5f;
        playerY = level.startY + 0.5f;
    }

    public static void addScore(int score) {
        GameLoop.activeGameLoop.score += score;
        CharSequence count = Integer.toString(score);
        GameLoop.activeGameLoop.scoreCounter.setText(count);
    }

    public static void setActiveLoop(GameLoop game) {
        GameLoop.activeGameLoop = game;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.gameTime += delta;
        this.sinceLastSpawn += delta;

        if (this.sinceLastSpawn >= 5 && customers.size() < 5) {
            //Create new customer offset location.
            Customer customer = new Customer();
            customer.setX(customers.size() * (Customer.entityWidth + 30));
            customers.add(customer);
            //this.addActor(customer);
            this.sinceLastSpawn = 0;
        }
    }

    @Override
    public void draw() {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
        super.draw();

        // Iterate through level array and draw tiles.
        Vector2 curPosition;
        Texture curTexture = Base.errorTexture;
        // Make sure the tiles are drawn first higher up the screen.
        for (int y = level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < level.getWidth(); x++) {
                curPosition = Vector2.gridUnitTranslate(x, y);
                Base.batch.draw(Base.floorTexture, curPosition.getAbsoluteX() + offsetX, curPosition.getAbsoluteY() + offsetY, Base.tile_pixel_width, Base.tile_pixel_height);

                switch (level.getTile(x, y)) {
                    case EMPTY:
                        continue;
                    case WALL:
                        curTexture = Base.wallTexture;
                        break;
                    case BAKING_STATION:
                        curTexture = Base.bakingStationTexture;
                        break;
                    case COUNTER_TOP:
                        curTexture = Base.counterTopTexture;
                        break;
                    
                    default:
                        System.out.println("Unknown tile type: " + level.getTile(x, y) + ", defaulting to floor.");
                        curTexture = Base.errorTexture;
                        break;
                }

                Base.batch.draw(curTexture, curPosition.getAbsoluteX() + offsetX, curPosition.getAbsoluteY() + offsetY, Base.tile_pixel_width, Base.tile_pixel_height);
            }
        }
    }
}
