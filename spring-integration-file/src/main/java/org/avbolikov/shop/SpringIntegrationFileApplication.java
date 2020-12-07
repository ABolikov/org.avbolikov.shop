package org.avbolikov.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@EnableIntegration
@IntegrationComponentScan
@SpringBootApplication
public class SpringIntegrationFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationFileApplication.class, args);
	}

}
