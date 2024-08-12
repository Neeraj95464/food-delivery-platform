package com.ynmio.CustomerServices.service;

import com.ynmio.CustomerServices.exception.ResourceNotFoundException;
import com.ynmio.CustomerServices.model.CustomerOrder;
import com.ynmio.CustomerServices.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public CustomerOrder getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    public CustomerOrder createOrder(CustomerOrder order) {
        return orderRepository.save(order);
    }

    public CustomerOrder updateOrder(Long id, CustomerOrder orderDetails) {
        CustomerOrder order = getOrderById(id);
        order.setOrderDate(orderDetails.getOrderDate());
        order.setStatus(orderDetails.getStatus());
        order.setTotalAmount(orderDetails.getTotalAmount());

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        CustomerOrder order = getOrderById(id);
        orderRepository.delete(order);
    }
}

