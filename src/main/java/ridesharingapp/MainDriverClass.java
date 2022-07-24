package ridesharingapp;

import java.util.Scanner;

/**
 * @author naveen.chauhan on 24/07/22
 */
public class MainDriverClass {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String input = "";
		SharingRideOrchestrator sharingRideOrchestrator = new SharingRideOrchestrator();

		while (!input.equalsIgnoreCase("Exit")) {
			input = scanner.nextLine();

			String[] commands = input.split("(");

			switch (commands[0]) {
				case "add_user":
					sharingRideOrchestrator.registerUser(commands[1]);
					break;
				case "add_vehicle":
					sharingRideOrchestrator.registerVehicle(commands[1]);
					break;
				case "offer_ride":
					sharingRideOrchestrator.offerRide(commands[1]);
					break;
				case "select_ride":
					sharingRideOrchestrator.selectRide(commands[1]);
					break;
				case "end_ride":
					sharingRideOrchestrator.endRide(commands[1]);
					break;
				case "print_ride_stats":
					sharingRideOrchestrator.printRideInfo();
					break;
			}
		}
	}
}
