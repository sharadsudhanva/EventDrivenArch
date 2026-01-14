package com.example.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    public String eventId;
    public String orderId;
    public OrderStatus status;
    public LocalDateTime createdAt;
}
