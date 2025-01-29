package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private String path;
    private final int SIZE;

    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("res/textures/SpriteSheet.png", 256);
    public static SpriteSheet characters = new SpriteSheet("res/textures/characters/CharacterSheet.png", 256);

    public SpriteSheet(String path, int SIZE) {
        this.path = path;
        this.SIZE = SIZE;
        pixels = new int[SIZE * SIZE];
        loadSheet(path);
    }

    private void loadSheet(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int w = image.getWidth();
            int h = image.getHeight();

            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSIZE() {
        return SIZE;
    }
}
