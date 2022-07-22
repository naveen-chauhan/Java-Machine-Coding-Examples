package distributedqueue.alernative.handlers;

import distributedqueue.alernative.models.Topic;
import distributedqueue.alernative.models.TopicSubscriberDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class TopicHandler {
	private Topic topic;
	private Map<String, SubscriberWorker> subscriberWorkers;

	public TopicHandler(Topic topic) {
		this.topic = topic;
		this.subscriberWorkers = new HashMap<>();
	}

	public void publish() {
		for (TopicSubscriberDetails topicSubscriberDetails : topic.getTopicSubscriberDetails()) {
			startWorkerSubscriber(topicSubscriberDetails);
		}
	}

	public void startWorkerSubscriber(TopicSubscriberDetails topicSubscriberDetails) {
		final String id = topicSubscriberDetails.getSubscriber().getId();

		if (!subscriberWorkers.containsKey(id)) {
			SubscriberWorker subscriberWorker = new SubscriberWorker(topic, topicSubscriberDetails);
			subscriberWorkers.put(id, subscriberWorker);
			new Thread(subscriberWorker).start();
		} else {
			subscriberWorkers.get(id).wakeUpIfNeeded();
		}


	}
}
