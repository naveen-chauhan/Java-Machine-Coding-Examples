package multilevel.cache.eviction;

import multilevel.cache.models.EvictionData;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * @author naveen.chauhan on 05/08/22
 */
public class LFUEvictionPolicy implements IEvictionPolicy {
	PriorityQueue<EvictionData> lfu;


	public void init() {
		lfu = new PriorityQueue<>(new SortbyFreq());
	}

	//Populating the evitionData
	public void add(String key) {
		if (lfu.isEmpty()) {
			lfu.add(new EvictionData(key, 1));
			return;
		}

		Optional<EvictionData> evictionDataOptional = lfu.stream().filter(data -> data.getKey().equals(key)).findAny();

		if (evictionDataOptional.isPresent()) {
			lfu.remove(evictionDataOptional.get());
			lfu.add(new EvictionData(evictionDataOptional.get().getKey(), evictionDataOptional.get().getCounter() + 1));
		} else {
			lfu.add(new EvictionData(key, 1));
		}
	}

	//Remove from the PQ
	@Override
	public void remove(String key) {
		Optional<EvictionData> evictionDataOptional = lfu.stream().filter(data -> data.getKey().equals(key)).findAny();
		evictionDataOptional.ifPresent(evictionData -> lfu.remove(evictionData));
	}


	//Evict the Lowest Frequency one
	public String evict() {
		EvictionData evictionData = lfu.poll();
		return evictionData.getKey();
	}

	class SortbyFreq implements Comparator<EvictionData> {
		@Override
		public int compare(EvictionData o1, EvictionData o2) {
			return o1.getCounter() - o2.getCounter();
		}
	}
}
