package multilevel.cache.cache;


import multilevel.cache.models.KeyValueEntity;

/**
 * @author naveen.chauhan on 05/08/22
 */
public interface IKeyValueStore {
	String get(String key);

	KeyValueEntity put(String key, String value);

	boolean containsKey(String key);

	void remove(String key);
}
