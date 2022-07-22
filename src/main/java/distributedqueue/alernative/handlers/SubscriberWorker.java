package distributedqueue.alernative.handlers;

import distributedqueue.alernative.models.Message;
import distributedqueue.alernative.models.Topic;
import distributedqueue.alernative.models.TopicSubscriberDetails;
import lombok.SneakyThrows;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class SubscriberWorker implements Runnable {

	private Topic topic;
	private TopicSubscriberDetails topicSubscriberDetails;

	public SubscriberWorker(Topic topic, TopicSubscriberDetails topicSubscriberDetails) {
		this.topic = topic;
		this.topicSubscriberDetails = topicSubscriberDetails;
	}

	@SneakyThrows
	@Override
	public void run() {
		synchronized (topicSubscriberDetails) {
			do {
				int curOffset = topicSubscriberDetails.getOffset().get();
				while (topicSubscriberDetails.getOffset().get() >= topic.getMessages().size()) {
					topicSubscriberDetails.wait();
				}

				Message message = topic.getMessages().get(topicSubscriberDetails.getOffset().get());
				topicSubscriberDetails.getSubscriber().consume(message);
				topicSubscriberDetails.getOffset().compareAndSet(curOffset, curOffset + 1);
			} while (true);
		}
	}

	public synchronized void wakeUpIfNeeded() {
		topicSubscriberDetails.notify();
	}
}
