package parkinglot.processor;

import parkinglot.model.*;

import java.util.List;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class ParkOrchestrator {

	public void parkVehicle(ParkingLot parkingLot, String[] commands) {
		String vehicleType = commands[1];
		String vehicleNumber = commands[2];
		String color = commands[3];
		VehicleDetails vehicleDetails = new VehicleDetails(vehicleType, vehicleNumber, color);
		List<ParkingFloor> parkingFloorList = parkingLot.getParkingFloorList();

		String ticketId = "None";

		switch (vehicleType) {
			case "CAR":
				for (int j = 0; j < parkingFloorList.size(); j++) {
					if (parkingFloorList.get(j).getFilledCarSlot() != parkingFloorList.get(j).getParkingSlotList().length - 3) {
						ParkingSlot[] parkingSlots = parkingFloorList.get(j).getParkingSlotList();
						for (int i = 3; i < parkingSlots.length; i++) {
							if (parkingSlots[i] == null) {
								ticketId = ParkingTicket.generateTicket(parkingLot.getParkingLotId(), j + 1, i + 1);
								parkingSlots[i] = new ParkingSlot(vehicleDetails, ticketId);
								parkingFloorList.get(j).getParkedTicketToSlotIndexMap().put(ticketId, i);
								parkingFloorList.get(j).setFilledCarSlot(parkingFloorList.get(j).getFilledCarSlot() + 1);
								break;
							}
						}
						break;
					}
				}
				break;
			case "BIKE":
				for (int j = 0; j < parkingFloorList.size(); j++) {
					if (parkingFloorList.get(j).getFilledBikeSlot() < 2) {
						ParkingSlot[] parkingSlots = parkingFloorList.get(j).getParkingSlotList();
						for (int i = 1; i < 3; i++) {
							if (parkingSlots[i] == null) {
								ticketId = ParkingTicket.generateTicket(parkingLot.getParkingLotId(), j + 1, i + 1);
								parkingSlots[i] = new ParkingSlot(vehicleDetails, ticketId);
								parkingFloorList.get(j).getParkedTicketToSlotIndexMap().put(ticketId, i);
								parkingFloorList.get(j).setFilledBikeSlot(parkingFloorList.get(j).getFilledBikeSlot() + 1);
								break;
							}
						}
						break;
					}
				}
				break;
			case "TRUCK":
				for (int j = 0; j < parkingFloorList.size(); j++) {
					if (parkingFloorList.get(j).getFilledTruckSlot() < 1) {
						ParkingSlot[] parkingSlots = parkingFloorList.get(j).getParkingSlotList();
						if (parkingSlots[0] == null) {
							ticketId = ParkingTicket.generateTicket(parkingLot.getParkingLotId(), j + 1, 1);
							parkingSlots[0] = new ParkingSlot(vehicleDetails, ticketId);
							parkingFloorList.get(j).getParkedTicketToSlotIndexMap().put(ticketId, 0);
							parkingFloorList.get(j).setFilledTruckSlot(parkingFloorList.get(j).getFilledTruckSlot() + 1);
							break;
						}
					}
				}
				break;
		}
		if (ticketId.equals("None")) {
			System.out.println("Parking Lot is full");
		} else {
			System.out.println("Parked vehicle. Ticket ID: "  + ticketId);
		}
	}

	public void unparkVehicle(ParkingLot parkingLot, String[] commands) {
//		unpark_vehicle PR1234_2_5
		String ticketId = commands[1];
		String[] ticketDetails = ticketId.split("_");
		int floorNo = Integer.parseInt(ticketDetails[1]);
		if (floorNo < 1 || floorNo > parkingLot.getParkingFloorList().size()) {
			System.out.println("Invalid Ticket");
			return;
		}
		ParkingFloor parkingFloor = parkingLot.getParkingFloorList().get(floorNo - 1);
		if (!parkingFloor.getParkedTicketToSlotIndexMap().containsKey(ticketId)) {
			System.out.println("Invalid Ticket");
			return;
		}
		int slotNo = Integer.parseInt(ticketDetails[2]);
		ParkingSlot parkingSlot = parkingFloor.getParkingSlotList()[slotNo - 1];
		parkingFloor.getParkingSlotList()[slotNo - 1] = null;
		parkingFloor.getParkedTicketToSlotIndexMap().remove(ticketId);
		VehicleDetails vehicleDetails = parkingSlot.getVehicleDetails();
		switch (vehicleDetails.getVehicleType()) {
			case "CAR":
				parkingFloor.setFilledCarSlot(parkingFloor.getFilledCarSlot() - 1);
				break;
			case "BIKE":
				parkingFloor.setFilledBikeSlot(parkingFloor.getFilledBikeSlot() - 1);
				break;
			case "TRUCK":
				parkingFloor.setFilledTruckSlot(parkingFloor.getFilledTruckSlot() - 1);
				break;
		}

		System.out.println("Unparked vehicle with Registration Number: "
				+ vehicleDetails.getRegistrationNumber()
				+ " and Color: "
				+ vehicleDetails.getColor());

	}
}
