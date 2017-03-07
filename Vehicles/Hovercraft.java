package il.ac.tau.cs.sw1.hw7;

public class Hovercraft implements SeaVessel, LandVehicle {

	private int passengers, speed, numOfWheels, x, y;
	private String name;

	public Hovercraft(int passengers, String name, int speed, int numOfWheels) {
		this.passengers = passengers;
		this.name = name;
		this.speed = speed;
		this.numOfWheels = numOfWheels;
	}

	/*****************************************************************************************************/
	
	/**********************
	 * Setters&Setters
	 *************************************************************/
	/*****************************************************************************************************/
	
	public int getMaxPassengers() {
		return passengers;
	}

	public String getName() {
		return name;
	}

	public int getMaxSpeed() {
		return speed;
	}

	public int getNumOfWheels() {
		return numOfWheels;
	}

	public void drive() {
		System.out.println("drive");
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
	 * @return summary of hovercraft's details
	 */
	public String getDetails() {
		return "Hovercraft: name:" + name + ", max-passengers:"
				+ passengers + ", max-speed:" + speed + ", num-of-wheels:"
				+ numOfWheels;
	}
	
	/**
	 * Sets hovercraft's x&y coordinates
	 */
	public void move(int x, int y){
		setX(x);
		setY(y);
	}

}
