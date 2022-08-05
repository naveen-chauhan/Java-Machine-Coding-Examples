package truecaller.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author naveen.chauhan on 23/07/22
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class TruecallerUser {
	private static final String DEFAULT_BUSINESS_NAME = "UNKNOWN";
	private static final String DEFAULT_SUBSCRIPTION_NAME = "FREEMIUM";

	private String name;
	private String phoneNumber;
	private List<ContactDetails> contactDetailsList;
	private List<String> blockedContactList;
	private String businessName;
	private PremiumInfo userSubscriptionInfo;


	private TruecallerUser(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.blockedContactList = new ArrayList<>();
		this.contactDetailsList = new ArrayList<>();
		this.businessName = DEFAULT_BUSINESS_NAME;
		this.userSubscriptionInfo = new PremiumInfo(DEFAULT_SUBSCRIPTION_NAME);
	}

	public static TruecallerUser register(String name, String phoneNumber) {
		//once it is register. Register in the GlobalDirectory as well.
		return new TruecallerUser(name, phoneNumber);
	}

	public void importContact(List<ContactDetails> userContactList) {
		//Think about safeguarding against the duplicates
		contactDetailsList.addAll(userContactList);
		System.out.println("Blocked the Contact Numbers");
	}

	public void blockNumber(String blockingContactNo) {
		blockedContactList.add(blockingContactNo);
		System.out.println("Blocked the " +  blockingContactNo + " Number");
	}

	public void unblockNumber(String contactNo) {
		blockedContactList.remove(contactNo);
		System.out.println("Removed the " +  contactNo + " Number from Blocking List");
	}

	public void upgradeToPremium() {
		userSubscriptionInfo = PremiumInfo.upgrade();
		System.out.println("User: " + name + " upgraded to the Premium Subscription. Welcome to the Premium Member Club !!");
	}

	public ContactDetails search(String identifier, String searchBy) {
		if (searchBy.equalsIgnoreCase("Phone")) {
			Optional<ContactDetails> contactDetails = contactDetailsList.stream().filter(contactDetails1 -> contactDetails1.getPhoneNumber().equals(identifier)).findAny();

			return contactDetails.orElse(null);
		} else if (searchBy.equalsIgnoreCase("Name")) {
			Optional<ContactDetails> contactDetails =
					contactDetailsList.stream()
					.filter(contactDetails1 -> contactDetails1.getContactName().equals(identifier))
					.findAny();

			return contactDetails.orElse(null);
		} else {
			System.out.println("Invalid search Operation");
			return null;
		}
	}

	public void addBusiness(String business) {
		businessName = business;
		System.out.println("Changed the Business to: " + business);
	}

	public void notifyCallingInfo(String callerName) {
		System.out.println("Call from: " +  callerName);
	}
}
