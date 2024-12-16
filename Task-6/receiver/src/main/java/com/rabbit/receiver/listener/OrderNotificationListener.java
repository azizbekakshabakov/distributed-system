package com.rabbit.receiver.listener;

import com.rabbit.receiver.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderNotificationListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "almaty_orders_queue", arguments = {
                    @Argument(name = "x-dead-letter-exchange", value = "dlx"),
                    @Argument(name = "x-dead-letter-routing-key", value = "dlx.orders_queue")
            }),
            exchange = @Exchange(value = "${mq.order.topic.exchange}",
                    type = ExchangeTypes.TOPIC),
            key = "order.#"
    ))
    public void receiveOrders(OrderDTO orderDTO) {
//        try {
//            log.info("Received order : {}", orderDTO);
//            processOrder(orderDTO);
//        }catch (Exception e){
//            log.error("Error processing order {}", orderDTO);
//            throw e;
//        }
        log.info("receive orders message : {}", orderDTO);
    }

//    private void processOrder(OrderDTO orderDTO) {
//        log.error("Error has happened  {}", orderDTO);
//        throw new RuntimeException("Failed to process order");
//    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "customer_updates_queue"),
            exchange = @Exchange(value = "${mq.order.fanout.exchange}",
                    type = ExchangeTypes.FANOUT),
            key = ""
    ))
    public void receiveCustomerOrderStatusUpdate(OrderDTO orderDTO) {
        log.info("Customer notification - ORDER : {}", orderDTO);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "courier_updates_queue"),
            exchange = @Exchange(value = "${mq.order.fanout.exchange}",
                    type = ExchangeTypes.FANOUT),
            key = ""
    ))
    public void receiveCourierOrderStatusUpdate(OrderDTO orderDTO) {
        log.info("Courier notification - ORDER : {}", orderDTO);
    }
}
