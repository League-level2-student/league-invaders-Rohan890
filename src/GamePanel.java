import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
    Font menuFont;
	Timer frameDraw;
	Timer alienSpawn;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Rocketship r = new Rocketship(250,700,50,50);
	ObjectManager o = new ObjectManager(r);
    @Override
	public void paintComponent(Graphics g){
    	if(currentState == MENU){
    	    drawMenuState(g);
    	}else if(currentState == GAME){
    	    drawGameState(g);
    	}else if(currentState == END){
    	    drawEndState(g);
    	}
	}
    void updateMenuState() {  }
    void updateGameState() { 
    	o.update();
    }
    void updateEndState()  {  }
    void drawMenuState(Graphics g) { 
    g.setColor(Color.BLUE);
    g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    g.setFont(titleFont);
    g.setColor(Color.WHITE);
    g.drawString("LEAGUE INVADERS", 25, 200);
    g.setFont(menuFont);
    g.drawString("Press ENTER to start", 150, 300);
    g.drawString("Press SPACE for instructions", 120, 380);
    }
    void drawGameState(Graphics g) { 
    	g.setColor(Color.BLACK);
    	g.drawImage(image, 0,0,500,800,null);
    	o.draw(g);

    	

    }
    void drawEndState(Graphics g)  { 
    	g.setColor(Color.RED);
    	g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER", 120, 200);
        g.setFont(menuFont);
        g.drawString("Press ENTER to Restart", 150, 380);

    }
    GamePanel(){
        titleFont = new Font("Arial", Font.PLAIN, 48);
        menuFont = new Font("Arial", Font.PLAIN, 20);
        frameDraw = new Timer(1000/60,this);
        frameDraw.start();
        if (needImage) {
            loadImage ("space.png");
        }
 
    }
    void startGame() {
    	  alienSpawn = new Timer(1000 , o);
    	    alienSpawn.start();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}          
		
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			startGame();
		    if (currentState == END) {
		    	alienSpawn.stop();
		        currentState = MENU;  
		    } else {
		        currentState++;
		        alienSpawn.restart();
		    }
		}   
		if (e.getKeyCode()==KeyEvent.VK_UP) {

		    r.up();
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
	
		    r.down();
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {

		    r.right();
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {

		    r.left();
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			o.addProj(r.getProjectile());
			System.out.println("space");
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}

