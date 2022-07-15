package parkinglot.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class ParkingFloor {
	private ParkingSlot[] parkingSlotList;
	private int filledCarSlot;
	private int filledBikeSlot;
	private int filledTruckSlot;
	private Map<String, Integer> parkedTicketToSlotIndexMap;

	public ParkingFloor(String parkingSlotSize) {
		this.parkingSlotList = new ParkingSlot[Integer.parseInt(parkingSlotSize)];
		this.parkedTicketToSlotIndexMap = new HashMap<>();
	}

	public ParkingSlot[] getParkingSlotList() {
		return parkingSlotList;
	}

	public void setParkingSlotList(ParkingSlot[] parkingSlotList) {
		this.parkingSlotList = parkingSlotList;
	}

	public int getFilledCarSlot() {
		return filledCarSlot;
	}

	public int getFilledBikeSlot() {
		return filledBikeSlot;
	}

	public int getFilledTruckSlot() {
		return filledTruckSlot;
	}

	public void setFilledCarSlot(int filledCarSlot) {
		this.filledCarSlot = filledCarSlot;
	}

	public void setFilledBikeSlot(int filledBikeSlot) {
		this.filledBikeSlot = filledBikeSlot;
	}

	public void setFilledTruckSlot(int filledTruckSlot) {
		this.filledTruckSlot = filledTruckSlot;
	}

	public Map<String, Integer> getParkedTicketToSlotIndexMap() {
		return parkedTicketToSlotIndexMap;
	}

	public void setParkedTicketToSlotIndexMap(Map<String, Integer> parkedTicketToSlotIndexMap) {
		this.parkedTicketToSlotIndexMap = parkedTicketToSlotIndexMap;
	}
}
