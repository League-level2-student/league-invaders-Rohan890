import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	int score = 0;
	Rocketship r;
	ArrayList<Projectile> proj = new ArrayList<Projectile>();
	ArrayList<Alien> al = new ArrayList<Alien>();
	Random random = new Random();

	ObjectManager(Rocketship r) {
		this.r = r;
	}

	void addProj(Projectile p) {
		proj.add(p);
	}

	void addAlien() {
		al.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void update() {
		for (int i = 0; i < al.size(); i++) {
			al.get(i).update();
			if (al.get(i).y >= LeagueInvaders.HEIGHT && al.get(i).y <= 0) {
				al.get(i).isActive = false;
			}
		}
		for (int i = 0; i < proj.size(); i++) {
			proj.get(i).update();
			if (proj.get(i).y >= LeagueInvaders.HEIGHT && proj.get(i).y == al.get(i).y) {
				proj.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}

	void draw(Graphics g) {
		r.draw(g);
		for (int i = 0; i < al.size(); i++) {
			al.get(i).draw(g);
		}
		for (int i = 0; i < proj.size(); i++) {
			proj.get(i).draw(g);
		}
	}

	void purgeObjects() {
		for (int i = 0; i < al.size(); i++) {
			if (al.get(i).isActive == false) {
				al.remove(i);
			}
		}
		for (int i = 0; i < proj.size(); i++) {
			if (proj.get(i).isActive == false) {
				proj.remove(i);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}

	void checkCollision() {

		for (int i = 0; i < al.size(); i++) {
			if (r.collisionBox.intersects(al.get(i).collisionBox)) {
				al.get(i).isActive = false;
				r.isActive = false;
				score = 0;
				
			}
			for (int o = 0; o < proj.size(); o++) {
				if (proj.get(o).collisionBox.intersects(al.get(i).collisionBox)) {
					al.get(i).isActive = false;
					proj.get(o).isActive = false;
					score+= 1;

				}
			}
		}

	}
	int getScore() {
		return score;
	}
}
