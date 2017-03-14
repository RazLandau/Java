package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;

public class StarFleetComparator implements Comparator<Spaceship> {

	private StarFleetFirePowerComparator starFleetFirePowerComparator = new StarFleetFirePowerComparator();
	private StarFleetCommissionYearComparator starFleetCommissionYearComparator = new StarFleetCommissionYearComparator();
	private StarFleetNameComparator starFleetNameComparator = new StarFleetNameComparator();

	public int compare(Spaceship o1, Spaceship o2) {
		int compareByFirePower = starFleetFirePowerComparator.compare(o1, o2);
		if (compareByFirePower != 0) {
			return compareByFirePower;
		}
		int compareByCommissionYear = starFleetCommissionYearComparator
				.compare(o1, o2);
		if (compareByCommissionYear != 0) {
			return compareByCommissionYear;
		}
		return starFleetNameComparator.compare(o1, o2);
	}

	public class StarFleetFirePowerComparator implements Comparator<Spaceship> {

		@Override
		public int compare(Spaceship o1, Spaceship o2) {
			return Integer.compare(o2.getFirePower(), o1.getFirePower());
		}

	}

	public class StarFleetCommissionYearComparator
			implements Comparator<Spaceship> {

		@Override
		public int compare(Spaceship o1, Spaceship o2) {
			return Integer.compare(o2.getCommissionYear(),
					o1.getCommissionYear());
		}

	}

	public class StarFleetNameComparator implements Comparator<Spaceship> {

		@Override
		public int compare(Spaceship o1, Spaceship o2) {
			return o1.getName().compareTo(o2.getName());
		}

	}
}
