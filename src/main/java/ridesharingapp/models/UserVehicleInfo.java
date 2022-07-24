package ridesharingapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author naveen.chauhan on 24/07/22
 */
@Getter
@AllArgsConstructor
public class UserVehicleInfo {
     private String ownerName;
     private String vehicleName;
     private String vehicleNumber;
}
