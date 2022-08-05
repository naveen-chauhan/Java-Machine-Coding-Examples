package multilevel.cache.cache;

import multilevel.cache.eviction.LFUEvictionPolicy;
import multilevel.cache.models.KeyValueEntity;

import java.util.ArrayList;

/**
 * @author naveen.chauhan on 05/08/22
 */
public class MultiLevelCache {
	private int maxCacheLevel;
	ArrayList<KeyValueStore> cache;

	private MultiLevelCache(int i, int[] maxCapacityAtEachLevel) {
		this.maxCacheLevel = i;
		this.cache = new ArrayList<>();
		for (int j = 0; j < maxCacheLevel; j++) {
			cache.add(new KeyValueStore(maxCapacityAtEachLevel[j], new LFUEvictionPolicy()));
		}
	}

	public static MultiLevelCache init(int i, int[] maxCapacityAtEachLevel) {
		return new MultiLevelCache(i, maxCapacityAtEachLevel);
	}

	public String read(String key) {
		String value = null;
		for (KeyValueStore keyValueStore : cache) {
			if (keyValueStore.containsKey(key)) {
				value = keyValueStore.get(key);
			}
		}
		if (value != null) {
			write(key, value);
		}

		return value;
	}

	public void write(String key, String value) {
		KeyValueEntity keyValueEntity = null;

		for (KeyValueStore keyValueStore : cache) {
			keyValueEntity = keyValueStore.put(key, value);
			if (keyValueEntity != null) {
				key = keyValueEntity.getKey();
				value = keyValueEntity.getValue();
			} else {
				break;
			}
		}

		if (keyValueEntity != null) {
			System.out.println("Evicted Key cannot be added as all the cache at each level is full: [" + keyValueEntity.getKey() + ": " + keyValueEntity.getValue() + "]");
		}
	}

	public void delete(String key) {
		for (KeyValueStore keyValueStore : cache) {
			keyValueStore.remove(key);
		}
	}
}
