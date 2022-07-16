package parkinglot.model;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class ParkingSlot {
	private boolean bookedSlot;
	private VehicleDetails vehicleDetails;
	private String parkingTicketId;

	private static final String UNBOOKED_SLOT_ID = "NONE";

	public ParkingSlot(VehicleDetails vehicleDetails, String parkingTicketId) {
		this.vehicleDetails = vehicleDetails;
		this.parkingTicketId = parkingTicketId;
	}

	public boolean isBookedSlot() {
		return bookedSlot;
	}

	public VehicleDetails getVehicleDetails() {
		return vehicleDetails;
	}

	public String getParkingTicketId() {
		return parkingTicketId;
	}
}
