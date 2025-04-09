package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.controller.dto.request.order.CreateOrderRequest;
import com.uade.marketplace.controller.dto.request.order.PayOrderRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @PostMapping()
    void createOrder(@RequestBody CreateOrderRequest request) {

    }

    @PutMapping("/{id}/pay")
    void payOrder(@PathVariable(name = "id") Long id, @RequestBody PayOrderRequest request) {

    }

    @PutMapping("/{id}/cancel")
    void cancelOrder(@PathVariable(name = "id") Long id) {

    }

    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable(name = "id") Long id) {

    }
}
