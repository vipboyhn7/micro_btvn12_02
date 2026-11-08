package com.example.productservice.order;

import java.time.Instant;

public record OrderCreatedEvent(
        Long orderId,
        Long productId,
        Integer quantity,
        String customerEmail,
        Instant createdAt
) {
    public static OrderCreatedEvent from(Order order) {
        return new OrderCreatedEvent(
                order.getId(),
                order.getProductId(),
                order.getQuantity(),
                order.getCustomerEmail(),
                order.getCreatedAt());
    }
}
