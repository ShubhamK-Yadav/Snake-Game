package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Game extends JPanel implements ActionListener{
	private static final int BOARD_WIDTH = 600, BOARD_HEIGHT = 600;
	private static final int UNIT_BLOCK = 30;
	private static int gameBlocks = BOARD_WIDTH * BOARD_HEIGHT / UNIT_BLOCK;
	
	private static int[] wholeSnakeX = new int [gameBlocks];
	private static int[] wholeSnakeY = new int [gameBlocks];
	private static int bodyParts = 3;
	private static int score = 0;
	private static int appleX;
	private static int appleY;
	
	
	public Game() {
		this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		this.setBackground(Color.black);
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
		// TODO Auto-generated method stub
		
	}

}
