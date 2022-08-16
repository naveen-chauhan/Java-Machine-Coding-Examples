package lru.based.cache.models;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class LRUEvictionData {
	private final String key;
	private final int frequency;

	public LRUEvictionData(String key, int freq) {
		this.key = key;
		this.frequency = freq;
	}

	public String getKey() {
		return key;
	}

	public int getFrequency() {
		return frequency;
	}
}
