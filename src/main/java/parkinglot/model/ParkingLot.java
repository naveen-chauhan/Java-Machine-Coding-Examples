package parkinglot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class ParkingLot {
	private String parkingLotId;
	private List<ParkingFloor> parkingFloorList;
	private List<String> parkedVehicleTicketIdList;

	public ParkingLot(String parkingLotId, String parkingFloorSize, String parkingSlotSize) {
		this.parkingLotId = parkingLotId;
		this.parkingFloorList = new ArrayList<>();
		for (int i = 0; i < Integer.parseInt(parkingFloorSize); i++) {
			parkingFloorList.add(new ParkingFloor(parkingSlotSize));
		}
		this.parkedVehicleTicketIdList = new ArrayList<>();
	}

	public String getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(String parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public List<ParkingFloor> getParkingFloorList() {
		return parkingFloorList;
	}

	public void setParkingFloorList(List<ParkingFloor> parkingFloorList) {
		this.parkingFloorList = parkingFloorList;
	}
}
