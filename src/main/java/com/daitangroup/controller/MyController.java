package com.daitangroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daitangroup.integration.FeignIntegration;
import com.daitangroup.integration.Microservice2Integration;

@RestController
@RefreshScope
public class MyController {

	@Value("${my.message}")
	private String message;

	@Autowired
	private Microservice2Integration integration;

	@Autowired
	private FeignIntegration feignIntegration;
	
	@RequestMapping("/")
	public String message() {
		StringBuilder sb = new StringBuilder();

		sb.append("message: " + message);
		sb.append("\n");
		sb.append("eureka-client: " + integration.eurekaClient());
		sb.append("\n");
		sb.append("ribbon-client: " + integration.ribbonClient());
		sb.append("\n");
		sb.append("feign-client: " + feignIntegration.feignClient());

		return sb.toString();
	}

	@RequestMapping("/entity")
	public String myEnity() {
		return feignIntegration.entity().getBody().getName();
	}
}
