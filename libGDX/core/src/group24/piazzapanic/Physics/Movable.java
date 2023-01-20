package group24.piazzapanic.Physics;

import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.maths.Vector2;

public interface Movable {

    public void actItem(float delta);

    public void drawItem(int x, int y);

    public Ingredient getIngredient();
}
