package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//configuration
@Configuration
@EnableSwagger2
//enable swagger
public class SwaggerConfig {

	//Bean - docket
	// swagger 2
		// all the paths
	// all the APIs
	
	public static final ApiInfo DEFAULT = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos",
	          "Contact Email", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT);
	}
	
}
