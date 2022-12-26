package group24.piazzapanic.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

import group24.piazzapanic.maths.Vector2;
import group24.piazzapanic.ui.FontHandler;

public class GameLoop extends Stage {
    private float gameTime;
    private float sinceLastSpawn;
    private ArrayList<Customer> customers;
    private Label scoreCounter;
    public Integer score;
    public static GameLoop activeGameLoop;

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
            this.addActor(customer);
            this.sinceLastSpawn = 0;
        }
    }
}
