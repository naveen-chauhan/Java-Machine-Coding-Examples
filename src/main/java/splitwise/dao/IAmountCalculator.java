package splitwise.dao;

import splitwise.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author naveen.chauhan on 05/07/22
 */
public interface IAmountCalculator {
	void calculateFinalSplit(String payerUserId,
	                         Integer totalAmount,
	                         List<String> userIdsOfSplitPerson,
	                         Map<String, User> userMap,
	                         List<Double> splitDetailList);
}
