package lruBasedCache;

/**
 * @author naveen.chauhan on 22/07/22
 */

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


class LRUCache {

	static class LRUEvictionData {
		String key;
		int frequency;

		public LRUEvictionData(String key, int freq) {
			this.key = key;
			this.frequency = freq;
		}
	}

	static class SortbyFreq implements Comparator<LRUEvictionData> {
		public int compare(LRUEvictionData a, LRUEvictionData b) {
			return a.frequency - b.frequency;
		}
	}

	static class LRUEvictionPolicy {
		PriorityQueue<LRUEvictionData> lru;
		AtomicInteger timeValue;


		public void init() {
			lru = new PriorityQueue<>(new SortbyFreq());
			timeValue = new AtomicInteger(1);
		}


		public void add(String key) {
			if (lru.isEmpty()) {
				lru.add(new LRUEvictionData(key, timeValue.incrementAndGet()));
				return;
			}

			Optional<LRUEvictionData> lruEvictionDataOptional = lru.stream().filter(data -> data.key.equals(key)).findAny();

			if (lruEvictionDataOptional.isPresent()) {
				lru.remove(lruEvictionDataOptional.get());
				lru.add(new LRUEvictionData(lruEvictionDataOptional.get().key, timeValue.incrementAndGet()));
			} else {
				lru.add(new LRUEvictionData(key, timeValue.incrementAndGet()));
			}
		}


		public String evict() {
			LRUEvictionData evictionData = lru.poll();
			return evictionData.key;
		}
	}

	static class KeyValueStore {
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
	
	static class Pair {
		String key;
		String value;

		public Pair(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}


	public static void main(String[] args) {
		int capacity = 5;
		KeyValueStore keyValueStore = new KeyValueStore(capacity);
	
		ArrayList<Pair> insertionData = new ArrayList<>();
		insertionData.add(new Pair("k1", "1"));
		insertionData.add(new Pair("k2", "2"));
		insertionData.add(new Pair("k3", "4"));
		insertionData.add(new Pair("k6", "4"));
		insertionData.add(new Pair("k1", "4"));
		insertionData.add(new Pair("k5", "4"));
		insertionData.add(new Pair("k7", "4"));
		insertionData.add(new Pair("k8", "4"));
		insertionData.add(new Pair("k4", "4"));
		insertionData.add(new Pair("k2", "4"));

		keyValueStore.insert(insertionData, "LRU");

	}
}

