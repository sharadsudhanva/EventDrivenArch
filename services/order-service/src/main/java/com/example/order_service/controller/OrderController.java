package com.example.order_service.controller;

import com.example.order_service.dto.Order;
import com.example.order_service.dto.OrderEvent;
import com.example.order_service.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/ctr/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer producer;

    @PostMapping("/orders")
    public ResponseEntity<OrderEvent> orderPlaced(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent event = new OrderEvent(
                UUID.randomUUID().toString(),
                order.getOrderId(),
                order.getQty(),
                LocalDateTime.now());

        producer.sendMessage(event);

        return ResponseEntity.ok(event);
    }

}
