package trello.processor;

import java.util.UUID;

/**
 * @author naveen.chauhan on 12/07/22
 */
public class IdGenerator {
	public static String generateId() {
		return UUID.randomUUID().toString();
	}
}
