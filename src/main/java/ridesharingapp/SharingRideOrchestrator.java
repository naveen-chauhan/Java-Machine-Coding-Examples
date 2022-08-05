package ridesharingapp;

import ridesharingapp.service.CentralRideSharingSvc;

/**
 * @author naveen.chauhan on 24/07/22
 */
public class SharingRideOrchestrator {
	CentralRideSharingSvc centralRideSharingSvc;

	public SharingRideOrchestrator() {
		this.centralRideSharingSvc = new CentralRideSharingSvc();
	}

	public void registerUser(String command) {
		centralRideSharingSvc.registerUser(command);
	}

	public void registerVehicle(String command) {
		centralRideSharingSvc.registerVehicle(command);
	}

	public void offerRide(String command) {
		centralRideSharingSvc.offerRide(command);
	}

	public void selectRide(String command) {
		centralRideSharingSvc.selectRide(command);
	}

	public void endRide(String command) {
		centralRideSharingSvc.endRide(command);
	}

	public void printRideInfo() {
		centralRideSharingSvc.printRideDetails();
	}
}
