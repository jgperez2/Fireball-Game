
public class Water {
	
	private Graphic graphic;
	public float   speed;
	private float direction;
	private float distanceTraveled;
	
	public Water(float x, float y, float direction){
		graphic = new Graphic("WATER");
		graphic.setX(x);
		graphic.setY(y);
		graphic.setDirection(direction);
		speed = 0.7f;
		distanceTraveled = 0;
	}
	
	public Graphic  giveGraphic(){
		return graphic;
	}
	
	public Water update(int time){
		
		distanceTraveled += (speed * time);
		if (distanceTraveled < 200){
			graphic.setY((speed * time)*graphic.getDirectionY() + graphic.getY());
			graphic.setX((speed * time)*graphic.getDirectionX() + graphic.getX());
			graphic.draw();
			return this;
		}
		return null;
	}

}