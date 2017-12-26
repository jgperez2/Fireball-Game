import java.util.ArrayList;
import java.util.Scanner;

public class Fireball2 {
	private Graphic graphic;
	private float speed;
	private boolean isAlive;
	private Hero1 hero;
	
	public Fireball2(float x, float y, float directionAngle) {
		graphic = new Graphic("WATER");
		speed = 0.4f;
		graphic.setX(x);
		graphic.setY(y);
		graphic.setDirection(directionAngle);
		isAlive = true;
	}
	
	public boolean shouldRemove() {
		if (isAlive){
			return false;
		}
		else{
			return true;
		}
	}
	
	public Graphic  giveGraphic(){
		return graphic;
	}
	
	public void destroy(){
		isAlive = false;
	}
	
	public void handleFire2(ArrayList<Fireball1> fire) {
		for (int i = 0; i < fire.size(); ++i){
				if (graphic.isCollidingWith(fire.get(i).giveGraphic())){
					destroy();
				}
		}
	}
	
	public void update(int time) {
		if (graphic.getX() < -100 || graphic.getX() > (GameEngine.getWidth() + 100) ||
				graphic.getY() < -100 || graphic.getY() > (GameEngine.getHeight() + 100)){
			isAlive = false;
			}
		
		if (isAlive) {
				graphic.setY((speed * time)*graphic.getDirectionY() + graphic.getY());
				graphic.setX((speed * time)*graphic.getDirectionX() + graphic.getX());
				graphic.draw();
		}				
	}
}
