package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends AbstractBattleship {

	private final static int BASIC_COST = 2500;
	private final static int SPEED_COST = 1000;

	public Fighter(String name, int commissionYear, float maximalSpeed,
			Set<? extends CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return super.getAnnualMaintenanceCost() + BASIC_COST + speedCost();
	}

	@Override
	public String toString() {
		return "Fighter" + super.toString() + "\r\n\tAnnualMaintenanceCost="
				+ getAnnualMaintenanceCost() + "\r\n\tWeaponArray="
				+ getWeaponArray();
	}

	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	private int speedCost() {
		return Math.round(SPEED_COST * getMaximalSpeed());
	}

}
