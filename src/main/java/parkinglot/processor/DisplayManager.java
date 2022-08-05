package parkinglot.processor;

import parkinglot.model.ParkingFloor;
import parkinglot.model.ParkingLot;

import java.util.ArrayList;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class DisplayManager {

	public void displayFreeCount(ParkingLot parkingLot, String[] commands) {
		int result = 0;
		int count = 1;
		switch (commands[2]) {
			case "CAR":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					result = parkingFloor.getParkingSlotList().length - 3 - parkingFloor.getFilledCarSlot();
					System.out.println("No. of free slots for CAR on Floor " + count + ":" + result);
					count++;
				}
				break;
			case "BIKE":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					result = 2 - parkingFloor.getFilledBikeSlot();
					System.out.println("No. of free slots for BIKE on Floor " + count + ":" + result);
					count++;
				}
				break;
			case "TRUCK":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					result = 1 - parkingFloor.getFilledTruckSlot();
					System.out.println("No. of free slots for TRUCK on Floor " + count + ":" + result);
					count++;
				}
				break;
		}

	}

	public void displayFreeSlots(ParkingLot parkingLot, String[] commands) {
		int count = 1;
		switch (commands[2]) {
			case "CAR":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					ArrayList<Integer> result = new ArrayList<>();
					for (int i = 3; i < parkingFloor.getParkingSlotList().length; i++) {
						if (parkingFloor.getParkingSlotList()[i] == null) {
							result.add(i + 1);
						}
					}
					System.out.println("Free slots for CAR on Floor " + count + ":" + result.toString()
							.replace("[", "")
							.replace("]", "")
							.trim());
					count++;
				}
				break;
			case "BIKE":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					ArrayList<Integer> result = new ArrayList<>();
					for (int i = 1; i < 3; i++) {
						if (parkingFloor.getParkingSlotList()[i] == null) {
							result.add(i + 1);
						}
					}
					System.out.println("Free slots for BIKE on Floor " + count + ":" + result.toString()
							.replace("[", "")
							.replace("]", "")
							.trim());
					count++;
				}
				break;
			case "TRUCK":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					if (parkingFloor.getParkingSlotList()[0] == null) {
						System.out.println("Free slots for BIKE on Floor " + count + ":" + "1");
					}
				}
				break;
		}
	}

	public void displayOccupiedSlots(ParkingLot parkingLot, String[] commands) {
		int count = 1;
		switch (commands[2]) {
			case "CAR":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					ArrayList<Integer> result = new ArrayList<>();
					for (int i = 3; i < parkingFloor.getParkingSlotList().length; i++) {
						if (parkingFloor.getParkingSlotList()[i] != null) {
							result.add(i + 1);
						}
					}
					System.out.println("Occupied slots for CAR on Floor " + count + ":" + result.toString()
							.replace("[", "")
							.replace("]", "")
							.trim());
					count++;
				}
				break;
			case "BIKE":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					ArrayList<Integer> result = new ArrayList<>();
					for (int i = 1; i < 3; i++) {
						if (parkingFloor.getParkingSlotList()[i] != null) {
							result.add(i + 1);
						}
					}
					System.out.println("Occupied slots for BIKE on Floor " + count + ":" + result.toString()
							.replace("[", "")
							.replace("]", "")
							.trim());
					count++;
				}
				break;
			case "TRUCK":
				for (ParkingFloor parkingFloor : parkingLot.getParkingFloorList()) {
					ArrayList<Integer> result = new ArrayList<>();
					if (parkingFloor.getParkingSlotList()[0] != null) {
						result.add(1);
					}
					System.out.println("Occupied slots for TRUCK on Floor " + count + ":" + result.toString()
							.replace("[", "")
							.replace("]", "")
							.trim());
				}
				break;
		}
	}
}
