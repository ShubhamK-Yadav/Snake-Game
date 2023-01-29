import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel implements ActionListener, KeyListener {
    private static final int BOARD_WIDTH = 600, BOARD_HEIGHT = 600;
    private static final int UNIT_BLOCK = 30;
    private static int gameBlocks = BOARD_WIDTH * BOARD_HEIGHT / UNIT_BLOCK;

    private static int[] wholeSnakeX = new int [gameBlocks];
    private static int[] wholeSnakeY = new int [gameBlocks];
    private static int bodyParts = 3;
    private static int score = 0;
    private static char direction = 'R';
    private static int appleX;
    private static int appleY;
    private static boolean running = false;
    private static Timer timer;


    public Game() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.black);
        this.addKeyListener(this);
        startGame();
    }

    public void startGame(){
        running = true;
        timer = new Timer(75, this);
        timer.start();
    }
    public void move(){
        for (int i = bodyParts; i > 0; i--){
            wholeSnakeX[i] = wholeSnakeX[i-1];
            wholeSnakeY[i] = wholeSnakeY[i-1];
        }

        if (direction == 'U'){
            wholeSnakeY[0] = wholeSnakeY[0] - UNIT_BLOCK;
        }
        if (direction == 'D'){
            wholeSnakeY[0] = wholeSnakeY[0] + UNIT_BLOCK;
        }
        if (direction == 'L'){
            wholeSnakeX[0] = wholeSnakeX[0] - UNIT_BLOCK;
        }
        if (direction == 'R'){
            wholeSnakeX[0] = wholeSnakeX[0] + UNIT_BLOCK;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    public void paint(Graphics g) {
        g.fillRect(0,0, 600,600);
        g.setColor(Color.black);

        //grid to keep track of the snake movement
        g.setColor(Color.green);
        for (int i = 1; i < 20; i++) {
            g.drawLine(i * UNIT_BLOCK, 0, i * UNIT_BLOCK, 600);
            g.drawLine(0, i*UNIT_BLOCK, 600, i*UNIT_BLOCK);
        }

        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(wholeSnakeX[i], wholeSnakeY[i], UNIT_BLOCK, UNIT_BLOCK);
            } else {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(wholeSnakeX[i], wholeSnakeY[i], UNIT_BLOCK, UNIT_BLOCK);
            }
        }
    }

    public int randomAppleCoord() {
        return 0;
    }

    public boolean checkCollisionApple() {
        return false;
    }

    public boolean checkCollisionSnake() {
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
            checkCollisionApple();
            checkCollisionSnake();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed code " + e.getKeyCode());
        if (e.getKeyCode() == (KeyEvent.VK_UP)){
            if (direction != 'D'){
                direction = 'U';
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            if (direction != 'U'){
                direction = 'D';
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (direction != 'R'){
                direction = 'L';
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (direction != 'L'){
                direction = 'R';
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
