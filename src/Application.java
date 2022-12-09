import java.awt.*;
import javax.swing.JFrame;

public class Application extends JFrame {
    public Application() {
        initUI();
    }

    //Multiples of 32
    public static final int SCREEN_HEIGHT = 32 * 18;
    public static final int SCREEN_WIDTH = 32 * 18;

    public static Board board = new Board();

    private void initUI() {
        add(board);
        addKeyListener(board);
        setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setResizable(false);
        setTitle("Coding Club");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application app = new Application();
            app.setVisible(true);
        });
    }
}