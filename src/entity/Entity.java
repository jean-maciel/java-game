package entity;

import graphics.Screen;
import level.Level;

import java.util.Random;

public abstract class Entity {

    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random(System.currentTimeMillis());

    public void update() {
    }

    public void render(Screen screen) {
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved() {
        removed = true;
    }
}
