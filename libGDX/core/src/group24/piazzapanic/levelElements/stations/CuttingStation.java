package group24.piazzapanic.levelElements.stations;

import com.badlogic.gdx.Gdx;

import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.Physics;

public class CuttingStation extends Station {

    public CuttingStation() {
        super(Base.cuttingStationTexture);
    }

    float timeKeyHeld; // Time key is held for

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Base.ACT_KEY) && GameData.player.getFacingStation() == this && Physics.isNear(this, GameData.player)) {
            if (super.item.getIngredient().getCuttingProgress() == 1){
                System.out.println("Already cut...");
                return; // The item is already cut, don't go any further. 
            }
            System.out.println(timeKeyHeld);
            timeKeyHeld += delta;
            if (timeKeyHeld > 3 && super.item.getIngredient().getCuttingProgress() == 0) {
                // Cutting is done! poggers
                super.item.getIngredient().cut();
                System.out.println("Cutting complete...");
            }
        }
        else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }

    }

