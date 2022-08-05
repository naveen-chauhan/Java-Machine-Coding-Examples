package lru.based.cache;

/**
 * @author naveen.chauhan on 22/07/22
 */

import lru.based.cache.cache.KeyValueStore;
import lru.based.cache.models.Pair;

import java.util.ArrayList;


class LRUCache {

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

