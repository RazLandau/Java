package il.ac.tau.cs.sw1.hw7;

public class Jeep implements LandVehicle {

	private int passengers, speed, numOfWheels, x, y;
	private String name;

	public Jeep(int passengers, String name, int speed, int numOfWheels) {
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
	 * Prints "drive" to stdout
	 */
	public void drive() {
		System.out.println("drive");
	}
	
	/**
	 * 
	 * @return summary of jeep's details
	 */
	public String getDetails(){
		return "Jeep: name:" + name + ", max-passengers:"
				+ passengers + ", max-speed:" + speed + ", num-of-wheels:"
				+ numOfWheels;
	}
	
	/**
	 * Sets jeep's x&y coordinates
	 */
	public void move(int x, int y){
		setX(x);
		setY(y);
	}

}
