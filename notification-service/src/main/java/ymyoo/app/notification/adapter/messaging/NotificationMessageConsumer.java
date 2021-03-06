package ymyoo.app.notification.adapter.messaging;

import ymyoo.infra.messaging.EventDrivenMessageConsumer;
import ymyoo.infra.messaging.Message;

import java.util.List;

/**
 * Created by 유영모 on 2017-01-25.
 */
public class NotificationMessageConsumer implements Runnable {
    private String channel;

    public NotificationMessageConsumer(String channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        EventDrivenMessageConsumer eventDrivenMessageConsumer = new EventDrivenMessageConsumer(channel);
        try {
            while (!Thread.currentThread().isInterrupted()) {
                List<Message> messages = eventDrivenMessageConsumer.poll();
                for(Message message : messages) {
                    NotificationChannelAdapter adapter = new NotificationChannelAdapter();
                    try {
                        adapter.notifyToPurchaser(message);
                    } catch (Exception e) {
//                        // 오류 시 MessageStore 채널로 내용 전송
//                        PurchaseNotification purchaseNotification = null;
//                        try {
//                            purchaseNotification = NotificationChannelAdapter.NotificationMessageTranslator.translate((Map)message.getBody());
//                        } catch (InvocationTargetException e1) {
//                            e1.printStackTrace();
//                        } catch (IllegalAccessException e1) {
//                            e1.printStackTrace();
//                        }
//
//                        String activity = e.getMessage();
//
//                        IncompleteBusinessActivity incompleteBusinessActivity =
//                                new IncompleteBusinessActivity(purchaseNotification.getOrderId(), activity);
//
//                        MessageStoreChannelAdapter messageStoreChannelAdapter = new MessageStoreChannelAdapter();
//                        messageStoreChannelAdapter.storeIncompleteActivity(incompleteBusinessActivity);
                    }
                }
            }
        } finally {
            eventDrivenMessageConsumer.close();
        }

    }
}
