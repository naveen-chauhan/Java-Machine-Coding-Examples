package distributedqueue.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naveen.chauhan on 20/07/22
 */
public class Producer {
	private String producerId;
	private List<String> subscribedTopics;

	public Producer(String producerId) {
		this.producerId = producerId;
		this.subscribedTopics = new ArrayList<>();
	}

	public String getProducerId() {
		return producerId;
	}

	public List<String> getSubscribedTopics() {
		return subscribedTopics;
	}
}
