package splitwise;

import java.util.*;

/**
 * @author naveen.chauhan on 04/07/22
 */
public class SplitWiseSvc implements ISplitWiseProcessor {

	private final Map<String ,User> userMap;

	public SplitWiseSvc() {
		this.userMap = new HashMap<>();
	}

	public Map<String, User> getUserMap() {
		return userMap;
	}

	@Override
	public void show() {
		if (userMap.isEmpty()) {
			System.out.println("No balances");
			return;
		}

		userMap.values().forEach(User::printKhataBook);
	}

	@Override
	public void show(String userId) {

		if (userMap.isEmpty()) {
			System.out.println("No balances");
			return;
		}

		userMap.values().stream()
				.filter(user -> user.getUserId().equals(userId))
				.forEach(User::printKhataBook);

		userMap.values().stream()
				.filter(user -> !user.getUserId().equals(userId))
				.forEach(user -> user.printKhataBookForAUser(userMap.get(userId).getUserName()));
	}

	@Override
	public void expense(String[] commands) {
		int counter = 0;
		String payerUserId = commands[1];
		Integer totalAmount = Integer.valueOf(commands[2]);
		Integer numberOfSplitPerson = Integer.valueOf(commands[3]);
		counter = 4;
		List<String> userIdsOfSplitPerson = new ArrayList<>();

		for (int i = 0; i < numberOfSplitPerson ; i++) {
			userIdsOfSplitPerson.add(commands[counter+i]);

			if (!userMap.containsKey(commands[counter+i])) {
				userMap.put(commands[counter+i], new User(commands[counter+i]));
			}
		}

		counter = counter + numberOfSplitPerson;
		SplitType splitType = SplitType.valueOf(commands[counter]);
		counter++;


		if (splitType.equals(SplitType.EQUAL)) {
			splitType.calculateFinalSplit(payerUserId, totalAmount, userIdsOfSplitPerson, userMap, new ArrayList<>());

		} else if (SplitType.EXACT.equals(splitType)) {
			List<Double> splitAmount = new ArrayList<>();
			for (int i = 0; i < numberOfSplitPerson ; i++) {
				splitAmount.add(Double.valueOf(commands[counter+i]));
			}

			//Check valid or Not
			if (!isSplitValid(totalAmount.doubleValue(), splitAmount)) {
				System.out.println("Splitting of total amount is Invalid. Bad Configuration, please try Again !!");
				return;
			}

			splitType.calculateFinalSplit(payerUserId, totalAmount, userIdsOfSplitPerson, userMap, splitAmount);


		} else  {
			List<Double> splitPercentage = new ArrayList<>();
			for (int i = 0; i < numberOfSplitPerson ; i++) {
				splitPercentage.add(Double.valueOf(commands[counter+i]));
			}

			//Check valid or Not
			if (!isSplitValid(100.00, splitPercentage)) {
				System.out.println("Splitting of total percentage amount is Invalid. Bad Configuration, please try Again !!");
				return;
			}

			splitType.calculateFinalSplit(payerUserId, totalAmount, userIdsOfSplitPerson, userMap, splitPercentage);


		}
	}

	public boolean isSplitValid(Double totalAmount, List<Double> splitAmounts) {
		Double totalSplitAmount =  0.0;
		for (Double splitAmount: splitAmounts) {
			totalSplitAmount = totalSplitAmount + splitAmount;
		}

		return Objects.equals(totalAmount, totalSplitAmount);
	}
}
