package snake;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public GameFrame() {
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Snake Game");
		this.add(new Game());
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new GameFrame();
	}
}
