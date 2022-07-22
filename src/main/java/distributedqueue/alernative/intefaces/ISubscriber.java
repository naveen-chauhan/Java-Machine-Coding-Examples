package distributedqueue.alernative.intefaces;

import distributedqueue.alernative.models.Message;

/**
 * @author naveen.chauhan on 22/07/22
 */
public interface ISubscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
