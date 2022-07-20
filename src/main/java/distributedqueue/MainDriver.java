package distributedqueue;

import distributedqueue.processors.DistributedQueueManager;

/**
 * @author naveen.chauhan on 20/07/22
 */
public class MainDriver {
	public static void main(String[] args) {

		DistributedQueueManager distributedQueueManager = new DistributedQueueManager();

		//Create two topics
		distributedQueueManager.createTopic("topic1");
		distributedQueueManager.createTopic("topic2");

		//Create two producers
		distributedQueueManager.createProducer("producer1");
		distributedQueueManager.createProducer("producer2");

		//Create five consumers
		distributedQueueManager.createConsumer("consumer1");
		distributedQueueManager.createConsumer("consumer2");
		distributedQueueManager.createConsumer("consumer3");
		distributedQueueManager.createConsumer("consumer4");
		distributedQueueManager.createConsumer("consumer5");

		//Subscribing all consumers to a topic1
		distributedQueueManager.subscribeToTopic("consumer1",  "topic1");
		distributedQueueManager.subscribeToTopic("consumer2",  "topic1");
		distributedQueueManager.subscribeToTopic("consumer3",  "topic1");
		distributedQueueManager.subscribeToTopic("consumer4",  "topic1");
		distributedQueueManager.subscribeToTopic("consumer5",  "topic1");

		//subscribing the consumer1, consumer3, consumer4 to topic2
		distributedQueueManager.subscribeToTopic("consumer1",  "topic2");
		distributedQueueManager.subscribeToTopic("consumer3",  "topic2");
		distributedQueueManager.subscribeToTopic("consumer4",  "topic2");

		//publishing the messages
		distributedQueueManager.sendMessage("producer1", "topic1", "Message 1");
		distributedQueueManager.sendMessage("producer1", "topic1", "Message 2");
		distributedQueueManager.sendMessage("producer2", "topic1", "Message 3");
		distributedQueueManager.sendMessage("producer1", "topic2", "Message 4");
		distributedQueueManager.sendMessage("producer2", "topic2", "Message 5");


		//Now Poll the message
		String message = distributedQueueManager.poll("consumer1", "topic1");
		System.out.println("Message Received: " + message);

	}
}
