package parkinglot;

import parkinglot.processor.ParkingLotManager;

import java.util.Scanner;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class ParkingLotDriverClass {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		input = scanner.nextLine();
		String commands[] = input.split(" ");
		ParkingLotManager parkingLotManager = null;
		if (commands[0].equals("create_parking_lot")) {
			parkingLotManager = new ParkingLotManager(commands);
			System.out.println("Created parking lot with " + commands[2] + " floors and " + commands[3] + " slots per floor");
		} else {
			System.out.println("Error: Bad Command");
			return;
		}

		while (!input.equalsIgnoreCase("Exit")) {
			input = scanner.nextLine();
			inputHandler(input, parkingLotManager);
		}

	}

	private static void inputHandler(String input, ParkingLotManager parkingLotManager) {
		String[] commands = input.split(" ");
		switch (commands[0]) {
			case "display":
				parkingLotManager.display(commands);
				break;
			case "park_vehicle":
				parkingLotManager.parkVehicle(commands);
				break;
			case "unpark_vehicle":
				parkingLotManager.unparkVehicle(commands);
				break;
		}
	}
}
