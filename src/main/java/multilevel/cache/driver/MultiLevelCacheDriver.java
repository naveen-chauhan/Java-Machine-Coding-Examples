package multilevel.cache.driver;

import multilevel.cache.cache.MultiLevelCache;

/**
 * @author naveen.chauhan on 05/08/22
 */
public class MultiLevelCacheDriver {
	public static void main(String[] args) {
		//MultiLevelKeyValueStore
		int maxCacheLevel = 5;

		//Capacity for all level
		int[] maxCapacityAtEachLevel = {2, 1, 3, 3, 2};

		//Initialize the multiLevelCache
		MultiLevelCache multiLevelCache = MultiLevelCache.init(5, maxCapacityAtEachLevel);

		multiLevelCache.write("key1", "value1");
		multiLevelCache.write("key2", "value2");
		//Frequency would be increased
		multiLevelCache.read("key2");

		//on eviction, key1 would be evicted first

		multiLevelCache.write("key3", "value3");
		multiLevelCache.write("key4", "value4");
		multiLevelCache.write("key5", "value5");
		multiLevelCache.write("key6", "value6");
		multiLevelCache.write("key7", "value7");
		multiLevelCache.write("key8", "value8");
		multiLevelCache.write("key9", "value9");
		multiLevelCache.write("key10", "value10");


		//should give
		System.out.println(multiLevelCache.read("key2"));

		multiLevelCache.delete("key3");
		//should be null
		System.out.println(multiLevelCache.read("key3"));

	}

}
