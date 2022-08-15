package distributedqueue.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naveen.chauhan on 20/07/22
 */
public class Consumer {
	private String consumerId;
	private List<String> subscribedTopics;
	//Todo: Since Topics are list we will need LastCommitted Offset as a list for each Subscribed Topics
	private Integer lastCommittedOffset;


	public Consumer(String consumerId) {
		this.consumerId = consumerId;
		this.subscribedTopics = new ArrayList<>();
		lastCommittedOffset = 0;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public List<String> getSubscribedTopics() {
		return subscribedTopics;
	}

	public Integer getLastCommittedOffset() {
		return lastCommittedOffset;
	}

	public String consume(Topic topic) {

		if (topic.getTopicMessages().size() <= lastCommittedOffset) {
			System.out.println("No New Message to poll");
		}

		String message = topic.getTopicMessages().get(lastCommittedOffset);
		this.lastCommittedOffset += 1;
		return message;
	}
}
