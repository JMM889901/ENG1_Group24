package group24.piazzapanic.game;

import java.io.File;
import java.util.Scanner;

import group24.piazzapanic.game.Tile;

public class Level {
    private String levelName;
    private Tile[][] grid;
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

            grid = new Tile[height][width];

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
                            grid[x][y] = Tile.EMPTY;
                            break;
                        case '*':
                            grid[x][y] = Tile.EMPTY;
                            startX = j;
                            startY = i;
                            break;

                        case 'w':
                            grid[x][y] = Tile.WALL;
                            break;
                        case 'b':
                            grid[x][y] = Tile.BAKING_STATION;
                            break;
                        case 't':
                            grid[x][y] = Tile.COUNTER_TOP;
                            break;
                        case 'c':
                            grid[x][y] = Tile.CUTTING_STATION;
                            break;
                        case 'f':
                            grid[x][y] = Tile.FRYING_STATION;
                            break;
                        case 'i':
                            grid[x][y] = Tile.INGREDIENT_STATION;
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

    public Tile getTile(int x, int y) {
        return grid[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
