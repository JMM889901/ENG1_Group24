package group24.piazzapanic.game;

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
    /**
     * The name of the level file on disk.
     */
    private final String levelName;
    /** The grid of stations */
    public Station[][] grid;
    /** The width of the level */
    private int width;
    /** The height of the level */
    private int height;
    /** The X starting position of the player */
    public int startX;
    /** The Y starting position of the player */
    public int startY;

    /**
     * Level constructor, reads level data from file and stores it in a 2D array of {@link Station}s.
     * @param levelName The name of the level file on disk.
     */
    public Level(String levelName) {
        this.levelName = levelName;
        // Read the level file from disk.
        try {
            width = 10;
            height = 5;

            String[] level_string = {"s111111112",
            ".........3",
            "g.dBCF1..3",
            "....*....4",
            "o.t.l.b.m."};

            grid = new Station[width][height];

            // The x and y coordinates of the current station.
            int x;
            int y;

            // Loop variables to interpret a level string array (i, the outer loop, refers to y coordinate).
            int j;
            int i = 0;

            for (String line : level_string) {
                if (line.length() != width) {
                    throw new Exception(
                            "Expected " + width + " characters in line " + i + ", but found " + line.length() + ".");
                }

                for (j = 0; j < width; j++) {
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
                        case '1':
                            grid[x][y] = new CounterTop(GameData.counterTopTexture);
                            break;
                        case '2':
                            grid[x][y] = new CounterTop(GameData.counterRightCornerTexture);
                            break;
                        case '3':
                            grid[x][y] = new CounterTop(GameData.counterRightTexture);
                            break;
                        case '4':
                            grid[x][y] = new CounterTop(GameData.counterEndTexture);
                            break;
                        case 'C':
                            grid[x][y] = new CuttingStation();
                            break;
                        case 'F':
                            grid[x][y] = new FryingStation();
                            break;
                        case 't': // tomato
                        case 'o': // onion
                        case 'l': // lettuce
                        case 'b': // bread
                        case 'm': // meat
                        case 'd': // dish (plate)
                            grid[x][y] = new IngredientStation(x, y,
                                    extrapolateIngredient(line.charAt(j)));
                            break;
                        case 'W': //wall
                            grid[x][y] = new Obstacle();
                            break;
                        case 'g':
                            grid[x][y] = new Bin();
                            break;
                        case 's':
                            grid[x][y] = new ServingStation(GameData.servingStationTexture);
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

                i += 1;
            }
            //levelScanner.close();
        } catch (Exception exception) {
            System.out.println("Error reading level, '" + levelName + "'.");
            exception.printStackTrace();
        }
    }


    /**
     * Extrapolates the ingredient from a single letter in the level file.
     * e.g. the letter "t" represents tomatoes
     * @param abbrevation The letter representing the ingredient
     * @return An {@link IngredientType} object representing the ingredient. Null if the ingredient is unknown.
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
            case 'd':
                return new IngredientType("dish");
            default:
                System.out.println("Unknown ingredient '" + abbrevation + "'.");
                return null;
        }
    }

    /**
     * Returns the name of the level file on disk.
     * @return A String representing the name of the level file on disk.
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Returns the station at the given coordinates.
     * @param x The integer x coordinate of the station.
     * @param y The integer y coordinate of the station.
     * @return The {@link Station} at the given coordinates. Null if the coordinates are out of bounds.
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
     * Returns the width of the level in grid units.
     * @return an integer representing the width of the level in grid units.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the level in grid units.
     * @return an integer representing the height of the level in grid units.
     */
    public int getHeight() {
        return height;
    }

}
