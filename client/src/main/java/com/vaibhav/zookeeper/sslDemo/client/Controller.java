package com.vaibhav.zookeeper.sslDemo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vaibhav Devekar
 *
 */
@RestController
public class Controller {

    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    Environment environment;
    
    @RequestMapping("/get")
    public String getRequest(@RequestParam String msg) {
        System.out.println("Sending back " + msg);
        return "ACK " + msg;        
    }
    
    @RequestMapping("/send")
    public String sendRequest(@RequestParam String msg) {
        String baseUrl = environment.getRequiredProperty("peerUrl");
        System.out.println("Sending " + msg);
        String response = restTemplate.getForObject(baseUrl + "/get?msg=" + msg, String.class);
        System.out.println("Received " + response);
        return response;        
    }    
}
