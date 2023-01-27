package group24.piazzapanic.levelElements.stations;
import com.badlogic.gdx.Gdx;
import group24.piazzapanic.Base;
import group24.piazzapanic.game.GameData;
import group24.piazzapanic.game.Physics;

public class CuttingStation extends Station {
    public CuttingStation() {
        super(Base.cuttingStationTexture);
    }
    @Override
    public void act(float delta) {
        if (super.item != null) {
            if (super.item.getIngredient() == null) {
                return;
            }
            if (Gdx.input.isKeyPressed(Base.ACT_KEY) && GameData.player.getFacingStation() == this
                    && Physics.isNear(this, GameData.player)) {
                if (super.item.getIngredient().getCuttingProgress() == 1) {
                    System.out.println("Already cut...");
                    return; // The item is already cut, don't go any further.
                }
                if (super.item.getIngredient().getCuttingProgress() == -1) {
                    timeKeyHeld = 0;
                    return;
                }
                timeKeyHeld += delta;
                if (timeKeyHeld > 3 && super.item.getIngredient().getCuttingProgress() == 0) {
                    super.item.getIngredient().cut();
                    System.out.println("Cutting complete...");
                    timeKeyHeld = 0;
                }
            } else {
                timeKeyHeld = 0; // Not pressing the button? sadge.
            }

        }
    }

}
