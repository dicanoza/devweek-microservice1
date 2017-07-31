package com.daitangroup.integration;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daitangroup.model.Entity;

@Component
@FeignClient(name = "MICROSERVICE2")
public interface EntityFeignIntegration {
	
	@RequestMapping("/entity")
	public ResponseEntity<Entity> entity();

}
