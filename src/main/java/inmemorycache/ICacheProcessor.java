package inmemorycache;

/**
 * @author naveen.chauhan on 23/06/22
 */
public interface ICacheProcessor {
	boolean set(String key, String value);

	String get(String key);
}
