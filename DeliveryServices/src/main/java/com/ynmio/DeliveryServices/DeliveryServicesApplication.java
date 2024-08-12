package com.ynmio.DeliveryServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeliveryServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryServicesApplication.class, args);
	}

}
