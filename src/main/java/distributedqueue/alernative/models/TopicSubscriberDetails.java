package distributedqueue.alernative.models;

import distributedqueue.alernative.intefaces.ISubscriber;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author naveen.chauhan on 22/07/22
 */
@Getter
public class TopicSubscriberDetails {
	private AtomicInteger offset;
	private ISubscriber subscriber;

	public TopicSubscriberDetails(ISubscriber subscriber) {
		this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
	}
}
