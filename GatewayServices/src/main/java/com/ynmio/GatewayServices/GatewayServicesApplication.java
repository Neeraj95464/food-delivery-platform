package com.ynmio.GatewayServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServicesApplication.class, args);
	}

}
