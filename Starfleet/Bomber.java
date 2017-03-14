package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends AbstractBattleship {

	private final static int BASIC_COST = 5000;
	private final static double TECHNICIAN_DISCOUNT = 0.1;

	private int numberOfTechnicians;

	public Bomber(String name, int commissionYear, float maximalSpeed,
			Set<CrewMember> crewMembers, List<Weapon> weapon,
			int numberOfTechnicians) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapon);
		this.numberOfTechnicians = numberOfTechnicians;
	}

	public int getNumberOfTechnicians() {
		return numberOfTechnicians;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return BASIC_COST
				+ techniciansDiscount(super.getAnnualMaintenanceCost());
	}

	@Override
	public String toString() {
		String output = "Bomber" + super.toString()
				+ "\r\n\tAnnualMaintenanceCost="
				+ getAnnualMaintenanceCost() + "\r\n\tWeaponArray="
				+ getWeaponArray() + "\r\n\tNumberOfTechnicians="
				+ getNumberOfTechnicians();
		return output;

	}

	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	private int techniciansDiscount(double d) {
		return (int) Math
				.round(d * (1 - (TECHNICIAN_DISCOUNT * numberOfTechnicians)));
	}
}
