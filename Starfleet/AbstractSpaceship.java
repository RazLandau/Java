package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class AbstractSpaceship implements Spaceship {

	private static final int DEFAULT_FIRE_POWER = 10;

	private String name;
	private int commissionYear;
	private float maximalSpeed;
	private int firePower = DEFAULT_FIRE_POWER;
	private Set<? extends CrewMember> crewMembers;

	public AbstractSpaceship(String name, int commissionYear,
			float maximalSpeed, Set<? extends CrewMember> crewMembers) {
		this.commissionYear = commissionYear;
		this.name = name;
		this.maximalSpeed = maximalSpeed;
		this.crewMembers = crewMembers;
	}

	public String getName() {
		return name;
	}

	public int getCommissionYear() {
		return commissionYear;
	}

	public float getMaximalSpeed() {
		return maximalSpeed;
	}

	public int getFirePower() {
		return firePower;
	}

	public Set<? extends CrewMember> getCrewMembers() {
		return crewMembers;
	}

	@Override
	public String toString() {
		return "\r\n\tName=" + getName() + "\r\n\tCommissionYear="
				+ getCommissionYear() + "\r\n\tMaximalSpeed="
				+ getMaximalSpeed() + "\r\n\tFirePower="
				+ getFirePower() + "\r\n\tCrewMembers="
				+ getNumberOfCrewMemebers();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSpaceship other = (AbstractSpaceship) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/
	
	private int getNumberOfCrewMemebers(){
		return crewMembers.size(); 
	}

}
