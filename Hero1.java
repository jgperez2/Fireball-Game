import java.util.ArrayList;

public class Hero1 {
	
	private static Graphic graphic;
	private float   speed;
	private int controlType;
	private int i = 0;
	private static ArrayList<Fireball1> fireball = new ArrayList<Fireball1>();
	private float t = 0;
	public int HP;
	
	public Hero1(float x, float y){
		graphic = new Graphic("HERO");
		graphic.setX(x);
		graphic.setY(y);
		speed = 0.12f;
		HP = 10;
	}
	
	public static ArrayList<Fireball1> giveFireball(){
		return fireball;
	}
	
	public static Float giveX(){
		return graphic.getX();
	}
	public static Float giveY(){
		return graphic.getY();
	}
	
	public boolean handleFireballCollisions(Fireball2 fireball) {
		if (graphic.isCollidingWith(fireball.giveGraphic())){
			--HP;
			return true;
		}		
		return false;
	}
	
	public void update(int time){
		float pi = (float) Math.PI;
		double dist = Math.sqrt(Math.pow((graphic.getX() - GameEngine.getMouseX()),
				2) + Math.pow((graphic.getY() - GameEngine.getMouseY()), 2));
		
		if (GameEngine.isKeyPressed("SPACE")){
			Fireball1 ball = new Fireball1(graphic.getX(), graphic.getY(), graphic.getDirection());
			fireball.add(ball);
		}
		for (int i = 0; i < fireball.size(); ++i){
			if (fireball.get(i).shouldRemove()){
				fireball.remove(i);
			}
			else
				fireball.get(i).update(time);
		}
		if (GameEngine.isKeyHeld("S") && GameEngine.isKeyHeld("D")) {
			graphic.setX((speed * time) + graphic.getX());
			graphic.setY((speed * time) + graphic.getY());
			graphic.setDirection(pi / 4);
		}else if (GameEngine.isKeyHeld("S") && GameEngine.isKeyHeld("A")) {
			graphic.setX(-1 * (speed * time) + graphic.getX());
			graphic.setY((speed * time) + graphic.getY());
			graphic.setDirection(3 * pi / 4);
		}else if (GameEngine.isKeyHeld("A") && GameEngine.isKeyHeld("W")) {
			graphic.setX(-1 * (speed * time) + graphic.getX());
			graphic.setY(-1 * (speed * time) + graphic.getY());
			graphic.setDirection(5 * pi / 4);
		}else if (GameEngine.isKeyHeld("W") && GameEngine.isKeyHeld("D")) {
			graphic.setX((speed * time) + graphic.getX());
			graphic.setY(-1 * (speed * time) + graphic.getY());
			graphic.setDirection(7 * pi / 4);
		}else if (GameEngine.isKeyHeld("S")) {
			graphic.setY((speed * time) + graphic.getY());
			graphic.setDirection(pi / 2);
		}else if (GameEngine.isKeyHeld("A")) {
			graphic.setX(-1 * (speed * time) + graphic.getX());
			graphic.setDirection(pi);
		}else if (GameEngine.isKeyHeld("W")) {
			graphic.setY((-1 * speed * time) + graphic.getY());
			graphic.setDirection((3 * pi) / 2);
		}else if (GameEngine.isKeyHeld("D")) {
			graphic.setX((speed * time) + graphic.getX());
			graphic.setDirection(0);
		}
		graphic.draw();
		return;
	}

}
