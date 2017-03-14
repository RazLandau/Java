package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter {

	private final static List<Weapon> DEFAULT_WEAPON = Arrays
			.asList(new Weapon("Laser Cannons", 10, 100));
	
	private static int STEALTH_ENGINE_COST = 50;
	private static int numberOfStealthEngines = 0;


	public StealthCruiser(String name, int commissionYear, float maximalSpeed,
			Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		numberOfStealthEngines++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed,
			Set<CrewMember> crewMembers) {
		this(name, commissionYear, maximalSpeed, crewMembers,
				new ArrayList<Weapon>(DEFAULT_WEAPON));
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return super.getAnnualMaintenanceCost() + enginesCost();
	}

	@Override
	public String toString() {
		return "StealthCruiser" + super.toString().substring(7);
	}

	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	private int enginesCost() {
		return STEALTH_ENGINE_COST * numberOfStealthEngines;
	}

}
