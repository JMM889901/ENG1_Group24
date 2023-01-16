package group24.piazzapanic.game;

import java.io.File;
import java.util.Scanner;

import com.badlogic.gdx.utils.Align;

import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.levelElements.stations.*;
import group24.piazzapanic.maths.Vector2;

/**
 * Reads level environment data from a file and stores it in a 2D array of stations.
 * It stores a grid, integer positions refer to the top left corner of a station/tile as shown on
 * screen to the player.
 */
public class Level {
    private String levelName;
    private Station[][] grid;
    private int width;
    private int height;

    public int startX;
    public int startY;

    public Level(String levelName) {
        this.levelName = levelName;

        try {
            File levelFile = new File(levelName);
            Scanner levelScanner = new Scanner(levelFile);

            String[] dimensions = levelScanner.nextLine().split("x");

            width = Integer.parseInt(dimensions[0]);
            height = Integer.parseInt(dimensions[1]);

            grid = new Station[width][height];

            int x, y;
            // Be aware that i is the outer loop, controlling the y coordinate.
            for (int i = 0; i < height; i++) {
                if (!levelScanner.hasNextLine()) {
                    throw new Exception("Expected " + height + " lines in level file, but found " + i + ".");
                }

                String line = levelScanner.nextLine();
                if (line.length() != width) {
                    throw new Exception(
                            "Expected " + width + " characters in line " + i + ", but found " + line.length() + ".");
                }

                for (int j = 0; j < width; j++) {
                    y = height - i - 1;
                    x = j;
                    Vector2 pos = Vector2.gridUnitTranslate(x, i);
                    switch (line.charAt(j)) {
                        case '.':
                            grid[x][y] = null;
                            break;
                        case '*':
                            grid[x][y] = null;
                            startX = j;
                            startY = height - i - 1;
                            break;

                        case 'B':
                            grid[x][y] = new BakingStation();
                            grid[x][y].setPosition(pos.getAbsoluteX(), pos.getAbsoluteX(), Align.center);
                            break;
                        case 'T':
                            grid[x][y] = new CounterTop();
                            grid[x][y].setPosition(pos.getAbsoluteX(), pos.getAbsoluteX(), Align.center);
                            break;
                        case 'C':
                            grid[x][y] = new CuttingStation();
                            grid[x][y].setPosition(pos.getAbsoluteX(), pos.getAbsoluteX(), Align.center);
                            break;
                        case 'F':
                            grid[x][y] = new FryingStation();
                            grid[x][y].setPosition(pos.getAbsoluteX(), pos.getAbsoluteX(), Align.center);
                            break;
                        case 't': // tomato  // TODO: add the other ingredients.
                        case 'o': // onion
                            grid[x][y] = new IngredientStation(
                                    new Ingredient(extrapolateIngredient(line.charAt(j)), null));
                            grid[x][y].setPosition(pos.getAbsoluteX(), pos.getAbsoluteX(), Align.center);
                            break;
                        case 'W':
                            grid[x][y] = new Obstacle();
                            grid[x][y].setPosition(pos.getAbsoluteX(), pos.getAbsoluteX(), Align.center);
                            break;
                        default:
                            throw new Exception("Unknown character '" + line.charAt(j) + "' in level file.");
                    }
                }
            }
            levelScanner.close();
        } catch (Exception exception) {
            System.out.println("Error reading level, '" + levelName + "'.");
            exception.printStackTrace();
        }
    }

    private static String extrapolateIngredient(char abbrevation) {
        switch (abbrevation) {
            case 't':
                return "tomato";
            case 'o':
                return "onion";
            default:
                System.out.println("Unknown ingredient '" + abbrevation + "'.");
                return null;
        }
    }

    public String getLevelName() {
        return levelName;
    }

    public Station getStation(int x, int y) {
        return grid[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
