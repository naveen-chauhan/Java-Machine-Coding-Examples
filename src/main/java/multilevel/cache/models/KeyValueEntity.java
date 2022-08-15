package multilevel.cache.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author naveen.chauhan on 05/08/22
 */
@AllArgsConstructor
@Getter
public class KeyValueEntity {
   private final String key;
   private final String value;
}
