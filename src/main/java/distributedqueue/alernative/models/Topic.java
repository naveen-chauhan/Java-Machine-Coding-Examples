package distributedqueue.alernative.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naveen.chauhan on 22/07/22
 */

@Getter
public class Topic {
	private String topicName;
	private String topicId;
	private List<Message> messages;
	private List<TopicSubscriberDetails> topicSubscriberDetails;


	public Topic(String topicName, String topicId) {
		this.topicName = topicName;
		this.topicId = topicId;
		this.messages = new ArrayList<>();
		this.topicSubscriberDetails = new ArrayList<>();
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

	public void addSubscriber(TopicSubscriberDetails topicSubscriberDetails) {
		this.topicSubscriberDetails.add(topicSubscriberDetails);
	}
}
