package il.ac.tau.cs.sw1.hw7;

public interface Vehicle extends VehicleInSpace {
	
	/**
	 * 
	 * @return the max passengers possible
	 */
	public int getMaxPassengers();
	
	/**
	 * 
	 * @return the vehicle name
	 */
	public String getName();
	
	/**
	 * 
	 * @return the max speed of the vehicle
	 */
	public int getMaxSpeed();

	/**
	 * 
	 * @return x coordinate of the vehicle
	 */
	public int getX();
	
	/**
	 * 
	 * @return y coordinate of the vehicle
	 */
	public int getY();
}
