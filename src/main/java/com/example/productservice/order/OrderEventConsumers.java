package com.example.productservice.order;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumers {
    @KafkaListener(
            topics = OrderService.ORDER_EVENTS_TOPIC,
            groupId = "email-service",
            containerFactory = "kafkaListenerContainerFactory")
    public void sendConfirmationEmail(OrderCreatedEvent event) {
        System.out.printf(
                "Email-Service: sending order confirmation for order %d to %s%n",
                event.orderId(), event.customerEmail());
    }

    @KafkaListener(
            topics = OrderService.ORDER_EVENTS_TOPIC,
            groupId = "inventory-service",
            containerFactory = "kafkaListenerContainerFactory")
    public void reserveInventory(OrderCreatedEvent event) {
        System.out.printf(
                "Inventory-Service: reserving %d unit(s) of product %d for order %d%n",
                event.quantity(), event.productId(), event.orderId());
    }
}
