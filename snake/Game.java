import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener{
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
    private static Random random = new Random();


    public Game() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.requestFocus();
        startGame();
    }

    public void startGame(){
        running = true;
        newApple();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed " + e.getKeyCode());
        if (e.getKeyCode() == (KeyEvent.VK_W)){
            if (direction != 'D'){
                direction = 'U';
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_S){
            if (direction != 'U'){
                direction = 'D';
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_A){
            if (direction != 'R'){
                direction = 'L';
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_D){
            if (direction != 'L'){
                direction = 'R';
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if(running == false){
                bodyParts = 3;
                score = 0;
                direction = 'R';
                running = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    public void paint(Graphics g) {
        g.fillRect(0,0, 600,600);
        g.setColor(Color.black);

        //grid to keep track of the snake movement
        g.setColor(Color.green);
        for (int i = 1; i < BOARD_HEIGHT/UNIT_BLOCK; i++) {
            g.drawLine(i * UNIT_BLOCK, 0, i * UNIT_BLOCK, 600);
            g.drawLine(0, i*UNIT_BLOCK, 600, i*UNIT_BLOCK);
        }
        g.setColor(Color.white);
        g.drawString("Score: " + score, BOARD_WIDTH - UNIT_BLOCK * 3, UNIT_BLOCK / 2);

        g.setColor(Color.red);
        g.fillRect(appleX, appleY, UNIT_BLOCK, UNIT_BLOCK);

        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(wholeSnakeX[i], wholeSnakeY[i], UNIT_BLOCK, UNIT_BLOCK);
            } else {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(wholeSnakeX[i], wholeSnakeY[i], UNIT_BLOCK, UNIT_BLOCK);
            }
        }

        gameOver(g);
    }

    public void newApple() {
        appleX = random.nextInt((int) BOARD_WIDTH / UNIT_BLOCK) * UNIT_BLOCK;
        appleY = random.nextInt((int) BOARD_HEIGHT / UNIT_BLOCK) * UNIT_BLOCK;
    }

    public void checkCollisionApple() {
        if (wholeSnakeX[0] == appleX && wholeSnakeY[0] == appleY) {
            newApple();
            bodyParts++;
            score++;
        }
    }

    public void checkCollisionSnake() {
        for (int i = bodyParts; i > 0; i--){
            if (wholeSnakeX[0] == wholeSnakeX[i] && wholeSnakeY[0] == wholeSnakeY[i]){
                running = false;
            }
        }

        if (wholeSnakeX[0] == BOARD_WIDTH|| wholeSnakeX[0] == -UNIT_BLOCK){
            running = false;
        }
        if (wholeSnakeY[0] == BOARD_HEIGHT || wholeSnakeY[0] == -UNIT_BLOCK){
            running = false;
        }
    }

    public void gameOver(Graphics g){
        if (running == false){
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString("Game Over!", BOARD_WIDTH/2 - UNIT_BLOCK * 5, BOARD_HEIGHT/2-UNIT_BLOCK * 2);
            g.drawString("Please Press Enter to restart game", BOARD_WIDTH/2 - UNIT_BLOCK * 5, BOARD_HEIGHT/2);
            Arrays.fill(wholeSnakeX, 0);
            Arrays.fill(wholeSnakeY, 0);
        }
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
}
