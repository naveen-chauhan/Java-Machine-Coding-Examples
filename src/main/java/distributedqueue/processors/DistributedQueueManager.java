package distributedqueue.processors;

import distributedqueue.models.Consumer;
import distributedqueue.models.DistributedQueue;
import distributedqueue.models.Producer;
import distributedqueue.models.Topic;

/**
 * @author naveen.chauhan on 20/07/22
 */
public class DistributedQueueManager {

	private final DistributedQueue distributedQueue;

	public DistributedQueueManager() {
		this.distributedQueue = new DistributedQueue();
	}

	public Topic createTopic(String topicName) {
		Topic topic = new Topic(topicName);
		distributedQueue.getTopicMap().put(topicName, topic);

		System.out.println("Successfully Created the Topic: " + topicName);
		return topic;
	}

	public Producer createProducer(String producerName) {
		Producer producer = new Producer(producerName);
		distributedQueue.getProducerMap().put(producerName, producer);

		System.out.println("Successfully Created the Producer: " + producerName);
		return producer;
	}

	public Consumer createConsumer(String consumerName) {
		Consumer consumer = new Consumer(consumerName);
		distributedQueue.getConsumerMap().put(consumerName, consumer);

		System.out.println("Successfully Created the Consumer: " + consumerName);
		return consumer;
	}

	public void subscribeToTopic(String consumer, String topicName) {
		if (!distributedQueue.getConsumerMap().containsKey(consumer)) {
			System.out.println("Error: ConsumerId Not Found, Please consumer first !!");
		}

		if (!distributedQueue.getTopicMap().containsKey(topicName)) {
			System.out.println("Error: Topic Not Found, Pls create the topic");
		}

		Topic topic = distributedQueue.getTopicMap().get(topicName);
		Consumer consumer1 = distributedQueue.getConsumerMap().get(consumer);

		consumer1.getSubscribedTopics().add(topic.getTopicName());
		topic.getSubscribers().add(consumer);
		System.out.println(consumer + " successfully subscribed to the Topic: " + topicName);
	}

	public void sendMessage(String producerName, String topicName, String message) {
		if (!distributedQueue.getProducerMap().containsKey(producerName)) {
			System.out.println("Error: Producer Not Found, Please create !!");
		}

		if (!distributedQueue.getTopicMap().containsKey(topicName)) {
			System.out.println("Error: Topic Not Found, Pls create the topic");
		}

		Producer producer = distributedQueue.getProducerMap().get(producerName);

		Topic topic = distributedQueue.getTopicMap().get(topicName);

		topic.produce(producer, message);
		System.out.println("Message Successfully Produced !!");
	}

	public String poll(String consumerName, String topicName) {
		if (!distributedQueue.getConsumerMap().containsKey(consumerName)) {
			System.out.println("Error: ConsumerId Not Found, Please consumer first !!");
		}

		if (!distributedQueue.getTopicMap().containsKey(topicName)) {
			System.out.println("Error: Topic Not Found, Pls create the topic");
		}

		Consumer consumer = distributedQueue.getConsumerMap().get(consumerName);
		Topic topic = distributedQueue.getTopicMap().get(topicName);


		return consumer.consume(topic);
	}
}
