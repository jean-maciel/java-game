package graphics;

public class Sprite {

    private final int SIZE;
    private int x, y;
    private SpriteSheet spriteSheet;

    public int[] pixels;

    public static Sprite grassSprite = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0x21292f);

    public Sprite(int SIZE, int x, int y, SpriteSheet spriteSheet) {
        this.SIZE = SIZE;
        this.x = x * SIZE;
        this.y = y * SIZE ;
        this.spriteSheet = spriteSheet;

        pixels = new int[SIZE * SIZE];

        load();
    }

    public Sprite(int SIZE, int color) {
        this.SIZE = SIZE;

        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            assert spriteSheet != null;
            System.arraycopy(spriteSheet.pixels, (x + this.x) + (y + this.y) * spriteSheet.getSIZE(), pixels, y * SIZE, SIZE);
        }
    }

    private void setColor(int color) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = color;
        }
    }

    public int getSIZE() {
        return SIZE;
    }
}
