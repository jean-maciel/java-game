package graphics;

import entity.mob.Player;
import level.tile.Tile;

import java.util.Random;

public class Screen {

    public int width, height;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;

    public int xOffset, yOffset;

    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new int[width * height];

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderTile(int xPos, int yPos, Tile tile) {
        xPos -= xOffset;
        yPos -= yOffset;

        for (int y = 0; y < tile.sprite.getSIZE(); y++) {
            int yAbsolute = y + yPos;

            for (int x = 0; x < tile.sprite.getSIZE(); x++) {
                int xAbsolute = x + xPos;

                if (xAbsolute < -tile.sprite.getSIZE() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;
                pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.getSIZE()];
            }
        }
    }

    public void renderPlayer(int xPos, int yPos, Sprite sprite) {
        xPos -= xOffset;
        yPos -= yOffset;

        for (int y = 0; y < 32; y++) {
            int yAbsolute = y + yPos;

            for (int x = 0; x < 32; x++) {
                int xAbsolute = x + xPos;

                if (xAbsolute < -32 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
                if (xAbsolute < 0) xAbsolute = 0;

                int col = sprite.pixels[x + y * 32];

                if (col != 0x00000000) {
                    pixels[xAbsolute + yAbsolute * width] = col;
                }
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
