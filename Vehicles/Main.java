package il.ac.tau.cs.sw1.hw7;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	/**
	 * 
	 * Main interaction with user
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Vehicle[] vehicles = getVehicleFromUser();
		writeVehiclesToFile(args[0], vehicles);
		writeVehiclesSummaryToFile(args[1], vehicles);
	}

	/**
	 * Tester simulating user (switch final argument with file paths!)
	 * @throws IOException
	 */
	public static void mainTester() throws IOException {

		final String vehiclesFile = "ARG[0]";
		final String vehiclesSummaryFile = "ARG[1]";

		Jeep jeep = new Jeep(5, "Grand-Cherokee", 210, 4);
		Hovercraft hovercraft = new Hovercraft(140, "Pomornik", 110, 8);
		Boat boat = new Boat(10, "Caravel", 15);
		Vehicle[] vehicles = { jeep, hovercraft, boat };
		writeVehiclesToFile(vehiclesFile, vehicles);
		writeVehiclesSummaryToFile(vehiclesSummaryFile, vehicles);
		VehicleInSpace[] vehiclesInSpace = { jeep, hovercraft, boat };
		jeep.move(1, 4);
		hovercraft.move(4, 1);
		assert getTravelTime(vehiclesInSpace, 3, 1, 3, 3, 6, true) == (double) 3
				/ 110;
		jeep.move(0, 0);
		hovercraft.move(0, 0);
		boat.move(0, 0);
		assert getTravelTime(vehiclesInSpace, 1, 1, 2, 2, 4, true) == 2
				* Math.sqrt(2) / 210;
	}

	/**
	 * 
	 * @return array of vehicles as given by the user (max 20), printing
	 *         feedback to stdout
	 * 
	 */
	public static Vehicle[] getVehicleFromUser() {
		Vehicle[] vehicles = new Vehicle[20];

		Scanner input = new Scanner(System.in);

		int i = 0;
		while (i < 20) {
			String vehicleType = getVehicleTypeFromUser(input);
			switch (vehicleType) {
			case ("J"):
				Jeep jeep = getJeepFromUser(input);
				vehicles[i++] = jeep;
				System.out.println(
						"Vehicle added: [" + jeep.getDetails() + "]\n");
				break;
			case ("B"):
				Boat boat = getBoatFromUser(input);
				vehicles[i++] = boat;
				System.out.println(
						"Vehicle added: [" + boat.getDetails() + "]\n");
				break;
			case ("H"):
				Hovercraft hovercraf = getHovercraftFromUser(input);
				vehicles[i++] = hovercraf;
				System.out.println(
						"Vehicle added: [" + hovercraf.getDetails() + "]\n");
				break;
			case ("X"):
				input.close();
				return vehicles;
			default:
				System.out.println("Unknown command. Please try again.\n");
			}
		}
		input.close();
		return vehicles;
	}

	/**
	 * 
	 * Writes array of vehicles to given file in standard format
	 * 
	 * @param outputFilename
	 * @param vehicles
	 * 
	 */
	public static void writeVehiclesToFile(String outputFilename,
			Vehicle[] vehicles) throws IOException {
		FileWriter fileWriter = new FileWriter(new File(outputFilename));
		writeLandVehiclesToFile(fileWriter, vehicles);
		fileWriter.write("\n\n");
		writeSeaVesselToFile(fileWriter, vehicles);
		fileWriter.close();
	}

	/**
	 * 
	 * Writes summary for all vehicles in array by category
	 * 
	 * @param outputFilename
	 * @param vehicles
	 * @throws IOException
	 */
	public static void writeVehiclesSummaryToFile(String outputFilename,
			Vehicle[] vehicles) throws IOException {
		FileWriter fileWriter = new FileWriter(new File(outputFilename));

		int numOfLandVehicles = numOfLandVehicles(vehicles);
		int numOfLandPassengers = NumOfLandPassengers(vehicles);
		int landMaxSpeed = getLandMaxSpeed(vehicles);
		int landMinSpeed = getLandMinSpeed(vehicles);

		writeLandVehiclesSummary(fileWriter, numOfLandVehicles,
				numOfLandPassengers, landMaxSpeed, landMinSpeed);

		int numOfSeaVessel = numOfSeaVessel(vehicles);
		int numOfSeaPassengers = NumOfSeaPassengers(vehicles);
		int seaMaxSpeed = getSeaMaxSpeed(vehicles);
		int seaMinSpeed = getSeaMinSpeed(vehicles);

		writeSeaVesselSummary(fileWriter, numOfSeaVessel, numOfSeaPassengers,
				seaMaxSpeed, seaMinSpeed);

		fileWriter.close();

	}

	/**
	 * 
	 * @param vehicles
	 * @param source_x
	 * @param source_y
	 * @param dest_x
	 * @param dest_y
	 * @param passengers
	 * @param land
	 * @return the minimal travel time from (source_x,source_y) to
	 *         (dest_x,dest_y) for given number of passengers from all vehicles
	 *         in given array
	 */
	public static double getTravelTime(VehicleInSpace[] vehicles, int source_x,
			int source_y, int dest_x, int dest_y, int passengers,
			boolean land) {
		double minTravelTime = Integer.MAX_VALUE;
		for (VehicleInSpace vehicle : vehicles) {
			if (vehicleIsDrivable((Vehicle) vehicle, passengers, land)) {
				double travelTime = vehicleTravelTime((Vehicle) vehicle,
						source_x, source_y, dest_x, dest_y);
				if (travelTime < minTravelTime) {
					minTravelTime = travelTime;
				}
			}
		}
		return minTravelTime;
	}

	/*****************************************************************************************************/

	/**********************
	 * helper functions
	 *************************************************************/
	/*****************************************************************************************************/

	/**
	 * 
	 * @param vehicle
	 * @param land
	 * @return true if vehicle is compatible with type, false otherwise
	 */
	private static boolean vehicleIsCompatible(Vehicle vehicle, boolean land) {
		if (land && vehicle instanceof LandVehicle) {
			return true;
		}
		if (!land && vehicle instanceof SeaVessel) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param vehicle
	 * @param passengers
	 * @return true if given vehicles has enough room for given passengers
	 */
	private static boolean vehicleHasEnoughRoom(Vehicle vehicle,
			int passengers) {
		return vehicle.getMaxPassengers() >= passengers;
	}

	/**
	 * 
	 * @param vehicle
	 * @param passengers
	 * @param land
	 * @return true if vehicle is drivable, false otherwise
	 */
	private static boolean vehicleIsDrivable(Vehicle vehicle, int passengers,
			boolean land) {
		return vehicleHasEnoughRoom(vehicle, passengers)
				&& vehicleIsCompatible(vehicle, land);
	}

	/**
	 * 
	 * @param vehicle
	 * @param source_x
	 * @param source_y
	 * @param dest_x
	 * @param dest_y
	 * @return travel distance from (source_x, source_y) to (dest_x, dest_y)
	 *         using given vehicle
	 */
	private static double vehicleTravelDistance(Vehicle vehicle, int source_x,
			int source_y, int dest_x, int dest_y) {
		double getToStart = Point.distance(vehicle.getX(), vehicle.getY(),
				source_x, source_y);
		double getToEnd = Point.distance(source_x, source_y, dest_x, dest_y);
		return getToStart + getToEnd;
	}

	/**
	 * 
	 * @param vehicle
	 * @param source_x
	 * @param source_y
	 * @param dest_x
	 * @param dest_y
	 * @return travel time from (source_x,source_y) to (dest_x,dest_y) in given
	 *         vehicle
	 */
	private static double vehicleTravelTime(Vehicle vehicle, int source_x,
			int source_y, int dest_x, int dest_y) {
		double travelDistance = vehicleTravelDistance(vehicle, source_x,
				source_y, dest_x, dest_y);
		return travelDistance / vehicle.getMaxSpeed();
	}

	/**
	 * 
	 * @param input
	 * @return jeep vehicle from user
	 */
	private static Jeep getJeepFromUser(Scanner input) {
		String name = getNameFromUser(input);
		int passengers = getPassengersFromUser(input);
		int speed = getSpeedFromUser(input);
		int numOfWheels = getNumOfWheelsFromUser(input);
		return new Jeep(passengers, name, speed, numOfWheels);
	}

	/**
	 * 
	 * @param input
	 * @return boat vehicle from user
	 */
	private static Boat getBoatFromUser(Scanner input) {
		String name = getNameFromUser(input);
		int passengers = getPassengersFromUser(input);
		int speed = getSpeedFromUser(input);
		return new Boat(passengers, name, speed);
	}

	/**
	 * 
	 * @param input
	 * @return hovercrafy vehicle from user
	 */
	private static Hovercraft getHovercraftFromUser(Scanner input) {
		String name = getNameFromUser(input);
		int passengers = getPassengersFromUser(input);
		int speed = getSpeedFromUser(input);
		int numOfWheels = getNumOfWheelsFromUser(input);
		return new Hovercraft(passengers, name, speed, numOfWheels);
	}

	/**
	 * 
	 * @param input
	 * @return type of vehicle from user
	 */
	private static String getVehicleTypeFromUser(Scanner input) {
		printChooseVehicle();
		return input.nextLine();
	}

	/**
	 * 
	 * @param input
	 * @return name of vehicle from user
	 */
	private static String getNameFromUser(Scanner input) {
		System.out.print("Please enter name: ");
		return input.nextLine();
	}

	/**
	 * 
	 * @param input
	 * @return max passangers for vehicle from user
	 */
	private static int getPassengersFromUser(Scanner input) {
		System.out.print("Please enter max passengers: ");
		return Integer.parseInt(input.nextLine());
	}

	/**
	 * 
	 * @param input
	 * @return max speed for vehicle from user
	 */
	private static int getSpeedFromUser(Scanner input) {
		System.out.print("Please enter max speed: ");
		return Integer.parseInt(input.nextLine());
	}

	/**
	 * 
	 * @param input
	 * @return number of wheels for vehicle from user
	 */
	private static int getNumOfWheelsFromUser(Scanner input) {
		System.out.print("Please enter num of wheels: ");
		return Integer.parseInt(input.nextLine());
	}

	/**
	 * Prints message to user in standard format
	 */
	private static void printChooseVehicle() {
		System.out.println("Please choose vehicle type:\n" + "J - Jeep\n"
				+ "B - Boat\n" + "H - Hovercraft\n" + "X - Exit");
	}

	/**
	 * 
	 * Writes all land vehicles in given vehicles array to given file in
	 * standard format
	 * 
	 * @param fileWriter
	 * @param vehicles
	 * @throws IOException
	 */
	private static void writeLandVehiclesToFile(FileWriter fileWriter,
			Vehicle[] vehicles) throws IOException {
		fileWriter.write("Land Vehicles:\n");
		for (int i = 0; i < vehicles.length; i++) {
			if (vehicles[i] instanceof Jeep) {
				fileWriter.write("\n" + ((Jeep) (vehicles[i])).getDetails());
			}
			if (vehicles[i] instanceof Hovercraft) {
				fileWriter.write(
						"\n" + ((Hovercraft) (vehicles[i])).getDetails());
			}
		}
	}

	/**
	 * 
	 * Writes all sea vessels in given vehicles array to given file in standard
	 * format
	 * 
	 * @param fileWriter
	 * @param vehicles
	 * @throws IOException
	 */
	private static void writeSeaVesselToFile(FileWriter fileWriter,
			Vehicle[] vehicles) throws IOException {
		fileWriter.write("Sea Vessel:\n");
		for (int i = 0; i < vehicles.length; i++) {
			if (vehicles[i] instanceof Boat) {
				fileWriter.write("\n" + ((Boat) (vehicles[i])).getDetails());
			}
			if (vehicles[i] instanceof Hovercraft) {
				fileWriter.write(
						"\n" + ((Hovercraft) (vehicles[i])).getDetails());
			}
		}
	}

	/**
	 * 
	 * @param vehicles
	 * @return number of land vehicles in given vehicles array
	 */
	private static int numOfLandVehicles(Vehicle[] vehicles) {
		int result = 0;
		for (int i = 0; i < vehicles.length; i++) {
			if (vehicles[i] instanceof LandVehicle) {
				result++;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param vehicles
	 * @return number of sea vessels in given vehicles array
	 */
	private static int numOfSeaVessel(Vehicle[] vehicles) {
		int result = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof SeaVessel) {
				result++;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param vehicles
	 * @return sea vessels passenger in given vehicles array
	 */
	private static int NumOfSeaPassengers(Vehicle[] vehicles) {
		int result = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof SeaVessel) {
				result += vehicle.getMaxPassengers();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param vehicles
	 * @return land vehicles passenger in given vehicles array
	 */
	private static int NumOfLandPassengers(Vehicle[] vehicles) {
		int result = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof LandVehicle) {
				result += vehicle.getMaxPassengers();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param vehicles
	 * @return max speed out of all sea vessels in given vehicles array
	 */
	private static int getSeaMaxSpeed(Vehicle[] vehicles) {
		int max = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof SeaVessel) {
				max = Math.max(max, vehicle.getMaxSpeed());
			}
		}
		return max;
	}

	/**
	 * 
	 * @param vehicles
	 * @return max speed out of all land vehicles in given vehicles array
	 */
	private static int getLandMaxSpeed(Vehicle[] vehicles) {
		int max = 0;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof LandVehicle) {
				max = Math.max(max, vehicle.getMaxSpeed());
			}
		}
		return max;
	}

	/**
	 * 
	 * @param vehicles
	 * @return min speed out of all land vehicles in given vehicles array
	 */
	private static int getLandMinSpeed(Vehicle[] vehicles) {
		int min = Integer.MAX_VALUE;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof LandVehicle) {
				min = Math.min(min, vehicle.getMaxSpeed());
			}
		}
		return min;
	}

	/**
	 * 
	 * @param vehicles
	 * @return min speed out of all sea vessels in given vehicles array
	 */
	private static int getSeaMinSpeed(Vehicle[] vehicles) {
		int min = Integer.MAX_VALUE;
		for (Vehicle vehicle : vehicles) {
			if (vehicle instanceof SeaVessel) {
				min = Math.min(min, vehicle.getMaxSpeed());
			}
		}
		return min;
	}

	/**
	 * 
	 * Writes summary for land vehicles to given file
	 * 
	 * @param fileWriter
	 * @param numOfLandVehicles
	 * @param numOfLandPassengers
	 * @param landMaxSpeed
	 * @param landMinSpeed
	 * @throws IOException
	 */
	private static void writeLandVehiclesSummary(FileWriter fileWriter,
			int numOfLandVehicles, int numOfLandPassengers, int landMaxSpeed,
			int landMinSpeed) throws IOException {
		fileWriter.write("Land vehicles:\r\n\r\n");
		fileWriter.write("Total land vehicles:" + numOfLandVehicles + "\r\n");
		fileWriter.write(
				"Total passengers possible:" + numOfLandPassengers + "\r\n");
		fileWriter.write("Max speed:" + landMaxSpeed + "\r\n");
		fileWriter.write("Min speed:" + landMinSpeed + "\r\n\r\n");
	}

	/**
	 * 
	 * Writes summary of sea vessels to given file
	 * 
	 * @param fileWriter
	 * @param numOfSeaVessel
	 * @param numOfSeaPassengers
	 * @param seaMaxSpeed
	 * @param seaMinSpeed
	 * @throws IOException
	 */
	private static void writeSeaVesselSummary(FileWriter fileWriter,
			int numOfSeaVessel, int numOfSeaPassengers, int seaMaxSpeed,
			int seaMinSpeed) throws IOException {
		fileWriter.write("Sea vessel:\r\n\r\n");
		fileWriter.write("Total sea vessel:" + numOfSeaVessel + "\r\n");
		fileWriter.write(
				"Total passengers possible:" + numOfSeaPassengers + "\r\n");
		fileWriter.write("Max speed:" + seaMaxSpeed + "\r\n");
		fileWriter.write("Min speed:" + seaMinSpeed);
	}
}
