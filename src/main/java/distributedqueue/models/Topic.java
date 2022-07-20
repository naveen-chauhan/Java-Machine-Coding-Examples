package distributedqueue.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author naveen.chauhan on 20/07/22
 */
public class Topic {
    private LinkedList<String> topic;
    private String topicName;
    private List<String> producers;
    private List<String> subscribers;

    public Topic(String topicName) {
        this.topic = new LinkedList<>();
        this.producers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public LinkedList<String> getTopic() {
        return topic;
    }

    public List<String> getProducers() {
        return producers;
    }

    public List<String> getSubscribers() {
        return subscribers;
    }

    public void produce(Producer producer, String message) {
        this.topic.add(message);
        if (!producers.contains(producer.getProducerId())) {
            producers.add(producer.getProducerId());
        }
    }

    //Add new subscriber
    //Add new Producer
    //Commit the logs once it got produced


}
