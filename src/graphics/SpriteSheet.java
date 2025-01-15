package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SpriteSheet {
    private String path;
    private final int SIZE;

    public int[] pixels;

    public SpriteSheet(String path, int SIZE) {
        this.path = path;
        this.SIZE = SIZE;
        pixels = new int[SIZE * SIZE];
        loadSheet();
    }

    private void loadSheet() {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(SpriteSheet.class.getResource(path)));
            int w = image.getWidth();
            int h = image.getHeight();

            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
