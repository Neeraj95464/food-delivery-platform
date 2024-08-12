package com.ynmio.OrderServices.service;

import com.ynmio.OrderServices.model.CustomerOrder;
import com.ynmio.OrderServices.model.PaymentRequest;
import com.ynmio.OrderServices.model.PaymentResponse;
import com.ynmio.OrderServices.model.RestaurantData;
import com.ynmio.OrderServices.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private RestaurantClient restaurantClient;
    @Autowired
    private CustomerOrderRepository orderRepository;
    @Autowired
    private RestTemplate template;

    public CustomerOrder createOrder(CustomerOrder orderRequest) {

        RestaurantData restaurantData = restaurantClient.getRestaurantData(orderRequest.getRestaurantId());

        PaymentRequest request = new PaymentRequest();
        request.setAmount(orderRequest.getAmount());
        PaymentResponse response = template.postForObject("http://PaymentServices/payments/process",
                request, PaymentResponse.class);

        if (response.getStatus().equals("SUCCESS")) {
            orderRequest.setRestaurantData(restaurantData);
            orderRequest.setAmount(orderRequest.getAmount());

            // Step 4: Notify Delivery Service
//            notifyDeliveryService(order);
            orderRequest.setTransactionId(response.getTransactionId());
            return orderRepository.save(orderRequest);
        } else {
            throw new RuntimeException("Payment processing failed.");
        }
    }

}

