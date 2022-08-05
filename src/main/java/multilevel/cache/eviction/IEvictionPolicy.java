package multilevel.cache.eviction;

/**
 * @author naveen.chauhan on 05/08/22
 */
public interface IEvictionPolicy {
	void init();

	String evict();

	void add(String key);

	void remove(String key);
}
