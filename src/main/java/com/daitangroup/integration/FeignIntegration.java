package com.daitangroup.integration;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daitangroup.model.Entity;

@Component
@FeignClient(name = "MICROSERVICE2", fallback = FeingIntegrationFallback.class)
public interface FeignIntegration {

	@RequestMapping(method = GET, value = "/")
	public String feignClient();

	@RequestMapping("/entity")
	public ResponseEntity<Entity> entity();

}
