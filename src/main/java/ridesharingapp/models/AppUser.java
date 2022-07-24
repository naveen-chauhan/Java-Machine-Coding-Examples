package ridesharingapp.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naveen.chauhan on 24/07/22
 */

@Getter
public class AppUser {
    private final String username;
    private final String gender;
    private final String age;
    private final List<UserVehicleInfo> vehicleInfoList;
    private int rideTaken; //counted only once ride is completed
    private int rideOffered; //Counted once it offers a ride.


    public AppUser(String username, String gender, String age) {
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.vehicleInfoList = new ArrayList<>();
        this.rideTaken = 0;
        this.rideOffered = 0;
    }

    public void addVehicle(UserVehicleInfo vehicleInfo) {
        vehicleInfoList.add(vehicleInfo);
    }


    public void addOfferedRide() {
        rideOffered++;
    }

    public void incrementRideTaken() {
        rideTaken++;
    }
}
