package parkinglot.model;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class ParkingTicket {
	public static String generateTicket(String parkingLotId, Integer floorNo, Integer slotNo) {
		return new StringBuilder()
				.append(parkingLotId)
				.append("_")
				.append(String.valueOf(floorNo))
				.append("_")
				.append(String.valueOf(slotNo))
				.toString();
	}
}
