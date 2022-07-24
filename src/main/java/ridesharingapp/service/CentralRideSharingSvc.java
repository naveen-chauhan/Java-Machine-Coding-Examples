package ridesharingapp.service;

import ridesharingapp.models.AppUser;
import ridesharingapp.models.OfferRideDetails;
import ridesharingapp.models.TakenRideDetails;
import ridesharingapp.models.UserVehicleInfo;

import java.util.*;

/**
 * @author naveen.chauhan on 24/07/22
 */
public class CentralRideSharingSvc {
	private final Map<String, AppUser> userRepository;
	private final List<OfferRideDetails> availableRides;
	private final List<TakenRideDetails> currentOccuringRides;
	private final Map<String, TakenRideDetails> takenRideDetailsMap;
	private final List<TakenRideDetails> completedRideDetails;

	public CentralRideSharingSvc() {
		userRepository = new HashMap<>();
		availableRides = new ArrayList<>();
		currentOccuringRides = new ArrayList<>();
		completedRideDetails = new ArrayList<>();
		takenRideDetailsMap = new HashMap<>();
	}

	private String[] getStrings(String command) {
		command = command.replaceAll("\\s", "");
		command = command.substring(0, command.length()-3);
		String[] inputs = command.split("“")[1].split(",");

		return inputs;
	}

	public void registerUser(String command) {
		String[] inputs = getStrings(command);

		if (inputs.length != 3) {
			System.out.println("Check the split Algorithm");
		}

		AppUser appUser = new AppUser(inputs[0], inputs[1], inputs[2]);
		userRepository.put(appUser.getUsername(), appUser);
		System.out.println("User Registered");
	}

	public void registerVehicle(String command) {
		String[] inputs = getStrings(command);

		if (inputs.length != 3) {
			System.out.println("Check the split Algorithm for Vehicle Registration");
		}

		if (!userRepository.containsKey(inputs[0])) {
			System.out.println("User Not registered. Kindly signUp First");
			return;
		}

		UserVehicleInfo vehicleInfo = new UserVehicleInfo(inputs[0], inputs[1], inputs[2]);
		userRepository.get(inputs[0]).addVehicle(vehicleInfo);
		System.out.println("Vehicle Registered for the User");
	}

	public void offerRide(String command) {
		String[] inputs = getStrings(command);

		if (!userRepository.containsKey(inputs[0])) {
			System.out.println("User Not registered. Kindly signUp First");
			return;
		}

		boolean isRideAlreadyOffered = availableRides.stream().anyMatch(rides -> rides.getVehicleNumber().equals(inputs[4]));
		isRideAlreadyOffered = isRideAlreadyOffered || currentOccuringRides.stream().anyMatch(takenRideDetails -> takenRideDetails.getVehicleNumber().equals(inputs[4]));

		if (isRideAlreadyOffered) {
			System.out.println("Ride Already Offered !!");
			return;
		}

		String originLocation = inputs[1].split("=")[1];
		Integer seats = Integer.parseInt(inputs[2].split("=")[1]);
		String vehicleName = inputs[3].split("=")[1];
		String destinationLocation = inputs[5].split("=")[1];

		OfferRideDetails offerRideDetails = new OfferRideDetails(inputs[0],
				originLocation,
				destinationLocation,
				seats,
				vehicleName,
				inputs[4]);

		availableRides.add(offerRideDetails);
//		userRepository.get(inputs[0]).addOfferedRide();

		System.out.println("Ride Addition Successfull !");
	}

	public void selectRide(String command) {
		String[] inputs = getStrings(command);

		if (!userRepository.containsKey(inputs[0])) {
			System.out.println("User Not registered. Kindly signUp First");
			return;
		}

		String traveller = inputs[0];
		String originLocation = inputs[1].split("=")[1];
		String destinationLocation = inputs[2].split("=")[1];
		Integer requiredSeats = Integer.parseInt(inputs[3].split("=")[1]);
		String[] choice = inputs[4].split("=");

		OfferRideDetails currentAvailableRideForUser = null;
		boolean isVehicleChoiceSelected = (choice[0].equals("MostVacant")) ? false : true;

		for (OfferRideDetails rideDetails: availableRides) {
			if (rideDetails.getSeats() >= requiredSeats
					&& rideDetails.getDestinationLocation().equals(destinationLocation)
						&& rideDetails.getOriginLocation().equals(originLocation)) {
				if (isVehicleChoiceSelected) {
					if (choice[1].equals(rideDetails.getVehicleName())) {
						currentAvailableRideForUser = rideDetails;
						break;
					}
				} else {
					if (Objects.isNull(currentAvailableRideForUser) || rideDetails.getSeats() > currentAvailableRideForUser.getSeats()) {
						currentAvailableRideForUser = rideDetails;
					}
				}
			}
		}

		if (Objects.nonNull(currentAvailableRideForUser)) {

			currentAvailableRideForUser.removeSeats(requiredSeats);
			if (currentAvailableRideForUser.getSeats() < 1) {
				availableRides.remove(currentAvailableRideForUser);
			}

			TakenRideDetails takenRideDetails = new TakenRideDetails(
					currentAvailableRideForUser.getUserName(),
					traveller,
					originLocation,
					destinationLocation,
					requiredSeats,
					currentAvailableRideForUser.getVehicleName(),
					currentAvailableRideForUser.getVehicleNumber());

			currentOccuringRides.add(takenRideDetails);
			String uniqueId = UUID.randomUUID().toString();
			takenRideDetailsMap.put(uniqueId, takenRideDetails);
			userRepository.get(takenRideDetails.getOwnerName()).addOfferedRide();
			System.out.println("Ride Given with the id: " + uniqueId);
		} else {
			System.out.println("No Available Found for the User");
		}
	}

	public void endRide(String command) {
		command = command.replaceAll("\\s", "");
		command = command.substring(0, command.length()-3);
		String[] result = command.split("”");
		String input = result[1];

		if (!takenRideDetailsMap.containsKey(input)) {
			System.out.println("Invalid Id, No Ride for this Id !!");
		}

		TakenRideDetails takenRideDetails = takenRideDetailsMap.get(input);

		//Add RideTaken and RideOffered Data in the repository
		userRepository.get(takenRideDetails.getTraveller()).incrementRideTaken();

		currentOccuringRides.remove(takenRideDetails);
		completedRideDetails.add(takenRideDetails);
		System.out.println("Successfully Ended Ride");
	}

	public void printRideDetails() {
		userRepository.values().forEach(appUser -> {
			System.out.println(appUser.getUsername()+ ": " + appUser.getRideTaken() + " Taken, " + appUser.getRideOffered() + " Offered  ");
		});
	}
}
