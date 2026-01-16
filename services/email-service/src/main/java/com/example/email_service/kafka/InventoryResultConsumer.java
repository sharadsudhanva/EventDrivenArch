package com.example.email_service.kafka;


import com.example.email_service.dto.InventoryEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryResultConsumer {

    private final Logger logger = LoggerFactory.getLogger(InventoryResultConsumer.class);

    @KafkaListener(topics = "inventory.reserved.v1")
    public void consumer(InventoryEvent event){
        logger.info("Message received from Inventory Service");
        logger.info("Message: {}", event);
    }
}
