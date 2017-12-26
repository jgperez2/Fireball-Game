import java.util.ArrayList;

public class Hero2 {
	
	private Graphic graphic;
	private float   speed;
	private int controlType;
	private int i = 0;
	private static ArrayList<Fireball2> fireball = new ArrayList<Fireball2>();
	private float t = 0;
	
	public Hero2(float x, float y){
		graphic = new Graphic("HERO");
		graphic.setX(x);
		graphic.setY(y);
		graphic.setDirection((float) Math.PI);
		speed = 0.12f;
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
	
	public void update(int time){
		float pi = (float) Math.PI;
		double dist = Math.sqrt(Math.pow((graphic.getX() - GameEngine.getMouseX()),
				2) + Math.pow((graphic.getY() - GameEngine.getMouseY()), 2));
		
		if (GameEngine.isKeyPressed("ENTER")){
			Fireball2 ball = new Fireball2(graphic.getX(), graphic.getY(), graphic.getDirection());
			fireball.add(ball);
		}
		for (int i = 0; i < fireball.size(); ++i){
			if (fireball.get(i).shouldRemove()){
				fireball.remove(i);
			}
			else
				fireball.get(i).update(time);
		}
		if (GameEngine.isKeyHeld("DOWN") && GameEngine.isKeyHeld("RIGHT")) {
			graphic.setX((speed * time) + graphic.getX());
			graphic.setY((speed * time) + graphic.getY());
			graphic.setDirection(pi / 4);
		}else if (GameEngine.isKeyHeld("DOWN") && GameEngine.isKeyHeld("LEFT")) {
			graphic.setX(-1 * (speed * time) + graphic.getX());
			graphic.setY((speed * time) + graphic.getY());
			graphic.setDirection(3 * pi / 4);
		}else if (GameEngine.isKeyHeld("LEFT") && GameEngine.isKeyHeld("UP")) {
			graphic.setX(-1 * (speed * time) + graphic.getX());
			graphic.setY(-1 * (speed * time) + graphic.getY());
			graphic.setDirection(5 * pi / 4);
		}else if (GameEngine.isKeyHeld("UP") && GameEngine.isKeyHeld("RIGHT")) {
			graphic.setX((speed * time) + graphic.getX());
			graphic.setY(-1 * (speed * time) + graphic.getY());
			graphic.setDirection(7 * pi / 4);
		}else if (GameEngine.isKeyHeld("DOWN")) {
			graphic.setY((speed * time) + graphic.getY());
			graphic.setDirection(pi / 2);
		}else if (GameEngine.isKeyHeld("LEFT")) {
			graphic.setX(-1 * (speed * time) + graphic.getX());
			graphic.setDirection(pi);
		}else if (GameEngine.isKeyHeld("UP")) {
			graphic.setY((-1 * speed * time) + graphic.getY());
			graphic.setDirection((3 * pi) / 2);
		}else if (GameEngine.isKeyHeld("RIGHT")) {
			graphic.setX((speed * time) + graphic.getX());
			graphic.setDirection(0);
		}
		graphic.draw();
		return;
	}

}
