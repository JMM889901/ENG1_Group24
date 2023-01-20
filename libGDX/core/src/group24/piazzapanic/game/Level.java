package group24.piazzapanic.game;

import java.io.File;
import java.util.Scanner;

import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.levelElements.IngredientType;
import group24.piazzapanic.levelElements.stations.*;
import group24.piazzapanic.maths.Vector2;

/**
 * Reads level environment data from a file and stores it in a 2D array of
 * stations.
 * It stores a grid, integer positions refer to the top left corner of a
 * station/tile as shown on
 * screen to the player.
 */
public class Level {
    private String levelName;
    public Station[][] grid;
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

                    switch (line.charAt(j)) {
                        case '.':
                            grid[x][y] = null;
                            continue;
                        case '*':
                            grid[x][y] = null;
                            startX = j;
                            startY = height - i - 1;
                            continue;
                        case 'B':
                            grid[x][y] = new BakingStation();
                            break;
                        case 'T':
                            grid[x][y] = new CounterTop();
                            break;
                        case 'C':
                            grid[x][y] = new CuttingStation();
                            break;
                        case 'F':
                            grid[x][y] = new FryingStation();
                            break;
                        case 't': // tomato
                        case 'o': // onion
                        case 'l': // lettuce pray. 
                        case 'b': // bread
                        case 'm': // meat
                            grid[x][y] = new IngredientStation(x, y,
                                    extrapolateIngredient(line.charAt(j)));
                            break;
                        case 'W':
                            grid[x][y] = new Obstacle();
                            break;
                        default:
                            System.out.println(
                                    "Unknown character '" + line.charAt(j) + "' in level file '" + levelName + "''.");
                            grid[x][y] = new ErrorStation();
                    }
                    Vector2 pos = Vector2.gridUnitTranslate(x, y);
                    grid[x][y].setPosition(pos.getAbsoluteX() + GameData.offsetX,
                            pos.getAbsoluteY() + GameData.offsetY);
                }
            }
            levelScanner.close();
        } catch (Exception exception) {
            System.out.println("Error reading level, '" + levelName + "'.");
            exception.printStackTrace();
        }
    }

    /**
     * @param abbrevation
     * @return The type of the ingredient.
     */
    private static IngredientType extrapolateIngredient(char abbrevation) {
        switch (abbrevation) {
            case 't':
                return new IngredientType("tomato");
            case 'o':
                return new IngredientType("onion");
            case 'l':
                return new IngredientType("lettuce");
            case 'm':
                return new IngredientType("meat");
            case 'b':
                return new IngredientType("bread");
            default:
                System.out.println("Unknown ingredient '" + abbrevation + "'.");
                return null;
        }
    }

    /**
     * @return String
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * @param x
     * @param y
     * @return Station or Null if no station
     */
    public Station getStation(int x, int y) {
        // Bounds check
        if (x < GameData.level.getWidth() && y < GameData.level.getHeight()) {
            if (x >= 0 && y >= 0) {
                return grid[x][y];
            }
        }
        return null;
    }

    /**
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return int
     */
    public int getHeight() {
        return height;
    }

}
