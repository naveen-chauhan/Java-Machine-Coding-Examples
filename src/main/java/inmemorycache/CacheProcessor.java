package inmemorycache;

import java.util.HashMap;

/**
 * @author naveen.chauhan on 23/06/22
 */
public class CacheProcessor implements ICacheProcessor{

    //Todo: Need Completion

    private HashMap<String,String> cache = new HashMap<String, String>();
    private int maxcapacity = 10;
    private EvictionManager evictionManager = new EvictionManager();

    public boolean set(String key, String value) {
        if (cache.size() == maxcapacity) {
            evictionManager.evict(cache, EvictionManager.EvictionType.RANDOM);
        }
        cache.put(key, value);
        return true;
    }

    public String get(String key) {
        return cache.get(key);
    }
}
