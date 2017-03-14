package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends AbstractSpaceship {

	final static int BASIC_COST = 3000;
	final static int MEGATON_COST = 5;
	final static int PASSENGER_COST = 3;

	private int cargoCapacity;
	private int passengerCapacity;

	public TransportShip(String name, int commissionYear, float maximalSpeed,
			Set<CrewMember> crewMembers, int cargoCapacity,
			int passengerCapacity) {
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	public int getAnnualMaintenanceCost() {
		return BASIC_COST + cargoCost() + passengersCost();
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	@Override
	public String toString() {
		return "TransportShip" + super.toString()
				+ "\r\n\tAnnualMaintenanceCost=" + getAnnualMaintenanceCost()
				+ "\r\n\tCargoCapacity=" + getCargoCapacity()
				+ "\r\n\tPassengerCapacity=" + getPassengerCapacity();
	}

	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	private int cargoCost() {
		return MEGATON_COST * cargoCapacity;
	}

	private int passengersCost() {
		return PASSENGER_COST * passengerCapacity;
	}

}
