package truecaller.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author naveen.chauhan on 23/07/22
 */

@Getter
public class TruecallerGlobalDirectory {
	private List<String> spamNumbers;
	private Map<String, String> phoneNumberToUserName;
	private List<ContactDetails> contactDetailsList;

	public TruecallerGlobalDirectory() {
		spamNumbers = new ArrayList<>();
		contactDetailsList = new ArrayList<>();
		phoneNumberToUserName = new HashMap<>();
	}

	public void addContact(String userName, String phoneNumber) {
		phoneNumberToUserName.put(phoneNumber, userName);
		contactDetailsList.add(new ContactDetails(userName, phoneNumber));
	}

	public void register(TruecallerUser user) {
		phoneNumberToUserName.put(user.getPhoneNumber(), user.getName());
		contactDetailsList.add(new ContactDetails(user.getName(), user.getPhoneNumber()));
	}

	public void reportSpam(String phoneNumber) {
		spamNumbers.add(phoneNumber);
	}

	public String searchByPhoneNumber(String phoneNumber) {
		return phoneNumberToUserName
				.getOrDefault(phoneNumber, "NO_ENTRY");
	}

	public String searchByUserName(String userName) {
		return contactDetailsList
				.stream()
				.filter(contactDetails -> contactDetails.getContactName().equals(userName))
				.map(ContactDetails::getPhoneNumber)
				.findAny()
				.orElse("NO_ENTRY");
	}

}
