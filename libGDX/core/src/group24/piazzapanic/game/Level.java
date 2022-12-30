package group24.piazzapanic.game;

import java.io.File;
import java.util.Scanner;

import group24.piazzapanic.levelElements.Ingredient;
import group24.piazzapanic.levelElements.stations.*;

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

            grid = new Station[height][width];

            int x, y;
            for(int i = 0; i < height; i++) {
                if(!levelScanner.hasNextLine()) {
                    throw new Exception("Expected " + height + " lines in level file, but found " + i + ".");
                }

                String line = levelScanner.nextLine();
                if(line.length() != width) {
                    throw new Exception("Expected " + width + " characters in line " + i + ", but found " + line.length() + ".");
                }

                for(int j = 0; j < width; j++) {
                    x = width - i - 2;
                    y = j;
                    switch(line.charAt(j)) {
                        case '.':
                            grid[x][y] = null;
                            break;
                        case '*':
                            grid[x][y] = null;
                            startX = j;
                            startY = i;
                            break;

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

                        case 't':  // tomato  // TODO: add the other ingredients.
                        case 'o':  // onion
                            grid[x][y] = new IngredientStation(new Ingredient(extrapolateIngredient(line.charAt(j)), null));
                            break;

                            case 'W':
                            grid[x][y] = new Obstacle();
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
        switch(abbrevation) {
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
        return grid[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
