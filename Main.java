package Breakout2;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();
		
		obj.setSize(700,600);
		obj.setTitle("Breakout Ball");
		obj.setResizable(false);
		obj.setLocationRelativeTo(null);
		obj.getContentPane().setCursor(null);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		obj.setVisible(true);
	}
}
