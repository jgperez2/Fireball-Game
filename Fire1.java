import java.util.ArrayList;
import java.util.Random;

public class Fire1 {
	
	private Random randGen;
	private int fireballCountdown;
	private Graphic graphic;
	private int heat;
	
	public Fire1(float x, float y, Random randGen) {
		graphic = new Graphic("FIRE");
		graphic.setPosition(x, y);
		graphic.setDirection(randGen.nextInt((int) (2 * Math.PI)));
		fireballCountdown = 3000 + randGen.nextInt(3001);
		this.randGen = randGen;
		heat = 40;
	}
	
	public boolean shouldRemove() {
		if (heat >= 1){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void handleFire2(ArrayList<Fireball2> fire) {
		for (int i = 0; i < fire.size(); ++i){
				if (graphic.isCollidingWith(fire.get(i).giveGraphic())){
					--heat;
				}
		}
	}
	
	public Fireball1 update(int time) {
		
		fireballCountdown -= time;
		if (heat >= 1){
			graphic.draw();
			if (fireballCountdown >= 0){
				return null;
			}
			else{
				fireballCountdown = 3000 + randGen.nextInt(3001);
				graphic.setDirection(randGen.nextInt((int) (2 * Math.PI)));
				return new Fireball1(graphic.getX(), graphic.getY(), graphic.getDirection());
			}
		}
		else{
			return null;
		}
	}	
}
