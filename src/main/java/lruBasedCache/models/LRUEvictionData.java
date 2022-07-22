package lruBasedCache.models;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class LRUEvictionData {
	String key;
	int frequency;

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
