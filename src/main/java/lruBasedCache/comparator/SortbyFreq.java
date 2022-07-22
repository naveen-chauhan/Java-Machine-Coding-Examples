package lruBasedCache.comparator;

import lruBasedCache.models.LRUEvictionData;

import java.util.Comparator;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class SortbyFreq implements Comparator<LRUEvictionData> {
	public int compare(LRUEvictionData a, LRUEvictionData b) {
		return a.getFrequency() - b.getFrequency();
	}
}
