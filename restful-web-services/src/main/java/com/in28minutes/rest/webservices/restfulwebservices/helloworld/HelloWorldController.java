package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//controller hhtp request

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
//	GET
//	URI - /hello-world
//	Method - Hello World
	@GetMapping(path="hello-world")
	public String helloWorld() {
		
		return "Hello  World";
	}
	
	@GetMapping(path="hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		
		return new HelloWorldBean("Hello  World Bean");
	}

	@GetMapping(path="hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		
		return new HelloWorldBean("Hello  World Bean "+name);
	}
	
	@GetMapping(path="/hello-world-internationlized")
	public String helloWorldinternationlized() {
		
		System.out.println("am here at helloWorldinternationlized "+LocaleContextHolder.getLocale());
//		return messageSource.getMessage("good.morning.message",null, locale);
		
		System.out.println(messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale()));
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
}
