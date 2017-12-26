import java.util.ArrayList;
import java.util.Random;

public class Enemy {
	
		private Graphic graphic;
		private float   speed;
		private static ArrayList<Fireball2> fireball = new ArrayList<Fireball2>();
		private int fireballCountdown;
		private Random randGen;
		Hero1 hero;
		
		
		public Enemy(float x, float y, Random randGen){
			this.randGen = randGen;
			graphic = new Graphic("PANT");
			graphic.setX(x);
			graphic.setY(y);
			speed = 0.06f;
			fireballCountdown = 3000 + randGen.nextInt(3001);
		}
		
		public Float giveX(){
			float x = graphic.getX();
			return x;
		}
		public Float giveY(){
			float y = graphic.getY();
			return y;
		}
		
		public static ArrayList<Fireball2> giveFireball(){
			return fireball;
		}
		
		public boolean handleFireballCollisions(ArrayList<Fireball1> fireballs) {
			for (int i = 0; i < fireballs.size(); ++i){
				if (graphic.isCollidingWith(fireballs.get(i).giveGraphic())){
					return true;
				}		
			}
			return false;
		}
		
		
		public Fireball2 update(int time){
			
			fireballCountdown -= time;
			double dist = Math.sqrt(Math.pow((graphic.getX() - hero.giveX()), 2) + Math.pow((graphic.getY() - hero.giveY()), 2));
			graphic.setDirection(hero.giveX(), hero.giveY());
			
			
			if ((dist - 100) > 0.0000) {
				graphic.setX((speed * time) * graphic.getDirectionX() + graphic.getX());
				graphic.setY((speed * time) * graphic.getDirectionY() + graphic.getY());
			} else{
				graphic.setX(-1 * (speed * time) * graphic.getDirectionX() + graphic.getX());
				graphic.setY(-1 * (speed * time) * graphic.getDirectionY() + graphic.getY());
			}
			
			if (fireballCountdown < 1){
				fireballCountdown = 2000 + randGen.nextInt(2001);
				return new Fireball2(graphic.getX(), graphic.getY(), (float) (graphic.getDirection() + ((Math.PI * randGen.nextFloat() / 8) - (Math.PI / 16))));
			}
			
			graphic.draw();
			return null;
		}

}
