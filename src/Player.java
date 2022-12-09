import java.awt.*;

public class Player extends Entity{
    private int health;
    private static final int SPEED = 4;


    public Player(int x, int y) {
        super(x, y, 32, 32, "assets/player.png");
    }

    @Override
    public void move() {
        //get user input
        //update sprite
        //update collisionbox
        if (Board.sKey) {
            y += SPEED;
            collisionBox.y = y;
        }
        if (Board.wKey) {
            y -= SPEED;
            collisionBox.y = y;
        }
        if (Board.aKey) {
            x -= SPEED;
            collisionBox.x = x;
        }
        if (Board.dKey) {
            x += SPEED;
            collisionBox.x = x;
        }
    }


}
