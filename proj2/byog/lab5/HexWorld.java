package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    private static final long SEED = 28154345;
    private static final Random RANDOM = new Random(SEED);

    private static int calcOffset(int x, int xStart, int s, int offset) {
        if (x < xStart + s - 1 || x > xStart + 2 * s - 3) {
            offset -= 1;
        }
        return offset;
    }

    private static boolean withinOffset(int y, int yStart, int s, int offset) {
        return y >= yStart + Math.abs(offset) && y <= yStart + 2 * s - Math.abs(offset) - 1;
    }

    public static void addHexagon(TETile[][] w, int s, int xStart, int yStart, TETile t) {
        int offset = s - 1;
        for (int x = xStart; x < xStart + 3 * s; x += 1) {
            for (int y = yStart; y < yStart + 3 * s; y += 1) {
                if (withinOffset(y, yStart, s, offset)) {
                    w[x][y] = t;
                }
            }
            offset = calcOffset(x, xStart, s, offset);
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.MOUNTAIN;
            case 3 -> Tileset.LOCKED_DOOR;
            case 4 -> Tileset.GRASS;
            case 5 -> Tileset.SAND;
            case 6 -> Tileset.TREE;
            default -> Tileset.NOTHING;
        };
    }

    private static int columnOffset(int s) {
        return s * 2 - 1;
    }

    private static int columnLimit(int s) {
        return columnOffset(s) * 5;
    }

    private static int rowOffset(int s) {
        return s * 2;
    }

    private static int rowLimit(int s, int offset) {
        return rowOffset(s) * 5 - offset;
    }

    private static void drawColumn(TETile[][] world, int x, int offset, int s) {
        for (int y = offset; y < rowLimit(s, offset); y += rowOffset(s)) {
            addHexagon(world, s, x, y, randomTile());
        }
    }

    public static void tessellateHexagons(TETile[][] world, int s) {
        int offset = 2 * s;
        for (int x = 0; x < columnLimit(s); x += columnOffset(s)) {
            drawColumn(world, x, Math.abs(offset), s);
            offset -= s;
        }
    }

    private static void fillTheWorldWithVoid(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillTheWorldWithVoid(world);
        tessellateHexagons(world, 3);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
