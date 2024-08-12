package com.ynmio.OrderServices.controller;
import com.ynmio.OrderServices.model.CustomerOrder;
import com.ynmio.OrderServices.repository.CustomerOrderRepository;
import com.ynmio.OrderServices.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class CustomerOrderController {

    private static Logger log= LoggerFactory.getLogger(CustomerOrderController.class);

    @Autowired
    private CustomerOrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @PostMapping
    @CircuitBreaker(name = "orderService",fallbackMethod = "paymentServiceNotResponding")
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        log.info("this one request we have received for order creation\n "+order.toString());
        CustomerOrder createdOrder=orderService.createOrder(order);
        log.info("this one we are returning after order creation\n "+createdOrder);
        return createdOrder;
    }
    public static CustomerOrder paymentServiceNotResponding(Exception e) {
        log.info("Error creating order: Payment service not responding. Please try again later. "+ e);
        return new CustomerOrder(null, "Payment Service Unavailable", 0.0, null, null, null);
    }
    @GetMapping
    public List<CustomerOrder> getOrders(){
        return orderRepository.findAll();
    }
}

