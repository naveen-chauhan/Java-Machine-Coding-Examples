package parkinglot.processor;

import parkinglot.model.ParkingLot;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class ParkingLotManager {
	private DisplayManager displayManager;
	private ParkOrchestrator parkOrchestrator;
	private ParkingLot parkingLot;

	public ParkingLotManager(String[] commands) {
		this.parkingLot = new ParkingLot(commands[1], commands[2], commands[3]);
		this.displayManager = new DisplayManager();
		this.parkOrchestrator = new ParkOrchestrator();
	}

	public void display(String[] commands) {
		if (commands[1].equals("free_count")) {
			displayManager.displayFreeCount(parkingLot, commands);
		} else if (commands[1].equals("free_slots")) {
			displayManager.displayFreeSlots(parkingLot, commands);
		} else if (commands[1].equals("occupied_slots")) {
			displayManager.displayOccupiedSlots(parkingLot, commands);
		}
	}

	public void parkVehicle(String[] commands) {
		parkOrchestrator.parkVehicle(parkingLot, commands);
	}

	public void unparkVehicle(String[] commands) {
		parkOrchestrator.unparkVehicle(parkingLot, commands);
	}
}
