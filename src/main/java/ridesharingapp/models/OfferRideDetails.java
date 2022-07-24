package ridesharingapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author naveen.chauhan on 24/07/22
 */

@Getter
@AllArgsConstructor
public class OfferRideDetails {
	private final String userName;
	private final String originLocation;
	private final String destinationLocation;
	private Integer seats;
	private final String vehicleName;
	private final String vehicleNumber;

	public void removeSeats(Integer seats) {
		this.seats -= seats;
	}
}
