package ymyoo.infra.messaging.queue.blockingqueue;

import ymyoo.infra.messaging.queue.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 유영모 on 2016-11-15.
 */
public class RequestBlockingQueue {

    private static RequestBlockingQueue self = null;

    private static BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);

    private RequestBlockingQueue() {
    }

    public static BlockingQueue<Message> getBlockingQueue() {
        if(self == null) {
            self =  new RequestBlockingQueue();
            return self.queue;
        } else {
            return self.queue;
        }
    }

}
