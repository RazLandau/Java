package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Fighter {

	private final static int ADDITIONAL_BASIC_COST = 500;
	private final static int CYLON_COST = 500;

	public CylonRaider(String name, int commissionYear, float maximalSpeed,
			Set<Cylon> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return super.getAnnualMaintenanceCost() + ADDITIONAL_BASIC_COST
				+ cyclonsCost();
	}

	@Override
	public String toString() {
		return "CylonRaider" + super.toString().substring(7);
	}

	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	private int cyclonsCost() {
		return CYLON_COST * getCrewMembers().size();
	}

}
