package lru.based.cache.evictionPolicy;

import lru.based.cache.comparator.SortbyFreq;
import lru.based.cache.models.LRUEvictionData;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class LRUEvictionPolicy {
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

		Optional<LRUEvictionData> lruEvictionDataOptional = lru.stream().filter(data -> data.getKey().equals(key)).findAny();

		if (lruEvictionDataOptional.isPresent()) {
			lru.remove(lruEvictionDataOptional.get());
			lru.add(new LRUEvictionData(lruEvictionDataOptional.get().getKey(), timeValue.incrementAndGet()));
		} else {
			lru.add(new LRUEvictionData(key, timeValue.incrementAndGet()));
		}
	}


	public String evict() {
		LRUEvictionData evictionData = lru.poll();
		return evictionData.getKey();
	}
}