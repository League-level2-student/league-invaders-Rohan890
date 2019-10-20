import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	GamePanel g;
	public static void main(String[] args) {
		LeagueInvaders l = new LeagueInvaders();
		l.createGUI();
		

	}
	LeagueInvaders(){
		g = new GamePanel();
	}
	void createGUI() {
		JFrame frame = new JFrame();
		frame.add(g);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(g);
		
		
	}
	void setup() {
		
	}
}
