package Breakout2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener,MouseMotionListener {

    private boolean play = false;
    private int score = 0;

    private int totalBricks;

    private Timer timer;
    private int delay = 6;

    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    
    private int barWidth = 100;
    
    private MapGenerator map;
    
    public Gameplay() {
    	// số hàng và số cột gạch
    	map = new MapGenerator(3, 7);
    	totalBricks = map.getTotalCollision();
    	//
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setCursor(null);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        //
        map.draw((Graphics2D)g);
        // Borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25) );
        g.drawString(""+ score, 500,30);
        // THE PADDLE
        g.setColor(Color.green);
        g.fillRect(playerX, 550, barWidth, 8);

        // The ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);
        if(totalBricks <=0) {
        	play= false;
        	ballXdir=0;
        	ballYdir=0;
        	g.setColor(Color.red);
        	g.setFont(new Font("serif",Font.BOLD,30));
        	g.drawString("You Won", 190,300);
        	
        	g.setFont(new Font("serif",Font.BOLD,20));
        	g.drawString("Ấn Enter để Restart ", 230,350);
        }
        if(ballposY >570) {
        	play= false;
        	ballXdir=0;
        	ballYdir=0;
        	g.setColor(Color.red);
        	g.setFont(new Font("serif",Font.BOLD,30));
        	g.drawString("Game Over, Scores:", 190,300);
        	
        	g.setFont(new Font("serif",Font.BOLD,20));
        	g.drawString("Ấn Enter để Restart ", 230,350);
        	 
        }
//        g.dispose();
    }

  

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX + barWidth >= 690) {
                playerX =690-barWidth;
            } else {
                moverRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moverLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
        	if(!play) {
        		play = true;
        		ballposX=120;
        		ballposY=350;
        		ballXdir=-1;
        		ballYdir=-2;
        		playerX=310;
        		score =0;
        		totalBricks=21;
        		map = new MapGenerator(3, 7);
        		
        		repaint();
        	}
        }

    }

    public void moverRight() {
        play = true;
        playerX += 20;
    }

    public void moverLeft() {
        play = true;
        playerX -= 20;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		//ball mơve nè
		if(play) {
			//ball chạm vật lí với thanh đỡ
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,barWidth,8))) {
				ballYdir=-ballYdir;
			}
			//
			A:for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					if(map.map[i][j]>0) {
						int brickX = j*map.brickWidth+80;
						int brickY = i*map.brickMeight+50;
						int brickWidth= map.brickWidth;
						int brickMeight = map.brickMeight;
						
						Rectangle rect = new  Rectangle(brickX,brickY, brickWidth, brickMeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(map.getBrickValue(i, j)-1, i, j);
							totalBricks--;
							score+=5;
							
							if(ballposX+19 <= brickRect.x|| ballposX + 1>= brickRect.x +brickRect.width) {
								ballXdir=-ballXdir;
							}else {
								ballYdir=-ballYdir;
							}
							break A;	
							
						}
					}
				}
			}
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX<0) {
				ballXdir= -ballXdir;
			}
			if(ballposY<0) {
				ballYdir = -ballYdir;
			}
			if(ballposX>670) {
				ballXdir= -ballXdir;
			}
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX()>0&&e.getX()<=700) {
			if(e.getX()>=700-barWidth) {
				play = true;
				playerX=700-barWidth;
			}else {
				play = true;
				playerX = e.getX();
			}
		}
	}	
}
