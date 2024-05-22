package Breakout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;

public class MapGenerator {
	public int map[][];
	public int brickWidth;
	public int brickMeight;
	public MapGenerator(int row,int col) {
		map = new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j]=1; 
			}
		}
		brickWidth=540/col;
		brickMeight=150/row;
	}
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j] >0) {
					g.setColor(Color.white);
					g.fillRect(j*brickWidth+80, i*brickMeight+50, brickWidth, brickMeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickWidth+80, i*brickMeight+50, brickWidth, brickMeight);
					
				}
			}
		}
	}
	public void setBrickValue(int value,int row,int col) {
		map[row][col]=value;
	}
	
	
	
	
}
