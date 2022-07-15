package splitwise.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 04/07/22
 */
public class User {
    private String userName;
    private String userId;
    private String emailId;
    private String mobileNumber;
    private Map<String, Double> khataBookMap;

    public User(String userId) {
        if (userId.isEmpty()) {
            System.out.println("Error: UserId cannot be empty");
            return;
        }
        this.userName ="User" + userId.charAt(userId.length()-1);
        this.userId = userId;
        this.emailId = "emailId";
        this.mobileNumber = "mobileNumber";
        this.khataBookMap = new HashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, Double> getKhataBookMap() {
        return khataBookMap;
    }

    public void printKhataBook() {
        khataBookMap
                .forEach((key, value) ->
                        System.out.println(userName + " owes " + key + ": " + value.intValue()));
    }

    public void printKhataBookForAUser(String id) {
        if (khataBookMap.containsKey(id)) {
            System.out.println(userName + " owes " + id + ": " + khataBookMap.get(id).intValue());
        }
    }
}
