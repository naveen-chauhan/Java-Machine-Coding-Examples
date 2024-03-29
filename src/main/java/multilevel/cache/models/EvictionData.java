package multilevel.cache.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author naveen.chauhan on 05/08/22
 */
@Getter
@AllArgsConstructor
public class EvictionData {
	private String key;
	private int counter;
}
