package rl.ratelimiter;

/**
 * @author naveen.chauhan on 10/08/22
 */
public class TokenBucketRateLimiter {

	private long currentAvailableTokens;
	private long lastRefillTimeStamp;
	private final long capacity;
	private final long fillingIntervalInSec;
	private final long tokenAmount;
	private static TokenBucketRateLimiter tokenBucketRateLimiter;

	private TokenBucketRateLimiter(long capacity, long fillingIntervalInSec, long noOfTokensToBeFilled) {
		this.capacity = capacity;
		this.fillingIntervalInSec = fillingIntervalInSec;
		lastRefillTimeStamp = System.currentTimeMillis();
		currentAvailableTokens = capacity;
		this.tokenAmount = noOfTokensToBeFilled;
	}

	public static TokenBucketRateLimiter getInstance(long capacity,
	                                                 long fillingIntervalInSec,
	                                                 long tokenToBeFilledInInterval) {
		if (tokenBucketRateLimiter == null) {
			synchronized (TokenBucketRateLimiter.class) {
				tokenBucketRateLimiter = new TokenBucketRateLimiter(capacity, fillingIntervalInSec, tokenToBeFilledInInterval);
			}
		}
		//else return
		return tokenBucketRateLimiter;
	}

	/*
	 * we need
	 *   availableTokens
	 *   lastTimeRefill
	 *   capacity of Bucket
	 *   Filling Rate - no of token filling and timeInterval*/

	public synchronized boolean consume() {
		refill();

		if (currentAvailableTokens > 0) {
			--currentAvailableTokens;
			return true;
		}

		return false;
	}

	private void refill() {
		//here we have to refill the tokens just in case it is time to refill the token

		/*
		 * Two ways -
		 *   1. We will update or fill the token when we reach the timeIntervalInSec
		 *   2. Or We can check how many token we have to introduce per sec and we just add only those tokens
		 * */

		long currentTimeStamp = System.currentTimeMillis();
		long durationInSec = (currentTimeStamp - lastRefillTimeStamp) / 1000;
		if (durationInSec > fillingIntervalInSec) {
			currentAvailableTokens += tokenAmount;

			//cannot spill more than that.
			if (currentAvailableTokens > capacity) {
				currentAvailableTokens = capacity;
			}
		}
		//Update the lastRefill Time

		lastRefillTimeStamp = currentTimeStamp;

	}
}
