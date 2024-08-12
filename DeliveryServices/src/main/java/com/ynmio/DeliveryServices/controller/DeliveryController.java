package com.ynmio.DeliveryServices.controller;

import com.ynmio.DeliveryServices.model.Delivery;
import com.ynmio.DeliveryServices.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @PostMapping("/schedule")
    public Delivery scheduleDelivery(@RequestBody Delivery deliveryRequest) {
        deliveryRequest.setDeliveryId(UUID.randomUUID().toString());
        deliveryRequest.setStatus("SCHEDULED");
        return deliveryRepository.save(deliveryRequest);
    }
    @GetMapping
    public List<Delivery> getDeliveries(){
        return deliveryRepository.findAll();
    }
}

