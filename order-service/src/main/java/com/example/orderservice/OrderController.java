package com.example.orderservice;


import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody OrderDto orderDto){
        orderDto.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrderDto(orderDto);

        orderProducer.sendMessage(orderEvent);

        return "Order placed successfully ...";
    }
}
