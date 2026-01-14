package com.example.inventory_service.kafka;

import com.example.inventory_service.dto.InventoryEvent;
import com.example.inventory_service.dto.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class InventoryEventProducer {

    private static final Logger log = LoggerFactory.getLogger(InventoryEventProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishInventoryResult(String eventId, String orderId, OrderStatus status){

        InventoryEvent inventoryEvent = new InventoryEvent(eventId, orderId, status, LocalDateTime.now());

        log.info("Publishing the Inventory Updated event: {}", inventoryEvent);

        kafkaTemplate.send("inventory.reserved.v1", eventId, inventoryEvent);
    }
}
