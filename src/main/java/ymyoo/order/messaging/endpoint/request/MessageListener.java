package ymyoo.order.messaging.endpoint.request;

/**
 * Created by 유영모 on 2016-12-23.
 */
public interface MessageListener {
    void onMessage(String message);
    String getCorrelationId();
}
