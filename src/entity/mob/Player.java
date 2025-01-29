package entity.mob;


import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;

public class Player extends Mob {
    private Keyboard input;

    public Player(Keyboard input) {
        this.input = input;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
    }

    @Override
    public void update() {
        int x = 0, y = 0;

        if (input.up) y--;
        if (input.down) y++;
        if (input.left) x--;
        if (input.right) x++;

        if (x != 0 || y != 0) {
            move(x, y);
        }
    }

    public void render(Screen screen) {
        screen.renderPlayer(x - 16, y - 16, Sprite.playerDown);
    }

}
