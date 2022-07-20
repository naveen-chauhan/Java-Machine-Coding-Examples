package distributedqueue.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author naveen.chauhan on 20/07/22
 */
public class Consumer {
   private String consumerId;
   private List<String> subscribedTopics;
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

      if (topic.getTopic().size() <= lastCommittedOffset) {
         System.out.println("No New Message to poll");
      }

      String message = topic.getTopic().get(lastCommittedOffset);
      this.lastCommittedOffset += 1;
      return message;
   }
}
