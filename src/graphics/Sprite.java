package graphics;

public class Sprite {

    private final int SIZE;
    private int x, y;
    private SpriteSheet spriteSheet;

    public int[] pixels;

    public Sprite(int SIZE, int x, int y, SpriteSheet spriteSheet) {
        this.SIZE = SIZE;
        this.x = x * SIZE;
        this.y = y * SIZE ;
    }
}
