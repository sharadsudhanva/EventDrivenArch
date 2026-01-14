package com.example.order_service.kafka;

import com.example.order_service.dto.InventoryEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEventConsumer {

    public static final Logger log = LoggerFactory.getLogger(InventoryEventConsumer.class);

    @KafkaListener( topics = "inventory.reserved.v1")
    public void consume(InventoryEvent event){

        log.info("Order service received response from Inventory");
        log.info("Received event {}", event);

    }
}
