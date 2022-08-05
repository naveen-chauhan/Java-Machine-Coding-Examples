package splitwise.model;

import splitwise.dao.IAmountCalculator;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author naveen.chauhan on 04/07/22
 */

/**
 * Multiple ways to maintain this implementations
*   One each splitType as class implementing an interface
*   Second - Enum implementing an interface each enum element implementing the interface methods */
public enum SplitType implements IAmountCalculator {

	PERCENT {
		@Override
		public void calculateFinalSplit(String payerUserId,
		                                Integer totalAmount,
		                                List<String> userIdsOfSplitPerson,
		                                Map<String, User> userMap,
		                                List<Double> splitDetailList) {



			for (int i = 0; i < splitDetailList.size(); i++) {
				if (userIdsOfSplitPerson.get(i).equals(payerUserId)) {
					continue;
				}

				Double percent = splitDetailList.get(i);
				Double percentAmount = (percent/100) * totalAmount;

				addFinalAmountOnPersons(payerUserId, userMap, percentAmount, userIdsOfSplitPerson.get(i));
			}

		}
	},

	EXACT {
		@Override
		public void calculateFinalSplit(String payerUserId,
		                                Integer totalAmount,
		                                List<String> userIdsOfSplitPerson,
		                                Map<String, User> userMap,
		                                List<Double> splitDetailList) {
			for (int i = 0; i < splitDetailList.size(); i++) {
				if (userIdsOfSplitPerson.get(i).equals(payerUserId)) {
					continue;
				}

				addFinalAmountOnPersons(payerUserId, userMap, splitDetailList.get(i), userIdsOfSplitPerson.get(i));
			}
		}
	},

	EQUAL {
		@Override
		public void calculateFinalSplit(String payerUserId,
		                                Integer totalAmount,
		                                List<String> userIdsOfSplitPerson,
		                                Map<String, User> userMap,
		                                List<Double> splitDetailList) {

			Double splitedAmount = (double) (totalAmount / userIdsOfSplitPerson.size());

			for (String userId: userIdsOfSplitPerson) {

				if (userId.equals(payerUserId)) {
					continue;
				}

				addFinalAmountOnPersons(payerUserId, userMap, splitedAmount, userId);
			}

		}
	};

	/*
	 * Case 1: has not owned or already owner, in this case just add the amount
	 * Case 2: reverse owning i,e who has paid had previously owned some amount, if currentSplit > ownedAmount
	 *           user will ultimately own the part else the vice-versa
	 * Case 3: payer is present in the splitPersonList, in this case we don't need to do anything
	 * */

	private static void addFinalAmountOnPersons(String payerUserId, Map<String, User> userMap, Double splitedAmount, String userId) {
		//In case user already owned
		if (userMap.get(userId).getKhataBookMap().containsKey(getUserName(userMap, payerUserId))) {
			Double currentOwnedAmount = userMap.get(userId).getKhataBookMap().get(getUserName(userMap, payerUserId));
			userMap.get(userId).getKhataBookMap().put(getUserName(userMap, payerUserId), currentOwnedAmount + splitedAmount);
		}
		//In case payer owned an amount
		else if (userMap.get(payerUserId).getKhataBookMap().containsKey(getUserName(userMap, userId))) {
			Double currentPayerOwnedAmount = userMap.get(payerUserId).getKhataBookMap().get(getUserName(userMap, userId));
			if (Objects.equals(currentPayerOwnedAmount, splitedAmount)) {
				userMap.get(payerUserId).getKhataBookMap().remove(getUserName(userMap, userId));
			} else if (currentPayerOwnedAmount > splitedAmount) {
				userMap.get(payerUserId).getKhataBookMap().put(getUserName(userMap, userId), currentPayerOwnedAmount - splitedAmount);
			} else { // currentPayerOwnedAmount < splited Amount
				userMap.get(payerUserId).getKhataBookMap().remove(getUserName(userMap, userId));
				userMap.get(userId).getKhataBookMap().put(getUserName(userMap, payerUserId), splitedAmount - currentPayerOwnedAmount);
			}

		}
		//If each other doesn't anything
		else {
			userMap.get(userId).getKhataBookMap().put(getUserName(userMap, payerUserId), splitedAmount);
		}
	}

	private static String getUserName(Map<String, User> userMap, String payerUserId) {
		return userMap.get(payerUserId).getUserName();
	}
}
