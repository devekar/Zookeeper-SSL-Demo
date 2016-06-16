
package com.vaibhav.zookeeper.sslDemo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
//@EnableDiscoveryClient
@EnableAutoConfiguration
public class ServiceApplication extends SpringBootServletInitializer {
    
    public static void main( String[] args ) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
