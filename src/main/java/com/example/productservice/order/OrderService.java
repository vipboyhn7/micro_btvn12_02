package com.example.productservice.order;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    public static final String ORDER_EVENTS_TOPIC = "order-events";

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderService(
            OrderRepository orderRepository,
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public Order create(CreateOrderRequest request) {
        Order order = orderRepository.save(new Order(
                request.productId(), request.quantity(), request.customerEmail()));

        kafkaTemplate.send(
                ORDER_EVENTS_TOPIC,
                order.getId().toString(),
                OrderCreatedEvent.from(order));
        return order;
    }
}
