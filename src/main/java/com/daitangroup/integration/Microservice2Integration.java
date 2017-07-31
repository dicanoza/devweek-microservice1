package com.daitangroup.integration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Component
public class Microservice2Integration {

	public static final String MICROSERVICE2 = "MICROSERVICE2";

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private EurekaClient discoveryClient;

	public String eurekaClient() {

		// Gets the next server
		InstanceInfo instance = discoveryClient.getNextServerFromEureka(MICROSERVICE2, false);

		ResponseEntity<String> response = new RestTemplate().getForEntity(instance.getHomePageUrl() + "/",
				String.class);

		return response.getBody();

	}

	public String ribbonClient() {
		
		ServiceInstance instance = loadBalancer.choose(MICROSERVICE2);
		URI baseUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
		ResponseEntity<String> response = new RestTemplate().getForEntity(baseUri + "/",
				String.class);

		return response.getBody();

	}

}
