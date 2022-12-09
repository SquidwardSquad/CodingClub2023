import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, KeyListener {

    public static boolean dKey, wKey, aKey, sKey = false;

    private Entity player = new Player(0, 0);

    //TESTING COLLISION OBJECTS
    private Rectangle testCollision = new Rectangle(100, 100, 64, 64);
    private Graphics2D g2d;

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

        //Render items here
        player.draw((Graphics2D)g);

        //TESTING RENDER CODE
        g2d = (Graphics2D) g;
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