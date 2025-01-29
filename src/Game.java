import entity.mob.Player;
import graphics.Screen;
import input.Keyboard;
import level.Level;
import level.RandomLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.Serial;

public class Game extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static int width = 512;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private boolean running = false;

    private Thread gameThread;
    private JFrame jFrame;
    private Keyboard keyboard;
    private Level level;
    private Screen screen;
    private Player player;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        jFrame = new JFrame();
        keyboard = new Keyboard();
        level = new RandomLevel(64, 64);
        player = new Player(keyboard);

        addKeyListener(keyboard);
    }

    public synchronized void start() {
        running = true;
        gameThread = new Thread(this, "Display");
        gameThread.start();
    }

    public synchronized void stop()  {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        final double nanoSeconds = 1_000_000_000.0 / 60.0;
        double delta = 0;

        int frames = 0;
        int updates = 0;

        requestFocus();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSeconds;
            lastTime = now;

            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                jFrame.setTitle("Game - FPS: " + frames + " | UPS: " + updates);
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    public void update() {
        keyboard.update();
        player.update();
    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();

        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;

        level.render(xScroll, yScroll, screen);
        player.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bufferStrategy.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();

        bufferStrategy.show();
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.jFrame.setResizable(false);
        game.jFrame.setTitle("Game");
        game.jFrame.add(game);
        game.jFrame.pack();
        game.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.jFrame.setLocationRelativeTo(null);
        game.jFrame.setVisible(true);

        game.start();
    }
}
