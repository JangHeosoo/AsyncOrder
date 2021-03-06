package ymyoo.infra.messaging;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by 유영모 on 2016-12-06.
 */
public class PollingMessageConsumerIntegrationTest extends KafkaIntegrationTest {

    @Test
    public void testRun() throws InterruptedException {
        // given
        String channelName = "TEST-REPLY";
        Thread r = new Thread(new PollingMessageConsumer(channelName));
        r.start();

        String callback1Key = "12345";
        String callback1Value = "hello";

        PollingMessageConsumer.registerListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                // then
                Assert.assertEquals(message.getBody(), callback1Value);
            }

            @Override
            public String getCorrelationId() {
                return callback1Key;
            }
        });

        String callback2Key = "67890";
        String callback2Value = "bye!";

        PollingMessageConsumer.registerListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                Assert.assertEquals(message.getBody(), callback2Value);
            }

            @Override
            public String getCorrelationId() {
                return callback2Key;
            }
        });

        // when
        sendMessage("TEST-REPLY", new Message(callback1Key, callback1Value));
        sendMessage("TEST-REPLY", new Message(callback2Key, callback2Value));

        // 비동기 처리 대기
        waitCurrentThread(5);
    }

}