package truecaller.services;

import truecaller.models.TruecallerGlobalDirectory;
import truecaller.models.TruecallerUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 23/07/22
 */
public class GlobalSvc {
	//PhoneNumber will store All.
	private Map<String, TruecallerUser> truecallerUserMap;
	//Global Directory
	private TruecallerGlobalDirectory truecallerGlobalDirectory;


	public GlobalSvc() {
		truecallerUserMap = new HashMap<>();
		truecallerGlobalDirectory = new TruecallerGlobalDirectory();
	}

	public void registerNewUser(String name, String phoneNumber) {
		TruecallerUser truecallerUser = TruecallerUser.register(name, phoneNumber);
		truecallerUserMap.put(phoneNumber, truecallerUser);

		//Register in the global Working Directory
		truecallerGlobalDirectory.register(truecallerUser);

		System.out.println("Successfully Registered the User");
	}

	void call(String fromNumber, String toNumber) {
		//Identify calls
		//Get the Called User
		if (truecallerUserMap.containsKey(toNumber)) {
			TruecallerUser user = truecallerUserMap.get(toNumber);
			//Data Not Present
			if (truecallerGlobalDirectory.getSpamNumbers().contains(fromNumber)) {
				user.notifyCallingInfo("Spam");
			} else
				user.notifyCallingInfo(truecallerGlobalDirectory.getPhoneNumberToUserName().getOrDefault(fromNumber, "Unknown"));
		}
	}

	void blockGlobalSpammer(String spamNumber, String userNumber) {
		if (truecallerUserMap.containsKey(userNumber)) {
			TruecallerUser user = truecallerUserMap.get(userNumber);
			user.blockNumber(spamNumber);

			if (!truecallerGlobalDirectory.getSpamNumbers().contains(spamNumber)) {
				truecallerGlobalDirectory.reportSpam(spamNumber);
			}
		}
	}

	//UserRelated Operations

	//GlobalDirectoryRelated Operations


}
