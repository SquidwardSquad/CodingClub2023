import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, KeyListener {

    public static boolean dKey, wKey, aKey, sKey = false;

    private Entity player = new Player(0, 0);
    private Tile tile1 = new Tile(32, 32, TileType.GROUND);
    private Tile tile2 = new Tile(64, 32, TileType.GRASS);
    private Tile tile3 = new Tile(96, 32, TileType.WALL);
    private Tile tile4 = new Tile(128, 32, TileType.BLANK);

    //TESTING COLLISION OBJECTS
    private Rectangle testCollision = new Rectangle(100, 100, 64, 64);

    public Board() {
        setBackground(Color.BLACK);
    }

    //STARTS GAME THREAD
    @Override
    public void addNotify() {
        super.addNotify();

        Thread game = new Thread(this);
        game.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHints(rh);

        //Render items here
        tile1.draw(g2d);
        tile2.draw(g2d);
        tile3.draw(g2d);
        tile4.draw(g2d);

        player.draw((Graphics2D)g);

        //TESTING RENDER CODE
        g2d.setColor(Color.GREEN);
        g2d.draw(testCollision);


    }

    @Override
    public void run() {
        //Game update method
        while (true) {

            player.move();

            if (player.getCollisionBox().intersects(testCollision)) {
                System.out.println("COLLIDING");
            }

            repaint();

            try {
                Thread.sleep(16);
            } catch(Exception e) {

            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //When key is pressed
        if( e.getKeyChar() == 'd' ) {
            dKey = true;
        }
        if( e.getKeyChar() == 'w' ) {
            wKey = true;
        }
        if( e.getKeyChar() == 'a'){
            aKey = true;
        }
        if( e.getKeyChar() == 's'){
            sKey = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Useful for receiving text input
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //When key is released
        if( e.getKeyChar() == 'd' ){
            dKey = false;
        }
        if( e.getKeyChar() == 'a'  ){
            aKey = false;
        }
        if( e.getKeyChar() == 'w'  ){
            wKey = false;
        }
        if( e.getKeyChar() == 's'  ){
            sKey = false;
        }
    }
}