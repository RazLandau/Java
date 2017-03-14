package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships,
	 * sorted in descending order by fire power, and then in descending order by
	 * commission year
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear(
			Collection<Spaceship> fleet) {

		List<String> result = new ArrayList<String>();
		List<Spaceship> sortedFleet = new ArrayList<Spaceship>(fleet);
		Collections.sort(sortedFleet, new StarFleetComparator());
		for (Spaceship spaceship : sortedFleet) {
			result.add(spaceship.toString());
		}
		return result;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the
	 * number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(
			Collection<Spaceship> fleet) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (Spaceship spaceship : fleet) {
			String className = spaceship.getClass().getSimpleName();
			Integer value = result.get(className);
			if (value != null) {
				result.put(className, ++value);
			} else {
				result.put(className, 1);
			}
		}
		return result;
	}

	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum
	 * of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost(Collection<Spaceship> fleet) {
		int result = 0;
		for (Spaceship spaceship : fleet) {
			result += spaceship.getAnnualMaintenanceCost();
		}
		return result;
	}

	/**
	 * Returns a set containing the names of all the fleet's weapons installed
	 * on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> result = new HashSet<String>();
		for (Spaceship spaceship : fleet) {
			if(spaceship instanceof AbstractBattleship){
				for(Weapon weapon : ((AbstractBattleship) spaceship).getWeapon()){
					result.add(weapon.getName());
				}
			}
		}
		return result;
	}

	/*
	 * Returns the total number of crew-members serving on board of the given
	 * fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(
			Collection<Spaceship> fleet) {
		int result = 0;
		for (Spaceship spaceship : fleet) {
			result += getNumberOfCrewMemebers(spaceship);
		}
		return result;
	}

	/*
	 * Returns the average age of all officers serving on board of the given
	 * fleet's ships.
	 */
	public static float getAverageAgeOfFleetOfficers(
			Collection<Spaceship> fleet) {
		float sum = 0;
		int count = 0;
		for (Spaceship spaceship : fleet) {
			for (Officer officer : getOfficers(spaceship)) {
				sum += officer.getAge();
				count++;
			}
		}
		return (sum / count);
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys),
	 * to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(
			Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> result = new HashMap<>();
		for (Spaceship spaceship : fleet) {
			Officer highestRankingOfficer = getHighestRankingOfficer(spaceship);
			if (highestRankingOfficer != null) {
				result.put(highestRankingOfficer, spaceship);
			}
		}
		return result;
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences. Each
	 * entry represents a pair composed of an officer rank, and the number of
	 * its occurrences among starfleet personnel. The returned list is sorted
	 * descendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(
			Collection<Spaceship> fleet) {
		Map<OfficerRank, Integer> ranksCounts = new HashMap<>();
		for (Spaceship spaceship : fleet) {
			for (Officer officer : getOfficers(spaceship)) {
				OfficerRank officerRank = officer.getRank();
				Integer value = ranksCounts.get(officerRank);
				if (value == null) {
					ranksCounts.put(officerRank, 1);
				} else {
					ranksCounts.put(officerRank, ++value);
				}
			}
		}
		List<Map.Entry<OfficerRank, Integer>> result = new ArrayList<>(
				ranksCounts.entrySet());
		Collections.sort(result,
				new Comparator<Map.Entry<OfficerRank, Integer>>() {

					@Override
					public int compare(Entry<OfficerRank, Integer> o1,
							Entry<OfficerRank, Integer> o2) {
						int compareByCount = Integer.compare(o2.getValue(),
								o1.getValue());
						if (compareByCount != 0) {
							return compareByCount;
						}
						return o1.getKey().compareTo(o2.getKey());
					}

				});
		return result;
	}

	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	private static Set<Officer> getOfficers(Spaceship spaceship) {
		Set<Officer> result = new HashSet<Officer>();
		for (CrewMember crewMember : spaceship.getCrewMembers()) {
			if (crewMember instanceof Officer) {
				result.add((Officer) crewMember);
			}
		}
		return result;
	}

	private static Officer getHighestRankingOfficer(Spaceship spaceship) {
		Officer result = null;
		OfficerRank highestRank = OfficerRank.Ensign;
		for (Officer officer : getOfficers(spaceship)) {
			OfficerRank officerRank = officer.getRank();
			if (officerRank.compareTo(highestRank) >= 0) {
				result = officer;
				highestRank = officerRank;
			}
		}
		return result;
	}
	
	private static int getNumberOfCrewMemebers(Spaceship spaceship){
		return spaceship.getCrewMembers().size(); 
	}
}
