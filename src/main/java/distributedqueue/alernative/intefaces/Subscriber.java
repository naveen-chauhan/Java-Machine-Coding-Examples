package distributedqueue.alernative.intefaces;

import distributedqueue.alernative.models.Message;
import lombok.AllArgsConstructor;

/**
 * @author naveen.chauhan on 22/07/22
 */
@AllArgsConstructor
public class Subscriber implements ISubscriber{
	private String id;
	private int sleepTimeInMillis;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void consume(Message message) throws InterruptedException {
		System.out.println("Subscriber: " + id + " started consuming: " + message.getMsg());
		Thread.sleep(sleepTimeInMillis);
		System.out.println("Subscriber: " + id + " done consuming: " + message.getMsg());
	}
}
