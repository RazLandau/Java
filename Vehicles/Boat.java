package il.ac.tau.cs.sw1.hw7;

public class Boat implements SeaVessel {

	private int passengers, speed, x, y;
	private String name;

	public Boat(int passengers, String name, int speed) {
		this.passengers = passengers;
		this.name = name;
		this.speed = speed;
	}
	
	/*****************************************************************************************************/
	
	/**********************
	 * Setters&Setters
	 *************************************************************/
	/*****************************************************************************************************/

	public int getMaxPassengers() {
		return passengers;
	}
	
	public void setMaxPassengers(int passengers){
		this.passengers = passengers;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public int getMaxSpeed() {
		return speed;
	}
	
	public void setMaxSpeed(int speed){
		this.speed = speed;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	/*****************************************************************************************************/
	
	/**********************
	 * Functions
	 *************************************************************/
	/*****************************************************************************************************/

	/**
	 * Prints "launch" to stdout
	 */
	public void launch() {
		System.out.println("launch");
	}
	
	/**
	 * 
	 * @return summary of boat's details
	 */
	public String getDetails(){
		return "Boat: name:" + name + ", max-passengers:"
				+ passengers + ", max-speed:" + speed;
	}
	
	/**
	 * Sets boat's x and y coordinates
	 */
	public void move(int x, int y){
		setX(x);
		setY(y);
	}
	

}
