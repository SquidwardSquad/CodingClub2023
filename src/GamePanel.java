import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    private final int TILE_LENGTH = 32;
    private final int SCREEN_WIDTH = TILE_LENGTH * 18;
    private final int SCREEN_HEIGHT = TILE_LENGTH * 18;
    private final int FPS = 60;

    public static final KeyHandler keyHandler = new KeyHandler();

    Thread gameThread;

    private Entity player = new Player(0, 0);
    private Tile tile1 = new Tile(32, 32, TileType.GROUND);
    private Tile tile2 = new Tile(64, 32, TileType.GRASS);
    private Tile tile3 = new Tile(96, 32, TileType.WALL);
    private Tile tile4 = new Tile(128, 32, TileType.BLANK);

    //TESTING COLLISION OBJECTS
    private Rectangle testCollision = new Rectangle(100, 100, 64, 64);

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        long frameInterval = 1000000000/FPS;
        long startFrameTime;
        long endFrameTime;
        long frameLength;
        long remainingFrameTime;

        //Game Loop
        while (gameThread != null) {

            startFrameTime = System.nanoTime();

            update();
            repaint();

            endFrameTime = System.nanoTime();
            frameLength = endFrameTime - startFrameTime;
            remainingFrameTime = frameInterval - frameLength;
            //System.out.println(remainingFrameTime);
            if (remainingFrameTime > 0) {
                try {
                    Thread.sleep(remainingFrameTime/1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    private void update() {
        player.move();

        if (player.getCollisionBox().intersects(testCollision)) {
            System.out.println("COLLIDING");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        //Render items here
        tile1.draw(g2d);
        tile2.draw(g2d);
        tile3.draw(g2d);
        tile4.draw(g2d);

        player.draw(g2d);

        //TESTING RENDER CODE
        g2d.setColor(Color.GREEN);
        g2d.draw(testCollision);

        g2d.dispose();
    }
}