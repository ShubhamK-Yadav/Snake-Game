package snake;
import javax.swing.*;

public class GameFrame extends JFrame {
    Game game = new Game();
    public GameFrame() {
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Snake Game");
        this.addKeyListener(game);
        this.add(game);
        this.pack();
    }
    public static void main(String[] args) {
        new GameFrame();
    }
}