package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class AbstractBattleship extends AbstractSpaceship {

	private List<Weapon> weapon;
	
	public AbstractBattleship(String name, int commissionYear,
			float maximalSpeed, Set<? extends CrewMember> crewMembers,
			List<Weapon> weapon) {
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapon = weapon;
	}

	public List<Weapon> getWeapon() {
		return weapon;
	}

	@Override
	public int getFirePower() {
		int result = 0;
		for (Weapon wep : weapon) {
			result += wep.getFirePower();
		}
		return super.getFirePower() + result;
	}

	public int getAnnualMaintenanceCost() {
		int result = 0;
		for (Weapon wep : weapon) {
			result += wep.getAnnualMaintenanceCost();
		}
		return result;
	}

	public String getWeaponArray() {
		String result = "[";
		for (Weapon wep : weapon) {
			result += wep.toString();
			result += ", ";
		}
		result = result.substring(0, result.length() - 2);
		result += "]";
		return result;
	}

}
