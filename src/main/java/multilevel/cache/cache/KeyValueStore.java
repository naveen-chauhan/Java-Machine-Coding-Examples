package multilevel.cache.cache;

import multilevel.cache.eviction.IEvictionPolicy;
import multilevel.cache.models.KeyValueEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 05/08/22
 */
public class KeyValueStore implements IKeyValueStore {
	Map<String, String> keyValueMap = null;
	IEvictionPolicy evictionPolicy;
	int capacity;

	public KeyValueStore(int capacity, IEvictionPolicy iEvictionPolicy) {
		this.capacity = capacity;
		this.evictionPolicy = iEvictionPolicy;
	}

	public void initializeMap() {
		this.keyValueMap = new HashMap<>();
		this.evictionPolicy.init();
	}

	@Override
	public KeyValueEntity put(String key, String value) {
		if (keyValueMap == null) {
			initializeMap();
		}

		String evictedKey = null;
		KeyValueEntity keyValueEntity = null;
		if (keyValueMap.size() == capacity) {
			evictedKey = evictionPolicy.evict();
			keyValueEntity = new KeyValueEntity(evictedKey, keyValueMap.get(evictedKey));
			keyValueMap.remove(evictedKey);
			System.out.println("[Logger]: Evicted the key: " + evictedKey);
		}

		keyValueMap.put(key, value);
		System.out.println("[Logger] Successfully added the Key: " + key);
		evictionPolicy.add(key);
		return keyValueEntity;
	}

	//On GET we are not removing the data, just increase the frequency and return the value of Map
	@Override
	public String get(String key) {
		if (keyValueMap == null || !keyValueMap.containsKey(key)) {
			return null;
		}
		String value = keyValueMap.get(key);
		evictionPolicy.add(key);

		return value;
	}

	@Override
	public boolean containsKey(String key) {
		return keyValueMap != null && keyValueMap.containsKey(key);
	}

	@Override
	public void remove(String key) {
		if (keyValueMap == null) {
			return;
		}

		//delete from the map and from the evictionPolicy
		keyValueMap.remove(key);
		evictionPolicy.remove(key);
		System.out.println("[Logger] Successfully removed the Key: " + key);

	}
}
