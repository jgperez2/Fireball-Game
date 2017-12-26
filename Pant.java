import java.util.ArrayList;
import java.util.Random;

public class Pant {
	private Graphic graphic;
	private Random randGen;
	private boolean isAlive;
	private Fire1 fire;
	private Pant pant;
	private Enemy enemy;
	 
	public Pant(float x, float y, Random randGen) {
		graphic = new Graphic("n");
		graphic.setPosition(x, y);
		graphic.setDirection(randGen.nextInt((int) (2 * Math.PI)));
		isAlive = true;
		this.randGen = randGen;
	}
	
	public boolean shouldRemove() {
		if (isAlive){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	public Fire1 handleFireballCollisions1(ArrayList<Fireball1> fireballs) {
		if (!this.isAlive)
			return null;
		for (int i = 0; i < fireballs.size(); ++i){
			if (graphic.isCollidingWith(fireballs.get(i).giveGraphic())){
				Fire1 fire = new Fire1(graphic.getX(), graphic.getY(), randGen);
				isAlive = false;
				fireballs.get(i).destroy();
				return fire; 
			}
		}
		return null;
	}
	public Fire2 handleFireballCollisions2(ArrayList<Fireball2> fireballs) {
		if (!this.isAlive)
			return null;
		for (int i = 0; i < fireballs.size(); ++i){
			if (graphic.isCollidingWith(fireballs.get(i).giveGraphic())){
				Fire2 fire = new Fire2(graphic.getX(), graphic.getY(), randGen);
				isAlive = false;
				fireballs.get(i).destroy();
				return fire; 
			}
		}
		return null;
	}
	public Enemy handleFireballCollisions3(ArrayList<Fireball2> fireballs) {
		if (!this.isAlive)
			return null;
		for (int i = 0; i < fireballs.size(); ++i){
			if (graphic.isCollidingWith(fireballs.get(i).giveGraphic())){
				Enemy enemy = new Enemy(graphic.getX(), graphic.getY(), randGen);
				isAlive = false;
				fireballs.get(i).destroy();
				return enemy; 
			}
		}
		return null;
	}

	public void update(int time) {
			graphic.draw();
	}
}
