package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class ExplorationShip extends AbstractSpaceship {

	final static int BASIC_COST = 4000;
	final static int COST_PER_LAB = 2500;
	
	private int numberOfResearchLabs;

	public ExplorationShip(String name, int commissionYear, float maximalSpeed,
			Set<CrewMember> crewMembers, int numberOfResearchLabs) {
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.numberOfResearchLabs = numberOfResearchLabs;
	}
	
	public int getNumberOfResearchLabs(){
		return numberOfResearchLabs;
	}

	public int getAnnualMaintenanceCost() {
		return BASIC_COST + researchersCost();
	}

	@Override
	public String toString() {
		return "ExplorationShip" + super.toString()
				+ "\r\n\tAnnualMaintenanceCost="
				+ getAnnualMaintenanceCost()
				+ "\r\n\tNumberOfResearchLabs=" + this.numberOfResearchLabs;
	}
	
	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/
	
	private int researchersCost(){
		return numberOfResearchLabs * COST_PER_LAB;
	}

}
