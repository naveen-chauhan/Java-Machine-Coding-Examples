package rl.apis;

import rl.ratelimiter.FixedWindowRateLimiter;
import rl.ratelimiter.TokenBucketRateLimiter;

/**
 * @author naveen.chauhan on 10/08/22
 */
public class DummySvcImpl implements IAPIInterface {

	private final TokenBucketRateLimiter tokenBucketRateLimiter;
	private final FixedWindowRateLimiter fixedWindowRateLimiter;

	public DummySvcImpl() {
		super();
		this.tokenBucketRateLimiter = TokenBucketRateLimiter.getInstance(5, 2, 3);
		this.fixedWindowRateLimiter = new FixedWindowRateLimiter();
	}

	@Override
	public void doAction() {
		//call the rate limiter to decide the if we need to serve the request
		if (tokenBucketRateLimiter.consume()) {
			//if needed, then do something here
			System.out.println("Hello, Mr Naveen");
			//end of api call
		} else {
			//throw //429 response or something else. Based how you have implemented
			System.out.println("[TokenBucketRateLimiter]: Too Many Requests, Rejecting.. please try again");
		}

		//Now try FixedWindowToo

		if (fixedWindowRateLimiter.rateLimit(0)) {
			//if needed, then do something here
			System.out.println("Hello, Mr Naveen");
			//end of api call
		} else {
			System.out.println("[FixedWindowRateLimiter]: Too Many Requests, Rejecting.. please try again");
		}

	}
}
