package inmemorykeyvaluestore.interfaces;

import java.util.List;

/**
 * @author naveen.chauhan on 19/07/22
 */
public interface IKeyValueStore<T> {
	T get(String key);

	void put(String key, T value);

	T search(String valueKeyAttribute, String valueAttribute);

	void delete(String key);

	List<String> keys();
}
