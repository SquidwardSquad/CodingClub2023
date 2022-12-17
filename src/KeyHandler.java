import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean dKey, wKey, aKey, sKey = false;

    @Override
    public void keyTyped(KeyEvent e) {
        //Does nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            wKey = true;
        }
        if(code == KeyEvent.VK_A) {
            aKey = true;
        }
        if(code == KeyEvent.VK_S) {
            sKey = true;
        }
        if(code == KeyEvent.VK_D) {
            dKey = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            wKey = false;
        }
        if (code == KeyEvent.VK_A) {
            aKey = false;
        }
        if (code == KeyEvent.VK_S) {
            sKey = false;
        }
        if (code == KeyEvent.VK_D) {
            dKey = false;
        }
    }
}
