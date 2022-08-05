package lru.based.cache.cache;

import lru.based.cache.evictionPolicy.LRUEvictionPolicy;
import lru.based.cache.models.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class KeyValueStore {
	Map<String, String> keyValueMap;
	LRUEvictionPolicy evictionPolicy;
	int capacity;

	public KeyValueStore(int capacity) {
		this.keyValueMap = new HashMap<>();
		this.capacity = capacity;
	}

	public void insert(ArrayList<Pair> data, String eviction) {
		if (eviction.equals("LRU")) {
			evictionPolicy = new LRUEvictionPolicy();
			evictionPolicy.init();
		}
		for (Pair keyValue : data) {
			if (keyValueMap.size() >= capacity) {
				String key = evictionPolicy.evict();
				System.out.println("Evicting the Key: " + key + " From the Keys" + keyValueMap.keySet());

				keyValueMap.remove(key);
			}
			keyValueMap.put(keyValue.getKey(), keyValue.getValue());
			evictionPolicy.add(keyValue.getKey());
		}
	}
}
