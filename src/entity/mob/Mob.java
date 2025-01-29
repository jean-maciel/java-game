package entity.mob;

import entity.Entity;
import graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int direction = 0;
    protected boolean moving = false;

    public void move(int x, int y) {
        if (x > 0) direction = 1;
        if (x < 0) direction = 3;
        if (y > 0) direction = 2;
        if (y < 0) direction = 0;

        if (!collision()) {
            this.x += x;
            this.y += y;
        }
    }

    @Override
    public void update() {

    }

    public void render() {

    }

    private boolean collision() {
        return false;
    }
}
