package distributedqueue.alernative.models;

import lombok.AllArgsConstructor;

/**
 * @author naveen.chauhan on 22/07/22
 */

@AllArgsConstructor
public class Message {
	private String msg;

	public String getMsg() {
		return msg;
	}
}
