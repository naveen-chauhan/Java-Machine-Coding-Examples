package parkinglot.model;

/**
 * @author naveen.chauhan on 15/07/22
 */
public class VehicleDetails {
	private String vehicleType;
	private String registrationNumber;
	private String color;

	public VehicleDetails(String vehicleType, String registrationNumber, String color) {
		this.vehicleType = vehicleType;
		this.registrationNumber = registrationNumber;
		this.color = color;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
