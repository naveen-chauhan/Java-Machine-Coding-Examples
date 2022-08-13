package rl.ratelimiter;

import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 10/08/22
 * <p>
 * Algo - 2 = for RateLimiting. There are a couple of interval to implement the rate limiting
 * 1. Token Bucket
 * 2. Fixed Window
 * 3. Sliding Window
 * and more.... but I am keeping this limiting to 1 and 2 options
 */
public class FixedWindowRateLimiter {
	//we can implement without per userId, if we want to limit the overall api limit

	/**
	 * Each window will be present for a couple of sec, then there will new window.
	 * And each window will have static Number of token
	 */
	private static final Duration WINDOW_SIZE_IN_SEC = Duration.ofSeconds(5);
	private static final Integer REQUEST_COUNT = 3;

	private final Map<Integer, User> userMap = new HashMap<>();

	public boolean rateLimit(int userId) {
		long currentWindowNumber = Instant.now().toEpochMilli() / WINDOW_SIZE_IN_SEC.toMillis();

		User user = userMap.computeIfAbsent(userId, k -> new User(currentWindowNumber));

		if (currentWindowNumber > user.getWindowNumber()) {
			user.reset(currentWindowNumber);
		}

		if (user.getRequestCount() < REQUEST_COUNT) {
			user.requestCount++;
			return true;
		}

		return false;
	}


	@Getter
	class User {
		private long windowNumber;
		private int requestCount;

		public User(long windowNumber) {
			this.windowNumber = windowNumber;
			this.requestCount = 0;
		}

		private void reset(long windowNumber) {
			this.windowNumber = windowNumber;
			this.requestCount = 0;
		}
	}


}
