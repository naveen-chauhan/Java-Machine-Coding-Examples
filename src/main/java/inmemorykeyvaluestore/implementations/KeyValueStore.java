package inmemorykeyvaluestore.implementations;

import inmemorykeyvaluestore.helper.JsonHelper;
import inmemorykeyvaluestore.interfaces.IKeyValueStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author naveen.chauhan on 19/07/22
 */
public class KeyValueStore implements IKeyValueStore<String> {

	HashMap<String, HashMap<String, String>> keyValueStore = new HashMap<>();

	@Override
	public String get(String key) {
		String result = "";
		HashMap<String, String> value = keyValueStore.get(key);
		if (value == null) {
			return result;
		}
		return JsonHelper.toString(value);
	}

	@Override
	public synchronized void put(String key, String value) {
		HashMap<String, String> hashMap = JsonHelper.readTree(value);
		keyValueStore.put(key, hashMap);
	}

	@Override
	public String search(String keyAttribute, String valueAttribute) {
		String result = "";
		for (Map.Entry<String, HashMap<String, String>> entry: keyValueStore.entrySet()) {
			if (entry.getValue().containsKey(keyAttribute) && entry.getValue().get(keyAttribute).equals(valueAttribute)) {
				result = result + entry.getKey() + ",";
			}
		}

		return result.length() == 0 ? result : result.substring(0, result.length()-1);

	}

	@Override
	public void delete(String key) {
		keyValueStore.remove(key);
	}

	@Override
	public List<String> keys() {
		return (List<String>) keyValueStore.keySet();
	}
}
