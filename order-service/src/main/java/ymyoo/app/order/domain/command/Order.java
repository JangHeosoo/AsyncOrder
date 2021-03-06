package ymyoo.app.order.domain.command;


import ymyoo.app.order.domain.command.workflow.OrderProcessManager;
import ymyoo.app.order.domain.command.workflow.OrderProcessManagerFactory;

/**
 * Created by 유영모 on 2016-10-07.
 */
public class Order {
    private String orderId;
    private Orderer orderer;
    private OrderItem orderItem;
    private OrderPayment orderPayment;
    private OrderProcessManager processManager;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public Order(Orderer orderer, OrderItem orderItem, OrderPayment orderPayment) {
        this.orderer = orderer;
        this.orderItem = orderItem;
        this.orderPayment = orderPayment;
        this.orderId = OrderIdGenerator.generate();
        this.processManager = OrderProcessManagerFactory.create(this);
    }

    public Order(Orderer orderer, OrderItem orderItem, OrderPayment orderPayment,
                 OrderProcessManager processManager) {
        this.orderer = orderer;
        this.orderItem = orderItem;
        this.orderPayment = orderPayment;
        this.orderId = OrderIdGenerator.generate();
        this.processManager = processManager;
    }

    public Orderer getOrderer() {
        return orderer;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public OrderPayment getOrderPayment() {
        return orderPayment;
    }

    public String placeOrder() {
        processManager.runWorkflow(this);
        return orderId;
    }

}
