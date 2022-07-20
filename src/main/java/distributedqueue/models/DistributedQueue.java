package distributedqueue.models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 20/07/22
 */
public class DistributedQueue {
     private Map<String, Topic> topicMap;
     private Map<String, Producer> producerMap;
     private Map<String, Consumer> consumerMap;

     public DistributedQueue() {
          this.topicMap = new HashMap<>();
          this.producerMap = new HashMap<>();
          this.consumerMap = new HashMap<>();
     }

     public Map<String, Topic> getTopicMap() {
          return topicMap;
     }

     public Map<String, Producer> getProducerMap() {
          return producerMap;
     }

     public Map<String, Consumer> getConsumerMap() {
          return consumerMap;
     }
}
