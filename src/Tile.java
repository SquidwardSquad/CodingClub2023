import javax.swing.*;
import java.awt.*;

enum TileType {
    GROUND,
    GRASS,
    WALL,
    BLANK;
}

public class Tile implements Drawable, Collidable {
    protected int x, y;
    protected Image image;
    protected Rectangle collisionBox;
    protected TileType type;

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        image = createImage("assets/tile.png"); //Fix tile image and math

        if (type == TileType.WALL) {
            collisionBox = createCollisionBox(x, y, 32, 32);
        }
        else {
            collisionBox = null;
        }

        this.type = type;

    }

    @Override
    public Image createImage(String file) {
        Image tempImg;
        ImageIcon tempIcon = new ImageIcon(file);
        tempImg = tempIcon.getImage();

        return tempImg;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int mx = type.ordinal() % 2; //How many across on tileset - BROKEN!!!
        int my = type.ordinal() / 2;

        g2d.drawImage(image, x, y, x + 32, y + 32,
                mx * 32, my * 32, (mx * 32) + 32, (my * 32) + 32, null);
    }

    @Override
    public Rectangle createCollisionBox(int x, int y, int width, int height) {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }
}
