package ridesharingapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author naveen.chauhan on 24/07/22
 */
@Getter
@AllArgsConstructor
public class TakenRideDetails {
    private final String ownerName;
	private final String traveller;
	private final String originLocation;
	private final String destinationLocation;
	private final Integer seat;
	private final String vehicleName;
	private final String vehicleNumber;
}
