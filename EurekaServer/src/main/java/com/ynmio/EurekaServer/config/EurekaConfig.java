package com.ynmio.EurekaServer.config;

import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaConfig {

    @Bean
    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
        EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
        config.setHostname("localhost");
        config.setIpAddress("127.0.0.1");
        config.setPreferIpAddress(false);
        config.setNonSecurePort(8086); // Ensure this matches your application's port
        return config;
    }
}

