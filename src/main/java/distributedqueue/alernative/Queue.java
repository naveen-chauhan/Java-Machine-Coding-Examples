package distributedqueue.alernative;

import distributedqueue.alernative.handlers.TopicHandler;
import distributedqueue.alernative.intefaces.ISubscriber;
import distributedqueue.alernative.models.Message;
import distributedqueue.alernative.models.Topic;
import distributedqueue.alernative.models.TopicSubscriberDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class Queue {
	private Map<String, TopicHandler> topicProcessors;

	public Queue() {
		this.topicProcessors = new HashMap<>();
	}

	public Topic createTopic(String topicName) {
		Topic topic = new Topic(topicName, UUID.randomUUID().toString());
		TopicHandler topicHandler = new TopicHandler(topic);
		topicProcessors.put(topic.getTopicId(), topicHandler);
		System.out.println("Created topic: " + topic.getTopicName());
		return topic;
	}

	public void publish(Topic topic, Message message) {
		topic.addMessage(message);
		System.out.println(message.getMsg() + " published to topic: " + topic.getTopicName());
		new Thread(() -> topicProcessors.get(topic.getTopicId()).publish()).start();
	}

	public void subscribe(ISubscriber subscriber, Topic topic) {
		topic.addSubscriber(new TopicSubscriberDetails(subscriber));
		System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());

	}

	public void reset(Topic topic, ISubscriber subscriber, Integer newOffset) {
		for (TopicSubscriberDetails topicSubscriberDetails : topic.getTopicSubscriberDetails()) {
			if (Objects.equals(topicSubscriberDetails.getSubscriber().getId(), subscriber.getId())) {
				topicSubscriberDetails.getOffset().set(newOffset);
				System.out.println(topicSubscriberDetails.getSubscriber().getId() + " offset reset to: " + newOffset);
				new Thread(() -> topicProcessors.get(topic).startWorkerSubscriber(topicSubscriberDetails)).start();
				break;
			}
		}
	}
}
