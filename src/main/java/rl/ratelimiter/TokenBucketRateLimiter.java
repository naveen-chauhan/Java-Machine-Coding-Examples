package rl.ratelimiter;

/**
 * @author naveen.chauhan on 10/08/22
 * Here we have considered that this RateLimiter will be overall RateLimiter
 * Else we need to store different rateLimiter for each User, like - Map<UserName/UserId, TokenBucketRateLimiter>
 *     which will solve for per-user Rate Limiting
 */
public class TokenBucketRateLimiter implements IRateLimiter {

	private long currentAvailableTokens;
	private long lastRefillTimeStamp;
	private final long capacity;
	private final long fillingIntervalInSec;
	private final long tokenToBeAdded;
	private final long tokensToBeAddedPerSec;
	private static TokenBucketRateLimiter tokenBucketRateLimiter;

	private TokenBucketRateLimiter(long capacity, long fillingIntervalInSec, long noOfTokensToBeFilled) {
		this.capacity = capacity;
		this.fillingIntervalInSec = fillingIntervalInSec;
		this.lastRefillTimeStamp = System.currentTimeMillis();
		this.currentAvailableTokens = capacity;
		this.tokenToBeAdded = noOfTokensToBeFilled;
		this.tokensToBeAddedPerSec = tokenToBeAdded/fillingIntervalInSec;
	}


	/**
	 * Used Singleton Design Pattern
	 * created only one instance of TokenBucketRateLimiter, so that we can handle the concurrency
	 * */
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

	/**
	 * Made the consume() method synchronized to made it thread safe
	 *  we need
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

//		//Option 1 - Where we are adding only when the refill interval is crossed
//		if (durationInSec > fillingIntervalInSec) {
//			currentAvailableTokens += tokenToBeAdded;
//
//			//cannot spill more than that.
//			if (currentAvailableTokens > capacity) {
//				currentAvailableTokens = capacity;
//			}
//			//Update the lastRefill Time
//			lastRefillTimeStamp = currentTimeStamp;
//		}


		//Option 2
		// Either do this, get how many token needs to fill on each second and we will fill per second wise/
		//We have fillingInterval and numberOfTokens to fill, so per number of tokens to fill = numberOfToken/fillingInterval
		//Now just find the (lastRefillInterval - currentTimeMillis)/1000  to make it per sec, then  multiply it to perSecTokenCount
		if (currentTimeStamp > lastRefillTimeStamp) {
			long elaspedSec = (currentTimeStamp - lastRefillTimeStamp)/1000;
			long tokensToBeAddedNow = elaspedSec * tokensToBeAddedPerSec;

			currentAvailableTokens = Math.min(capacity, currentAvailableTokens + tokensToBeAddedNow);
			lastRefillTimeStamp = currentTimeStamp;
		}

	}
}
