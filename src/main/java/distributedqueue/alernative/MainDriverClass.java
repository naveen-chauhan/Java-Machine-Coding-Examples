package distributedqueue.alernative;

import distributedqueue.alernative.intefaces.Subscriber;
import distributedqueue.alernative.models.Message;
import distributedqueue.alernative.models.Topic;

/**
 * @author naveen.chauhan on 22/07/22
 */
public class MainDriverClass {
	public static void main(String[] args) throws InterruptedException {
		Queue queue = new Queue();

		Topic topic1 = queue.createTopic("t1");
		Topic topic2 = queue.createTopic("t2");
		Subscriber subscriber1 = new Subscriber("sub1", 10000);
		Subscriber subscriber2 = new Subscriber("sub2", 10000);

		queue.subscribe(subscriber1, topic1);
		queue.subscribe(subscriber2, topic1);


		Subscriber subscriber3 = new Subscriber("sub3", 10000);
		queue.subscribe(subscriber3, topic2);

		queue.publish(topic1, new Message("Message 1"));
		queue.publish(topic1, new Message("Message 2"));
		queue.publish(topic2, new Message("Message 3"));

		Thread.sleep(10000);

		queue.publish(topic2, new Message("Message 4"));
		queue.publish(topic1, new Message("Message 5"));

		queue.reset(topic1, subscriber1, 0);

	}
}
