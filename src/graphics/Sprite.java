package graphics;

import java.awt.image.BufferedImage;

public class Sprite {

    private final int SIZE;
    private int x, y;
    private SpriteSheet spriteSheet;

    public int[] pixels;

    public static Sprite grassSprite = new Sprite(16, 0, 0, SpriteSheet.tiles);

    public Sprite(int SIZE, int x, int y, SpriteSheet spriteSheet) {
        this.SIZE = SIZE;
        this.x = x * SIZE;
        this.y = y * SIZE ;
        this.spriteSheet = spriteSheet;

        pixels = new int[SIZE * SIZE];

        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            assert spriteSheet != null;
            System.arraycopy(spriteSheet.pixels, (x + this.x) + (y + this.y) * spriteSheet.getSIZE(), pixels, y * SIZE, SIZE);
        }
    }

    public int getSIZE() {
        return SIZE;
    }
}
