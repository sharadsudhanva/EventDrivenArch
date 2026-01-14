package com.example.inventory_service.kafka;

import com.example.inventory_service.dto.OrderEvent;
import com.example.inventory_service.dto.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    private final InventoryEventProducer producer;

    @KafkaListener(
            topics = "order.created.v1",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event){

        logger.info(String.format("Order Received :: %s", event.toString()));

        boolean probability = Math.random() > 0.5;
        OrderStatus status = probability ? OrderStatus.FAILURE : OrderStatus.SUCCESS;

        logger.info("Publishing inventory update");
        producer.publishInventoryResult(
                event.getEventId(),
                event.getOrderId(),
                status
        );
    }
}
