package com.daitangroup.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.daitangroup.model.Entity;

@Component
public class FeingIntegrationFallback implements FeignIntegration {

	@Override
	public String feignClient() {
		return "Some fallback message";
	}

	@Override
	public ResponseEntity<Entity> entity() {
		return  ResponseEntity.ok(new Entity("pedro"));
	}

}
