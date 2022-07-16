package inmemorycache;

import java.util.HashMap;

/**
 * @author naveen.chauhan on 23/06/22
 */
public class EvictionManager {
    private EvictionType evictionType;

    public boolean evict(HashMap<String, String> cache, EvictionType evictionType) {
        if (evictionType.equals(EvictionType.LRU)) {

        } else if (evictionType.equals(EvictionType.RANDOM)) {

        } else {

        }
        return true;
    }

    public enum EvictionType {
        LRU,
        RANDOM,
        DEFAULT
    }

}
